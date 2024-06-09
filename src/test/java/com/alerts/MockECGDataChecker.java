package com.alerts;

import com.data_management.Patient;

/**
 * Mock implementation of the {@link ECGDataChecker} for testing purposes.
 * This mock class simulates the behavior of the ECGDataChecker and tracks if ECG data has been checked.
 */
public class MockECGDataChecker extends ECGDataChecker {
    private boolean ecgChecked = false;

    /**
     * Constructs a new MockECGDataChecker with a specified {@link AlertManager}.
     *
     * @param alertManager the alert manager used to handle alert triggering
     */
    public MockECGDataChecker(AlertManager alertManager) {
        super(alertManager);
    }

    /**
     * Simulates checking ECG data.
     *
     * @param patient the patient data to evaluate
     */
    @Override
    public void checkECGData(Patient patient) {
        ecgChecked = true;
    }

    /**
     * Checks if ECG data has been evaluated.
     *
     * @return true if ECG data has been checked, false otherwise
     */
    public boolean isECGChecked() {
        return ecgChecked;
    }
}
