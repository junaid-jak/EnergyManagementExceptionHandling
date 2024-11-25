package com.energy.management;

import java.io.*;
import java.util.List;

public class LogFileManager {
    // Inner class representing a log entry
    public static class LogEntry {
        private String date;
        private String station;
        private String energySource;
        private double energyConsumed;

        public LogEntry(String date, String station, String energySource, double energyConsumed) {
            this.date = date;
            this.station = station;
            this.energySource = energySource;
            this.energyConsumed = energyConsumed;
        }

        @Override
        public String toString() {
            return date + "," + station + "," + energySource + "," + energyConsumed;
        }
    }

    // Method to create a log file
    public void createLogFile(String fileName, List<LogEntry> entries) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (LogEntry entry : entries) {
                writer.write(entry.toString());
                writer.newLine();
            }
        }
    }

    // Method to move a log file
    public void moveLogFile(String fileName, String newLocation) throws IOException {
        File file = new File(fileName);
        File newFile = new File(newLocation);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + fileName);
        }
        if (!file.renameTo(newFile)) {
            throw new IOException("Failed to move file: " + fileName);
        }
    }

    // Method to delete a log file
    public void deleteLogFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists() || !file.delete()) {
            throw new IOException("Failed to delete file: " + fileName);
        }
    }

    // Method to archive a log file
    public void archiveLogFile(String fileName, String archiveLocation) throws IOException {
        moveLogFile(fileName, archiveLocation);
    }

    // Method to display log files in the current directory
    public void displayLogFiles() {
        File currentDir = new File(".");
        File[] files = currentDir.listFiles();
        if (files != null) {
            System.out.println("Log files:");
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".log")) {
                    System.out.println(file.getName());
                }
            }
        } else {
            System.out.println("No log files found.");
        }
    }
}
