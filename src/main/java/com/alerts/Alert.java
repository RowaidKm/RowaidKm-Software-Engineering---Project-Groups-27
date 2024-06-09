package com.alerts;

/**
<<<<<<< HEAD
 * Represents an alert.
 * This class encapsulates the details of an alert including the patient ID, the condition that triggered the alert,
 * and the timestamp of when the alert was triggered.
=======
 * Represents an alert for a specific patient condition.
 * This class stores all necessary details for an alert, including the patient ID,
 * the condition that triggered the alert, and the timestamp when the alert was generated.
>>>>>>> 3532c14 ( Finishing the Unit Tests)
 */
public class Alert {

<<<<<<< HEAD
    private int patientId; // The ID of the patient for whom the alert is generated
    private String condition; // The condition that triggered the alert
    private long timestamp; // The timestamp when the alert was triggered

    /**
     * Constructs an Alert object with the specified patient ID, condition, and timestamp.
     * 
     * @param patientId  The ID of the patient for whom the alert is generated.
     * @param condition  The condition that triggered the alert.
     * @param timestamp  The timestamp when the alert was triggered.
=======
    /**
     * Constructs a new Alert with specified details.
     *
     * @param patientId the unique identifier for the patient
     * @param condition the condition that triggered the alert
     * @param timestamp the time at which the alert was generated, in milliseconds since epoch
>>>>>>> 3532c14 ( Finishing the Unit Tests)
     */
    public Alert(int patientId, String condition, long timestamp) {
        this.patientId = patientId;
        this.condition = condition;
        this.timestamp = timestamp;
    }

    /**
<<<<<<< HEAD
     * Returns the ID of the patient for whom the alert is generated.
     * 
     * @return The patient ID.
=======
     * Returns the patient ID associated with this alert.
     *
     * @return the patient ID
>>>>>>> 3532c14 ( Finishing the Unit Tests)
     */
    public int getPatientId() {
        return patientId;
    }

    /**
<<<<<<< HEAD
     * Returns the condition that triggered the alert.
     * 
     * @return The condition.
=======
     * Returns the condition that triggered this alert.
     *
     * @return the condition
>>>>>>> 3532c14 ( Finishing the Unit Tests)
     */
    public String getCondition() {
        return condition;
    }

    /**
<<<<<<< HEAD
     * Returns the timestamp when the alert was triggered.
     * 
     * @return The timestamp.
=======
     * Returns the timestamp when this alert was generated.
     *
     * @return the timestamp in milliseconds since epoch
>>>>>>> 3532c14 ( Finishing the Unit Tests)
     */
    public long getTimestamp() {
        return timestamp;
    }
}
