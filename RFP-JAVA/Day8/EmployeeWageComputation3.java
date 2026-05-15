package Day8;

import java.util.ArrayList;
import java.util.List;

public class EmployeeWageComputation3 {

    // Class Variables (Static Constants for UC 7)
    public static final int IS_PART_TIME = 1;
    public static final int IS_FULL_TIME = 2;
    public static final int WAGE_PER_HOUR = 20;
    public static final int FULL_DAY_HOURS = 8;
    public static final int PART_TIME_HOURS = 8; // As specified in UC 3
    public static final int MAX_WORKING_DAYS = 20;
    public static final int MAX_WORKING_HOURS = 100;

    // Collection Library to store daily wages (Prerequisite 4)
    public static List<Integer> dailyWages = new ArrayList<>();

    // Class Method to Compute Employee Wage (UC 7)
    public static void computeEmpWage() {
        int empHrs = 0;
        int totalEmpHrs = 0;
        int totalWorkingDays = 0;

        // START: Welcome Message
        System.out.println("Welcome to Employee Wage Computation Program on Master Branch\n");

        // UC 6: Calculate Wages till a condition of total working hours or days is reached
        while (totalEmpHrs <= MAX_WORKING_HOURS && totalWorkingDays < MAX_WORKING_DAYS) {
            totalWorkingDays++;
            
            // UC 1: Check Employee is Present or Absent using RANDOM
            int empCheck = (int) Math.floor(Math.random() * 10) % 3;

            // UC 4: Solving using Switch Case Statement
            switch (empCheck) {
                case IS_PART_TIME:
                    // UC 3: Add Part time Employee & Wage
                    empHrs = PART_TIME_HOURS; 
                    System.out.print("Part-Time | ");
                    break;
                case IS_FULL_TIME:
                    // UC 2: Calculate Daily Employee Wage
                    empHrs = FULL_DAY_HOURS;
                    System.out.print("Full-Time | ");
                    break;
                default:
                    empHrs = 0;
                    System.out.print("Absent    | ");
            }

            // Calculate Daily Wage
            totalEmpHrs += empHrs;
            int dailyWage = empHrs * WAGE_PER_HOUR;
            
            // Adding daily wage to our Collection
            dailyWages.add(dailyWage);

            System.out.println("Day #: " + totalWorkingDays + " | Emp Hrs: " + empHrs + " | Daily Wage: $" + dailyWage);
        }

        // UC 5: Calculating Wages for a Month
        int totalEmpWage = totalEmpHrs * WAGE_PER_HOUR;
        
        System.out.println("\n--- Monthly Wage Summary ---");
        System.out.println("Total Working Days: " + totalWorkingDays);
        System.out.println("Total Working Hours: " + totalEmpHrs);
        System.out.println("Total Employee Wage: $" + totalEmpWage);
        System.out.println("Daily Wage History: " + dailyWages);
    }

    public static void main(String[] args) {
        // Calling the Class Method directly (UC 7)
        computeEmpWage();
    }
}
