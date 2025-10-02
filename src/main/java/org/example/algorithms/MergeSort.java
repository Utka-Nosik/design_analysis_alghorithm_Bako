package org.example.algorithms;

import org.example.metrics.DepthTracker;
import org.example.metrics.Metrics;

public class MergeSort {

    private static final int INSERTION_SORT_CUTOFF = 16;

    public static Metrics sort(int[] a) {
        Metrics metrics = new Metrics();
        DepthTracker.reset();

        if (a == null || a.length <= 1) {
            return metrics;
        }

        int[] aux = new int[a.length];
        metrics.incrementAllocations(); // For the auxiliary buffer

        sort(a, aux, 0, a.length - 1, metrics);

        metrics.setMaxRecursionDepth(DepthTracker.getMaxDepth());
        return metrics;
    }

    private static void sort(int[] a, int[] aux, int lo, int hi, Metrics metrics) {
        DepthTracker.enter();

        if (hi <= lo + INSERTION_SORT_CUTOFF - 1) {
            insertionSort(a, lo, hi, metrics);
            DepthTracker.leave();
            return;
        }

        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid, metrics);
        sort(a, aux, mid + 1, hi, metrics);
        merge(a, aux, lo, mid, hi, metrics);

        DepthTracker.leave();
    }

    private static void merge(int[] a, int[] aux, int lo, int mid, int hi, Metrics metrics) {
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);

        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            metrics.incrementComparisons();
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (aux[j] < aux[i]) {
                metrics.incrementComparisons();
                a[k] = aux[j++];
            } else {
                metrics.incrementComparisons();
                a[k] = aux[i++];
            }
        }
    }

    private static void insertionSort(int[] a, int lo, int hi, Metrics metrics) {
        for (int i = lo + 1; i <= hi; i++) {
            int current = a[i];
            int j = i - 1;
            while (j >= lo) {
                metrics.incrementComparisons();
                if (a[j] > current) {
                    a[j + 1] = a[j];
                    j--;
                } else {
                    break;
                }
            }
            a[j + 1] = current;
        }
    }
}