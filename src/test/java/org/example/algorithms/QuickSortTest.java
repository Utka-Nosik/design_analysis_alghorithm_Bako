package org.example.algorithms;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class QuickSortTest {

    @Test
    void testSortsSimpleArray() {
        int[] actual = {5, 1, 6, 2, 3, 4};
        int[] expected = {1, 2, 3, 4, 5, 6};
        QuickSort.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testSortsEmptyArray() {
        int[] actual = {};
        int[] expected = {};
        QuickSort.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testSortsSingleElementArray() {
        int[] actual = {42};
        int[] expected = {42};
        QuickSort.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testSortsAlreadySortedArray() {
        int[] actual = {10, 20, 30, 40, 50};
        int[] expected = {10, 20, 30, 40, 50};
        QuickSort.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testSortsReverseSortedArray() {
        int[] actual = {50, 40, 30, 20, 10};
        int[] expected = {10, 20, 30, 40, 50};
        QuickSort.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testSortsArrayWithDuplicates() {
        int[] actual = {5, 1, 6, 2, 3, 4, 1, 5};
        int[] expected = {1, 1, 2, 3, 4, 5, 5, 6};
        QuickSort.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testSortsRandomArrays() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int size = random.nextInt(1000);
            int[] arr = new int[size];
            for (int j = 0; j < size; j++) {
                arr[j] = random.nextInt(10000);
            }

            int[] arrCopy = Arrays.copyOf(arr, arr.length);
            Arrays.sort(arrCopy);
            QuickSort.sort(arr);

            assertArrayEquals(arrCopy, arr, "Failed on random array of size " + size);
        }
    }
}