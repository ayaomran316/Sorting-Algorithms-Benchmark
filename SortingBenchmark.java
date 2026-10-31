import java.util.Arrays;
import java.util.Random;

// ==================================================
//          SORTING ALGORITHMS BENCHMARK
// ==================================================

public class SortingBenchmark {
sc
    // Project Settings
    static final int SIZE = 100_000;

    static final Random random = new Random(42);

    // ==================================================
    //                    MAIN
    // ==================================================

    public static void main(String[] args) {

        System.out.println("\n");
        System.out.println("==============================================");
        System.out.println("       SORTING ALGORITHMS BENCHMARK");
        System.out.println("==============================================");

        System.out.println("Input Size: " + SIZE);
        System.out.println("Algorithms: 5");
        System.out.println("Test Cases: 4");

        System.out.println("----------------------------------------------");

        // Step 1: Generate test datasets
        int[][] datasets = DatasetFactory.createAll(SIZE);


        // Step 2: Algorithm names
        String[] names = {
            "Selection Sort",
            "Insertion Sort",
            "Bubble Sort",
            "Merge Sort",
            "Quick Sort"
        };

        // Step 3: Store execution times
        long[][] results = new long[5][4];


        // Step 4: Warm up the Java Virtual Machine
        int[] warmupData = Arrays.copyOf(datasets[0], 2000);

        for (int i = 0; i < 5; i++) {

            int[] temp = Arrays.copyOf(warmupData, warmupData.length);

            SortingAlgorithms.execute(i, temp);
        }

        // Step 5: Run all algorithms on all test cases
        for (int i = 0; i < 5; i++) {

            System.out.println("\nTesting: " + names[i]);

            for (int j = 0; j < 4; j++) {

                // Independent copy for fair comparison
                int[] testArray = Arrays.copyOf(
                    datasets[j],
                    datasets[j].length
                );


                // Start measuring
                long start = System.nanoTime();

                SortingAlgorithms.execute(i, testArray);

                long end = System.nanoTime();


                // Convert nanoseconds to microseconds
                long elapsed = (end - start) / 1000;


                // Verify sorting correctness
                if (!BenchmarkEngine.isSorted(testArray)) {

                    throw new IllegalStateException(
                        names[i] + " produced incorrect result!"
                    );
                }


                // Save execution time
                results[i][j] = elapsed;

                System.out.println(
                    "Case " + (j + 1) +
                    " completed: " + elapsed + " us"
                );
            }
        }


        // Step 6: Print final comparison table
        ConsoleReport.display(names, results);

        System.out.println("\nAll algorithms passed the correctness test.");
    }

    // ==================================================
    //                 DATASET FACTORY
    // ==================================================

    static class DatasetFactory {


        public static int[][] createAll(int size) {

            int[][] datasets = new int[4][];

            datasets[0] = randomData(size);

            datasets[1] = reverseData(size);

            datasets[2] = partialData(size);

            datasets[3] = sortedData(size);

            return datasets;
        }

        // 1. Random Array
        private static int[] randomData(int size) {

            int[] array = new int[size];

            for (int i = 0; i < size; i++) {

                array[i] = random.nextInt(1_000_000);
            }

            return array;
        }

        // 2. Reverse Sorted Array
        private static int[] reverseData(int size) {

            int[] array = new int[size];

            for (int i = 0; i < size; i++) {

                array[i] = size - i;
            }

            return array;
        }

        // 3. Partially Sorted Array
        // 80% positions remain unchanged
        // 20% positions are randomly rearranged

        private static int[] partialData(int size) {

            int[] array = sortedData(size);

            int changedCount = size / 5;

            int[] positions = new int[size];

            for (int i = 0; i < size; i++) {

                positions[i] = i;
            }


            // Shuffle positions
            for (int i = size - 1; i > 0; i--) {

                int j = random.nextInt(i + 1);

                int temp = positions[i];

                positions[i] = positions[j];

                positions[j] = temp;
            }

            // Save selected values
            int[] values = new int[changedCount];

            for (int i = 0; i < changedCount; i++) {

                values[i] = array[positions[i]];
            }


            // Shuffle selected values
            for (int i = changedCount - 1; i > 0; i--) {

                int j = random.nextInt(i + 1);

                int temp = values[i];

                values[i] = values[j];

                values[j] = temp;
            }


            // Put shuffled values back
            for (int i = 0; i < changedCount; i++) {

                array[positions[i]] = values[i];
            }

            return array;
        }


        // 4. Fully Sorted Array
        private static int[] sortedData(int size) {

            int[] array = new int[size];

            for (int i = 0; i < size; i++) {

                array[i] = i + 1;
            }

            return array;
        }
    }

    // ==================================================
    //                SORTING ALGORITHMS
    // ==================================================

    static class SortingAlgorithms {


