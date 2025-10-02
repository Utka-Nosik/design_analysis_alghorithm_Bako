package org.example.metrics;

public class Metrics {
    private long comparisons;
    private long allocations;
    private int maxRecursionDepth;

    public Metrics() {
        reset();
    }

    public void reset() {
        this.comparisons = 0;
        this.allocations = 0;
        this.maxRecursionDepth = 0;
    }

    public void incrementComparisons() {
        this.comparisons++;
    }

    public void incrementAllocations() {
        this.allocations++;
    }

    public void setMaxRecursionDepth(int depth) {
        this.maxRecursionDepth = depth;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getAllocations() {
        return allocations;
    }

    public int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }

    @Override
    public String toString() {
        return String.format("Comparisons: %d, Allocations: %d, Max Depth: %d",
                comparisons, allocations, maxRecursionDepth);
    }
}