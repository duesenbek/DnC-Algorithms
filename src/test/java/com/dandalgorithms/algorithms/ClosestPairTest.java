package com.dandalgorithms.algorithms;

import com.dandalgorithms.util.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClosestPairTest {

    @Test
    public void testBasicClosestPair() {
        Point[] points = {
            new Point(1, 2),
            new Point(4, 6), 
            new Point(7, 8),
            new Point(2, 3),
            new Point(5, 5)
        };
        
        ClosestPair finder = new ClosestPair();
        double result = finder.findClosestPair(points);
        
        assertEquals(1.414, result, 0.01);
    }

    @Test
    public void testCompareWithBruteForce() {
        Point[] points = new Point[100];
        for (int i = 0; i < 100; i++) {
            points[i] = new Point(Math.random() * 100, Math.random() * 100);
        }
        
        ClosestPair finder = new ClosestPair();
        double algorithmResult = finder.findClosestPair(points);
        double bruteForceResult = bruteForce(points);
        
        assertEquals(bruteForceResult, algorithmResult, 0.0001);
    }

    @Test
    public void testMetrics() {
        Point[] points = {
            new Point(1, 2), new Point(4, 6), new Point(7, 8),
            new Point(2, 3), new Point(5, 5), new Point(9, 1),
            new Point(3, 7), new Point(6, 4), new Point(8, 2)
        };
        
        ClosestPair finder = new ClosestPair();
        double result = finder.findClosestPair(points);
        
        var metrics = finder.getMetrics();
        
        assertTrue(metrics.getComparisons() > 0);
        assertTrue(metrics.getElapsedTimeNanos() > 0);
        assertTrue(metrics.getMaxDepth() > 0);
        
        System.out.println("ClosestPair - Сравнений: " + metrics.getComparisons());
        System.out.println("ClosestPair - Время (нс): " + metrics.getElapsedTimeNanos());
        System.out.println("ClosestPair - Глубина рекурсии: " + metrics.getMaxDepth());
    }
    
    private double bruteForce(Point[] points) {
        double minDistance = Double.MAX_VALUE;
        
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double distance = points[i].distanceTo(points[j]);
                if (distance < minDistance) {
                    minDistance = distance;
                }
            }
        }
        
        return minDistance;
    }
}
