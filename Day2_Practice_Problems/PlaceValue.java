package Day2_Practice_Problems;

import java.util.Scanner;


public class PlaceValue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number to know the place value");
        int n = sc.nextInt();

        if (n == 1) System.out.println("Unit");
        else if (n == 10) System.out.println("Ten");
        else if (n == 100) System.out.println("Hundred");
        else if (n == 1000) System.out.println("Thousand");
        else System.out.println("Invalid number");
        sc.close();
    }
}