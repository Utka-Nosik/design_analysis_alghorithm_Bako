package org.example.algorithms;

import org.example.domain.Point;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ClosestPairTest {

    private static final double DELTA = 1e-9;

    @Test
    void testSimpleCase() {
        Point[] points = {
                new Point(2, 3), new Point(12, 30),
                new Point(40, 50), new Point(5, 1),
                new Point(12, 10), new Point(3, 4)
        };
        double expected = Math.sqrt(2); // Pair (2,3) and (3,4)
        assertEquals(expected, ClosestPair.findClosestPair(points), DELTA);
    }

    @Test
    void testTwoPoints() {
        Point[] points = { new Point(0, 0), new Point(3, 4) };
        assertEquals(5.0, ClosestPair.findClosestPair(points), DELTA);
    }

    @Test
    void testEmptyAndSinglePoint() {
        Point[] empty = {};
        Point[] single = { new Point(1, 1) };
        assertEquals(Double.POSITIVE_INFINITY, ClosestPair.findClosestPair(empty), DELTA);
        assertEquals(Double.POSITIVE_INFINITY, ClosestPair.findClosestPair(single), DELTA);
    }

    private double bruteForceReference(Point[] points) {
        if (points == null || points.length < 2) {
            return Double.POSITIVE_INFINITY;
        }
        double minDistance = Double.POSITIVE_INFINITY;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                minDistance = Math.min(minDistance, points[i].distanceTo(points[j]));
            }
        }
        return minDistance;
    }

    @Test
    void testAgainstBruteForce() {
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            int n = random.nextInt(999) + 2; // from 2 to 1000 points
            Point[] points = new Point[n];
            for (int j = 0; j < n; j++) {
                points[j] = new Point(random.nextDouble() * 1000, random.nextDouble() * 1000);
            }

            double expected = bruteForceReference(points);
            double actual = ClosestPair.findClosestPair(points);

            assertEquals(expected, actual, DELTA, "Failed for " + n + " random points");
        }
    }
}