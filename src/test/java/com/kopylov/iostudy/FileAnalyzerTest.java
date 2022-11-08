package com.kopylov.iostudy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.kopylov.iostudy.FileAnalyzer.splitIntoSentences;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileAnalyzerTest {

    @DisplayName("Add Content And Divides It Into Sentences")
    @Test
    public void whenAddContentAndSplitIntoSentences_thenReturnDividedSentences() {
        List<String> dividedSentences = splitIntoSentences("Hello World!Hello Java.Some duck?");
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
        sentencesWithWord.add("duck");
        int expectedCount = 2;
        int actualCount = FileAnalyzer.countWordMentions(sentencesWithWord, "Hello");
        assertEquals(expectedCount, actualCount);

    }

    @DisplayName("Add Content Return Sentence With Word")
    @Test
    public void whenAddContentDividedSentences_thenReturnSentenceWithWord() {
        String word = "Java";
        String content = "Hello World!Hello Java.Some duck?";
        List<String> dividedSentences = FileAnalyzer.filter(splitIntoSentences(content), word);

        assertEquals("Hello Java.", dividedSentences.get(0));
    }

    @DisplayName("Throw Exception If Content Not Contain The Required Word")
    @Test
    public void whenAddContentDividedSentencesTryReturnSentenceDoesNotExist_thenReturnNoSuchElementException() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            String word = "London";
            String content = "Hello World!Hello Java.Some duck?";
            FileAnalyzer.filter(splitIntoSentences(content), word);
        });
    }
}
