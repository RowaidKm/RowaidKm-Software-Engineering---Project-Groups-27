package com.alerts;

/**
 * Represents an alert.
 * This class encapsulates the details of an alert including the patient ID, the condition that triggered the alert,
 * and the timestamp of when the alert was triggered.
 */
public class Alert {

    private int patientId; // The ID of the patient for whom the alert is generated
    private String condition; // The condition that triggered the alert
    private long timestamp; // The timestamp when the alert was triggered

    /**
     * Constructs an Alert object with the specified patient ID, condition, and timestamp.
     * 
     * @param patientId  The ID of the patient for whom the alert is generated.
     * @param condition  The condition that triggered the alert.
     * @param timestamp  The timestamp when the alert was triggered.
     */
    public Alert(int patientId, String condition, long timestamp) {
        this.patientId = patientId;
        this.condition = condition;
        this.timestamp = timestamp;
    }

    /**
     * Returns the ID of the patient for whom the alert is generated.
     * 
     * @return The patient ID.
     */
    public int getPatientId() {
        return patientId;
    }

    /**
     * Returns the condition that triggered the alert.
     * 
     * @return The condition.
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Returns the timestamp when the alert was triggered.
     * 
     * @return The timestamp.
     */
    public long getTimestamp() {
        return timestamp;
    }
}
