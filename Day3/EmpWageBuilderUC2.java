package Day3;

public class EmpWageBuilderUC2 {

    public static void main(String[] args) {

        int IS_PRESENT = 1;
        int WAGE_PER_HOUR = 20;
        int FULL_DAY_HOUR = 8;

        int empWage = 0;
       	int empCheck = (int) (Math.floor(Math.random() * 10) % 2);

        if (empCheck == IS_PRESENT) {
            empWage = WAGE_PER_HOUR * FULL_DAY_HOUR;
            System.out.println("Employee is Present");
        } else {
            empWage = 0;
            System.out.println("Employee is Absent");
        }

        System.out.println("Daily Employee Wage: " + empWage);
    }
}
