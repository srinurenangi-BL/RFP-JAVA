package Day6;

import java.util.Scanner;

public class Binary {

    // Swaps the two nibbles in the lowest byte (8 bits) of the integer
    public static int swapNibbles(int x) {
        // Isolate the rightmost byte: (x & 0x0F) gets the lower nibble, (x & 0xF0) gets the upper.
        // We shift them to swap places.
        int lowerNibble = (x & 0x0F) << 4;
        int upperNibble = (x & 0xF0) >> 4;
        return (lowerNibble | upperNibble);
    }

    // Checks if a number is a power of 2
    public static boolean isPowerOf2(int n) {
        if (n == 0) return false;
        // Power of 2 numbers have only 1 bit set. n & (n-1) clears that bit.
        return (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int num = sc.nextInt();

        // Use Util class to get 32-bit binary
        String binaryRep = Util.toBinary(num);
        System.out.println("32-bit Binary representation: " + binaryRep);

        // Swap nibbles
        int swappedNum = swapNibbles(num);
        System.out.println("Number after swapping nibbles (lowest byte): " + swappedNum);

        // Check power of 2
        if (isPowerOf2(swappedNum)) {
            System.out.println(swappedNum + " is a power of 2.");
        } else {
            System.out.println(swappedNum + " is NOT a power of 2.");
        }
    }
}
