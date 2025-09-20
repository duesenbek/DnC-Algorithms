package com.dandalgorithms.algorithms;

import com.dandalgorithms.metrics.MetricsTracker;

public class MergeSort {
    private static final int INSERTION_SORT_THRESHOLD = 10;
    private MetricsTracker metrics;

    public MergeSort() {
        this.metrics = new MetricsTracker();
    }

    public void sort(int[] array) {
        metrics.reset();
        metrics.startTimer();

        if (array == null || array.length <= 1) {
            metrics.stopTimer();
            return;
        }

        int[] temp = new int[array.length];
        mergeSort(array, temp, 0, array.length - 1);

        metrics.stopTimer();
    }

    private void mergeSort(int[] array, int[] temp, int left, int right) {
        metrics.enterRecursion();

        if (right - left <= INSERTION_SORT_THRESHOLD) {
            insertionSort(array, left, right);
            metrics.exitRecursion();
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(array, temp, left, mid);
        mergeSort(array, temp, mid + 1, right);
        merge(array, temp, left, mid, right);

        metrics.exitRecursion();
    }

    private void merge(int[] array, int[] temp, int left, int mid, int right) {
        System.arraycopy(array, left, temp, left, right - left + 1);

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            metrics.incrementComparisons();
            if (temp[i] <= temp[j]) {
                array[k++] = temp[i++];
            } else {
                array[k++] = temp[j++];
            }
        }

        while (i <= mid) {
            array[k++] = temp[i++];
        }
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
