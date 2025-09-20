package com.dandalgorithms.metrics;

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
}
