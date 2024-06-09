package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link BloodSaturationDataGenerator} class.
 */
class BloodSaturationDataGeneratorTest {

    private BloodSaturationDataGenerator bloodSaturationDataGenerator;
    private TestOutputStrategy testOutputStrategy;
    private static final int PATIENT_COUNT = 10;

    /**
     * Sets up the test environment before each test.
     * Initializes the BloodSaturationDataGenerator with a specified number of patients.
     * Initializes the TestOutputStrategy to capture output for verification.
     */
    @BeforeEach
    void setUp() {
        bloodSaturationDataGenerator = new BloodSaturationDataGenerator(PATIENT_COUNT);
        testOutputStrategy = new TestOutputStrategy();
    }

    /**
     * Tests the generate method of the BloodSaturationDataGenerator.
     * Verifies that the generated blood saturation data is output correctly.
     */
    @Test
    void testGenerate() {
        int patientId = 1;
        bloodSaturationDataGenerator.generate(patientId, testOutputStrategy);

        List<String> output = testOutputStrategy.getOutput();
        assertEquals(1, output.size(), "Expected output to contain one entry for blood saturation.");

        assertTrue(output.get(0).contains("Saturation"), "Expected output to contain blood saturation data.");
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
