package org.example.algorithms;

import org.example.domain.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ClosestPair {

    public static double findClosestPair(Point[] points) {
        if (points == null || points.length < 2) {
            return Double.POSITIVE_INFINITY;
        }

        Point[] pointsSortedByX = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsSortedByX, Comparator.comparingDouble(Point::x));

        return findClosest(pointsSortedByX, 0, pointsSortedByX.length - 1);
    }

    private static double findClosest(Point[] pointsX, int left, int right) {
        if (right - left < 3) {
            return bruteForce(pointsX, left, right);
        }

        int mid = left + (right - left) / 2;
        Point midPoint = pointsX[mid];

        double deltaLeft = findClosest(pointsX, left, mid);
        double deltaRight = findClosest(pointsX, mid + 1, right);
        double delta = Math.min(deltaLeft, deltaRight);

        List<Point> strip = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (Math.abs(pointsX[i].x() - midPoint.x()) < delta) {
                strip.add(pointsX[i]);
            }
        }

        strip.sort(Comparator.comparingDouble(Point::y));

        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y() - strip.get(i).y()) < delta; j++) {
                double distance = strip.get(i).distanceTo(strip.get(j));
                if (distance < delta) {
                    delta = distance;
                }
            }
        }

        return delta;
    }

    private static double bruteForce(Point[] points, int left, int right) {
        double minDistance = Double.POSITIVE_INFINITY;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                double distance = points[i].distanceTo(points[j]);
                if (distance < minDistance) {
                    minDistance = distance;
                }
            }
        }
        return minDistance;
    }
}