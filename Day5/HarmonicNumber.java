package Day5;

import java.util.Scanner;

public class HarmonicNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Harmonic Value N: ");
        int n = scanner.nextInt();

        if (n == 0) {
            System.out.println("N cannot be 0.");
            return;
        }

        double harmonicSum = 0.0;
        for (int i = 1; i <= n; i++) {
            harmonicSum += 1.0 / i;
        }

        System.out.println("The " + n + "th Harmonic Value is: " + harmonicSum);
    }
}