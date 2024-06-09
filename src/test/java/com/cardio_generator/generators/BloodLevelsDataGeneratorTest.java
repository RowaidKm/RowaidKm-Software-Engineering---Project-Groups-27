package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link BloodLevelsDataGenerator} class.
 */
class BloodLevelsDataGeneratorTest {

    private BloodLevelsDataGenerator bloodLevelsDataGenerator;
    private TestOutputStrategy testOutputStrategy;
    private static final int PATIENT_COUNT = 10;

    /**
     * Sets up the test environment before each test.
     * Initializes the BloodLevelsDataGenerator with a specified number of patients.
     * Initializes the TestOutputStrategy to capture output for verification.
     */
    @BeforeEach
    void setUp() {
        bloodLevelsDataGenerator = new BloodLevelsDataGenerator(PATIENT_COUNT);
        testOutputStrategy = new TestOutputStrategy();
    }

    /**
     * Tests the generate method of the BloodLevelsDataGenerator.
     * Verifies that the generated blood levels data is output correctly.
     */
    @Test
    void testGenerate() {
        int patientId = 1;
        bloodLevelsDataGenerator.generate(patientId, testOutputStrategy);

        List<String> output = testOutputStrategy.getOutput();
        assertEquals(3, output.size(), "Expected output to contain three entries for cholesterol, white blood cells, and red blood cells.");

        assertTrue(output.get(0).contains("Cholesterol"), "Expected output to contain cholesterol data.");
        assertTrue(output.get(1).contains("WhiteBloodCells"), "Expected output to contain white blood cells data.");
        assertTrue(output.get(2).contains("RedBloodCells"), "Expected output to contain red blood cells data.");
    }

    /**
     * A simple OutputStrategy implementation for testing purposes.
     * Captures the output for verification.
     */
    private static class TestOutputStrategy implements OutputStrategy {
        private final List<String> output = new ArrayList<>();

        @Override
        public void output(int patientId, long timestamp, String label, String data) {
            output.add(label + "," + data);
        }

        public List<String> getOutput() {
            return output;
        }
    }
}
