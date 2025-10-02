package org.example.metrics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CsvWriter {

    private final String filePath;

    public CsvWriter(String filePath) {
        this.filePath = filePath;
        initFile();
    }

    private void initFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
                writer.println("Algorithm,InputSize,TimeMillis,Comparisons,Allocations,MaxDepth");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeMetrics(String algorithmName, int inputSize, long timeMillis, Metrics metrics) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            StringBuilder sb = new StringBuilder();
            sb.append(algorithmName).append(',');
            sb.append(inputSize).append(',');
            sb.append(timeMillis).append(',');
            sb.append(metrics.getComparisons()).append(',');
            sb.append(metrics.getAllocations()).append(',');
            sb.append(metrics.getMaxRecursionDepth());
            writer.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}