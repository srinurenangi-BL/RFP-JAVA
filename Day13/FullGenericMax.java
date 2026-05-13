package Day13;

import java.util.Arrays;

public class FullGenericMax<T extends Comparable<T>> {
    
    // UC 4: Variable arguments to take more than 3 parameters
    @SafeVarargs
    public static <T extends Comparable<T>> T findMax(T... args) {
        Arrays.sort(args); // Sorting to find the maximum
        T max = args[args.length - 1];
        printMax(max); // UC 5: Internal call to print method
        return max;
    }

    // UC 5: Print the maximum value to std out
    public static <T> void printMax(T max) {
        System.out.println("The maximum value is: " + max);
    }

    public static void main(String[] args) {
        findMax(10, 20, 50, 40, 30); // Test with 5 Integers
        findMax("Apple", "Peach", "Banana", "Zebra"); // Test with 4 Strings
    }
}
