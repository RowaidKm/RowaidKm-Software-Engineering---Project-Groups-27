package com.data_management;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Unit tests for the {@link DataStorage} class.
 */
class DataStorageTest {

    private DataStorage storage;

    @BeforeEach
    void setUp() {
        storage = new DataStorage();
    }

    /**
     * Tests the addPatientData and getRecords methods of the DataStorage class.
     * Verifies that data is correctly added and retrieved.
     */
    @Test
    void testAddAndGetRecords() {
        storage.addPatientData(1, 100.0, "WhiteBloodCells", 1714376789050L);
        storage.addPatientData(1, 200.0, "WhiteBloodCells", 1714376789051L);

        List<PatientRecord> records = storage.getRecords(1, 1714376789050L, 1714376789051L);
        assertEquals(2, records.size()); // Check if two records are retrieved
        assertEquals(100.0, records.get(0).getMeasurementValue()); // Validate first record
        assertEquals(200.0, records.get(1).getMeasurementValue()); // Validate second record
    }

    /**
     * Tests the getRecords method with a time range that excludes all records.
     * Verifies that no records are retrieved.
     */
    @Test
    void testGetRecordsWithNoMatches() {
        storage.addPatientData(1, 100.0, "WhiteBloodCells", 1714376789050L);
        storage.addPatientData(1, 200.0, "WhiteBloodCells", 1714376789051L);

        List<PatientRecord> records = storage.getRecords(1, 1714376789052L, 1714376789053L);
        assertEquals(0, records.size()); // Check if no records are retrieved
    }

    /**
     * Tests the getRecords method with a different patient ID.
     * Verifies that no records are retrieved for the different patient ID.
     */
    @Test
    void testGetRecordsWithDifferentPatientId() {
        storage.addPatientData(1, 100.0, "WhiteBloodCells", 1714376789050L);
        storage.addPatientData(2, 200.0, "WhiteBloodCells", 1714376789051L);

        List<PatientRecord> records = storage.getRecords(1, 1714376789050L, 1714376789051L);
        assertEquals(1, records.size()); // Check if one record is retrieved
        assertEquals(100.0, records.get(0).getMeasurementValue()); // Validate the record
    }

    /**
     * Tests the getAllPatients method.
     * Verifies that all patients are correctly retrieved.
     */
    @Test
    void testGetAllPatients() {
        storage.addPatientData(1, 100.0, "WhiteBloodCells", 1714376789050L);
        storage.addPatientData(2, 200.0, "BloodPressure", 1714376789051L);

        List<Patient> patients = storage.getAllPatients();
        assertEquals(2, patients.size()); // Check if two patients are retrieved
    }

    /**
     * Tests the main method of DataStorage.
     * Verifies that it runs without throwing any exceptions.
     */
    @Test
    void testMain() {
        String[] args = {};
        assertDoesNotThrow(() -> DataStorage.main(args));
    }
}
