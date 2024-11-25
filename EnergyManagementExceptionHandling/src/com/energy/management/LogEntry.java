package com.energy.management;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class LogEntry {
    private String entryMessage;

    public LogEntry(String entryMessage) {
        this.entryMessage = entryMessage;
    }

    // Write a log entry to the log file
    public void writeToLogFile() {
        try (FileWriter writer = new FileWriter("logfile.txt", true)) {
            writer.write(new Date().toString() + ": " + entryMessage + "\n");
            System.out.println("Log entry added.");
        } catch (IOException e) {
            System.out.println("Error writing log entry.");
            e.printStackTrace();
        }
    }
}

