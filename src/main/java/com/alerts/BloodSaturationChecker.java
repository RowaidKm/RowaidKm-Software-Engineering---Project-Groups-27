package com.alerts;

import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BloodSaturationChecker {
    private AlertManager alertManager;
    private Map<Integer, Integer> lastProcessedRecordIndexMap; // Map to track last processed record index for each patient

    public BloodSaturationChecker(AlertManager alertManager) {
        this.alertManager = alertManager;
        this.lastProcessedRecordIndexMap = new HashMap<>();
    }

    public void checkBloodSaturationLevels(Patient patient) {
        List<PatientRecord> records = patient.getPatientRecords();
        int patientId = patient.getPatientId();
        int lastProcessedIndex = getLastProcessedRecordIndex(patientId);

        for (int i = lastProcessedIndex; i < records.size(); i++) {
            PatientRecord current = records.get(i);
            if ("BloodSaturation".equals(current.getRecordType())) {
                double saturation = current.getMeasurementValue();

                // Low Saturation Alert
                if (saturation < 92) {
                    alertManager.triggerAlert(new Alert(patient.getPatientId(), "Low Blood Saturation Alert", System.currentTimeMillis()));
                }

                // Rapid Drop Alert
                for (int j = i + 1; j < records.size(); j++) {
                    PatientRecord next = records.get(j);
                    if ("BloodSaturation".equals(next.getRecordType())) {
                        double nextSaturation = next.getMeasurementValue();
                        long timeDifference = next.getTimestamp() - current.getTimestamp();
                        if (timeDifference <= 10 * 60 * 1000 && saturation - nextSaturation >= 5) {
                            alertManager.triggerAlert(new Alert(patient.getPatientId(), "Rapid Drop in Blood Saturation Alert", System.currentTimeMillis()));
                            break;
                        }
                    }
                }
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