        // Select the required algorithm
        public static void execute(int choice, int[] array) {

            switch (choice) {

                case 0:
                    selectionSort(array);
                    break;

                case 1:
                    insertionSort(array);
                    break;

                case 2:
                    bubbleSort(array);
                    break;

                case 3:
                    mergeSort(array);
                    break;

                case 4:
                    quickSort(array, 0, array.length - 1);
                    break;

                default:
                    throw new IllegalArgumentException(
                        "Invalid algorithm"
                    );
            }
        }

        // 1. SELECTION SORT

        private static void selectionSort(int[] array) {

            int n = array.length;

            for (int i = 0; i < n - 1; i++) {

                int minIndex = i;

                for (int j = i + 1; j < n; j++) {

                    if (array[j] < array[minIndex]) {

                        minIndex = j;
                    }
                }


                int temp = array[i];

                array[i] = array[minIndex];

                array[minIndex] = temp;
            }
        }

        // 2. INSERTION SORT

        private static void insertionSort(int[] array) {

            for (int i = 1; i < array.length; i++) {

                int key = array[i];

                int j = i - 1;


                while (j >= 0 && array[j] > key) {

                    array[j + 1] = array[j];

                    j--;
                }


                array[j + 1] = key;
            }
        }

        // 3. BUBBLE SORT

        private static void bubbleSort(int[] array) {

            int n = array.length;

            for (int i = 0; i < n - 1; i++) {

                boolean swapped = false;

                for (int j = 0; j < n - i - 1; j++) {

                    if (array[j] > array[j + 1]) {

                        int temp = array[j];

                        array[j] = array[j + 1];

                        array[j + 1] = temp;

                        swapped = true;
                    }
                }


                // Early stopping
                if (!swapped) {

                    break;
                }
            }
        }

        // 4. MERGE SORT

        private static void mergeSort(int[] array) {

            if (array.length < 2) {

                return;
            }


            int mid = array.length / 2;


            int[] left = Arrays.copyOfRange(
                array, 0, mid
            );

            int[] right = Arrays.copyOfRange(
                array, mid, array.length
            );


            mergeSort(left);

            mergeSort(right);


            merge(array, left, right);
        }


        // Merge two sorted arrays
        private static void merge(
            int[] array,
            int[] left,
            int[] right
        ) {

            int i = 0;

            int j = 0;

            int k = 0;


            while (i < left.length && j < right.length) {

                if (left[i] <= right[j]) {

                    array[k++] = left[i++];

                } else {

                    array[k++] = right[j++];
                }
            }


            while (i < left.length) {

                array[k++] = left[i++];
            }


            while (j < right.length) {

                array[k++] = right[j++];
            }
        }



        // 5. QUICK SORT

        private static void quickSort(
            int[] array,
            int low,
            int high
        ) {

            if (low >= high) {

                return;
            }


            // Choose middle element as pivot
            int pivot = array[low + (high - low) / 2];

            int i = low;

            int j = high;


            while (i <= j) {

                while (array[i] < pivot) {

                    i++;
                }


                while (array[j] > pivot) {

                    j--;
                }


                if (i <= j) {

                    int temp = array[i];

                    array[i] = array[j];

                    array[j] = temp;

                    i++;

                    j--;
                }
            }


            if (low < j) {

                quickSort(array, low, j);
            }


            if (i < high) {

                quickSort(array, i, high);
            }
        }
    }

    // ==================================================
    //                 BENCHMARK ENGINE
    // ==================================================

    static class BenchmarkEngine {

        // Check if the array is sorted correctly
        public static boolean isSorted(int[] array) {

            for (int i = 1; i < array.length; i++) {

                if (array[i] < array[i - 1]) {

                    return false;
                }
            }

            return true;
        }
    }

    // ==================================================
    //                  CONSOLE REPORT
    // ==================================================

    static class ConsoleReport {


        public static void display(
            String[] names,
            long[][] results
        ) {

            String[] cases = {

                "Random",
                "Reverse",
                "Partial",
                "Sorted"
            };


            System.out.println("\n\n");

            System.out.println(
                "=============================================================="
            );

            System.out.println(
                "                 FINAL BENCHMARK REPORT"
            );

            System.out.println(
                "=============================================================="
            );

            System.out.printf(
                "%-18s %12s %12s %12s %12s%n",
                "Algorithm",
                cases[0],
                cases[1],
                cases[2],
                cases[3]
            );


            System.out.println(
                "--------------------------------------------------------------"
            );

            for (int i = 0; i < names.length; i++) {

                System.out.printf(
                    "%-18s %12d %12d %12d %12d%n",

                    names[i],

                    results[i][0],

                    results[i][1],

                    results[i][2],

                    results[i][3]
                );
            }
            System.out.println(
                "=============================================================="
            );

            System.out.println(
                "Time Unit: Microseconds (us)"
            );
        }
    }
}