package com.dandalgorithms.algorithms;

import com.dandalgorithms.metrics.MetricsTracker;
import com.dandalgorithms.util.Point;
import java.util.Arrays;

public class ClosestPair {
    private MetricsTracker metrics;
    
    public ClosestPair() {
        this.metrics = new MetricsTracker();
    }
    
    public double findClosestPair(Point[] points) {
        metrics.reset();
        metrics.startTimer();
        
        if (points == null || points.length < 2) {
            metrics.stopTimer();
            throw new IllegalArgumentException("Need at least 2 points");
        }
        
        Point[] pointsX = points.clone();
        Arrays.sort(pointsX);
        
        double result = closestPair(pointsX, 0, pointsX.length - 1);
        metrics.stopTimer();
        return result;
    }
    
    private double closestPair(Point[] pointsX, int left, int right) {
        metrics.enterRecursion();
        
        int n = right - left + 1;
        if (n <= 3) {
            metrics.exitRecursion();
            return bruteForce(pointsX, left, right);
        }
        
        int mid = left + (right - left) / 2;
        Point midPoint = pointsX[mid];
        
        double leftMin = closestPair(pointsX, left, mid);
        double rightMin = closestPair(pointsX, mid + 1, right);
        double minDistance = Math.min(leftMin, rightMin);
        
        Point[] strip = new Point[n];
        int stripSize = 0;
        
        for (int i = left; i <= right; i++) {
            metrics.incrementComparisons();
            if (Math.abs(pointsX[i].x - midPoint.x) < minDistance) {
                strip[stripSize++] = pointsX[i];
            }
        }
        
        Arrays.sort(strip, 0, stripSize, (p1, p2) -> Double.compare(p1.y, p2.y));
        
        double stripMin = minDistance;
        for (int i = 0; i < stripSize; i++) {
            for (int j = i + 1; j < stripSize && (strip[j].y - strip[i].y) < minDistance; j++) {
                metrics.incrementComparisons();
                double distance = strip[i].distanceTo(strip[j]);
                if (distance < stripMin) {
                    stripMin = distance;
                }
            }
        }
        
        metrics.exitRecursion();
        return Math.min(minDistance, stripMin);
    }
    
    private double bruteForce(Point[] points, int left, int right) {
        double minDistance = Double.MAX_VALUE;
        
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                metrics.incrementComparisons();
                double distance = points[i].distanceTo(points[j]);
                if (distance < minDistance) {
                    minDistance = distance;
                }
            }
        }
        
        return minDistance;
    }
    
    public MetricsTracker getMetrics() {
        return metrics;
    }
}
