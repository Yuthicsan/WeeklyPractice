package Week3;

import java.util.ArrayList;
import java.util.List;

public class TransactionFeeSortingforAuditCompliance {

    public static class TransactionAuditSystem {

        // Transaction class
        static class Transaction {
            String id;
            double fee;
            String timestamp; // HH:mm

            public Transaction(String id, double fee, String timestamp) {
                this.id = id;
                this.fee = fee;
                this.timestamp = timestamp;
            }

            @Override
            public String toString() {
                return id + ":" + fee + "@" + timestamp;
            }
        }

        // ------------------ Bubble Sort ------------------
        public static void bubbleSortByFee(List<Transaction> list) {
            int n = list.size();
            int passes = 0, swaps = 0;

            for (int i = 0; i < n - 1; i++) {
                boolean swapped = false;
                passes++;

                for (int j = 0; j < n - i - 1; j++) {
                    if (list.get(j).fee > list.get(j + 1).fee) {
                        Transaction temp = list.get(j);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, temp);

                        swaps++;
                        swapped = true;
                    }
                }

                if (!swapped) break; // early termination
            }

            System.out.println("Bubble Sort -> Passes: " + passes + ", Swaps: " + swaps);
        }

        // ------------------ Insertion Sort ------------------
        public static void insertionSortByFeeAndTime(List<Transaction> list) {
            for (int i = 1; i < list.size(); i++) {
                Transaction key = list.get(i);
                int j = i - 1;

                while (j >= 0 && compare(list.get(j), key) > 0) {
                    list.set(j + 1, list.get(j)); // shift
                    j--;
                }
                list.set(j + 1, key);
            }
        }

        // Comparison: fee first, then timestamp
        private static int compare(Transaction t1, Transaction t2) {
            if (t1.fee != t2.fee) {
                return Double.compare(t1.fee, t2.fee);
            }
            return t1.timestamp.compareTo(t2.timestamp);
        }

        // ------------------ Outlier Detection ------------------
        public static List<Transaction> findOutliers(List<Transaction> list) {
            List<Transaction> outliers = new ArrayList<>();
            for (Transaction t : list) {
                if (t.fee > 50) {
                    outliers.add(t);
                }
            }
            return outliers;
        }

        // ------------------ Main Method ------------------
        public static void main(String[] args) {

            List<Transaction> transactions = new ArrayList<>();

            transactions.add(new Transaction("id1", 10.5, "10:00"));
            transactions.add(new Transaction("id2", 25.0, "09:30"));
            transactions.add(new Transaction("id3", 5.0, "10:15"));

            // -------- Bubble Sort (≤ 100) --------
            List<Transaction> smallBatch = new ArrayList<>(transactions);
            bubbleSortByFee(smallBatch);
            System.out.println("Bubble Sorted: " + smallBatch);

            // -------- Insertion Sort (100–1000) --------
            List<Transaction> mediumBatch = new ArrayList<>(transactions);
            insertionSortByFeeAndTime(mediumBatch);
            System.out.println("Insertion Sorted: " + mediumBatch);

            // -------- Outliers --------
            List<Transaction> outliers = findOutliers(transactions);
            System.out.println("High-fee outliers: " + outliers);
        }
    }
}
