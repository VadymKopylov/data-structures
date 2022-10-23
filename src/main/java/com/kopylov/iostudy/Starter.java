package com.kopylov.iostudy;

import java.io.File;
import java.io.IOException;

public class Starter {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Wrong argument.Enter java FileAnalyzer /path to file/ /search word/");
        }
        String path = null;
        String word = null;

        for (String string : args) {
            File pathToFile = new File(string);
            if (pathToFile.isDirectory()) {
                path = string;
            } else {
                word = string;
            }
        }
        File file = new File(path);
        if (!file.isFile()) {
            System.out.println("There is no file with this directory");
        }
        FileAnalyzer fileAnalyzer = new FileAnalyzer();
        fileAnalyzer.analyze(path, word);
    }
}
