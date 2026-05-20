package Day34;
import Day34.PayrollSystemException;
import Day34.EmployeePayrollData;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PayrollDBService {
    private static PayrollDBService instance;
    private final String dbURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
    private final String dbUser = "root";
    private final String dbPassword = "password"; // Replace with your environment password

    private PayrollDBService() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load MySQL Driver reference mapping");
        }
    }

    public static synchronized PayrollDBService getInstance() {
        if (instance == null) {
            instance = new PayrollDBService();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbURL, dbUser, dbPassword);
    }

    // UC 2 & UC 12: Read entire normalized framework dataset
    public List<EmployeePayrollData> getAllEmployees() throws PayrollSystemException {
        List<EmployeePayrollData> list = new ArrayList<>();
        String sql = "SELECT e.id, e.name, e.gender, e.phone, e.address, e.start_date, " +
                     "p.basic_pay, p.deductions, p.taxable_pay, p.income_tax, p.net_pay, d.dept_name " +
                     "FROM employee e " +
                     "LEFT JOIN payroll p ON e.id = p.employee_id " +
                     "LEFT JOIN employee_department ed ON e.id = ed.employee_id " +
                     "LEFT JOIN department d ON ed.department_id = d.id";

        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                EmployeePayrollData emp = list.stream().filter(x -> x.id == id).findFirst().orElse(null);
                
                if (emp == null) {
                    emp = new EmployeePayrollData();
                    emp.id = id;
                    emp.name = rs.getString("name");
                    emp.gender = rs.getString("gender").charAt(0);
                    emp.phone = rs.getString("phone");
                    emp.address = rs.getString("address");
                    emp.startDate = rs.getDate("start_date").toLocalDate();
                    emp.basicPay = rs.getDouble("basic_pay");
                    emp.deductions = rs.getDouble("deductions");
                    emp.taxablePay = rs.getDouble("taxable_pay");
                    emp.incomeTax = rs.getDouble("income_tax");
                    emp.netPay = rs.getDouble("net_pay");
                    list.add(emp);
                }
                String deptName = rs.getString("dept_name");
                if (deptName != null) {
                    emp.departments.add(deptName);
                }
            }
        } catch (SQLException e) {
            throw new PayrollSystemException("Query failed: " + e.getMessage());
        }
        return list;
    }

    // UC 3 & UC 4: Update execution using Cached PreparedStatements
    public boolean updateEmployeeSalary(String name, double newBasicPay) throws PayrollSystemException {
        double deductions = newBasicPay * 0.05;
        double taxablePay = newBasicPay - deductions;
        double incomeTax = taxablePay * 0.10;
        double netPay = taxablePay - incomeTax;

        String sql = "UPDATE payroll p JOIN employee e ON p.employee_id = e.id " +
                     "SET p.basic_pay = ?, p.deductions = ?, p.taxable_pay = ?, p.income_tax = ?, p.net_pay = ? " +
                     "WHERE e.name = ?";

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
            throw new PayrollSystemException("Salary sync error: " + e.getMessage());
        }
    }

    // UC 5: Filter data by date range
    public List<EmployeePayrollData> getEmployeesByDateRange(LocalDate start, LocalDate end) throws PayrollSystemException {
        List<EmployeePayrollData> result = new ArrayList<>();
        String sql = "SELECT id, name, basic_pay, start_date FROM employee e " +
                     "JOIN payroll p ON e.id = p.employee_id WHERE e.start_date BETWEEN ? AND ?";

        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDate(1, Date.valueOf(start));
            stmt.setDate(2, Date.valueOf(end));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result.add(new EmployeePayrollData(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("basic_pay"),
                        rs.getDate("start_date").toLocalDate()
                    ));
                }
            }
        } catch (SQLException e) {
            throw new PayrollSystemException("Date range read error: " + e.getMessage());
        }
        return result;
    }

    // UC 7: Complete Multi-Table ACID Transaction Safe Addition
    public boolean addEmployeeToSystemTransactional(EmployeePayrollData emp) throws PayrollSystemException {
        Connection conn = null;
        PreparedStatement empStmt = null;
        PreparedStatement payrollStmt = null;
        PreparedStatement deptCheckStmt = null;
        PreparedStatement deptInsertStmt = null;
        PreparedStatement junctionStmt = null;

        try {
            conn = this.getConnection();
            conn.setAutoCommit(false); // Begin ACID Transaction Block

            // 1. Insert Profile Master Data Record
            String empSql = "INSERT INTO employee (name, gender, start_date, phone, address) VALUES (?, ?, ?, ?, ?)";
            empStmt = conn.prepareStatement(empSql, Statement.RETURN_GENERATED_KEYS);
            empStmt.setString(1, emp.name);
            empStmt.setString(2, String.valueOf(emp.gender));
            empStmt.setDate(3, Date.valueOf(emp.startDate));
            empStmt.setString(4, emp.phone);
            empStmt.setString(5, emp.address);
            empStmt.executeUpdate();

            int empId = 0;
            try (ResultSet rs = empStmt.getGeneratedKeys()) {
                if (rs.next()) empId = rs.getInt(1);
            }

            // 2. Compute financial offsets and save to Payroll Table
            double deductions = emp.basicPay * 0.05;
            double taxablePay = emp.basicPay - deductions;
            double incomeTax = taxablePay * 0.10;
            double netPay = taxablePay - incomeTax;

            String payrollSql = "INSERT INTO payroll (employee_id, basic_pay, deductions, taxable_pay, income_tax, net_pay) VALUES (?, ?, ?, ?, ?, ?)";
            payrollStmt = conn.prepareStatement(payrollSql);
            payrollStmt.setInt(1, empId);
            payrollStmt.setDouble(2, emp.basicPay);
            payrollStmt.setDouble(3, deductions);
            payrollStmt.setDouble(4, taxablePay);
            payrollStmt.setDouble(5, incomeTax);
            payrollStmt.setDouble(6, netPay);
            payrollStmt.executeUpdate();

            // 3. Map to multiple structural departments
            for (String dept : emp.departments) {
                String checkDeptSql = "SELECT id FROM department WHERE dept_name = ?";
                deptCheckStmt = conn.prepareStatement(checkDeptSql);
                deptCheckStmt.setString(1, dept);
                int deptId = 0;
                
                try (ResultSet rs = deptCheckStmt.executeQuery()) {
                    if (rs.next()) {
                        deptId = rs.getInt("id");
                    } else {
                        String insertDeptSql = "INSERT INTO department (dept_name) VALUES (?)";
                        deptInsertStmt = conn.prepareStatement(insertDeptSql, Statement.RETURN_GENERATED_KEYS);
                        deptInsertStmt.setString(1, dept);
                        deptInsertStmt.executeUpdate();
                        try (ResultSet rsKey = deptInsertStmt.getGeneratedKeys()) {
                            if (rsKey.next()) deptId = rsKey.getInt(1);
                        }
                    }
                }

                String junctionSql = "INSERT INTO employee_department (employee_id, department_id) VALUES (?, ?)";
                junctionStmt = conn.prepareStatement(junctionSql);
                junctionStmt.setInt(1, empId);
                junctionStmt.setInt(2, deptId);
                junctionStmt.executeUpdate();
            }

            conn.commit(); // Commit all steps atomically
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { System.err.println("Rollback failed"); }
            }
            throw new PayrollSystemException("Transaction failed, system state rolled back: " + e.getMessage());
        } finally {
            try {
                if (empStmt != null) empStmt.close();
                if (payrollStmt != null) payrollStmt.close();
                if (deptCheckStmt != null) deptCheckStmt.close();
                if (deptInsertStmt != null) deptInsertStmt.close();
                if (junctionStmt != null) junctionStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Resource cleanup warning");
            }
        }
    }
}
