package Day35.Employee_Payrole_CRUD;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Day35.Employee_Payrole_CRUD.EmployeePayrollData;
import Day35.Employee_Payrole_CRUD.PayrollSystemException;

public class PayrollDBService {
    private static PayrollDBService instance;
    private final String url = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false&allowPublicKeyRetrieval=true";
    private final String user = "root";
    private final String password = "password"; // Set to target execution profile parameters

    private PayrollDBService() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Database driver missing from class registration paths");
        }
    }

    // Thread-safe Singleton Accessor Instance Method
    public static synchronized PayrollDBService getInstance() {
        if (instance == null) {
            instance = new PayrollDBService();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // UC 2, UC 10 & UC 12: Read active employees from normalized database
    public List<EmployeePayrollData> getAllActiveEmployees() throws PayrollSystemException {
        List<EmployeePayrollData> employeeList = new ArrayList<>();
        String sql = "SELECT e.id, e.name, e.gender, e.phone, e.address, e.start_date, e.is_active, " +
                     "p.basic_pay, p.deductions, p.taxable_pay, p.income_tax, p.net_pay, d.dept_name " +
                     "FROM employee e " +
                     "LEFT JOIN payroll_details p ON e.id = p.employee_id " +
                     "LEFT JOIN employee_department ed ON e.id = ed.employee_id " +
                     "LEFT JOIN department d ON ed.department_id = d.id " +
                     "WHERE e.is_active = TRUE"; // Filter out soft-deleted records (UC 12)

        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int empId = rs.getInt("id");
                EmployeePayrollData emp = employeeList.stream()
                        .filter(x -> x.id == empId)
                        .findFirst()
                        .orElse(null);

                if (emp == null) {
                    emp = new EmployeePayrollData();
                    emp.id = empId;
                    emp.name = rs.getString("name");
                    emp.gender = rs.getString("gender").charAt(0);
                    emp.phone = rs.getString("phone");
                    emp.address = rs.getString("address");
                    emp.startDate = rs.getDate("start_date").toLocalDate();
                    emp.isActive = rs.getBoolean("is_active");
                    emp.basicPay = rs.getDouble("basic_pay");
                    emp.deductions = rs.getDouble("deductions");
                    emp.taxablePay = rs.getDouble("taxable_pay");
                    emp.incomeTax = rs.getDouble("income_tax");
                    emp.netPay = rs.getDouble("net_pay");
                    employeeList.add(emp);
                }
                String department = rs.getString("dept_name");
                if (department != null) {
                    emp.departments.add(department);
                }
            }
        } catch (SQLException e) {
            throw new PayrollSystemException("Failed to read active employee records: " + e.getLocalizedMessage());
        }
        return employeeList;
    }

    // UC 3 & UC 4: Update execution using Cached PreparedStatements
    public boolean updateEmployeeSalary(String name, double newBasicPay) throws PayrollSystemException {
        // Business logic offsets (Deductions = 20%, Taxable Pay = Basic - Deductions, Tax = 10%, Net Pay = Taxable - Tax)
        double deductions = newBasicPay * 0.20;
        double taxablePay = newBasicPay - deductions;
        double incomeTax = taxablePay * 0.10;
        double netPay = taxablePay - incomeTax;

        String sql = "UPDATE payroll_details p JOIN employee e ON p.employee_id = e.id " +
                     "SET p.basic_pay = ?, p.deductions = ?, p.taxable_pay = ?, p.income_tax = ?, p.net_pay = ? " +
                     "WHERE e.name = ? AND e.is_active = TRUE";

        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDouble(1, newBasicPay);
            stmt.setDouble(2, deductions);
            stmt.setDouble(3, taxablePay);
            stmt.setDouble(4, incomeTax);
            stmt.setDouble(5, netPay);
            stmt.setString(6, name);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new PayrollSystemException("Failed to sync updated salary data: " + e.getMessage());
        }
    }

    // UC 5: Parameterized Date Range Filtering
    public List<EmployeePayrollData> getEmployeesByJoiningDateRange(LocalDate start, LocalDate end) throws PayrollSystemException {
        List<EmployeePayrollData> listings = new ArrayList<>();
        String sql = "SELECT e.id, e.name, p.basic_pay, e.start_date FROM employee e " +
                     "JOIN payroll_details p ON e.id = p.employee_id " +
                     "WHERE e.start_date BETWEEN ? AND ? AND e.is_active = TRUE";

        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDate(1, Date.valueOf(start));
            stmt.setDate(2, Date.valueOf(end));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    listings.add(new EmployeePayrollData(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("basic_pay"),
                            rs.getDate("start_date").toLocalDate()
                    ));
                }
            }
        } catch (SQLException e) {
            throw new PayrollSystemException("Failed to filter records by date range: " + e.getMessage());
        }
        return listings;
    }

    // UC 7, UC 8 & UC 11: Multi-Table ACID Transaction Writing
    public boolean addEmployeeWithPayrollAndDepartments(EmployeePayrollData emp) throws PayrollSystemException {
        Connection conn = null;
        PreparedStatement empStmt = null;
        PreparedStatement payrollStmt = null;
        PreparedStatement checkDeptStmt = null;
        PreparedStatement insertDeptStmt = null;
        PreparedStatement mapStmt = null;

        try {
            conn = this.getConnection();
            conn.setAutoCommit(false); // Begin the ACID Transaction boundary

            // 1. Insert Base Personal Profile Information
            String empSql = "INSERT INTO employee (name, gender, start_date, phone, address) VALUES (?, ?, ?, ?, ?)";
            empStmt = conn.prepareStatement(empSql, Statement.RETURN_GENERATED_KEYS);
            empStmt.setString(1, emp.name);
            empStmt.setString(2, String.valueOf(emp.gender));
            empStmt.setDate(3, Date.valueOf(emp.startDate));
            empStmt.setString(4, emp.phone);
            empStmt.setString(5, emp.address);
            empStmt.executeUpdate();

            int identityGeneratedId = 0;
            try (ResultSet rsKeys = empStmt.getGeneratedKeys()) {
                if (rsKeys.next()) {
                    identityGeneratedId = rsKeys.getInt(1);
                }
            }
            emp.id = identityGeneratedId;

            // 2. Compute financial offsets automatically (UC 7 rules)
            double basic = emp.basicPay;
            double deductions = basic * 0.20;
            double taxable = basic - deductions;
            double tax = taxable * 0.10;
            double netPay = basic - tax;

            String payrollSql = "INSERT INTO payroll_details (employee_id, basic_pay, deductions, taxable_pay, income_tax, net_pay) VALUES (?, ?, ?, ?, ?, ?)";
            payrollStmt = conn.prepareStatement(payrollSql);
            payrollStmt.setInt(1, identityGeneratedId);
            payrollStmt.setDouble(2, basic);
            payrollStmt.setDouble(3, deductions);
            payrollStmt.setDouble(4, taxable);
            payrollStmt.setDouble(5, tax);
            payrollStmt.setDouble(6, netPay);
            payrollStmt.executeUpdate();

            // 3. Dynamic Assignment across multi-department profiles (UC 11)
            for (String deptName : emp.departments) {
                String checkDept = "SELECT id FROM department WHERE dept_name = ?";
                checkDeptStmt = conn.prepareStatement(checkDept);
                checkDeptStmt.setString(1, deptName);
                int departmentId = 0;

                try (ResultSet rs = checkDeptStmt.executeQuery()) {
                    if (rs.next()) {
                        departmentId = rs.getInt("id");
                    } else {
                        String insertDept = "INSERT INTO department (dept_name) VALUES (?)";
                        insertDeptStmt = conn.prepareStatement(insertDept, Statement.RETURN_GENERATED_KEYS);
                        insertDeptStmt.setString(1, deptName);
                        insertDeptStmt.executeUpdate();
                        try (ResultSet rsD = insertDeptStmt.getGeneratedKeys()) {
                            if (rsD.next()) departmentId = rsD.getInt(1);
                        }
                    }
                }

                String mapSql = "INSERT INTO employee_department (employee_id, department_id) VALUES (?, ?)";
                mapStmt = conn.prepareStatement(mapSql);
                mapStmt.setInt(1, identityGeneratedId);
                mapStmt.setInt(2, departmentId);
                mapStmt.executeUpdate();
            }

            conn.commit(); // Commit all steps atomically if successful
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback transaction state on any execution failure
                } catch (SQLException txEx) {
                    System.err.println("Failed to roll back transaction state: " + txEx.getMessage());
                }
            }
            throw new PayrollSystemException("Transaction aborted. System state rolled back: " + e.getMessage());
        } finally {
            // Safe resource cleanup closure procedures
            try {
                if (empStmt != null) empStmt.close();
                if (payrollStmt != null) payrollStmt.close();
                if (checkDeptStmt != null) checkDeptStmt.close();
                if (insertDeptStmt != null) insertDeptStmt.close();
                if (mapStmt != null) mapStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Warning: Failure closing database connections");
            }
        }
    }

    // UC 12: Soft-Delete Implementation for Audit Compliance
    public boolean removeEmployeeFromSystem(String name) throws PayrollSystemException {
        String sql = "UPDATE employee SET is_active = FALSE WHERE name = ? AND is_active = TRUE";
        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new PayrollSystemException("Failed to safely remove employee profile: " + e.getMessage());
        }
    }
}
