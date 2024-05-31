package com.alerts;

import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ECGDataChecker {
    private AlertManager alertManager;
    private Map<Integer, Integer> lastProcessedRecordIndexMap; // Map to track last processed record index for each patient

    private static final double MIN_HEART_RATE = 50; // bpm
    private static final double MAX_HEART_RATE = 100; // bpm
    private static final double RAPID_FLUCTUATION_THRESHOLD = 20; // bpm
    private static final long MAX_TIME_DIFFERENCE = 5 * 1000; // 5 seconds in milliseconds

    public ECGDataChecker(AlertManager alertManager) {
        this.alertManager = alertManager;
        this.lastProcessedRecordIndexMap = new HashMap<>();
    }

    public void checkECGData(Patient patient) {
        List<PatientRecord> records = patient.getPatientRecords();
        int patientId = patient.getPatientId();
        int lastProcessedIndex = getLastProcessedRecordIndex(patientId);

        PatientRecord previousECGRecord = null;

        for (int i = lastProcessedIndex; i < records.size(); i++) {
            PatientRecord current = records.get(i);
            if ("ECG".equals(current.getRecordType())) {
                double heartRate = current.getMeasurementValue();

                // Abnormal Heart Rate Alert
                if (heartRate < MIN_HEART_RATE || heartRate > MAX_HEART_RATE) {
                    alertManager.triggerAlert(new Alert(patient.getPatientId(), "Abnormal Heart Rate Alert", System.currentTimeMillis()));
                }

                // Irregular Beat Alert
                if (previousECGRecord != null) {
                    long timeDifference = current.getTimestamp() - previousECGRecord.getTimestamp();
                    double previousHeartRate = previousECGRecord.getMeasurementValue();

                    // Check for significant variations in heart rate within 5 seconds
                    if (timeDifference <= MAX_TIME_DIFFERENCE && Math.abs(heartRate - previousHeartRate) > RAPID_FLUCTUATION_THRESHOLD) {
                        alertManager.triggerAlert(new Alert(patient.getPatientId(), "Irregular Beat Alert", System.currentTimeMillis()));
                    }
                }

                previousECGRecord = current; // Update the previous ECG record to the current one
            }
            lastProcessedIndex = i + 1; // Update the last processed index to the next record
        }

        // Update the last processed record index in the map
        updateLastProcessedRecordIndex(patientId, lastProcessedIndex);
    }

    private int getLastProcessedRecordIndex(int patientId) {
        return lastProcessedRecordIndexMap.getOrDefault(patientId, 0);
    }

    private void updateLastProcessedRecordIndex(int patientId, int index) {
        lastProcessedRecordIndexMap.put(patientId, index);
    }
}
