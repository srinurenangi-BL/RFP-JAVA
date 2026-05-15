package Day16_And_17;

import java.util.Arrays;

public class BigOBenchmark {

    public static void main(String[] args) {
        int size = 100000; // Dataset size
        int[] data = new int[size];
        for (int i = 0; i < size; i++) data[i] = i;

        System.out.println("--- Search Benchmark (Size: " + size + ") ---");
        
        // 1. Linear Search O(N)
        long start = System.nanoTime();
        linearSearch(data, size - 1);
        long end = System.nanoTime();
        System.out.println("Linear Search O(N): " + (end - start) + " ns");

        // 2. Binary Search O(log N)
        start = System.nanoTime();
        Arrays.binarySearch(data, size - 1);
        end = System.nanoTime();
        System.out.println("Binary Search O(log N): " + (end - start) + " ns");

        System.out.println("\n--- String Benchmark (Iterations: 50,000) ---");
        benchmarkStrings(50000);
    }

    // O(N) Complexity
    public static boolean linearSearch(int[] arr, int target) {
        for (int n : arr) {
            if (n == target) return true;
        }
        return false;
    }

    // Comparing String vs StringBuilder
    public static void benchmarkStrings(int n) {
        // O(N^2) due to immutability and copying
        long start = System.currentTimeMillis();
        String str = "";
        for (int i = 0; i < n; i++) {
            str += "a"; 
        }
        long end = System.currentTimeMillis();
        System.out.println("String (Immutable) O(N^2): " + (end - start) + " ms");

        // O(N) due to mutable internal buffer
        start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("a");
        }
        end = System.currentTimeMillis();
        System.out.println("StringBuilder (Mutable) O(N): " + (end - start) + " ms");
    }
}
