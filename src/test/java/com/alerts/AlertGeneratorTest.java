package com.alerts;

import com.data_management.DataStorage;
import com.data_management.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link AlertGenerator} class.
 */
class AlertGeneratorTest {

    private DataStorage dataStorage;
    private MockAlertManager alertManager;
    private MockBloodPressureChecker bloodPressureChecker;
    private MockBloodSaturationChecker bloodSaturationChecker;
    private MockCombinedAlertChecker combinedAlertChecker;
    private MockECGDataChecker ecgDataChecker;
    private AlertGenerator alertGenerator;
    private Patient patient;

    @BeforeEach
    void setUp() {
        // Initialize the MockAlertManager
        alertManager = new MockAlertManager();

        // Initialize the DataStorage and AlertGenerator
        dataStorage = new DataStorage();  // Use a real or a simple stub implementation if available
        alertGenerator = new AlertGenerator(dataStorage);

        // Initialize the mock checkers
        bloodPressureChecker = new MockBloodPressureChecker(alertManager);
        bloodSaturationChecker = new MockBloodSaturationChecker(alertManager);
        combinedAlertChecker = new MockCombinedAlertChecker(alertManager);
        ecgDataChecker = new MockECGDataChecker(alertManager);

        // Inject mocks into the alertGenerator
        alertGenerator.bloodPressureChecker = bloodPressureChecker;
        alertGenerator.bloodSaturationChecker = bloodSaturationChecker;
        alertGenerator.combinedAlertChecker = combinedAlertChecker;
        alertGenerator.ecgDataChecker = ecgDataChecker;

        // Create a sample patient for testing
        patient = new Patient(1);
    }

    @Test
    void testEvaluateData() {
        // Call the method to evaluate data
        alertGenerator.evaluateData(patient);

        // Verify that each checker method was called
        assertTrue(bloodPressureChecker.isTrendsChecked(), "Blood pressure trends should be checked");
        assertTrue(bloodPressureChecker.isThresholdsChecked(), "Blood pressure thresholds should be checked");
        assertTrue(bloodSaturationChecker.isSaturationChecked(), "Blood saturation levels should be checked");
        assertTrue(combinedAlertChecker.isCombinedChecked(), "Combined alerts should be checked");
        assertTrue(ecgDataChecker.isECGChecked(), "ECG data should be checked");

        // Verify that an alert was triggered if conditions were met
        // This part depends on your implementation, you might need to add more checks here
        // assertTrue(alertManager.isAlertTriggered(), "An alert should have been triggered");
    }
}
