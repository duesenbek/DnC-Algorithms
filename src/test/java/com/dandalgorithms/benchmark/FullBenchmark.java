package com.dandalgorithms.benchmark;

import com.dandalgorithms.algorithms.*;
import com.dandalgorithms.metrics.CSVWriter;
import com.dandalgorithms.metrics.MetricsTracker;
import com.dandalgorithms.util.Point;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Random;

public class FullBenchmark {
    
    @Test
    public void runFullBenchmark() throws IOException {
        CSVWriter csvWriter = new CSVWriter("benchmark_results.csv");
        csvWriter.writeHeader();
        
        int[] sizes = {10, 50, 100, 500, 1000, 2000, 5000};
        Random random = new Random();
        
        System.out.println("🚀 Starting comprehensive benchmark...");
        System.out.println("========================================");
        
        for (int size : sizes) {
            System.out.println("\n📊 Testing size: " + size);
            System.out.println("----------------------------------------");
            
            int[] array = generateRandomArray(size, random);
            Point[] points = generateRandomPoints(size, random);
            int k = size / 2;
            
            testAlgorithm("MergeSort", size, () -> {
                MergeSort sorter = new MergeSort();
                sorter.sort(array.clone());
                return sorter.getMetrics();
            }, csvWriter);
            
            testAlgorithm("QuickSort", size, () -> {
                QuickSort sorter = new QuickSort();
                sorter.sort(array.clone());
                return sorter.getMetrics();
            }, csvWriter);
            
            testAlgorithm("Select", size, () -> {
                DeterministicSelect sorter = new DeterministicSelect();
                sorter.select(array.clone(), k);
                return sorter.getMetrics();
            }, csvWriter);
            
            if (size <= 2000) {
                testAlgorithm("ClosestPair", size, () -> {
                    ClosestPair finder = new ClosestPair();
                    finder.findClosestPair(points);
                    return finder.getMetrics();
                }, csvWriter);
            }
            
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
        
        System.out.println("\n✅ Benchmark completed!");
        System.out.println("📁 Results saved to: benchmark_results.csv");
    }
    
    private void testAlgorithm(String name, int size, AlgorithmTest test, CSVWriter csvWriter) {
        try {
            long startTime = System.currentTimeMillis();
            MetricsTracker metrics = test.run();
            long endTime = System.currentTimeMillis();
            
            csvWriter.writeMetrics(name, size, metrics);
            
            System.out.printf("%-12s | Time: %6d ns | Comparisons: %6d | Depth: %2d\n",
                name,
                metrics.getElapsedTimeNanos(),
                metrics.getComparisons(),
                metrics.getMaxDepth());
            
            if (endTime - startTime > 10000) {
                System.out.println("⚠️  " + name + " is taking too long for size " + size);
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error in " + name + " for size " + size + ": " + e.getMessage());
        }
    }
    
    private int[] generateRandomArray(int size, Random random) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size * 10);
        }
        return array;
    }
    
    private Point[] generateRandomPoints(int size, Random random) {
        Point[] points = new Point[size];
        for (int i = 0; i < size; i++) {
            points[i] = new Point(random.nextDouble() * 1000, random.nextDouble() * 1000);
        }
        return points;
    }
    
    @FunctionalInterface
    private interface AlgorithmTest {
        MetricsTracker run();
    }
}
