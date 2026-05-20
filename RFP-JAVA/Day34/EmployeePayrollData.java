package Day34;
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
    public double basicPay;
    public double deductions;
    public double taxablePay;
    public double incomeTax;
    public double netPay;
    public List<String> departments = new ArrayList<>();

    public EmployeePayrollData() {}

    public EmployeePayrollData(int id, String name, double basicPay, LocalDate startDate) {
        this.id = id;
        this.name = name;
        this.basicPay = basicPay;
        this.startDate = startDate;
    }
}
