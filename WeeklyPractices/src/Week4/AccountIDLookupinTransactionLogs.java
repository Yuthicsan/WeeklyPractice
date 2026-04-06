package Week4;

import java.util.Arrays;

public class AccountIDLookupinTransactionLogs {

    public class AccountLookupSystem {

        // ------------------ Linear Search ------------------
        public static void linearSearch(String[] arr, String target) {
            int first = -1, last = -1;
            int comparisons = 0;

            for (int i = 0; i < arr.length; i++) {
                comparisons++;

                if (arr[i].equals(target)) {
                    if (first == -1) first = i;
                    last = i;
                }
            }

            System.out.println("Linear Search:");
            System.out.println("First occurrence: " + first);
            System.out.println("Last occurrence: " + last);
            System.out.println("Comparisons: " + comparisons);
        }

        // ------------------ Binary Search (Find One) ------------------
        public static int binarySearch(String[] arr, String target) {
            int low = 0, high = arr.length - 1;
            int comparisons = 0;

            while (low <= high) {
                int mid = (low + high) / 2;
                comparisons++;

                if (arr[mid].equals(target)) {
                    System.out.println("Binary Search Comparisons: " + comparisons);
                    return mid;
                } else if (arr[mid].compareTo(target) < 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            System.out.println("Binary Search Comparisons: " + comparisons);
            return -1;
        }

        // ------------------ Count Occurrences (Binary) ------------------
        public static int countOccurrences(String[] arr, String target) {
            int first = findFirst(arr, target);
            int last = findLast(arr, target);

            if (first == -1) return 0;
            return last - first + 1;
        }

        private static int findFirst(String[] arr, String target) {
            int low = 0, high = arr.length - 1;
            int result = -1;

            while (low <= high) {
                int mid = (low + high) / 2;

                if (arr[mid].equals(target)) {
                    result = mid;
                    high = mid - 1; // move left
                } else if (arr[mid].compareTo(target) < 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            return result;
        }

        private static int findLast(String[] arr, String target) {
            int low = 0, high = arr.length - 1;
            int result = -1;

            while (low <= high) {
                int mid = (low + high) / 2;

                if (arr[mid].equals(target)) {
                    result = mid;
                    low = mid + 1; // move right
                } else if (arr[mid].compareTo(target) < 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            return result;
        }

        // ------------------ Main ------------------
        public static void main(String[] args) {

            String[] logs = {"accB", "accA", "accB", "accC"};

            // -------- Linear Search (Unsorted) --------
            linearSearch(logs, "accB");

            // -------- Sort for Binary Search --------
            Arrays.sort(logs);
            System.out.println("\nSorted Logs: " + Arrays.toString(logs));

            // -------- Binary Search --------
            int index = binarySearch(logs, "accB");
            System.out.println("Binary Search Index: " + index);

            // -------- Count Occurrences --------
            int count = countOccurrences(logs, "accB");
            System.out.println("Total Occurrences: " + count);
        }
    }
}
