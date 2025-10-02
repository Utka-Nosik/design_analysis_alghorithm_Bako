package org.example.algorithms;

import org.example.metrics.DepthTracker;
import org.example.metrics.Metrics;
import java.util.Random;

public class QuickSort {

    private static final Random RANDOM = new Random();

    public static Metrics sort(int[] a) {
        Metrics metrics = new Metrics();
        DepthTracker.reset();

        if (a == null || a.length <= 1) {
            return metrics;
        }

        sort(a, 0, a.length - 1, metrics);

        metrics.setMaxRecursionDepth(DepthTracker.getMaxDepth());
        return metrics;
    }

    private static void sort(int[] a, int lo, int hi, Metrics metrics) {
        while (lo < hi) {
            DepthTracker.enter();
            int p = partition(a, lo, hi, metrics);

            if (p - lo < hi - p) {
                sort(a, lo, p - 1, metrics);
                lo = p + 1;
            } else {
                sort(a, p + 1, hi, metrics);
                hi = p - 1;
            }
            DepthTracker.leave();
        }
    }

    private static int partition(int[] a, int lo, int hi, Metrics metrics) {
        int randomIndex = lo + RANDOM.nextInt(hi - lo + 1);
        swap(a, randomIndex, hi);
        int pivot = a[hi];

        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            metrics.incrementComparisons();
            if (a[j] < pivot) {
                i++;
                swap(a, i, j);
            }
        }

        swap(a, i + 1, hi);
        return i + 1;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}