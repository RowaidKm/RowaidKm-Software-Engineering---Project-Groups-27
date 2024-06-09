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
 * Unit tests for the {@link ECGDataChecker} class.
 */
class ECGDataCheckerTest {

    private ECGDataChecker ecgDataChecker;
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
        ecgDataChecker = new ECGDataChecker(alertManager);

        patient = new Patient(1);
        records = new ArrayList<>();

        // Adding records to the patient's record list
        records.add(new PatientRecord(1, 55, "ECG", System.currentTimeMillis() - 10000)); // Normal Heart Rate
        records.add(new PatientRecord(1, 45, "ECG", System.currentTimeMillis() - 8000)); // Low Heart Rate
        records.add(new PatientRecord(1, 105, "ECG", System.currentTimeMillis() - 6000)); // High Heart Rate
        records.add(new PatientRecord(1, 85, "ECG", System.currentTimeMillis() - 4000)); // Normal Heart Rate
        records.add(new PatientRecord(1, 110, "ECG", System.currentTimeMillis() - 2000)); // Rapid Fluctuation

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
     * Tests the checkECGData method of the ECGDataChecker.
     */
    @Test
    void testCheckECGData() {
        ecgDataChecker.checkECGData(patient);

        String output = outContent.toString().trim();

        assertTrue(output.contains("Alert Triggered: Abnormal Heart Rate Alert for Patient ID: 1"),
                "Expected abnormal heart rate alert to be triggered.");
        assertTrue(output.contains("Alert Triggered: Irregular Beat Alert for Patient ID: 1"),
                "Expected irregular beat alert to be triggered.");
    }
}
