package com.dandalgorithms.algorithms;

import com.dandalgorithms.metrics.MetricsTracker;

public class QuickSort {
    private static final int INSERTION_SORT_THRESHOLD = 10;
    private MetricsTracker metrics;

    public QuickSort() {
        this.metrics = new MetricsTracker();
    }

    public void sort(int[] array) {
        metrics.reset();
        metrics.startTimer();

        if (array == null || array.length <= 1) {
            metrics.stopTimer();
            return;
        }

        quickSort(array, 0, array.length - 1);
        metrics.stopTimer();
    }

    private void quickSort(int[] array, int low, int high) {
        metrics.enterRecursion();

        if (high - low <= INSERTION_SORT_THRESHOLD) {
            insertionSort(array, low, high);
            metrics.exitRecursion();
            return;
        }

        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }

        metrics.exitRecursion();
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            metrics.incrementComparisons();
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void insertionSort(int[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= left && array[j] > key) {
                metrics.incrementComparisons();
                array[j + 1] = array[j];
                j--;
            }
            if (j >= left) {
                metrics.incrementComparisons();
            }
            array[j + 1] = key;
        }
    }

    public MetricsTracker getMetrics() {
        return metrics;
    }
}
