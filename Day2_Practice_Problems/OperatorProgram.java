package Day2_Practice_Problems;

import java.util.Scanner;

public class OperatorProgram {
	 public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        int a = sc.nextInt();
	        int b = sc.nextInt();
	        int c = sc.nextInt();
	        sc.close();

	        System.out.println("a + b * c = " + (a + b * c));
	        System.out.println("c + a / b = " + (c + a / b));
	        System.out.println("a % b + c = " + (a % b + c));
	        System.out.println("a * b + c = " + (a * b + c));

	        System.out.println("Max = " + Math.max(a, Math.max(b, c)));
	        System.out.println("Min = " + Math.min(a, Math.min(b, c)));
	    }

}
