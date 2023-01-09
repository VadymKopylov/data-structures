package com.kopylov.iostudy;

import java.io.*;

public class FileManager {
    public static int countFiles(String path) {
        File file = toDirectoryFile(path);
        return countFiles(file);
    }

    public static int countDirs(String path) {
        File file = toDirectoryFile(path);
        return countDirs(file);
    }

    public static void copy(String from, String to) throws IOException {
        File fromFile = new File(from);
        File toFile = new File(to);
        if (!fromFile.exists()) {
            throw new IllegalArgumentException("Please make sure the file to copy exists.");
        } else {
            copy(fromFile, toFile);
        }
    }

    public static void move(String from, String to) {
        new File(from).renameTo(new File(to));
    }

    private static File toDirectoryFile(String path) {
        File file = new File(path);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("Please enter a directory ");
        }
        return file;
    }

    private static int countFiles(File file) {
        int count = 0;
        File[] files = file.listFiles();

        if (files == null) {
            return 0;
        }

        for (File innerFile : files) {
            if (innerFile.isFile()) {
                count++;
            } else {
                count += countFiles(innerFile);
            }
        }
        return count;
    }

    private static int countDirs(File file) {
        int count = 0;
        File[] files = file.listFiles();
        if (files != null) {
            for (File internalFile : files) {
                if (internalFile.isDirectory()) {
                    count++;
                    count += countDirs(internalFile);
                }
            }
        }
        return count;
    }

    private static void copy(File from, File to) throws IOException {
        if (from.isFile()) {
            copyFile(from, to);
        } else {
            copyDirs(from, to);
        }
    }
    private static void copyDirs(File from, File to) throws IOException {
        if (!to.exists()) {
            to.mkdir();
        }
        for (String fileName : from.list()) {
            File fromChildFile = new File(from, fileName);
            File toChildFile = new File(to, fileName);
            copy(fromChildFile, toChildFile);
        }
    }

    private static void copyFile(File from, File to) throws IOException {
        try (InputStream inputStream = new FileInputStream(from);
             OutputStream outputStream = new FileOutputStream(to)) {
            inputStream.transferTo(outputStream);
        }
    }
}
