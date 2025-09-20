package com.dandalgorithms.algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {

    @Test
    public void testSortBasic() {
        int[] array = {5, 2, 8, 1, 9, 3, 7, 4, 6};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        
        QuickSort sorter = new QuickSort();
        sorter.sort(array);
        
        assertArrayEquals(expected, array);
    }

    @Test
    public void testEmptyArray() {
        int[] array = {};
        int[] expected = {};
        
        QuickSort sorter = new QuickSort();
        sorter.sort(array);
        
        assertArrayEquals(expected, array);
    }

    @Test
    public void testSingleElement() {
        int[] array = {42};
        int[] expected = {42};
        
        QuickSort sorter = new QuickSort();
        sorter.sort(array);
        
        assertArrayEquals(expected, array);
    }

    @Test
    public void testAlreadySorted() {
        int[] array = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        
        QuickSort sorter = new QuickSort();
        sorter.sort(array);
        
        assertArrayEquals(expected, array);
    }

    @Test
    public void testReverseSorted() {
        int[] array = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        
        QuickSort sorter = new QuickSort();
        sorter.sort(array);
        
        assertArrayEquals(expected, array);
    }

    @Test
    public void testMetrics() {
        int[] array = {5, 2, 8, 1, 9, 3, 7, 4, 6};
        
        QuickSort sorter = new QuickSort();
        sorter.sort(array);
        
        var metrics = sorter.getMetrics();
        
        assertTrue(metrics.getComparisons() > 0);
        assertTrue(metrics.getElapsedTimeNanos() > 0);
        assertTrue(metrics.getMaxDepth() > 0);
        
        System.out.println("Сравнений: " + metrics.getComparisons());
        System.out.println("Время (нс): " + metrics.getElapsedTimeNanos());
        System.out.println("Глубина рекурсии: " + metrics.getMaxDepth());
    }
}
