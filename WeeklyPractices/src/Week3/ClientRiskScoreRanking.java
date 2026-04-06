package Week3;
import java.util.*;
public class ClientRiskScoreRanking {

    public static class ClientRiskSystem {

        // ------------------ Client Class ------------------
        static class Client {
            String name;
            int riskScore;
            double accountBalance;

            public Client(String name, int riskScore, double accountBalance) {
                this.name = name;
                this.riskScore = riskScore;
                this.accountBalance = accountBalance;
            }

            @Override
            public String toString() {
                return name + ":" + riskScore + "($" + accountBalance + ")";
            }
        }

        // ------------------ Bubble Sort (Ascending Risk) ------------------
        public static void bubbleSortByRisk(Client[] arr) {
            int n = arr.length;
            int swaps = 0;

            for (int i = 0; i < n - 1; i++) {
                boolean swapped = false;

                for (int j = 0; j < n - i - 1; j++) {
                    if (arr[j].riskScore > arr[j + 1].riskScore) {

                        // Swap
                        Client temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;

                        swaps++;
                        swapped = true;

                        // Visualization of swaps
                        System.out.println("Swapped: " + arr[j] + " <-> " + arr[j + 1]);
                    }
                }

                if (!swapped) break; // optimization
            }

            System.out.println("Total swaps: " + swaps);
        }

        // ------------------ Insertion Sort (DESC Risk + Balance) ------------------
        public static void insertionSortDesc(Client[] arr) {
            for (int i = 1; i < arr.length; i++) {
                Client key = arr[i];
                int j = i - 1;

                while (j >= 0 && compare(arr[j], key) < 0) {
                    arr[j + 1] = arr[j]; // shift right
                    j--;
                }

                arr[j + 1] = key;
            }
        }

        // Compare: Higher risk first, then higher balance
        private static int compare(Client c1, Client c2) {
            if (c1.riskScore != c2.riskScore) {
                return Integer.compare(c1.riskScore, c2.riskScore);
            }
            return Double.compare(c1.accountBalance, c2.accountBalance);
        }

        // ------------------ Top N Highest Risk ------------------
        public static void printTopN(Client[] arr, int n) {
            System.out.println("Top " + n + " High-Risk Clients:");
            for (int i = 0; i < Math.min(n, arr.length); i++) {
                System.out.println(arr[i].name + "(" + arr[i].riskScore + ")");
            }
        }

        // ------------------ Main ------------------
        public static void main(String[] args) {

            Client[] clients = {
                    new Client("clientC", 80, 1000),
                    new Client("clientA", 20, 500),
                    new Client("clientB", 50, 800)
            };

            // -------- Bubble Sort --------
            Client[] bubbleArray = clients.clone();
            bubbleSortByRisk(bubbleArray);
            System.out.println("Bubble Sorted (ASC): " + Arrays.toString(bubbleArray));

            // -------- Insertion Sort --------
            Client[] insertionArray = clients.clone();
            insertionSortDesc(insertionArray);
            System.out.println("Insertion Sorted (DESC): " + Arrays.toString(insertionArray));

            // -------- Top Risks --------
            printTopN(insertionArray, 3);
        }
    }
}
