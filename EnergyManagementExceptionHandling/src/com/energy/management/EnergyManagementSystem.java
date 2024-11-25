package com.energy.management;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnergyManagementSystem {
    public static void main(String[] args) {
        LogFileManager manager = new LogFileManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnergy Management System");
            System.out.println("1. Create Log File");
            System.out.println("2. Move Log File");
            System.out.println("3. Delete Log File");
            System.out.println("4. Archive Log File");
            System.out.println("5. Display Log Files");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = -1;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
                continue; // Restart the loop
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter file name: ");
                    String fileName = scanner.nextLine();
                    List<LogFileManager.LogEntry> entries = new ArrayList<>();
                    entries.add(new LogFileManager.LogEntry("2024-11-24", "Station1", "Solar", 50.5));
                    entries.add(new LogFileManager.LogEntry("2024-11-24", "Station2", "Wind", 30.2));
                    try {
                        manager.createLogFile(fileName, entries);
                        System.out.println("Log file created successfully.");
                    } catch (IOException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.print("Enter file name: ");
                    String fileName = scanner.nextLine();
                    System.out.print("Enter new location (path): ");
                    String newLocation = scanner.nextLine();
                    try {
                        manager.moveLogFile(fileName, newLocation);
                        System.out.println("Log file moved successfully.");
                    } catch (IOException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.print("Enter file name: ");
                    String fileName = scanner.nextLine();
                    try {
                        manager.deleteLogFile(fileName);
                        System.out.println("Log file deleted successfully.");
                    } catch (IOException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                }
                case 4 -> {
                    System.out.print("Enter file name: ");
                    String fileName = scanner.nextLine();
                    System.out.print("Enter archive location (path): ");
                    String archiveLocation = scanner.nextLine();
                    try {
                        manager.archiveLogFile(fileName, archiveLocation);
                        System.out.println("Log file archived successfully.");
                    } catch (IOException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                }
                case 5 -> manager.displayLogFiles();
                case 6 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
