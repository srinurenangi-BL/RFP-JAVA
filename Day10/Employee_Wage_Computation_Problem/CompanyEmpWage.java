package Day10.Employee_Wage_Computation_Problem;

import java.util.ArrayList;
import java.util.List;

public class CompanyEmpWage {
    public final String companyName;
    public final int empRatePerHour;
    public final int numOfWorkingDays;
    public final int maxHoursPerMonth;
    
    public int totalEmpWage;
    public List<Integer> dailyWages; // UC 13: Store Daily Wage

    public CompanyEmpWage(String companyName, int empRatePerHour, int numOfWorkingDays, int maxHoursPerMonth) {
        this.companyName = companyName;
        this.empRatePerHour = empRatePerHour;
        this.numOfWorkingDays = numOfWorkingDays;
        this.maxHoursPerMonth = maxHoursPerMonth;
        this.dailyWages = new ArrayList<>();
    }

    public void setTotalEmpWage(int totalEmpWage) {
        this.totalEmpWage = totalEmpWage;
    }

    @Override
    public String toString() {
        return "\nCompany: " + companyName + " | Total Wage: $" + totalEmpWage + " | Daily Wage History: " + dailyWages;
    }
}
