package com.energy.management;

import java.io.File;

public class LogFile {
    private File file;

    public LogFile(String fileName) {
        this.file = new File(fileName);
    }

    public boolean exists() {
        return file.exists();
    }

    public void create() {
        try {
            if (file.createNewFile()) {
                System.out.println("Log file created: " + file.getName());
            } else {
                System.out.println("Log file already exists.");
            }
        } catch (Exception e) {
            System.out.println("Error creating log file.");
            e.printStackTrace();
        }
    }

    public void delete() {
        if (file.delete()) {
            System.out.println("Log file deleted: " + file.getName());
        } else {
            System.out.println("Failed to delete the log file.");
        }
    }

    public boolean renameTo(File newFile) {
        return file.renameTo(newFile);
    }
}
