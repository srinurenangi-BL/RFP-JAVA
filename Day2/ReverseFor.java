package Day2;

import java.util.Scanner;

public class ReverseFor {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your number to reverse : ");
		int n=sc.nextInt();
		sc.close();
		int reverse=0;
		for(;n!=0;n/=10)
		{
			reverse=reverse*10+(n%10);

		}
		
		System.out.println("Reverse of number is  "+reverse);
	
	}

}
