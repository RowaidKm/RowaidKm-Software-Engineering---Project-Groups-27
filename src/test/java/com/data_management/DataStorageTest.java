package com.data_management;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Unit tests for the {@link DataStorage} class.
 */
class DataStorageTest {

    /**
     * Tests the addPatientData and getRecords methods of the DataStorage class.
     * Verifies that patient data can be added and retrieved correctly.
     */
    @Test
    void testAddAndGetRecords() {
        // Create a DataStorage instance
        DataStorage storage = new DataStorage();
        
        // Add patient data
        storage.addPatientData(1, 100.0, "WhiteBloodCells", 1714376789050L);
        storage.addPatientData(1, 200.0, "WhiteBloodCells", 1714376789051L);

        // Retrieve records within the specified time range
        List<PatientRecord> records = storage.getRecords(1, 1714376789050L, 1714376789051L);
        
        // Verify that two records are retrieved
        assertEquals(2, records.size(), "Expected to retrieve 2 records");

        // Validate the first record's measurement value
        assertEquals(100.0, records.get(0).getMeasurementValue(), "Expected measurement value to be 100.0");
    }
}
