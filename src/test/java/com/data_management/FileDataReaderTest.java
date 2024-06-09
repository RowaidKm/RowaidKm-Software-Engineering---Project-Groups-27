package com.data_management;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FileDataReaderTest {

    private DataStorage dataStorage;
    private FileDataReader fileDataReader;
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        dataStorage = new DataStorage();
        fileDataReader = new FileDataReader(dataStorage);

        // Create a temporary file with sample data
        tempFile = Files.createTempFile("testData", ".csv");
        Files.write(tempFile, "1,161803398874,98.6,temperature\n2,161803398875,120,pressure\n".getBytes());
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    void testReadData() throws IOException {
        // Call the readData method and check that it does not throw any exceptions
        assertDoesNotThrow(() -> fileDataReader.readData(tempFile.toString()));

        // Verify that the dataStorage has stored the correct data
        assertEquals(1, dataStorage.getRecords(1, 161803398874L, 161803398874L).size());
        assertEquals(98.6, dataStorage.getRecords(1, 161803398874L, 161803398874L).get(0).getMeasurementValue());

        assertEquals(1, dataStorage.getRecords(2, 161803398875L, 161803398875L).size());
        assertEquals(120, dataStorage.getRecords(2, 161803398875L, 161803398875L).get(0).getMeasurementValue());
    }

    @Test
    void testConnectToWebSocket() {
        // Implement this to test WebSocket connection
        // Note: This test requires a running WebSocket server for full validation
        assertDoesNotThrow(() -> fileDataReader.connectToWebSocket("ws://localhost:8080"));
    }
}
