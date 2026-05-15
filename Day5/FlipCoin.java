package Day5;

import java.util.Scanner;

public class FlipCoin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of times to flip the coin: ");
        int flips = scanner.nextInt();

        if (flips <= 0) {
            System.out.println("Please enter a positive integer.");
            return;
        }

        int heads = 0;
        int tails = 0;

        for (int i = 0; i < flips; i++) {
            if (Math.random() < 0.5) {
                tails++;
            } else {
                heads++;
            }
        }

        double headPercentage = (double) heads / flips * 100;
        double tailPercentage = (double) tails / flips * 100;

        System.out.println("Heads: " + headPercentage + "%");
        System.out.println("Tails: " + tailPercentage + "%");
    }
}
