package com.data_management;

import com.alerts.AlertManager;
import com.alerts.CombinedAlertChecker;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link CombinedAlertChecker} class.
 */
class CombinedAlertCheckerTest {

    /**
     * Tests the checkCombinedAlerts method of the CombinedAlertChecker.
     * Verifies that a combined alert is triggered correctly for a patient with low blood pressure and low blood saturation.
     */
    @Test
    void testCombinedAlert() {
        AlertManager alertManager = new AlertManager();
        CombinedAlertChecker checker = new CombinedAlertChecker(alertManager);

        // Create a patient and add records for blood pressure and blood saturation
        Patient patient = new Patient(1);
        patient.addRecord(85, "BloodPressure", System.currentTimeMillis());
        patient.addRecord(80, "BloodSaturation", System.currentTimeMillis());

        // Capture System.out output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Check for combined alerts
        checker.checkCombinedAlerts(patient);

        // Expected output for the combined alert
        String expectedOutput = "Alert Triggered: Hypotensive Hypoxemia Alert for Patient ID: 1\n";
        
        // Verify that the alert was triggered correctly
        assertEquals(expectedOutput,outContent.toString());
    }
}
