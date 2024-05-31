package com.data_management;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileDataReader implements DataReader {
    
    private DataStorage dataStorage;

    public FileDataReader(DataStorage storage){
        this.dataStorage=storage;
    }

    @Override
    public void readData(String outputDir) throws IOException {
        Path path = Paths.get(outputDir);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(this::processLine);
        }
    }

    void processLine(String line) {
        // Assuming the line contains comma-separated values like "patientId,timestamp,value,type"
        String[] parts = line.split(",");
        if (parts.length == 4) {
            int patientId = Integer.parseInt(parts[0]);
            long timestamp = Long.parseLong(parts[1]);
            double measurementValue = Double.parseDouble(parts[2]);
            String recordType = parts[3];
            //  to handle the parsed data and store it
            dataStorage.addPatientData(patientId,measurementValue,recordType,timestamp);
        }
    }

}
