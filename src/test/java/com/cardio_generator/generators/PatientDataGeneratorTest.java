package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link ECGDataGenerator} class.
 */
class PatientDataGeneratorTest {

    private ECGDataGenerator ecgDataGenerator;
    private TestOutputStrategy testOutputStrategy;
    private static final int PATIENT_COUNT = 10;
    private static final int PATIENT_ID = 1;

    /**
     * Sets up the test environment before each test.
     * Initializes the ECGDataGenerator with a specified number of patients.
     * Initializes the TestOutputStrategy to capture output for verification.
     */
    @BeforeEach
    void setUp() {
        ecgDataGenerator = new ECGDataGenerator(PATIENT_COUNT);
        testOutputStrategy = new TestOutputStrategy();
    }

    /**
     * Tests the generate method of the ECGDataGenerator.
     * Verifies that the generated ECG data is output correctly.
     */
    @Test
    void testGenerate() {
        ecgDataGenerator.generate(PATIENT_ID, testOutputStrategy);

        List<String> output = testOutputStrategy.getOutput();
        assertEquals(1, output.size(), "Expected output to contain one entry for ECG data.");
        assertTrue(output.get(0).contains("ECG"), "Expected output to contain ECG data.");
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
