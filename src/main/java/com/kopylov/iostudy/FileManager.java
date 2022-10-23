package com.kopylov.iostudy;

import java.io.*;

public class FileManager {
    public static int countFiles(String path) {
        File file = verifyCorrectnessOfDirectory(path);
        return countFiles(file);
    }

    public static int CountDirs(String path) {
        File file = verifyCorrectnessOfDirectory(path);
        return countDirs(file);
    }

    public static void copy(String from, String to) throws IOException {
        File fromFile = new File(from);
        File toFile = new File(to);
        if (!fromFile.exists() || toFile.isFile()) {
            throw new IllegalArgumentException("Please make sure the file to copy exists and the location to copy is a directory.");
        } else {
            copy(fromFile, toFile);
        }
    }

    public static void move(String from, String to) throws FileNotFoundException {

    }

    private static File verifyCorrectnessOfDirectory(String path) {
        File file = new File(path);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("Please enter a directory ");
        }
        return file;
    }

    private static int countFiles(File file) {
        int count = 0;
        File[] files = file.listFiles();
        if (files != null) {
            for (File internalFile : files) {
                if (internalFile.isFile()) {
                    count++;
                } else {
                    count += countFiles(file);
                }
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
                    count += countDirs(file);
                }
            }
        }
        return count;
    }

    private static void copy(File from, File to) throws IOException {
        if (from.isFile()) {
            InputStream inputStream = new FileInputStream(from);
            OutputStream outputStream = new FileOutputStream(to);
            inputStream.transferTo(outputStream);
        } else {
            if(!to.exists()){
                to.mkdir();
            }
            String[] dir = from.list();
            if (dir != null) {
                for (String files : dir) {
                    File subFileFrom = new File(from,files);
                    File subFileTo = new File(to,files);
                    copy(subFileFrom, subFileTo);
                }
            }
        }
    }
}
