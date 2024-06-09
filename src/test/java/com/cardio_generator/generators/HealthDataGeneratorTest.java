package com.cardio_generator.generators;

import com.data_management.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link HealthDataGenerator} class.
 */
class HealthDataGeneratorTest {

    private HealthDataGenerator healthDataGenerator;
    private Patient patient;

    // Stream to capture System.out
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Sets up the test environment before each test.
     * Initializes the HealthDataGenerator and a Patient object.
     * Redirects System.out to capture output.
     */
    @BeforeEach
    void setUp() {
        healthDataGenerator = new HealthDataGenerator();
        patient = new Patient(1); // Initialize the patient with ID 1

        // Redirect System.out to capture output
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Resets System.out after each test.
     */
    @BeforeEach
    void tearDown() {
        // Reset System.out
        System.setOut(originalOut);
    }

    /**
     * Tests the generateManualAlert method of the HealthDataGenerator.
     * Verifies that the generated alert message has the correct patient ID.
     */
    @Test
    void testGenerateManualAlert() {
        healthDataGenerator.generateManualAlert(patient);

        String expectedMessage = "Alert Manually Triggered For Patient ID :" + patient.getPatientId();
        String actualMessage = outContent.toString().trim();

        assertEquals(expectedMessage, actualMessage, "Expected the alert to have the correct patient ID.");
    }
}
