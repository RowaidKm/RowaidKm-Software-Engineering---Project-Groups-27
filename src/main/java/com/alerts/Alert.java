package com.alerts;

/**
 * Represents an alert for a patient.
 * This class encapsulates the information related to an alert, such as the patient ID,
 * the condition that triggered the alert, and the timestamp when the alert was generated.
 */
public class Alert {

    private int patientId;
    private String condition;
    private long timestamp;

    /**
     * Constructs an Alert object with the specified patient ID, condition, and timestamp.
     *
     * @param patientId The ID of the patient associated with the alert.
     * @param condition The condition or reason that triggered the alert.
     * @param timestamp The timestamp when the alert was generated.
     */
    public Alert(int patientId, String condition, long timestamp) {
        this.patientId = patientId;
        this.condition = condition;
        this.timestamp = timestamp;
    }

    /**
     * Gets the ID of the patient associated with the alert.
     *
     * @return The patient ID.
     */
    public int getPatientId() {
        return patientId;
    }

    /**
     * Gets the condition or reason that triggered the alert.
     *
     * @return The condition.
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Gets the timestamp when the alert was generated.
     *
     * @return The timestamp.
     */
    public long getTimestamp() {
        return timestamp;
    }
}
