using System;
using System.Collections.Generic;
using System.Data;
using Microsoft.Data.SqlClient;

public class PayrollDBService
{
    private static PayrollDBService _instance = null;
    private static readonly object _lock = new object();
    
    // Connection string pointing toward target SQL Server setup
    private readonly string connectionString = "Data Source=(localdb)\\MSSQLLocalDB;Initial Catalog=payroll_service;Integrated Security=True;";

    private PayrollDBService() { }

    public static PayrollDBService GetInstance()
    {
        lock (_lock)
        {
            if (_instance == null)
            {
                _instance = new PayrollDBService();
            }
            return _instance;
        }
    }

    // UC 2: Retrieve Complete Datasets Mapping Complex Schemas
    public List<EmployeePayrollData> GetAllEmployees()
    {
        List<EmployeePayrollData> employeeList = new List<EmployeePayrollData>();
        
        string query = @"SELECT e.EmployeeID, e.EmployeeName, e.Gender, e.Phone, e.Address, e.StartDate,
                                p.BasicPay, p.Deductions, p.TaxablePay, p.IncomeTax, p.NetPay,
                                d.DepartmentName
                         FROM Company_Employee e
                         LEFT JOIN Payroll p ON e.EmployeeID = p.EmployeeID
                         LEFT JOIN Employee_Department ed ON e.EmployeeID = ed.EmployeeID
                         LEFT JOIN Department d ON ed.DepartmentID = d.DepartmentID;";

        using (SqlConnection connection = new SqlConnection(connectionString))
        {
            SqlCommand command = new SqlCommand(query, connection);
            connection.Open();
            using (SqlDataReader reader = command.ExecuteReader())
            {
                while (reader.Read())
                {
                    int empId = reader.GetInt32(0);
                    EmployeePayrollData existingEmp = employeeList.Find(x => x.EmployeeID == empId);

                    if (existingEmp == null)
                    {
                        existingEmp = new EmployeePayrollData
                        {
                            EmployeeID = empId,
                            EmployeeName = reader.GetString(1),
                            Gender = Convert.ToChar(reader.GetString(2)),
                            Phone = reader.IsDBNull(3) ? null : reader.GetString(3),
                            Address = reader.IsDBNull(4) ? null : reader.GetString(4),
                            StartDate = reader.GetDateTime(5),
                            BasicPay = reader.IsDBNull(6) ? 0 : reader.GetDecimal(6),
                            Deductions = reader.IsDBNull(7) ? 0 : reader.GetDecimal(7),
                            TaxablePay = reader.IsDBNull(8) ? 0 : reader.GetDecimal(8),
                            IncomeTax = reader.IsDBNull(9) ? 0 : reader.GetDecimal(9),
                            NetPay = reader.IsDBNull(10) ? 0 : reader.GetDecimal(10)
                        };
                        employeeList.Add(existingEmp);
                    }

                    if (!reader.IsDBNull(11))
                    {
                        existingEmp.Departments.Add(reader.GetString(11));
                    }
                }
            }
        }
        return employeeList;
    }

    // UC 3 & UC 4: Parameterized Execution via Cached Prepared Statements 
    public bool UpdateEmployeeSalary(string name, decimal newBasicPay)
    {
        // Re-calculate basic metrics uniformly inline
        decimal deductions = newBasicPay * 0.05m;
        decimal taxablePay = newBasicPay - deductions;
        decimal incomeTax = taxablePay * 0.10m;
        decimal netPay = taxablePay - incomeTax;

        string updateQuery = @"UPDATE p 
                               SET p.BasicPay = @BasicPay, p.Deductions = @Deductions, 
                                   p.TaxablePay = @TaxablePay, p.IncomeTax = @IncomeTax, p.NetPay = @NetPay
                               FROM Payroll p
                               INNER JOIN Company_Employee e ON p.EmployeeID = e.EmployeeID
                               WHERE e.EmployeeName = @Name;";

        using (SqlConnection connection = new SqlConnection(connectionString))
        {
            SqlCommand command = new SqlCommand(updateQuery, connection);
            command.Parameters.Add("@BasicPay", SqlDbType.Decimal).Value = newBasicPay;
            command.Parameters.Add("@Deductions", SqlDbType.Decimal).Value = deductions;
            command.Parameters.Add("@TaxablePay", SqlDbType.Decimal).Value = taxablePay;
            command.Parameters.Add("@IncomeTax", SqlDbType.Decimal).Value = incomeTax;
            command.Parameters.Add("@NetPay", SqlDbType.Decimal).Value = netPay;
            command.Parameters.Add("@Name", SqlDbType.VarChar).Value = name;

            connection.Open();
            command.Prepare(); // Cache executed execution plan path explicitly inside SQL instance engine context
            int rowsAffected = command.ExecuteNonQuery();
            return rowsAffected > 0;
        }
    }

