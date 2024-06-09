package com.alerts;

import com.data_management.Patient;
import com.data_management.PatientRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link CombinedAlertChecker} class.
 */
class CombinedAlertCheckerTest {

    private CombinedAlertChecker combinedAlertChecker;
    private AlertManager alertManager;
    private Patient patient;
    private List<PatientRecord> records;

    // For capturing System.out
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        // Redirect System.out to capture output
        System.setOut(new PrintStream(outContent));

        alertManager = new AlertManager();
        combinedAlertChecker = new CombinedAlertChecker(alertManager);

        patient = new Patient(1);
        records = new ArrayList<>();

        // Adding records to the patient's record list
        records.add(new PatientRecord(1, 85, "BloodPressure", System.currentTimeMillis() - 300000));
        records.add(new PatientRecord(1, 88, "BloodSaturation", System.currentTimeMillis() - 200000));
        records.add(new PatientRecord(1, 120, "BloodPressure", System.currentTimeMillis() - 100000));

        // Setting the records to the patient
        for (PatientRecord record : records) {
            patient.addRecord(record.getMeasurementValue(), record.getRecordType(), record.getTimestamp());
        }
    }

    /**
     * Resets System.out after each test.
     */
    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        // Reset System.out
        System.setOut(originalOut);
    }

    /**
     * Tests the checkCombinedAlerts method of the CombinedAlertChecker.
     */
    @Test
    void testCheckCombinedAlerts() {
        combinedAlertChecker.checkCombinedAlerts(patient);

        String output = outContent.toString().trim();
        assertTrue(output.contains("Alert Triggered: Hypotensive Hypoxemia Alert for Patient ID: 1"),
                "Expected combined alert to be triggered.");
    }
}
