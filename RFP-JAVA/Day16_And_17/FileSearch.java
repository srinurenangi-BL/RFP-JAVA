package Day16_And_17;

import java.io.*;
import java.util.Arrays;

public class FileSearch {

    public static void main(String[] args) {
        String filePath = "large_text_file.txt";
        String targetWord = "java";

        // Step 1: Load words from the file
        String[] words = readWords(filePath);
        if (words == null) return;

        // --- Option A: Linear Search ---
        long start = System.nanoTime();
        int linearIndex = linearSearch(words, targetWord);
        long end = System.nanoTime();
        System.out.println("Linear Search: Index " + linearIndex + " (Time: " + (end-start) + " ns)");

        // --- Option B: Binary Search (Requires Sorting) ---
        Arrays.sort(words); // Ensure data is sorted
        start = System.nanoTime();
        int binaryIndex = binarySearch(words, targetWord);
        end = System.nanoTime();
        System.out.println("Binary Search: Index " + binaryIndex + " (Time: " + (end-start) + " ns)");
    }

    // LINEAR SEARCH: O(n)
    private static int linearSearch(String[] words, String target) {
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(target)) return i;
        }
        return -1;
    }

    // BINARY SEARCH: O(log n)
    private static int binarySearch(String[] words, String target) {
        int low = 0, high = words.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2; // Overflow safe
            int cmp = words[mid].compareTo(target);
            if (cmp == 0) return mid;
            if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    private static String[] readWords(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line.toLowerCase().replaceAll("[^a-zA-Z ]", "")).append(" ");
            }
            return sb.toString().trim().split("\\s+");
        } catch (IOException e) {
            return null;
        }
    }
}
