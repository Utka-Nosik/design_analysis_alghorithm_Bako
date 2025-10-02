package org.example.algorithms;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeterministicSelectTest {

    @Test
    void testSelectsMedian() {
        int[] arr = {3, 1, 4, 5, 9, 2, 6}; // sorted: 1, 2, 3, 4, 5, 6, 9
        int k = 3; // 4th smallest element (median)
        int expected = 4;
        assertEquals(expected, DeterministicSelect.select(arr, k));
    }

    @Test
    void testSelectsSmallest() {
        int[] arr = {3, 1, 4, 5, 9, 2, 6};
        int k = 0;
        int expected = 1;
        assertEquals(expected, DeterministicSelect.select(arr, k));
    }

    @Test
    void testSelectsLargest() {
        int[] arr = {3, 1, 4, 5, 9, 2, 6};
        int k = arr.length - 1;
        int expected = 9;
        assertEquals(expected, DeterministicSelect.select(arr, k));
    }

    @Test
    void testThrowsExceptionForInvalidK() {
        int[] arr = {1, 2, 3};
        assertThrows(IllegalArgumentException.class, () -> DeterministicSelect.select(arr, -1));
        assertThrows(IllegalArgumentException.class, () -> DeterministicSelect.select(arr, 3));
    }

    @Test
    void testThrowsExceptionForEmptyArray() {
        int[] arr = {};
        assertThrows(IllegalArgumentException.class, () -> DeterministicSelect.select(arr, 0));
    }

    @Test
    void testCorrectnessAgainstStandardSort() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int size = random.nextInt(999) + 1;
            int[] arr = new int[size];
            for (int j = 0; j < size; j++) {
                arr[j] = random.nextInt(10000);
            }

            int k = random.nextInt(size);

            int[] sortedArr = Arrays.copyOf(arr, arr.length);
            Arrays.sort(sortedArr);
            int expected = sortedArr[k];

            int actual = DeterministicSelect.select(arr, k);

            assertEquals(expected, actual, "Failed on random array of size " + size + " for k=" + k);
        }
    }
}