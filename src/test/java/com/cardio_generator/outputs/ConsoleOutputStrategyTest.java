package com.cardio_generator.outputs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link ConsoleOutputStrategy} class.
 */
class ConsoleOutputStrategyTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Sets up the test environment before each test.
     * Redirects System.out to capture console output.
     */
    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Resets the System.out after each test.
     */
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        outContent.reset();
    }

    /**
     * Tests the output method of the ConsoleOutputStrategy.
     * Verifies that the correct data is printed to the console.
     */
    @Test
    void testOutput() {
        ConsoleOutputStrategy consoleOutputStrategy = new ConsoleOutputStrategy();
        int patientId = 1;
        long timestamp = 123456789L;
        String label = "ECG";
        String data = "85 bpm";

        consoleOutputStrategy.output(patientId, timestamp, label, data);

        String expectedOutput = String.format("Patient ID: %d, Timestamp: %d, Label: %s, Data: %s", patientId, timestamp, label, data);
        assertEquals(expectedOutput, outContent.toString().trim(), "Expected output to match the formatted data.");
    }
}
