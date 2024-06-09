package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link BloodPressureDataGenerator} class.
 */
class BloodPressureDataGeneratorTest {

    private BloodPressureDataGenerator bloodPressureDataGenerator;
    private TestOutputStrategy testOutputStrategy;
    private static final int PATIENT_COUNT = 10;

    /**
     * Sets up the test environment before each test.
     * Initializes the BloodPressureDataGenerator with a specified number of patients.
     * Initializes the TestOutputStrategy to capture output for verification.
     */
    @BeforeEach
    void setUp() {
        bloodPressureDataGenerator = new BloodPressureDataGenerator(PATIENT_COUNT);
        testOutputStrategy = new TestOutputStrategy();
    }

    /**
     * Tests the generate method of the BloodPressureDataGenerator.
     * Verifies that the generated blood pressure data is output correctly.
     */
    @Test
    void testGenerate() {
        int patientId = 1;
        bloodPressureDataGenerator.generate(patientId, testOutputStrategy);

        List<String> output = testOutputStrategy.getOutput();
        assertEquals(2, output.size(), "Expected output to contain two entries for systolic and diastolic pressure.");

        assertTrue(output.get(0).contains("SystolicPressure"), "Expected output to contain systolic pressure data.");
        assertTrue(output.get(1).contains("DiastolicPressure"), "Expected output to contain diastolic pressure data.");
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
