package Day6;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LogicalPrograms {

    // 1. Fibonacci Series
    public static void printFibonacci(int count) {
        int n1 = 0, n2 = 1, n3;
        System.out.print("Fibonacci Series: " + n1 + " " + n2);
        for (int i = 2; i < count; ++i) {
            n3 = n1 + n2;
            System.out.print(" " + n3);
            n1 = n2;
            n2 = n3;
        }
        System.out.println();
    }

    // 2. Perfect Number
    public static void checkPerfectNumber(int num) {
        int sum = 0;
        for (int i = 1; i <= num / 2; i++) {
            if (num % i == 0) sum += i;
        }
        if (sum == num && num > 0) {
            System.out.println(num + " is a Perfect Number.");
        } else {
            System.out.println(num + " is NOT a Perfect Number.");
        }
    }

    // 3. Prime Number
    public static void checkPrime(int num) {
        if (num <= 1) {
            System.out.println(num + " is NOT a Prime Number.");
            return;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                System.out.println(num + " is NOT a Prime Number.");
                return;
            }
        }
        System.out.println(num + " is a Prime Number.");
    }

    // 4. Reverse a Number
    public static void reverseNumber(int num) {
        int original = num;
        int reverse = 0;
        while (num != 0) {
            int remainder = num % 10;
            reverse = reverse * 10 + remainder;
            num = num / 10;
        }
        System.out.println("Reverse of " + original + " is " + reverse);
    }

    // 5. Coupon Numbers (Static functions for generation and processing)
    public static int generateRandomCoupon(int n) {
        return (int) (Math.random() * n);
    }

    public static void processDistinctCoupons(int n) {
        Set<Integer> collected = new HashSet<>();
        int randomCount = 0;
        while (collected.size() < n) {
            int randomCoupon = generateRandomCoupon(n);
            collected.add(randomCoupon);
            randomCount++;
        }
        System.out.println("Total random numbers generated to collect " + n + " distinct coupons: " + randomCount);
    }

    // 6. Simulate Stopwatch
    public static void simulateStopwatch() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Press Enter to START the stopwatch...");
        scanner.nextLine();
        long startTime = System.currentTimeMillis();

        System.out.print("Press Enter to STOP the stopwatch...");
        scanner.nextLine();
        long stopTime = System.currentTimeMillis();

        long elapsedTime = stopTime - startTime;
        System.out.println("Elapsed Time: " + (elapsedTime / 1000.0) + " seconds.");
    }

    public static void main(String[] args) {
        System.out.println("--- Logical Programs ---");
        printFibonacci(10);
        checkPerfectNumber(28);
        checkPrime(29);
        reverseNumber(12345);
        processDistinctCoupons(10);
        simulateStopwatch();
    }
}