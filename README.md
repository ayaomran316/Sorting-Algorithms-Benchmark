# Sorting Algorithms Benchmark

## 1. Project Overview
This project compares the performance of five sorting algorithms implemented in Java.

The purpose is to measure and analyze the execution time of each algorithm using different input datasets.

## 2. Sorting Algorithms
The project implements the following algorithms:

1. Selection Sort
2. Insertion Sort
3. Bubble Sort
4. Merge Sort
5. Quick Sort

## 3. Experimental Setup
- **Programming Language:** Java
- **Input Size:** 100,000 elements
- **Number of Algorithms:** 5
- **Test Cases:** 4
- **Time Measurement:** `System.nanoTime()`
- **Time Unit:** Microseconds (µs)

## 4. Test Cases
Each algorithm was tested using four types of input:

- **Random:** Elements in random order.
- **Reverse:** Elements arranged in descending order.
- **Partial:** Partially sorted elements.
- **Sorted:** Elements already arranged in ascending order.

## 5. Benchmark Results

Execution time in microseconds (µs):

| Algorithm | Random | Reverse | Partial | Sorted |
|---|---:|---:|---:|---:|
| Selection Sort | 5,977,942 | 3,186,874 | 2,037,562 | 2,041,996 |
| Insertion Sort | 1,015,338 | 2,035,989 | 258,136 | 241 |
| Bubble Sort | 16,565,456 | 3,825,381 | 9,458,967 | 2,227 |
| Merge Sort | 15,005 | 8,104 | 9,601 | 7,291 |
| Quick Sort | 8,685 | 1,791 | 3,692 | 1,691 |

*Note: Execution times may vary between runs and depending on the computer.*

## 6. Time Complexity

| Algorithm | Best Case | Average Case | Worst Case |
|---|---|---|---|
| Selection Sort | O(n²) | O(n²) | O(n²) |
| Insertion Sort | O(n) | O(n²) | O(n²) |
| Bubble Sort | O(n) | O(n²) | O(n²) |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) |
| Quick Sort | O(n log n) | O(n log n) | O(n²) |

## 7. Analysis
- Quick Sort achieved the shortest execution time in the Random, Reverse, and Partial test cases in this experiment.
- Insertion Sort performed best when the input was already sorted.
- Bubble Sort recorded the longest execution time in most test cases.
- Merge Sort showed relatively consistent performance across the different datasets.
- The results demonstrate that algorithm performance depends on both the algorithm and the arrangement of input data.

## 8. Conclusion
This experiment demonstrates the importance of selecting an appropriate sorting algorithm based on input size and data arrangement.

For large datasets, algorithms with O(n log n) average time complexity, such as Merge Sort and Quick Sort, generally perform better than quadratic algorithms.

## 9. How to Run
1. Clone or download this repository.
2. Open the project in NetBeans or another Java IDE.
3. Run the `SortingBenchmark.java` file.
4. View the execution times and benchmark report in the console.

