package Day2;

import java.util.Scanner;

public class ReverseWhile {
	public static void main(String[] args) {
		
		
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter your number to reverse : ");
		
		int n=sc.nextInt();
		int reverse=0;
		while(n!=0)
		{
			reverse=reverse*10 +(n%10);
			n/=10;
		}
		sc.close();
		System.out.println("Reverse of number : "+reverse);
	}
}
