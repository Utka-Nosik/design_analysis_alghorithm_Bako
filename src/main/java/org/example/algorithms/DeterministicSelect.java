package org.example.algorithms;

import org.example.metrics.DepthTracker;
import org.example.metrics.Metrics;
import org.example.util.AlgorithmUtils;

public class DeterministicSelect {

    public static int select(int[] a, int k) {
        if (a == null || a.length == 0 || k < 0 || k >= a.length) {
            throw new IllegalArgumentException("Invalid input or k is out of bounds");
        }

        Metrics metrics = new Metrics();
        DepthTracker.reset();

        int[] copy = java.util.Arrays.copyOf(a, a.length);
        int result = select(copy, 0, copy.length - 1, k, metrics);

        metrics.setMaxRecursionDepth(DepthTracker.getMaxDepth());
        // System.out.println("DeterministicSelect metrics: " + metrics);
        return result;
    }

    private static int select(int[] a, int lo, int hi, int k, Metrics metrics) {
        while (true) {
            DepthTracker.enter();
            if (lo == hi) {
                DepthTracker.leave();
                return a[lo];
            }

            int pivotValue = findPivot(a, lo, hi, metrics);
            int pivotIndex = findAndMovePivot(a, lo, hi, pivotValue);

            int p = AlgorithmUtils.partition(a, lo, pivotIndex, metrics);

            if (k == p) {
                DepthTracker.leave();
                return a[p];
            } else if (k < p) {
                hi = p - 1;
            } else {
                lo = p + 1;
            }
            DepthTracker.leave();
        }
    }

    private static int findPivot(int[] a, int lo, int hi, Metrics metrics) {
        int n = hi - lo + 1;
        if (n <= 5) {
            return getMedianOfGroup(a, lo, hi, metrics);
        }

        int numGroups = (n + 4) / 5;
        int[] medians = new int[numGroups];
        metrics.incrementAllocations();

        for (int i = 0; i < numGroups; i++) {
            int groupStart = lo + i * 5;
            int groupEnd = Math.min(groupStart + 4, hi);
            medians[i] = getMedianOfGroup(a, groupStart, groupEnd, metrics);
        }

        return select(medians, 0, numGroups - 1, numGroups / 2, metrics);
    }

    private static int getMedianOfGroup(int[] a, int lo, int hi, Metrics metrics) {
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
        return a[lo + (hi - lo) / 2];
    }

    private static int findAndMovePivot(int[] a, int lo, int hi, int pivotValue) {
        for (int i = lo; i <= hi; i++) {
            if (a[i] == pivotValue) {
                AlgorithmUtils.swap(a, i, hi);
                return hi;
            }
        }
        return hi;
    }
}