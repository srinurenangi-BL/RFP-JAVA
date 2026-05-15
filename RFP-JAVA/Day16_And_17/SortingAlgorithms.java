package Day16_And_17;

import java.util.Arrays;

public class SortingAlgorithms {

    public static void main(String[] args) {
        // 1. Bubble Sort - Student Marks
        int[] studentMarks = {85, 42, 91, 33, 76};
        bubbleSort(studentMarks);
        System.out.println("Bubble Sort (Marks): " + Arrays.toString(studentMarks));

        // 2. Insertion Sort - Employee IDs
        int[] employeeIds = {1005, 1001, 1009, 1003, 1002};
        insertionSort(employeeIds);
        System.out.println("Insertion Sort (IDs): " + Arrays.toString(employeeIds));

        // 3. Merge Sort - Book Prices
        double[] bookPrices = {599.50, 120.00, 450.75, 99.99, 1100.00};
        mergeSort(bookPrices, 0, bookPrices.length - 1);
        System.out.println("Merge Sort (Prices): " + Arrays.toString(bookPrices));

        // 4. Quick Sort - Product Prices
        double[] productPrices = {25.0, 10.5, 45.0, 15.2, 30.0};
        quickSort(productPrices, 0, productPrices.length - 1);
        System.out.println("Quick Sort (Products): " + Arrays.toString(productPrices));

        // 5. Selection Sort - Exam Scores
        int[] examScores = {67, 89, 54, 98, 72};
        selectionSort(examScores);
        System.out.println("Selection Sort (Scores): " + Arrays.toString(examScores));

        // 6. Heap Sort - Salaries
        int[] salaries = {50000, 30000, 75000, 40000, 60000};
        heapSort(salaries);
        System.out.println("Heap Sort (Salaries): " + Arrays.toString(salaries));

        // 7. Counting Sort - Student Ages
        int[] ages = {12, 15, 10, 18, 12, 14, 11, 17};
        countingSort(ages);
        System.out.println("Counting Sort (Ages): " + Arrays.toString(ages));
    }

    // 1. BUBBLE SORT - O(N^2)
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    // 2. INSERTION SORT - O(N^2)
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // 3. MERGE SORT - O(N log N)
    public static void mergeSort(double[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(double[] arr, int l, int m, int r) {
        int n1 = m - l + 1, n2 = r - m;
        double[] L = new double[n1], R = new double[n2];
        for (int i = 0; i < n1; ++i) L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // 4. QUICK SORT - O(N log N)
    public static void quickSort(double[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(double[] arr, int low, int high) {
        double pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                double temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        double temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // 5. SELECTION SORT - O(N^2)
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    // 6. HEAP SORT - O(N log N)
    public static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) heapify(arr, n, i);
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i, l = 2 * i + 1, r = 2 * i + 2;
        if (l < n && arr[l] > arr[largest]) largest = l;
        if (r < n && arr[r] > arr[largest]) largest = r;
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    // 7. COUNTING SORT - O(N + k)
    public static void countingSort(int[] arr) {
        int n = arr.length;
        int max = 18; // Range given as 10-18
        int[] count = new int[max + 1];
        int[] output = new int[n];

        for (int age : arr) count[age]++;
        for (int i = 1; i <= max; i++) count[i] += count[i - 1];
        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }
        System.arraycopy(output, 0, arr, 0, n);
    }
}
