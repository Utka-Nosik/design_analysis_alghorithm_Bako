package org.example.util;

import org.example.metrics.Metrics;
import java.util.Random;

public final class AlgorithmUtils {

    private static final Random RANDOM = new Random();

    private AlgorithmUtils() {
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void shuffle(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            int j = RANDOM.nextInt(i + 1);
            swap(a, i, j);
        }
    }

    public static int partition(int[] a, int lo, int hi, Metrics metrics) {
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
}