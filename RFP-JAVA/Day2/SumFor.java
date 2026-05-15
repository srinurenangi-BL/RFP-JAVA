package Day2;

import java.util.Scanner;

public class SumFor {
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter number :");
	int n=sc.nextInt();
	int sum=0;
	for (int i=0; i<=n; i++) {
		sum+=i;
		
	}
	System.out.println("sum of all numbers upto "+n+ " is "+sum);
	sc.close();
	
}
}
