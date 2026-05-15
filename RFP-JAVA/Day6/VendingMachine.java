package Day6;

import java.util.Scanner;

public class VendingMachine {
    // Available notes in descending order
    static final int[] NOTES = {1000, 500, 100, 50, 10, 5, 2, 1};

    public static void calculateChange(int amount, int noteIndex, int totalNotes) {
        if (amount == 0) {
            System.out.println("Total Minimum Notes Needed: " + totalNotes);
            return;
        }
        if (noteIndex >= NOTES.length) return;

        int noteValue = NOTES[noteIndex];
        if (amount >= noteValue) {
            int count = amount / noteValue;
            System.out.println(noteValue + " Rs Notes : " + count);
            amount = amount % noteValue;
            totalNotes += count;
        }
        // Recursive call for the next smaller note
        calculateChange(amount, noteIndex + 1, totalNotes);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the change amount to be returned: Rs. ");
        int change = sc.nextInt();
        
        System.out.println("Change Breakdown:");
        calculateChange(change, 0, 0);
    }
}
