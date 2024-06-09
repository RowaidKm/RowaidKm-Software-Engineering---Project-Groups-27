package com.cardio_generator.outputs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link FileOutputStrategy} class.
 */
class FileOutputStrategyTest {

    private Path tempDirectory;

    /**
     * Sets up the test environment before each test.
     * Creates a temporary directory for file output.
     * 
     * @throws IOException if an I/O error occurs
     */
    @BeforeEach
    void setUp() throws IOException {
        tempDirectory = Files.createTempDirectory("file_output_test");
    }

    /**
     * Cleans up the test environment after each test.
     * Deletes the temporary directory and its contents.
     * 
     * @throws IOException if an I/O error occurs
     */
    @AfterEach
    void tearDown() throws IOException {
        Files.walk(tempDirectory)
             .map(Path::toFile)
             .forEach(file -> {
                 if (!file.delete()) {
                     file.deleteOnExit();
                 }
             });
    }

    /**
     * Tests the output method of the FileOutputStrategy.
     * Verifies that the correct data is written to the file.
     * 
     * @throws IOException if an I/O error occurs
     */
    @Test
    void testOutput() throws IOException {
        String baseDirectory = tempDirectory.toString();
        FileOutputStrategy fileOutputStrategy = new FileOutputStrategy(baseDirectory);
        int patientId = 1;
        long timestamp = 123456789L;
        String label = "ECG";
        String data = "85 bpm";

        fileOutputStrategy.output(patientId, timestamp, label, data);

        Path filePath = Paths.get(baseDirectory, label + ".txt");
        assertTrue(Files.exists(filePath), "Expected file to be created.");

        List<String> lines = Files.readAllLines(filePath);
        assertEquals(1, lines.size(), "Expected one line in the file.");
        String expectedOutput = String.format("Patient ID: %d, Timestamp: %d, Label: %s, Data: %s", patientId, timestamp, label, data);
        assertEquals(expectedOutput, lines.get(0), "Expected output to match the formatted data.");
    }
}
