package Week4;
import java.util.*;

public class RiskThresholdBinaryLookup {

    public class RiskThresholdLookup {

        // ------------------ Linear Search ------------------
        public static void linearSearch(int[] arr, int target) {
            int comparisons = 0;
            boolean found = false;

            for (int i = 0; i < arr.length; i++) {
                comparisons++;
                if (arr[i] == target) {
                    System.out.println("Linear: Found at index " + i);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Linear: Not found");
            }

            System.out.println("Linear Comparisons: " + comparisons);
        }

        // ------------------ Binary Search (Insertion Point) ------------------
        public static int binaryInsertionPoint(int[] arr, int target) {
            int low = 0, high = arr.length - 1;
            int comparisons = 0;

            while (low <= high) {
                int mid = (low + high) / 2;
                comparisons++;

                if (arr[mid] == target) {
                    System.out.println("Binary Comparisons: " + comparisons);
                    return mid;
                } else if (arr[mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            System.out.println("Binary Comparisons: " + comparisons);
            return low; // insertion point
        }

        // ------------------ Floor (largest ≤ target) ------------------
        public static Integer findFloor(int[] arr, int target) {
            int low = 0, high = arr.length - 1;
            Integer floor = null;

            while (low <= high) {
                int mid = (low + high) / 2;

                if (arr[mid] == target) {
                    return arr[mid];
                } else if (arr[mid] < target) {
                    floor = arr[mid];
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            return floor;
        }

        // ------------------ Ceiling (smallest ≥ target) ------------------
        public static Integer findCeiling(int[] arr, int target) {
            int low = 0, high = arr.length - 1;
            Integer ceil = null;

            while (low <= high) {
                int mid = (low + high) / 2;

                if (arr[mid] == target) {
                    return arr[mid];
                } else if (arr[mid] < target) {
                    low = mid + 1;
                } else {
                    ceil = arr[mid];
                    high = mid - 1;
                }
            }

            return ceil;
        }

        // ------------------ Main ------------------
        public static void main(String[] args) {

            int[] unsorted = {50, 10, 100, 25}; // unsorted for linear
            int[] sorted = {10, 25, 50, 100};   // sorted for binary

            int target = 30;

            // -------- Linear Search --------
            linearSearch(unsorted, target);

            // -------- Binary Search --------
            int insertionIndex = binaryInsertionPoint(sorted, target);
            System.out.println("Insertion Point Index: " + insertionIndex);

            // -------- Floor & Ceiling --------
            Integer floor = findFloor(sorted, target);
            Integer ceil = findCeiling(sorted, target);

            System.out.println("Floor(" + target + "): " + floor);
            System.out.println("Ceiling(" + target + "): " + ceil);
        }
    }
}
