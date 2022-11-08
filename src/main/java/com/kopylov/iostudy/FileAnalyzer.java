package com.kopylov.iostudy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

public class FileAnalyzer {

    private static final Pattern SENTENCE_PATTERN = Pattern.compile("((?<=[.?!]))");

    public FileInformation analyze(String path, String word) throws IOException {
        String content = readContent(path);
        List<String> sentences = splitIntoSentences(content);
        List<String> sentencesWithWord = filter(sentences, word);
        int count = countWordMentions(sentencesWithWord, word);
        return new FileInformation(count, sentencesWithWord);
    }

    static String readContent(String path) throws IOException {
        File pathToFile = new File(path);
        InputStream inputStream = new FileInputStream(pathToFile);
        int fileLength = (int) pathToFile.length();
        byte[] contentArray = new byte[fileLength];
        inputStream.read(contentArray);
        return new String(contentArray);
    }

    static List<String> splitIntoSentences(String content) {
        String[] sentences = SENTENCE_PATTERN.split(content);
        List<String> dividedSentences = new ArrayList<>();
        for (String sentence : sentences) {
            dividedSentences.add(sentence);
        }
        return dividedSentences;
    }

    static List<String> filter(List<String> sentences, String word) {
        List<String> sentencesWithWord = new ArrayList<>();
        for (String sentence : sentences) {
            if (sentence.contains(word)) {
                sentencesWithWord.add(sentence);
            }
        }
        if (sentencesWithWord.isEmpty()) {
            throw new NoSuchElementException("No sentences with this word");
        }
        return sentencesWithWord;
    }

    static int countWordMentions(List<String> sentencesWithWord, String word) {
        int counter = 0;
        for (String sentence : sentencesWithWord) {
            int currentIndex;
            int index = 0;
            while ((currentIndex = sentence.indexOf(word, index)) != -1) {
                counter++;
                index = currentIndex + 1;
            }
        }
        return counter;
    }
}

