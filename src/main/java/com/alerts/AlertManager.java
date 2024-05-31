package com.alerts;

public class AlertManager {

    public void triggerAlert(Alert alert) {
        // Implementation might involve logging the alert or notifying staff
        System.out.println("Alert Triggered: " + alert.getCondition() + " for Patient ID: " + alert.getPatientId());
        // Additional logic to notify staff, log alerts, etc.
    }

    public void triggerManualAlert(Alert alert){
        // Impleminting Manuall Triggers be nurses or medical staff
        System.out.println("Alert Manually Triggered For Patient ID :"+ alert.getPatientId());
    }

}