    // UC 7 & UC 8: High integrity Multi-Table Transaction Injection Strategy 
    public bool AddEmployeeToPayrollSystem(EmployeePayrollData employee)
    {
        using (SqlConnection connection = new SqlConnection(connectionString))
        {
            connection.Open();
            SqlTransaction transaction = connection.BeginTransaction();

            try
            {
                // Insert Base Profile Metadata details
                string empQuery = @"INSERT INTO Company_Employee (EmployeeName, Gender, StartDate, Address, Phone) 
                                    OUTPUT INSERTED.EmployeeID 
                                    VALUES (@Name, @Gender, @StartDate, @Address, @Phone);";
                
                SqlCommand empCommand = new SqlCommand(empQuery, connection, transaction);
                empCommand.Parameters.AddWithValue("@Name", employee.EmployeeName);
                empCommand.Parameters.AddWithValue("@Gender", employee.Gender);
                empCommand.Parameters.AddWithValue("@StartDate", employee.StartDate);
                empCommand.Parameters.AddWithValue("@Address", employee.Address ?? (object)DBNull.Value);
                empCommand.Parameters.AddWithValue("@Phone", employee.Phone ?? (object)DBNull.Value);

                int newEmpId = (int)empCommand.ExecuteScalar();

                // Compute Payroll values
                decimal deductions = employee.BasicPay * 0.05m;
                decimal taxablePay = employee.BasicPay - deductions;
                decimal incomeTax = taxablePay * 0.10m;
                decimal netPay = taxablePay - incomeTax;

                // Insert into Payroll Table
                string payrollQuery = @"INSERT INTO Payroll (EmployeeID, BasicPay, Deductions, TaxablePay, IncomeTax, NetPay)
                                        VALUES (@EmpID, @BasicPay, @Deductions, @TaxablePay, @IncomeTax, @NetPay);";
                
                SqlCommand payrollCommand = new SqlCommand(payrollQuery, connection, transaction);
                payrollCommand.Parameters.AddWithValue("@EmpID", newEmpId);
                payrollCommand.Parameters.AddWithValue("@BasicPay", employee.BasicPay);
                payrollCommand.Parameters.AddWithValue("@Deductions", deductions);
                payrollCommand.Parameters.AddWithValue("@TaxablePay", taxablePay);
                payrollCommand.Parameters.AddWithValue("@IncomeTax", incomeTax);
                payrollCommand.Parameters.AddWithValue("@NetPay", netPay);
                payrollCommand.ExecuteNonQuery();

                // Multi-Department structural associations
                foreach (var deptName in employee.Departments)
                {
                    // Retrieve or verify structural validation target mapping identifier
                    string getDeptIdQuery = "SELECT DepartmentID FROM Department WHERE DepartmentName = @DeptName;";
                    SqlCommand deptIdCmd = new SqlCommand(getDeptIdQuery, connection, transaction);
                    deptIdCmd.Parameters.AddWithValue("@DeptName", deptName);
                    object deptIdObj = deptIdCmd.ExecuteScalar();

                    int deptId;
                    if (deptIdObj == null)
                    {
                        string insertDeptQuery = "INSERT INTO Department (DepartmentName) OUTPUT INSERTED.DepartmentID VALUES (@DeptName);";
                        SqlCommand insDeptCmd = new SqlCommand(insertDeptQuery, connection, transaction);
                        insDeptCmd.Parameters.AddWithValue("@DeptName", deptName);
                        deptId = (int)insDeptCmd.ExecuteScalar();
                    }
                    else
                    {
                        deptId = (int)deptIdObj;
                    }

                    // Insert connection record into Junction Table
                    string junctionQuery = "INSERT INTO Employee_Department (EmployeeID, DepartmentID) VALUES (@EmpID, @DeptID);";
                    SqlCommand junctionCmd = new SqlCommand(junctionQuery, connection, transaction);
                    junctionCmd.Parameters.AddWithValue("@EmpID", newEmpId);
                    junctionCmd.Parameters.AddWithValue("@DeptID", deptId);
                    junctionCmd.ExecuteNonQuery();
                }

                transaction.Commit();
                return true;
            }
            catch (Exception)
            {
                transaction.Rollback();
                throw;
            }
        }
    }
}