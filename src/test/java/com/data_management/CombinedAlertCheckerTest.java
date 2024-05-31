package com.data_management;

import com.alerts.AlertManager;
import com.alerts.CombinedAlertChecker;
import com.data_management.Patient;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import static org.junit.jupiter.api.Assertions.*;

class CombinedAlertCheckerTest {

    @Test
    void testCombinedAlert() {
        AlertManager alertManager = new AlertManager();
        CombinedAlertChecker checker = new CombinedAlertChecker(alertManager);

    
        Patient patient = new Patient(1);
        patient.addRecord( 85, "BloodPressure", System.currentTimeMillis());
        patient.addRecord(80,"BloodSaturation" ,System.currentTimeMillis());

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        checker.checkCombinedAlerts(patient);

        String expectedOutput = "Alert Triggered: Hypotensive Hypoxemia Alert for Patient ID: 1\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
