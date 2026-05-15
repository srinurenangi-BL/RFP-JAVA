package Day4;

import java.util.ArrayList;
import java.util.List;

public class EmployeeWageBuilder {

    // Constants for Switch Case (UC 3)
    public static final int IS_PART_TIME = 1;
    public static final int IS_FULL_TIME = 2;

    // Instance Variables (Prerequisite 3)
    private int empRatePerHour;
    private int numOfWorkingDays;
    private int maxHoursPerMonth;
    
    // Collection Library to store daily wages (Prerequisite 4)
    private List<Integer> dailyWages;

    // Constructor to initialize variables
    public EmployeeWageBuilder(int empRatePerHour, int numOfWorkingDays, int maxHoursPerMonth) {
        this.empRatePerHour = empRatePerHour;
        this.numOfWorkingDays = numOfWorkingDays;
        this.maxHoursPerMonth = maxHoursPerMonth;
        this.dailyWages = new ArrayList<>();
    }

    // Instance Method to compute wage
    public void computeWage() {
        int empHrs = 0;
        int totalEmpHrs = 0;
        int totalWorkingDays = 0;

        // START: Welcome Message
        System.out.println("Welcome to Employee Wage Computation Program on Master Branch\n");

        // UC 5: Calculate Wages till a condition of total working hours or days is reached
        while (totalEmpHrs <= maxHoursPerMonth && totalWorkingDays < numOfWorkingDays) {
            totalWorkingDays++;
            
            // UC 1: Check Employee is Present or Absent using RANDOM
            int empCheck = (int) Math.floor(Math.random() * 10) % 3;

            // UC 3: Solving using Switch Case Statement
            switch (empCheck) {
                case IS_PART_TIME:
                    // UC 2: Add Part time Employee & Wage (Assuming 8 hours as requested)
                    empHrs = 8; 
                    System.out.print("Part-Time | ");
                    break;
                case IS_FULL_TIME:
                    // UC 1: Full Day Hour is 8
                    empHrs = 8;
                    System.out.print("Full-Time | ");
                    break;
                default:
                    empHrs = 0;
                    System.out.print("Absent    | ");
            }

            // Calculate Daily Wage
            totalEmpHrs += empHrs;
            int dailyWage = empHrs * empRatePerHour;
            
            // Adding daily wage to our Collection
            dailyWages.add(dailyWage);

            System.out.println("Day #: " + totalWorkingDays + " | Emp Hrs: " + empHrs + " | Daily Wage: $" + dailyWage);
        }

        // UC 4: Calculating Wages for a Month
        int totalEmpWage = totalEmpHrs * empRatePerHour;
        
        System.out.println("\n--- Monthly Wage Summary ---");
        System.out.println("Total Working Days: " + totalWorkingDays);
        System.out.println("Total Working Hours: " + totalEmpHrs);
        System.out.println("Total Employee Wage: $" + totalEmpWage);
        System.out.println("Daily Wage History: " + dailyWages);
    }

    public static void main(String[] args) {
        // Creating an Object/Instance of the class (Prerequisite 3)
        // Passing parameters: WagePerHour=20, WorkingDays=20, MaxHours=100
        EmployeeWageBuilder emp1 = new EmployeeWageBuilder(20, 20, 100);
        
        // Calling instance method
        emp1.computeWage();
    }
}