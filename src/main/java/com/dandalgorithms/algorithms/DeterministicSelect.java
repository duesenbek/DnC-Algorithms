package com.dandalgorithms.algorithms;

import com.dandalgorithms.metrics.MetricsTracker;
import java.util.Arrays;

public class DeterministicSelect {
    private MetricsTracker metrics;

    public DeterministicSelect() {
        this.metrics = new MetricsTracker();
    }

    public int select(int[] array, int k) {
        metrics.reset();
        metrics.startTimer();
        
        if (array == null || array.length == 0 || k < 0 || k >= array.length) {
            metrics.stopTimer();
            throw new IllegalArgumentException("Invalid input");
        }
        
        int result = select(array, 0, array.length - 1, k);
        metrics.stopTimer();
        return result;
    }

    private int select(int[] array, int left, int right, int k) {
        metrics.enterRecursion();
        
        if (right - left + 1 <= 5) {
            insertionSort(array, left, right);
            metrics.exitRecursion();
            return array[left + k];
        }

        int numGroups = (right - left + 1 + 4) / 5;
        int[] medians = new int[numGroups];
        
        for (int i = 0; i < numGroups; i++) {
            int groupStart = left + i * 5;
            int groupEnd = Math.min(groupStart + 4, right);
            insertionSort(array, groupStart, groupEnd);
            medians[i] = array[groupStart + (groupEnd - groupStart) / 2];
        }

        int medianOfMedians = select(medians, 0, medians.length - 1, medians.length / 2);
        int pivotIndex = partition(array, left, right, medianOfMedians);
        int relativeK = pivotIndex - left;
        
        if (k == relativeK) {
            metrics.exitRecursion();
            return array[pivotIndex];
        } else if (k < relativeK) {
            metrics.exitRecursion();
            return select(array, left, pivotIndex - 1, k);
        } else {
            metrics.exitRecursion();
            return select(array, pivotIndex + 1, right, k - relativeK - 1);
        }
    }

    private int partition(int[] array, int left, int right, int pivotValue) {
        int pivotIndex = left;
        for (int i = left; i <= right; i++) {
            metrics.incrementComparisons();
            if (array[i] == pivotValue) {
                pivotIndex = i;
                break;
            }
        }

        swap(array, pivotIndex, right);

        int i = left;
        for (int j = left; j < right; j++) {
            metrics.incrementComparisons();
            if (array[j] <= pivotValue) {
                swap(array, i, j);
                i++;
            }
        }

        swap(array, i, right);
        return i;
    }

    private void insertionSort(int[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= left) {
                metrics.incrementComparisons();
                if (array[j] > key) {
                    array[j + 1] = array[j];
                    j--;
                } else {
                    break;
                }
            }
            array[j + 1] = key;
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public MetricsTracker getMetrics() {
        return metrics;
    }
}
