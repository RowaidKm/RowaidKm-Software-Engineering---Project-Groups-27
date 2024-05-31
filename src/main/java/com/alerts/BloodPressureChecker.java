package com.alerts;

import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BloodPressureChecker {
    private AlertManager alertManager;
    private Map<Integer, Integer> lastProcessedRecordIndexMap; // Map to track last processed record index for each patient

    public BloodPressureChecker(AlertManager alertManager) {
        this.alertManager = alertManager;
        this.lastProcessedRecordIndexMap = new HashMap<>();
    }

    public void checkBloodPressureTrends(Patient patient) {
        List<PatientRecord> records = patient.getPatientRecords();
        int patientId = patient.getPatientId();
        int lastProcessedIndex = getLastProcessedRecordIndex(patientId);

        PatientRecord first = null;
        PatientRecord second = null;

        for (int i = lastProcessedIndex; i < records.size(); i++) {
            PatientRecord current = records.get(i);
            if ("BloodPressure".equals(current.getRecordType())) {
                if (first == null) {
                    first = current;
                } else if (second == null) {
                    second = current;
                } else {
                    double v1 = first.getMeasurementValue();
                    double v2 = second.getMeasurementValue();
                    double v3 = current.getMeasurementValue();

                    if ((v2 > v1 + 10 && v3 > v2 + 10) || (v2 < v1 - 10 && v3 < v2 - 10)) {
                        alertManager.triggerAlert(new Alert(patient.getPatientId(), "Blood Pressure Trend Alert", System.currentTimeMillis()));
                        // Reset first and second to check for new trends
                        first = null;
                        second = null;
                    } else {
                        // Shift the records
                        first = second;
                        second = current;
                    }
                }
            }
            lastProcessedIndex = i + 1; // Update the last processed index to the next record
        }

        // Update the last processed record index in the map
        updateLastProcessedRecordIndex(patientId, lastProcessedIndex);
    }

    public void checkBloodPressureThresholds(Patient patient) {
        List<PatientRecord> records = patient.getPatientRecords();
        for (PatientRecord record : records) {
            if ("BloodPressure".equals(record.getRecordType())) {
                double value = record.getMeasurementValue();
                if (value > 180 || value < 90) {
                    alertManager.triggerAlert(new Alert(patient.getPatientId(), "Critical Blood Pressure Threshold Alert", System.currentTimeMillis()));
                }
            }
        }
    }

    private int getLastProcessedRecordIndex(int patientId) {
        return lastProcessedRecordIndexMap.getOrDefault(patientId, 0);
    }

    private void updateLastProcessedRecordIndex(int patientId, int index) {
        lastProcessedRecordIndexMap.put(patientId, index);
    }
}
