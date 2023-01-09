package com.kopylov.iostudy;

import java.util.List;

public class FileInformation {
    private final int wordCount;
    private final List<String> sentencesWithWord;

    public FileInformation(int wordCount, List<String> sentencesWithWord) {
        this.wordCount = wordCount;
        this.sentencesWithWord = sentencesWithWord;
    }
    public int getWordCount() {
        return wordCount;
    }

    public List<String> getSentencesWithWord() {
        return sentencesWithWord;
    }





}


