package com.kopylov.iostudy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileManagerTest {

    @BeforeEach
    public void before() throws IOException {
        new File("src/test/resources/EmptyFolder").mkdirs();
        new File("src/test/resources/Test2").mkdirs();
        new File("src/test/resources/Test2/Test3").mkdirs();
        new File("src/test/resources/Test2/Test1.txt").createNewFile();
        new File("src/test/resources/Test2/Test3/Test3.txt").createNewFile();
        new File("src/test/resources/Test2/Test2.txt").createNewFile();
        new FileOutputStream("src/test/resources/Test1.txt").write("Hello Java !".getBytes());
    }

    @DisplayName("Count all files in directory along the specified path ")
    @Test
    public void testCountFile() {
        int expectedCount = 4;
        int actualCount = FileManager.countFiles("src/test/resources/");
        assertEquals(expectedCount, actualCount);
    }

    @DisplayName("Throw exception if the method is called on a non-existent folder ")
    @Test
    public void testCountFileIfFolderNotExistAndThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            FileManager.countFiles("src/test/resources/NonExistFolder");
        });
    }

    @DisplayName("Count all directories along the specified path")
    @Test
    public void testCountDirs() {
        int expectedCount = 3;
        int actualCount = FileManager.countDirs("src/test/resources/");
        assertEquals(expectedCount, actualCount);
    }

    @DisplayName("Count all directories in empty folder")
    @Test
    public void testCountDirsInEmptyFolder() {
        int expectedCount = 0;
        assertEquals(expectedCount, FileManager.countDirs("src/test/resources/EmptyFolder"));
    }

    @DisplayName("Throw exception if method is called  on a non-existent folder ")
    @Test
    public void testCountDirIfFolderNotExistAndThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            FileManager.countDirs("src/test/resources/NonExistFolder");
        });
    }

    @DisplayName("Copy the file to another directory and check if the copy is correct ")
    @Test
    public void testCopyFileAndCheckCorrectnessOfInputStream() throws IOException {
        FileManager.copy("src/test/resources/Test1.txt", "src/test/resources/Test2/Test1.txt");
        File file = new File("src/test/resources/Test2/Test1.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        int fileLength = (int) file.length();
        byte[] contentArray = new byte[fileLength];
        fileInputStream.read(contentArray);
        assertEquals("Hello Java !", new String(contentArray));
        fileInputStream.close();
    }

    @DisplayName("Throw exception if method is called with wrong parameters")
    @Test
    public void testCallMethodCopyFileWithWrongParameters() {
        assertThrows(IllegalArgumentException.class, () -> {
            FileManager.copy("Text.txt", "test.txt");
        });
    }

    @DisplayName("Move the file to another directory and check if the move is correct ")
    @Test
    public void testMoveFile() throws IOException {
        FileManager.move("src/test/resources/Test1.txt", "src/test/resources/Test2");
        File file = new File("src/test/resources/Test1.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        int fileLength = (int) file.length();
        byte[] contentArray = new byte[fileLength];
        fileInputStream.read(contentArray);
        assertEquals("Hello Java !", new String(contentArray));
    }

    @AfterEach
    public void after() {
        new File("src/test/resources/Test2/Test1.txt").delete();
        new File("src/test/resources/Test2/Test3/Test3.txt").delete();
        new File("src/test/resources/Test2/Test2.txt").delete();
        new File("src/test/resources/Test1.txt").delete();
        new File("src/test/resources/Test2/Test3").delete();
        new File("src/test/resources/Test2").delete();
        new File("src/test/resources/EmptyFolder").delete();
    }
}
