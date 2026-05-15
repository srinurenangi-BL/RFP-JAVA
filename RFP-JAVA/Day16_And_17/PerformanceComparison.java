package Day16_And_17;

import java.util.*;

public class PerformanceComparison {

    public static void main(String[] args) {
        System.out.println("--- 1. Search Performance: Linear vs Binary ---");
        benchmarkSearch(100000);

        System.out.println("\n--- 2. Sorting Performance: Bubble vs Merge ---");
        benchmarkSort(10000);

        System.out.println("\n--- 3. String Performance: String vs StringBuilder ---");
        benchmarkString(50000);

        System.out.println("\n--- 4. Fibonacci: Recursive vs Iterative ---");
        benchmarkFibonacci(40);
    }

    // --- 1. SEARCH BENCHMARK ---
    public static void benchmarkSearch(int n) {
        int[] data = new int[n];
        for (int i = 0; i < n; i++) data[i] = i;
        int target = n - 1;

        // Linear Search O(N)
        long start = System.nanoTime();
        for (int i : data) { if (i == target) break; }
        long end = System.nanoTime();
        System.out.println("Linear Search O(N): " + (end - start) / 1000 + " us");

        // Binary Search O(log N)
        start = System.nanoTime();
        Arrays.binarySearch(data, target);
        end = System.nanoTime();
        System.out.println("Binary Search O(log N): " + (end - start) / 1000 + " us");
    }

    // --- 2. SORTING BENCHMARK ---
    public static void benchmarkSort(int n) {
        int[] arr1 = new Random().ints(n, 0, n).toArray();
        int[] arr2 = Arrays.copyOf(arr1, arr1.length);

        // Bubble Sort O(N^2)
        long start = System.currentTimeMillis();
        bubbleSort(arr1);
        long end = System.currentTimeMillis();
        System.out.println("Bubble Sort O(N^2): " + (end - start) + " ms");

        // Built-in Sort (Dual-Pivot Quicksort/MergeSort) O(N log N)
        start = System.currentTimeMillis();
        Arrays.sort(arr2);
        end = System.currentTimeMillis();
        System.out.println("Merge/Quick Sort O(N log N): " + (end - start) + " ms");
    }

    private static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }

    // --- 3. STRING CONCATENATION BENCHMARK ---
    public static void benchmarkString(int n) {
        // String O(N^2)
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < n; i++) s += "a";
        long end = System.currentTimeMillis();
        System.out.println("String Concatenation O(N^2): " + (end - start) + " ms");

        // StringBuilder O(N)
        start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append("a");
        end = System.currentTimeMillis();
        System.out.println("StringBuilder O(N): " + (end - start) + " ms");
    }

    // --- 4. FIBONACCI BENCHMARK ---
    public static void benchmarkFibonacci(int n) {
        long start = System.currentTimeMillis();
        long recResult = fibRecursive(n);
        long end = System.currentTimeMillis();
        System.out.println("Recursive Fibonacci O(2^n) for N=" + n + ": " + (end - start) + " ms");

        start = System.nanoTime();
        long iterResult = fibIterative(n);
        end = System.nanoTime();
        System.out.println("Iterative Fibonacci O(N) for N=" + n + ": " + (end - start) / 1000 + " us");
    }

    public static long fibRecursive(int n) {
        if (n <= 1) return n;
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

    public static long fibIterative(int n) {
        long a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            long temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }
}
