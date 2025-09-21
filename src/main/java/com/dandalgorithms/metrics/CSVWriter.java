package com.dandalgorithms.metrics;

import java.io.FileWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    private String filename;
    
    public CSVWriter(String filename) {
        this.filename = filename;
    }
    
    public void writeHeader() throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Algorithm,Size,TimeNanos,Comparisons,MaxDepth\n");
        }
    }
    
    public void writeMetrics(String algorithm, int size, MetricsTracker metrics) throws IOException {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(String.format("%s,%d,%d,%d,%d\n",
                algorithm,
                size,
                metrics.getElapsedTimeNanos(),
                metrics.getComparisons(),
                metrics.getMaxDepth()));
        }
    }
    
    public void writeMetrics(String algorithmName, int arraySize, long timeNanos, 
                           long comparisons, int maxDepth, long memoryUsage) throws IOException {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(String.format("%s,%d,%d,%d,%d,%d\n",
                algorithmName,
                arraySize,
                timeNanos,
                comparisons,
                maxDepth,
                memoryUsage));
        }
    }
    
    public void writeRawData(String data) throws IOException {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(data);
            writer.write("\n");
        }
    }
    
    public void clearFile() throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("");
        }
    }
}
