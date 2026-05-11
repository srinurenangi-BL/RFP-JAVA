package JavaDay3PracticeProblems;

public class EmpWageBuilderUC5 {

    public static void main(String[] args) {

    	final int IS_PART_TIME = 1;
    	final  int IS_FULL_TIME = 2;
        int WAGE_PER_HOUR = 20;
        int WORKING_DAYS = 20;

        int totalEmpWage = 0;

        for (int day = 1; day <= WORKING_DAYS; day++) {

            int empHours = 0;
            int empCheck = (int) (Math.floor(Math.random() * 10) % 3);

            switch (empCheck) {
                case IS_PART_TIME:
                    empHours = 4;
                    break;

                case IS_FULL_TIME:
                    empHours = 8;
                    break;

                default:
                    empHours = 0;
            }

            int dailyWage = empHours * WAGE_PER_HOUR;
            totalEmpWage += dailyWage;
        }

        System.out.println("Total Employee Monthly Wage: " + totalEmpWage);
    }
}
