package com.data_management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Patient} class.
 */
class PatientTest {

    private Patient patient;

    /**
     * Sets up a new Patient instance before each test.
     */
    @BeforeEach
    void setUp() {
        patient = new Patient(1);
    }

    /**
     * Tests the constructor of the Patient class.
     * Ensures the patient ID is set correctly and the list of records is initialized as empty.
     */
    @Test
    void testConstructor() {
        assertEquals(1, patient.getPatientId());
        assertTrue(patient.getPatientRecords().isEmpty());
    }

    /**
     * Tests the addRecord method.
     * Adds a record and verifies that it is correctly added to the patient's list of records.
     */
    @Test
    void testAddRecord() {
        long timestamp = System.currentTimeMillis();
        patient.addRecord(120.5, "BloodPressure", timestamp);

        List<PatientRecord> records = patient.getPatientRecords();
        assertEquals(1, records.size());

        PatientRecord record = records.get(0);
        assertEquals(1, record.getPatientId());
        assertEquals(120.5, record.getMeasurementValue());
        assertEquals("BloodPressure", record.getRecordType());
        assertEquals(timestamp, record.getTimestamp());
    }

    /**
     * Tests the getRecords method for a specified time range.
     * Adds records with different timestamps and retrieves those within a specific range.
     */
    @Test
    void testGetRecordsWithinRange() {
        long currentTime = System.currentTimeMillis();
        long oneHourAgo = currentTime - 3600000;
        long twoHoursAgo = currentTime - 7200000;

        patient.addRecord(70, "HeartRate", twoHoursAgo);
        patient.addRecord(120, "BloodPressure", oneHourAgo);
        patient.addRecord(98, "OxygenSaturation", currentTime);

        List<PatientRecord> records = patient.getRecords(oneHourAgo, currentTime);
        assertEquals(2, records.size());

        assertEquals("BloodPressure", records.get(0).getRecordType());
        assertEquals("OxygenSaturation", records.get(1).getRecordType());
    }

    /**
     * Tests the getPatientRecords method.
     * Adds records and retrieves all records to ensure they are returned correctly.
     */
    @Test
    void testGetPatientRecords() {
        long timestamp1 = System.currentTimeMillis() - 1000;
        long timestamp2 = System.currentTimeMillis();
        
        patient.addRecord(80, "HeartRate", timestamp1);
        patient.addRecord(130, "BloodPressure", timestamp2);

        List<PatientRecord> records = patient.getPatientRecords();
        assertEquals(2, records.size());

        assertEquals("HeartRate", records.get(0).getRecordType());
        assertEquals("BloodPressure", records.get(1).getRecordType());
    }
}
