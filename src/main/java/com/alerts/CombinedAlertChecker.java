package com.alerts;

import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombinedAlertChecker {
    private AlertManager alertManager;
    private Map<Integer, Integer> lastProcessedRecordIndexMap; // Map to track last processed record index for each patient

    public CombinedAlertChecker(AlertManager alertManager) {
        this.alertManager = alertManager;
        this.lastProcessedRecordIndexMap = new HashMap<>();
    }

    public void checkCombinedAlerts(Patient patient) {
        List<PatientRecord> records = patient.getPatientRecords();
        int patientId = patient.getPatientId();
        int lastProcessedIndex = getLastProcessedRecordIndex(patientId);

        boolean lowBloodPressure = false;
        boolean lowBloodSaturation = false;

        for (int i = lastProcessedIndex; i < records.size(); i++) {
            PatientRecord current = records.get(i);
            if ("BloodPressure".equals(current.getRecordType())) {
                if (current.getMeasurementValue() < 90) {
                    lowBloodPressure = true;
                }
            } else if ("BloodSaturation".equals(current.getRecordType())) {
                if (current.getMeasurementValue() < 92) {
                    lowBloodSaturation = true;
                }
            }

            if (lowBloodPressure && lowBloodSaturation) {
                alertManager.triggerAlert(new Alert(patient.getPatientId(), "Hypotensive Hypoxemia Alert", System.currentTimeMillis()));
                break; // Exit the loop once the alert is triggered
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
