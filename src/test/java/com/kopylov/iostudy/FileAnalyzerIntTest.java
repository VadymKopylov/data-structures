package com.kopylov.iostudy;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static com.kopylov.iostudy.FileAnalyzer.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileAnalyzerIntTest {

    private final FileAnalyzer analyzer = new FileAnalyzer();

    @BeforeEach
    public void before() throws IOException {
        new File("src/test/resources/TestFolder").mkdirs();
        new File("src/test/resources/TestFolder/Text.txt").createNewFile();
        new File("src/test/resources/TestFolder/Text2.txt").createNewFile();
        new FileOutputStream("src/test/resources/TestFolder/Text.txt").write("Hello World!Hello Java.Hello duck?".getBytes());
    }

    @DisplayName("Read Content From File")
    @Test
    public void testReadContentFromFile() throws IOException {
        String content = analyzer.readContent("src/test/resources/TestFolder/Text.txt");
        String expectedContent = "Hello World!Hello Java.Hello duck?";

        assertEquals(expectedContent, content);
    }

    @DisplayName("Read Content From Empty File")
    @Test
    public void testReadContentFromEmptyFile() throws IOException {
        String content = analyzer.readContent("src/test/resources/TestFolder/Text2.txt");
        String expectedContent = "";

        assertEquals(expectedContent, content);
    }

    @DisplayName("Split Content Into Sentences")
    @Test
    public void testSplitIntoSentencesContentFromFile() throws IOException {
        String expectedSentences = "Hello World!, Hello Java., Hello duck?";
        String content = analyzer.readContent("src/test/resources/TestFolder/Text.txt");
        List<String> sentences = analyzer.splitIntoSentences(content);
        String result = String.join(", ", sentences);
        assertEquals(expectedSentences, result);
    }

    @DisplayName("Filter Content Into Sentences With Word")
    @Test
    public void testFilterSentencesWithDeclaredWordFromFile() throws IOException {
        String expectedSentences = "Hello World!";
        String content = analyzer.readContent("src/test/resources/TestFolder/Text.txt");
        List<String> sentences = analyzer.splitIntoSentences(content);
        List<String> filteredSentences = analyzer.filter(sentences, "World");
        String result = String.join(", ", filteredSentences);
        assertEquals(expectedSentences, result);
    }

    @DisplayName("Count Word Mentions From Content ")
    @Test
    public void testCountWordMentionsFromFile() throws IOException {
        int expectedCount = 1;
        String content = analyzer.readContent("src/test/resources/TestFolder/Text.txt");
        List<String> sentences = analyzer.splitIntoSentences(content);
        List<String> filteredSentences = analyzer.filter(sentences, "World");
        int count = analyzer.countWordMentions(filteredSentences, "World");
        assertEquals(expectedCount, count);
    }

    @AfterEach
    public void after() {
        new File("src/test/resources/TestFolder/Text.txt").delete();
        new File("src/test/resources/TestFolder/Text2.txt").delete();
        new File("src/test/resources/TestFolder").delete();
    }
}
