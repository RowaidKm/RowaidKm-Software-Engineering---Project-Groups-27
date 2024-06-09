package com.alerts;

import com.data_management.Patient;
import com.data_management.PatientRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link BloodSaturationChecker} class.
 */
class BloodSaturationCheckerTest {

    private BloodSaturationChecker bloodSaturationChecker;
    private MockAlertManager alertManager;
    private Patient patient;
    private List<PatientRecord> records;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        alertManager = new MockAlertManager();
        bloodSaturationChecker = new BloodSaturationChecker(alertManager);

        patient = new Patient(1);
        records = new ArrayList<>();

        // Adding records to the patient's record list
        records.add(new PatientRecord(1, 95, "BloodSaturation", System.currentTimeMillis() - 600000));
        records.add(new PatientRecord(1, 94, "BloodSaturation", System.currentTimeMillis() - 300000));
        records.add(new PatientRecord(1, 89, "BloodSaturation", System.currentTimeMillis() - 100000));

        // Setting the records to the patient
        for (PatientRecord record : records) {
            patient.addRecord(record.getMeasurementValue(), record.getRecordType(), record.getTimestamp());
        }
    }

    /**
     * Tests the checkBloodSaturationLevels method for low blood saturation levels.
     */
    @Test
    void testCheckLowBloodSaturationLevels() {
        bloodSaturationChecker.checkBloodSaturationLevels(patient);

        assertTrue(alertManager.isAlertTriggered(), "Low Blood Saturation Alert should be triggered");
    }

    /**
     * Tests the checkBloodSaturationLevels method for rapid drop in blood saturation levels.
     */
    @Test
    void testCheckRapidDropInBloodSaturationLevels() {
        // Adding a record to simulate a rapid drop
        patient.addRecord(83, "BloodSaturation", System.currentTimeMillis() - 50000);

        bloodSaturationChecker.checkBloodSaturationLevels(patient);

        assertTrue(alertManager.isAlertTriggered(), "Rapid Drop in Blood Saturation Alert should be triggered");
    }
}
