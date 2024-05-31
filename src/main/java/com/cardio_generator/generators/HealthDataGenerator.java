package com.cardio_generator.generators;

import com.data_management.Patient;
import com.alerts.Alert;
import com.alerts.AlertManager;

public class HealthDataGenerator {

    private AlertManager alertManager;

    public HealthDataGenerator() {
        this.alertManager = new AlertManager(); // Initialize the AlertManager here
    }

    public void generateManualAlert(Patient patient) {
        Alert alert = new Alert(patient.getPatientId(), "Manual Alert", System.currentTimeMillis());
        alertManager.triggerManualAlert(alert);
    }
}
