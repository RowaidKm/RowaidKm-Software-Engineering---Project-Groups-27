package com.data_management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link PatientRecord} class.
 */
class PatientRecordTest {

    private PatientRecord patientRecord;
    private int patientId = 1;
    private String recordType = "BloodPressure";
    private double measurementValue = 120.5;
    private long timestamp = System.currentTimeMillis();

    /**
     * Sets up a new PatientRecord instance before each test.
     */
    @BeforeEach
    void setUp() {
        patientRecord = new PatientRecord(patientId, measurementValue, recordType, timestamp);
    }

    /**
     * Tests the constructor of the PatientRecord class.
     * Ensures the fields are set correctly.
     */
    @Test
    void testConstructor() {
        assertEquals(patientId, patientRecord.getPatientId());
        assertEquals(recordType, patientRecord.getRecordType());
        assertEquals(measurementValue, patientRecord.getMeasurementValue());
        assertEquals(timestamp, patientRecord.getTimestamp());
    }

    /**
     * Tests the getPatientId method.
     * Ensures the method returns the correct patient ID.
     */
    @Test
    void testGetPatientId() {
        assertEquals(patientId, patientRecord.getPatientId());
    }

    /**
     * Tests the getRecordType method.
     * Ensures the method returns the correct record type.
     */
    @Test
    void testGetRecordType() {
        assertEquals(recordType, patientRecord.getRecordType());
    }

    /**
     * Tests the getMeasurementValue method.
     * Ensures the method returns the correct measurement value.
     */
    @Test
    void testGetMeasurementValue() {
        assertEquals(measurementValue, patientRecord.getMeasurementValue());
    }

    /**
     * Tests the getTimestamp method.
     * Ensures the method returns the correct timestamp.
     */
    @Test
    void testGetTimestamp() {
        assertEquals(timestamp, patientRecord.getTimestamp());
    }
}
