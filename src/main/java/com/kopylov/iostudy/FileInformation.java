package com.kopylov.iostudy;

import java.util.List;

public class FileInformation {
    int wordCount;
    List<String> sentencesWithWord;

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


