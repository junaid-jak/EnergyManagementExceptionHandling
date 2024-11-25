package com.energy.management;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class LogFileManagerTest {
    
    // Test the createLogFile method
    @Test
    public void testCreateLogFile() throws IOException {
        LogFileManager logFileManager = new LogFileManager();
        String fileName = "testLogFile.log";
        
        // Prepare log entries
        List<LogFileManager.LogEntry> entries = Arrays.asList(
            new LogFileManager.LogEntry("2024-11-24", "Station1", "Solar", 150.0),
            new LogFileManager.LogEntry("2024-11-25", "Station2", "Wind", 200.5)
        );
        
        // Try creating the log file with the entries
        logFileManager.createLogFile(fileName, entries);
        
        // Check if the file is created
        File file = new File(fileName);
        assertTrue(file.exists(), "Log file should be created");

        // Check if the contents are correct
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            assertEquals("2024-11-24,Station1,Solar,150.0", line, "First log entry should match");
            line = reader.readLine();
            assertEquals("2024-11-25,Station2,Wind,200.5", line, "Second log entry should match");
        }

        // Clean up
        file.delete();
    }
    
    // Test the deleteLogFile method
    @Test
    public void testDeleteLogFile() throws IOException {
        LogFileManager logFileManager = new LogFileManager();
        String fileName = "testLogFileToDelete.log";
        
        // Prepare log entries and create the file
        List<LogFileManager.LogEntry> entries = Arrays.asList(
            new LogFileManager.LogEntry("2024-11-24", "Station1", "Solar", 150.0)
        );
        logFileManager.createLogFile(fileName, entries);
        
        // Delete the log file
        logFileManager.deleteLogFile(fileName);
        
        // Check if the file is deleted
        File file = new File(fileName);
        assertFalse(file.exists(), "Log file should be deleted");
    }
    
    // Test the moveLogFile method
    @Test
    public void testMoveLogFile() throws IOException {
        LogFileManager logFileManager = new LogFileManager();
        String sourceFile = "testLogFileToMove.log";
        String destinationFile = "movedLogFile.log";
        
        // Prepare log entries and create the source file
        List<LogFileManager.LogEntry> entries = Arrays.asList(
            new LogFileManager.LogEntry("2024-11-24", "Station1", "Solar", 150.0)
        );
        logFileManager.createLogFile(sourceFile, entries);
        
        // Move the log file
        logFileManager.moveLogFile(sourceFile, destinationFile);
        
        // Check if the file has been moved
        File fileSource = new File(sourceFile);
        File fileDest = new File(destinationFile);
        
        assertFalse(fileSource.exists(), "Source file should not exist");
        assertTrue(fileDest.exists(), "Destination file should exist");

        // Clean up
        fileDest.delete();
    }
    
    // Test the archiveLogFile method (which internally calls moveLogFile)
    @Test
    public void testArchiveLogFile() throws IOException {
        LogFileManager logFileManager = new LogFileManager();
        String sourceFile = "testLogFileToArchive.log";
        String archiveFile = "archivedLogFile.log";
        
        // Prepare log entries and create the source file
        List<LogFileManager.LogEntry> entries = Arrays.asList(
            new LogFileManager.LogEntry("2024-11-24", "Station1", "Solar", 150.0)
        );
        logFileManager.createLogFile(sourceFile, entries);
        
        // Archive the log file (move it to a different location)
        logFileManager.archiveLogFile(sourceFile, archiveFile);
        
        // Check if the archive file exists
        File fileArchive = new File(archiveFile);
        assertTrue(fileArchive.exists(), "Archived file should exist");

        // Clean up
        fileArchive.delete();
    }
    
    // Test the displayLogFiles method (which lists .log files in the current directory)
    @Test
    public void testDisplayLogFiles() throws IOException {
        LogFileManager logFileManager = new LogFileManager();
        
        // Prepare log entries and create a log file
        String fileName = "testLogFileToDisplay.log";
        List<LogFileManager.LogEntry> entries = Arrays.asList(
            new LogFileManager.LogEntry("2024-11-24", "Station1", "Solar", 150.0)
        );
        logFileManager.createLogFile(fileName, entries);
        
        // Redirect system output to capture it
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        
        // Display log files (this should list the test file)
        logFileManager.displayLogFiles();
        
        // Check if the output contains the file name
        String output = baos.toString();
        assertTrue(output.contains(fileName), "Log file should be listed");
        
        // Clean up
        new File(fileName).delete();
    }
}
