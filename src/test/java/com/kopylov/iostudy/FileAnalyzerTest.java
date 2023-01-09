package com.kopylov.iostudy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileAnalyzerTest {

    private final FileAnalyzer analyzer = new FileAnalyzer();

    @DisplayName("Add Content And Divides It Into Sentences")
    @Test
    public void whenAddContentAndSplitIntoSentences_thenReturnDividedSentences() {
        List<String> dividedSentences = analyzer.splitIntoSentences("Hello World!Hello Java.Some duck?");
        String expectedFirst = "Hello World!";
        String expectedSecond = "Hello Java.";
        String expectedThird = "Some duck?";

        assertEquals(expectedFirst, dividedSentences.get(0));
        assertEquals(expectedSecond, dividedSentences.get(1));
        assertEquals(expectedThird, dividedSentences.get(2));
    }

    @DisplayName("Add Divided Sentences Return Count Sentences With Word")
    @Test
    public void whenAddDividedSentences_thenCountWordMentionsReturnCorrectCountSentencesWithWord() {
        List<String> sentencesWithWord = new ArrayList<>();
        sentencesWithWord.add("Hello World");
        sentencesWithWord.add("Hello Java");
        sentencesWithWord.add("Hello Hello");
        sentencesWithWord.add("duck");
        int expectedCount = 4;
        int actualCount = analyzer.countWordMentions(sentencesWithWord, "Hello");
        assertEquals(expectedCount, actualCount);

    }

    @DisplayName("Add Content Return Sentence With Word")
    @Test
    public void whenAddContentDividedSentences_thenReturnSentenceWithWord() {
        String word = "duck";
        String content = "Hello World!Hello Java.Some duck?";
        List<String> dividedSentences = analyzer.filter(analyzer.splitIntoSentences(content), word);

        assertEquals("Some duck?", dividedSentences.get(0));
    }
}
