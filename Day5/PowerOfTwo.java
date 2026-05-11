package Day5;

public class PowerOfTwo {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide N as a command-line argument.");
            return;
        }

        int n = Integer.parseInt(args[0]);

        if (n < 0 || n >= 31) {
            System.out.println("N must be between 0 and 30 to prevent integer overflow.");
            return;
        }

        System.out.println("Powers of 2 up to 2^" + n + ":");
        for (int i = 0; i <= n; i++) {
            System.out.println("2^" + i + " = " + (int) Math.pow(2, i));
        }
    }
}