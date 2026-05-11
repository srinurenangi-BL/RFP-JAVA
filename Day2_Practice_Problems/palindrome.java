package Day2_Practice_Problems;

import java.util.Scanner;

public class palindrome {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your number to check the palindrome : ");
		int n=sc.nextInt();
		sc.close();
		int rev=0, original=n;
		for(;n!=0;n/=10) {
			rev=rev*10+(n%10);			
		}
		if(rev==original) {
			System.out.println("The given number "+original+" is a palindrome");
		}
		else {
			System.out.println("The given number is not a palindrome number");
		}
		
		
	}
	

}
