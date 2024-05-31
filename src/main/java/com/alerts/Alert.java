package com.alerts;

// Represents an alert
public class Alert {
<<<<<<< HEAD
    private String patientId;
    private String condition;
    private long timestamp;

    public Alert(String patientId, String condition, long timestamp) {
=======
    private int patientId;
    private String condition;
    private long timestamp;

    public Alert(int patientId, String condition, long timestamp) {
>>>>>>> 12fcfcb (Implementing Missing Functionality and Some Test Cases,Running Junit Test for some Classes and generating the Coverage REport)
        this.patientId = patientId;
        this.condition = condition;
        this.timestamp = timestamp;
    }

<<<<<<< HEAD
    public String getPatientId() {
=======
    public int getPatientId() {
>>>>>>> 12fcfcb (Implementing Missing Functionality and Some Test Cases,Running Junit Test for some Classes and generating the Coverage REport)
        return patientId;
    }

    public String getCondition() {
        return condition;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
