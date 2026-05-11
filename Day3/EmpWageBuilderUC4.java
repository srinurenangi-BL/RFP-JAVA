package Day3;

public class EmpWageBuilderUC4 {

    public static void main(String[] args) {

    	final int IS_PART_TIME = 1;
        final int IS_FULL_TIME = 2;
        int WAGE_PER_HOUR = 20;

        int empHours = 0;
        int empCheck = (int) (Math.floor(Math.random() * 10) % 3);

        switch (empCheck) {

            case IS_PART_TIME:
                empHours = 8;
                System.out.println("Employee is Part Time");
                break;

            case IS_FULL_TIME:
                empHours = 8;
                System.out.println("Employee is Full Time");
                break;

            default:
                empHours = 0;
                System.out.println("Employee is Absent");
        }

        int empWage = empHours * WAGE_PER_HOUR;
        System.out.println("Employee Wage: " + empWage);
    }
}
