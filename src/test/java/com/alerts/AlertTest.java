package com.alerts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Alert} class.
 */
class AlertTest {

    private Alert alert;
    private int patientId = 1;
    private String condition = "Critical";
    private long timestamp = System.currentTimeMillis();

    /**
     * Sets up a new Alert instance before each test.
     */
    @BeforeEach
    void setUp() {
        alert = new Alert(patientId, condition, timestamp);
    }

    /**
     * Tests the constructor of the Alert class.
     * Ensures the fields are set correctly.
     */
    @Test
    void testConstructor() {
        assertEquals(patientId, alert.getPatientId());
        assertEquals(condition, alert.getCondition());
        assertEquals(timestamp, alert.getTimestamp());
    }

    /**
     * Tests the getPatientId method.
     * Ensures the method returns the correct patient ID.
     */
    @Test
    void testGetPatientId() {
        assertEquals(patientId, alert.getPatientId());
    }

    /**
     * Tests the getCondition method.
     * Ensures the method returns the correct condition.
     */
    @Test
    void testGetCondition() {
        assertEquals(condition, alert.getCondition());
    }

    /**
     * Tests the getTimestamp method.
     * Ensures the method returns the correct timestamp.
     */
    @Test
    void testGetTimestamp() {
        assertEquals(timestamp, alert.getTimestamp());
    }
}
