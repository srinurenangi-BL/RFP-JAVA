package Day35.Employee_Payrole_CRUD;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollData {
    public int id;
    public String name;
    public char gender;
    public String phone;
    public String address;
    public LocalDate startDate;
    public boolean isActive = true;
    
    // Financial Fields (UC 9)
    public double basicPay;
    public double deductions;
    public double taxablePay;
    public double incomeTax;
    public double netPay;
    
    // Department List Mapping (UC 9)
    public List<String> departments = new ArrayList<>();

    public EmployeePayrollData() {}

    public EmployeePayrollData(int id, String name, double basicPay, LocalDate startDate) {
        this.id = id;
        this.name = name;
        this.basicPay = basicPay;
        this.startDate = startDate;
    }
}
