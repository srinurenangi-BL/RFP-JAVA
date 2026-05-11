package JavaDay3PracticeProblems;

public class EmpWageBuilderUC3 {

    public static void main(String[] args) {

        int IS_PART_TIME = 1;
        int IS_FULL_TIME = 2;
        int WAGE_PER_HOUR = 20;

        int empHours = 0;
        int empCheck = (int) (Math.floor(Math.random() * 10) % 3);

        if (empCheck == IS_PART_TIME) {
            empHours = 8;
            System.out.println("Employee is Part Time");
        } else if (empCheck == IS_FULL_TIME) {
            empHours = 8;
            System.out.println("Employee is Full Time");
        } else {
            empHours = 0;
            System.out.println("Employee is Absent");
        }

        int empWage = empHours * WAGE_PER_HOUR;
        System.out.println("Employee Wage: " + empWage);
    }
}
