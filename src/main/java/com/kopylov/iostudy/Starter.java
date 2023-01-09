package com.kopylov.iostudy;

import java.util.List;

import java.io.File;
import java.io.IOException;

public class Starter {

    public static void main(String[] args) throws IOException {
        checkArgumentCorrectness(args);
        String path = getPath(args[0]);
        String word = args[1];

        FileAnalyzer fileAnalyzer = new FileAnalyzer();
        FileInformation fileInformation = fileAnalyzer.analyze(path, word);
        printSentencesWithWord(fileInformation.getSentencesWithWord());
        printCountWord(fileInformation.getWordCount());
    }

    public static void printSentencesWithWord(List<String> sentencesWithWord) {
        System.out.println(sentencesWithWord);
    }

    public static void printCountWord(int wordCount) {
        System.out.println("Word in file is - " + wordCount);
    }

    private static void checkArgumentCorrectness(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Wrong argument.Enter java FileAnalyzer /path to file/ /search word/");
        }
    }

    private static String getPath(String path){
        File pathToFile = new File(path);
        if(pathToFile.isDirectory()){
            return path;
        } else{
            throw new IllegalArgumentException("Please make sure that you enter directory");
        }
    }
}
