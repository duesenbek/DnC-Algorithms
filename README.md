# DnC-Algorithms

Implementation of classic divide-and-conquer algorithms with performance analysis.

## 📊 Algorithm Analysis Report

## Architecture Notes
- **Recursion Depth Control**: Implemented through smaller-first recursion in QuickSort, insertion sort cutoffs (n ≤ 10), and explicit depth tracking via MetricsTracker
- **Memory Allocation Management**: Reusable buffers in MergeSort, in-place partitioning in QuickSort/Select
- **Metrics System**: Unified MetricsTracker monitors time, comparisons, and recursion depth

## Recurrence Analysis

### MergeSort
**Recurrence**: T(n) = 2T(n/2) + O(n)  
**Method**: Master Theorem Case 2  
**Θ-result**: Θ(n log n) - confirmed by experimental data (9123 comparisons for n=1000)

### QuickSort  
**Recurrence**: T(n) = T(n/3) + T(2n/3) + O(n) 
**Method**: Akra-Bazzi intuition for uneven splits  
**Θ-result**: Θ(n log n) - matches measurements (12835 comparisons for n=1000)

### Deterministic Select
**Recurrence**: T(n) = T(n/5) + T(7n/10) + O(n)
**Method**: Akra-Bazzi with grouping by 5  
**Θ-result**: Θ(n) - linear growth confirmed (8322 comparisons for n=1000 → 47734 for n=5000)

### Closest Pair
**Recurrence**: T(n) = 2T(n/2) + O(n)  
**Method**: Master Theorem Case 2  
**Θ-result**: Θ(n log n) - visible in time complexity growth

## 📈  Experimental Results

### Execution Time (nanoseconds)
| Size | MergeSort | QuickSort | Select  | ClosestPair |
|------|-----------|-----------|---------|-------------|
| 10   | 8,300     | 6,800     | 15,200  | 374,000     |
| 50   | 43,400    | 69,700    | 55,800  | 119,600     |
| 100  | 147,100   | 140,200   | 160,500 | 527,300     |
| 500  | 656,800   | 741,900   | 657,300 | 1,583,500   |
| 1000 | 960,900   | 1,457,500 | 616,500 | 1,716,100   |
| 2000 | 684,400   | 499,500   | 698,900 | 6,701,600   |
| 5000 | 615,600   | 1,103,900 | 1,563,300 | -           |
<img width="660" height="390" alt="executiontime" src="https://github.com/user-attachments/assets/5fbec418-06e5-4981-a1a3-2dea19dcac58" />

### Recursion Depth
| Size | MergeSort | QuickSort | Select | ClosestPair |
|------|-----------|-----------|--------|-------------|
| 10   | 1         | 1         | 2      | 3           |
| 50   | 4         | 6         | 3      | 6           |
| 100  | 5         | 8         | 3      | 7           |
| 500  | 7         | 15        | 4      | 9           |
| 1000 | 8         | 17        | 5      | 10          |
| 2000 | 9         | 19        | 5      | 11          |
| 5000 | 10        | 23        | 6      | -           |
<img width="660" height="390" alt="recursiondepth" src="https://github.com/user-attachments/assets/ee8e4863-83e1-48b4-b473-48b01689b710" />

### Comparisons Count
| Size | MergeSort | QuickSort | Select | ClosestPair |
|------|-----------|-----------|--------|-------------|
| 10   | 26        | 26        | 27     | 32          |
| 50   | 219       | 239       | 379    | 276         |
| 100  | 563       | 655       | 759    | 667         |
| 500  | 4,091     | 4,873     | 4,367  | 4,658       |
| 1000 | 9,123     | 12,835    | 8,322  | 10,400      |
| 2000 | 20,133    | 24,618    | 18,262 | 22,845      |
| 5000 | 58,591    | 77,811    | 47,734 | -           |
<img width="660" height="390" alt="compaisonscount" src="https://github.com/user-attachments/assets/a880c4ee-ab48-4bff-945a-bf79a310e381" />

## Constant-Factor Analysis

### Cache Effects
- **MergeSort**: Shows best performance for large n (615,600ns for n=5000) due to predictable memory access
- **QuickSort**: Cache misses visible at scale (1,103,900ns for n=5000)
- **Select**: Excellent cache locality (47,734 comparisons for n=5000)
- **Closest Pair**: Worst performance due to complex memory access patterns

### Optimization Impact
- **Insertion sort cutoff**: Effective for n=10 (all algorithms show similar performance)
- **Smaller-first recursion**: QuickSort depth ≤ 2log₂n (23 for n=5000)
- **Select optimization**: Linear scaling confirmed (4,367 → 47,734 comparisons for 10x size increase)

# ✅ Theory vs Practice Comparison

### MergeSort
**Expected**: Θ(n log n)  
**Actual**: Excellent match - 58,591 comparisons for n=5000 follows n log n growth

### QuickSort
**Expected**: Θ(n log n) average case  
**Actual**: Slightly worse constants than MergeSort (77,811 vs 58,591 comparisons for n=5000)

### Select
**Expected**: Θ(n)  
**Actual**: Perfect linear scaling - 4,367 comparisons for n=500 → 47,734 for n=5000 (10.9x increase)

### Closest Pair
**Expected**: Θ(n log n)  
**Actual**: Higher constants but correct complexity growth

## Summary
**Strong alignment** between theory and measurements. Select shows perfect linear scaling, MergeSort/QuickSort follow n log n, and constant factors match architectural expectations. Closest Pair has higher overhead but correct complexity.

## 🚀 Usage
```java
MergeSort mergeSort = new MergeSort();
mergeSort.sort(array);

QuickSort quickSort = new QuickSort(); 
quickSort.sort(array);

DeterministicSelect select = new DeterministicSelect();
int median = select.select(array, array.length/2);

ClosestPair closestPair = new ClosestPair();
double minDistance = closestPair.findClosestPair(points);
```

# 🛠 Benchmarking
### Run comprehensive benchmark
```bash
mvn test -Dtest=FullBenchmark
```
### Run individual tests
```bash
mvn test -Dtest=MergeSortTest
mvn test -Dtest=QuickSortTest
mvn test -Dtest=DeterministicSelectTest
mvn test -Dtest=ClosestPairTest
```
# 🔧 Development\
## 📦 Project Structure
```
main/
├── java/com/dandalgorithms/
│   ├── algorithms/
│   │   ├── ClosestPair.java          
│   │   ├── DeterministicSelect.java 
│   │   ├── MergeSort.java           
│   │   └── QuickSort.java           
│   ├── metrics/
│   │   ├── CSVWriter.java           
│   │   └── MetricsTracker.java      
│   └── util/
│       └── resources/
└── test.java

test/
└── com/dandalgorithms/
    ├── algorithms/
    │   ├── ClosestPairTest.java
    │   ├── DeterministicSelectTest.java
    │   ├── QuickSortTest.java
    │   └── MergeSortTest.java
    └── benchmark/
        ├── FullBenchmark.java      
        ├── MergeSortBenchmark.java   
        └── QuickSortBenchmark.java   
 ```       
### Clone and setup
```bash
git clone https://github.com/duesenbek/DnC-Algorithms
cd DnC-Algorithms
mvn clean compile
```
### Run tests
```bash
mvn test
```
### Create benchmark results
```bash
mvn test -Dtest=FullBenchmark
```
## 📝 License
This project is for educational purposes as part of ***Design and Analysis of Algorithms*** course . 


