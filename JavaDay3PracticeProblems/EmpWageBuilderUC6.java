package JavaDay3PracticeProblems;

public class EmpWageBuilderUC6 {

    public static void main(String[] args) {

       final int IS_PART_TIME = 1;
       final int IS_FULL_TIME = 2;
        int WAGE_PER_HOUR = 20;

        int MAX_WORKING_DAYS = 20;
        int MAX_WORKING_HOURS = 100;

        int totalEmpHours = 0;
        int totalWorkingDays = 0;

        while (totalEmpHours < MAX_WORKING_HOURS && totalWorkingDays < MAX_WORKING_DAYS) {

            totalWorkingDays++;
            int empCheck = (int) (Math.floor(Math.random() * 10) % 3);
            int empHours = 0;

            switch (empCheck) {

                case IS_PART_TIME:
                    empHours = 8;
                    break;

                case IS_FULL_TIME:
                    empHours = 8;
                    break;

                default:
                    empHours = 0;
            }

            totalEmpHours += empHours;
        }

        int totalEmpWage = totalEmpHours * WAGE_PER_HOUR;
        System.out.println("Total Working Days: " + totalWorkingDays);
        System.out.println("Total Employee Hours: " + totalEmpHours);
        System.out.println("Total Employee Wage: " + totalEmpWage);
    }
}
