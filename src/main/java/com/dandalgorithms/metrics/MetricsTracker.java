package com.dandalgorithms.metrics;

public class MetricsTracker {
    private long comparisons;
    private long startTime;
    private long endTime;
    private int maxDepth;
    private int currentDepth;

    public MetricsTracker() {
        reset();
    }

    public void reset() {
        comparisons = 0;
        startTime = 0;
        endTime = 0;
        maxDepth = 0;
        currentDepth = 0;
    }

    public void incrementComparisons() {
        comparisons++;
    }

    public void incrementComparisons(long count) {
        comparisons += count;
    }

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        endTime = System.nanoTime();
    }

    public void enterRecursion() {
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    public void exitRecursion() {
        currentDepth--;
    }

    public long getComparisons() { return comparisons; }
    public long getElapsedTimeNanos() { return endTime - startTime; }
    public int getMaxDepth() { return maxDepth; }
}
