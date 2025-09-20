package com.dandalgorithms.algorithms;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class DeterministicSelectTest {

    @Test
    public void testSelectBasic() {
        int[] array = {5, 2, 8, 1, 9, 3, 7, 4, 6};
        
        DeterministicSelect selector = new DeterministicSelect();
        
        assertEquals(1, selector.select(array, 0));
        assertEquals(5, selector.select(array, 4));
        assertEquals(9, selector.select(array, 8));
    }

    @Test
    public void testCompareWithArraysSort() {
        int[] array = {12, 3, 7, 8, 1, 9, 4, 6, 11, 2, 10, 5};
        int[] sorted = array.clone();
        Arrays.sort(sorted);
        
        DeterministicSelect selector = new DeterministicSelect();
        
        for (int k = 0; k < array.length; k++) {
            int result = selector.select(array.clone(), k);
            assertEquals(sorted[k], result, "Failed for k=" + k);
        }
    }

    @Test
    public void testMetrics() {
        int[] array = {5, 2, 8, 1, 9, 3, 7, 4, 6};
        
        DeterministicSelect selector = new DeterministicSelect();
        int result = selector.select(array, 4);
        
        var metrics = selector.getMetrics();
        
        assertTrue(metrics.getComparisons() > 0);
        assertTrue(metrics.getElapsedTimeNanos() > 0);
        assertTrue(metrics.getMaxDepth() > 0);
        
        System.out.println("Select - Сравнений: " + metrics.getComparisons());
        System.out.println("Select - Время (нс): " + metrics.getElapsedTimeNanos());
        System.out.println("Select - Глубина рекурсии: " + metrics.getMaxDepth());
    }
}
