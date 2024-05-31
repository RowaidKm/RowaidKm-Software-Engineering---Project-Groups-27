package com.data_management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


 class FileDataReaderTest {

    

    private DataStorage dataStorage;
    private FileDataReader fileDataReader;

    @BeforeEach
    public void setUp() {
        dataStorage = new DataStorage();
        fileDataReader = new FileDataReader(dataStorage);
    }

    @Test
    public void testReadData() throws IOException {
        // Create a temporary file with sample data
        Path tempFile = Files.createTempFile("testData", ".csv");
        Files.write(tempFile, "1,161803398874,98.6,temperature\n2,161803398875,120,pressure\n".getBytes());

        // Call the readData method and check that it does not throw any exceptions
        assertDoesNotThrow(() -> fileDataReader.readData(tempFile.toString()));

        // Verify that the dataStorage has stored the correct data within the time range
        List<String> expectedData = new ArrayList<>();
        expectedData.add("1,98.6,temperature,161803398874");
        expectedData.add("2,120.0,pressure,161803398875");

        long startTime = 161803398873L;
        long endTime = 161803398876L;
        List<PatientRecord> actualRecords = dataStorage.getRecords(1, startTime, endTime);
        
        assertEquals(1, actualRecords.size());
        assertEquals(98.6, actualRecords.get(0).getMeasurementValue());
        assertEquals("temperature", actualRecords.get(0).getRecordType());
        assertEquals(161803398874L, actualRecords.get(0).getTimestamp());

        // Clean up the temporary file
        Files.deleteIfExists(tempFile);
    }

    @Test
    public void testProcessLine() {
        String line = "1,161803398874,98.6,temperature";
        assertDoesNotThrow(() -> {
            fileDataReader.processLine(line);
        });

        // Verify that the dataStorage has stored the correct data within the time range
        long startTime = 161803398873L;
        long endTime = 161803398876L;
        List<PatientRecord> actualRecords = dataStorage.getRecords(1, startTime, endTime);
        
        assertEquals(1, actualRecords.size());
        assertEquals(98.6, actualRecords.get(0).getMeasurementValue());
        assertEquals("temperature", actualRecords.get(0).getRecordType());
        assertEquals(161803398874L, actualRecords.get(0).getTimestamp());
    }
}
