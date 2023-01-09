package com.kopylov.iostudy;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FileAnalyzer {

    private static final Pattern SENTENCE_PATTERN = Pattern.compile("((?<=[.?!]))");

     public FileInformation analyze(String path, String word) throws IOException {
        String content = readContent(path);
        List<String> sentences = splitIntoSentences(content);
        List<String> sentencesWithWord = filter(sentences, word);
        int count = countWordMentions(sentencesWithWord, word);
        return new FileInformation(count, sentencesWithWord);
    }

     String readContent(String path) throws IOException {
        File pathToFile = new File(path);
        InputStream inputStream = new FileInputStream(pathToFile);
        int fileLength = (int) pathToFile.length();
        byte[] contentArray = new byte[fileLength];
        inputStream.read(contentArray);
        return new String(contentArray);
    }

     List<String> splitIntoSentences(String content) {
        String[] sentences = SENTENCE_PATTERN.split(content);
        return Arrays.stream(sentences).toList();
    }

     List<String> filter(List<String> sentences, String word) {
        return sentences.stream()
                .filter(String -> String.contains(word))
                .toList();
    }

     int countWordMentions(List<String> sentencesWithWord, String word) {
        List<String> listOfWords = sentencesWithWord.stream()
                .flatMap(String -> Stream.of(String.split(" ")))
                .toList();
        return (int) listOfWords.stream()
                .filter(String -> String.contains(word))
                .count();
    }
}

