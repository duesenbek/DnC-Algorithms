package com.dandalgorithms.benchmark;

import com.dandalgorithms.algorithms.QuickSort;
import com.dandalgorithms.metrics.CSVWriter;
import com.dandalgorithms.metrics.MetricsTracker;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;

public class QuickSortBenchmark {
    
    @Test
    public void runBenchmark() throws IOException {
        CSVWriter csvWriter = new CSVWriter("quicksort_results.csv");
        csvWriter.writeHeader();
        
        int[] sizes = {10, 100, 1000, 5000, 10000};
        Random random = new Random();
        
        for (int size : sizes) {
            System.out.println("Testing size: " + size);
            
            int[] array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(size * 10);
            }
            
            QuickSort sorter = new QuickSort();
            sorter.sort(array);
            
            MetricsTracker metrics = sorter.getMetrics();
            csvWriter.writeMetrics("QuickSort", size, metrics);
            
            System.out.printf("Size: %d, Time: %d ns, Comparisons: %d, Depth: %d\n",
                size, metrics.getElapsedTimeNanos(), metrics.getComparisons(), metrics.getMaxDepth());
        }
        
        System.out.println("Benchmark completed! Check quicksort_results.csv");
    }
}
