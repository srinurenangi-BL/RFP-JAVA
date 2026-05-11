package Day2_Practice_Problems;

import java.util.Scanner;
public class SumWhile {
	  public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        System.out.println("Enter a number to find the sum of all numbers upto that number");
	        int n = sc.nextInt();

	        int sum = 0, i = 1;
	        while (i <= n) {
	            sum += i;
	            i++;
	        }
	        System.out.println("Sum = " + sum);
	        sc.close();
	    }
	   

}

 