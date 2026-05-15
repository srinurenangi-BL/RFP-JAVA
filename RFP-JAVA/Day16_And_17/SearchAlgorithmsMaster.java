package Day16_And_17;

import java.util.Arrays;

public class SearchAlgorithmsMaster {

    public static void main(String[] args) {
        // --- Linear Search Tests ---
        int[] negativeArr = {10, 5, -2, 8, -5};
        System.out.println("First Negative Index: " + findFirstNegative(negativeArr));

        String[] sentences = {"Java is great", "Algorithms are fun", "Coding is life"};
        System.out.println("Search Sentence: " + findSentenceWithWord(sentences, "fun"));

        // --- Binary Search Tests ---
        int[] rotatedArr = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Rotation Point (Smallest): " + findRotationPoint(rotatedArr));

        int[] peakArr = {1, 2, 3, 1};
        System.out.println("Peak Element: " + findPeakElement(peakArr));

        int[][] matrix = { {1, 3, 5}, {7, 10, 11}, {16, 20, 23} };
        System.out.println("Search 2D Matrix (10): " + search2DMatrix(matrix, 10));

        int[] duplicates = {1, 2, 2, 2, 3, 4, 5};
        System.out.println("First/Last of '2': " + Arrays.toString(findRange(duplicates, 2)));

        // --- Challenge ---
        int[] missingData = {3, 4, -1, 1};
        System.out.println("First Missing Positive: " + findFirstMissingPositive(missingData));
    }

    // --- LINEAR SEARCH ---

    /** UC: Find first negative number */
    public static int findFirstNegative(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) return i; // Return early
        }
        return -1;
    }

    /** UC: Find sentence containing word */
    public static String findSentenceWithWord(String[] sentences, String word) {
        for (String s : sentences) {
            if (s.toLowerCase().contains(word.toLowerCase())) return s;
        }
        return "Not Found";
    }

    // --- BINARY SEARCH ---

    /** UC: Find Rotation Point (Smallest element) in Rotated Sorted Array */
    
    public static int findRotationPoint(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > arr[right]) left = mid + 1;
            else right = mid;
        }
        return arr[left];
    }

    /** UC: Find Peak Element (greater than neighbors) */
    public static int findPeakElement(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < arr[mid + 1]) left = mid + 1;
            else right = mid;
        }
        return arr[left];
    }

    /** UC: 2D Matrix Search (Flattening Logic) */
    
    public static boolean search2DMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int rows = matrix.length, cols = matrix[0].length;
        int low = 0, high = (rows * cols) - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midValue = matrix[mid / cols][mid % cols]; // row = index/cols, col = index%cols
            if (midValue == target) return true;
            if (midValue < target) low = mid + 1;
            else high = mid - 1;
        }
        return false;
    }

    /** UC: First and Last Occurrence */
    public static int[] findRange(int[] arr, int target) {
        return new int[]{findBound(arr, target, true), findBound(arr, target, false)};
    }

    private static int findBound(int[] arr, int target, boolean isFirst) {
        int left = 0, right = arr.length - 1, res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                res = mid;
                if (isFirst) right = mid - 1; // Continue searching left
                else left = mid + 1; // Continue searching right
            } else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return res;
    }

    // --- CHALLENGE: FIRST MISSING POSITIVE ---

    /** Logic: Place each number at its correct index (1 at index 0, 2 at index 1...) */
    public static int findFirstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                // Swap logic to put number in correct position
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) return i + 1;
        }
        return n + 1;
    }
}
