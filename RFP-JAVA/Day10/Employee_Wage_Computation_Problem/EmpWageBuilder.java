package Day10.Employee_Wage_Computation_Problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpWageBuilder implements IComputeEmpWage {
    public static final int IS_PART_TIME = 1;
    public static final int IS_FULL_TIME = 2;

    // UC 12: Refactor to use ArrayList instead of array
    private List<CompanyEmpWage> companyEmpWageList;
    
    // Used for O(1) query lookups by company name
    private Map<String, CompanyEmpWage> companyToEmpWageMap;

    public EmpWageBuilder() {
        companyEmpWageList = new ArrayList<>();
        companyToEmpWageMap = new HashMap<>();
    }

    @Override
    public void addCompanyEmpWage(String companyName, int empRatePerHour, int numOfWorkingDays, int maxHoursPerMonth) {
        CompanyEmpWage companyEmpWage = new CompanyEmpWage(companyName, empRatePerHour, numOfWorkingDays, maxHoursPerMonth);
        companyEmpWageList.add(companyEmpWage);
        companyToEmpWageMap.put(companyName, companyEmpWage);
    }

    @Override
    public void computeEmpWage() {
        for (CompanyEmpWage companyEmpWage : companyEmpWageList) {
            companyEmpWage.setTotalEmpWage(this.computeEmpWage(companyEmpWage));
            System.out.println(companyEmpWage);
        }
    }

    // Class Method with function parameters (UC 8 & UC 9 logic applied)
    private int computeEmpWage(CompanyEmpWage companyEmpWage) {
        int empHrs = 0;
        int totalEmpHrs = 0;
        int totalWorkingDays = 0;

        System.out.println("\n--- Computing Wage for " + companyEmpWage.companyName + " ---");

        while (totalEmpHrs <= companyEmpWage.maxHoursPerMonth && totalWorkingDays < companyEmpWage.numOfWorkingDays) {
            totalWorkingDays++;
            
            // UC 1: Random Attendance
            int empCheck = (int) Math.floor(Math.random() * 10) % 3;

            // UC 3: Switch Case
            switch (empCheck) {
                case IS_PART_TIME:
                    empHrs = 8; // UC 2: Assuming PT is 8
                    break;
                case IS_FULL_TIME:
                    empHrs = 8; // UC 1: Assuming FT is 8
                    break;
                default:
                    empHrs = 0;
            }

            totalEmpHrs += empHrs;
            int dailyWage = empHrs * companyEmpWage.empRatePerHour;
            
            // UC 13: Store the Daily Wage
            companyEmpWage.dailyWages.add(dailyWage);

            System.out.println("Day #: " + totalWorkingDays + " | Emp Hrs: " + empHrs + " | Daily Wage: $" + dailyWage);
        }
        return totalEmpHrs * companyEmpWage.empRatePerHour;
    }

    // Final UC: Get Total Wage when queried by Company
    @Override
    public int getTotalWage(String companyName) {
        if (companyToEmpWageMap.containsKey(companyName)) {
            return companyToEmpWageMap.get(companyName).totalEmpWage;
        }
        return 0; // Return 0 if company not found
    }

    public static void main(String[] args) {
        // Start Requirement
        System.out.println("Welcome to Employee Wage Computation Program on Master Branch");

        EmpWageBuilder empWageBuilder = new EmpWageBuilder();
        
        // Adding multiple companies with their unique constraints
        empWageBuilder.addCompanyEmpWage("D-Mart", 20, 20, 100);
        empWageBuilder.addCompanyEmpWage("Reliance", 10, 25, 120);
        empWageBuilder.addCompanyEmpWage("Amazon", 25, 15, 80);

        // Compute wages
        empWageBuilder.computeEmpWage();

        // Final UC Query Testing
        System.out.println("\n--- Query Results ---");
        System.out.println("Total Wage for Reliance: $" + empWageBuilder.getTotalWage("Reliance"));
        System.out.println("Total Wage for Amazon: $" + empWageBuilder.getTotalWage("Amazon"));
    }
}