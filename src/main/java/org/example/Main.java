package org.example;

import org.example.algorithms.*;
import org.example.domain.Point;
import org.example.metrics.CsvWriter;
import org.example.metrics.Metrics;

import java.util.Random;

public class Main {
    private static final Random RANDOM = new Random();
    private static final String CSV_FILE_PATH = "results.csv";

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: <algorithm> <size> [k]");
            System.err.println("Algorithms: mergesort, quicksort, select, closest");
            return;
        }

        String algorithm = args[0].toLowerCase();
        int size = Integer.parseInt(args[1]);

        CsvWriter csvWriter = new CsvWriter(CSV_FILE_PATH);
        long startTime, endTime;
        Metrics metrics;

        switch (algorithm) {
            case "mergesort":
                int[] mergeSortData = generateRandomIntArray(size);
                startTime = System.nanoTime();
                metrics = MergeSort.sort(mergeSortData);
                endTime = System.nanoTime();
                break;

            case "quicksort":
                int[] quickSortData = generateRandomIntArray(size);
                startTime = System.nanoTime();
                metrics = QuickSort.sort(quickSortData);
                endTime = System.nanoTime();
                break;

            case "select":
                if (args.length < 3) {
                    System.err.println("Usage: select <size> <k>");
                    return;
                }
                int k = Integer.parseInt(args[2]);
                int[] selectData = generateRandomIntArray(size);
                startTime = System.nanoTime();
                DeterministicSelect.select(selectData, k);
                endTime = System.nanoTime();
                metrics = new Metrics();
                break;

            case "closest":
                Point[] closestPairData = generateRandomPointsArray(size);
                startTime = System.nanoTime();
                ClosestPair.findClosestPair(closestPairData);
                endTime = System.nanoTime();
                metrics = new Metrics();
                break;

            default:
                System.err.println("Unknown algorithm: " + algorithm);
                return;
        }

        long durationMillis = (endTime - startTime) / 1_000_000;
        csvWriter.writeMetrics(algorithm, size, durationMillis, metrics);
        System.out.printf("Executed %s on size %d in %d ms. Results saved to %s%n",
                algorithm, size, durationMillis, CSV_FILE_PATH);
    }

    private static int[] generateRandomIntArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = RANDOM.nextInt();
        }
        return arr;
    }

    private static Point[] generateRandomPointsArray(int size) {
        Point[] points = new Point[size];
        for (int i = 0; i < size; i++) {
            points[i] = new Point(RANDOM.nextDouble() * 1000, RANDOM.nextDouble() * 1000);
        }
        return points;
    }
}