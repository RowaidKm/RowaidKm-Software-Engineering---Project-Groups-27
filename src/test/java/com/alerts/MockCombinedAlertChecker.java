package com.alerts;

import com.data_management.Patient;

/**
 * Mock implementation of the {@link CombinedAlertChecker} for testing purposes.
 * This mock class simulates the behavior of the CombinedAlertChecker and tracks if combined alerts have been checked.
 */
public class MockCombinedAlertChecker extends CombinedAlertChecker {
    private boolean combinedChecked = false;

    /**
     * Constructs a new MockCombinedAlertChecker with a specified {@link AlertManager}.
     *
     * @param alertManager the alert manager used to handle alert triggering
     */
    public MockCombinedAlertChecker(AlertManager alertManager) {
        super(alertManager);
    }

    /**
     * Simulates checking combined alerts.
     *
     * @param patient the patient data to evaluate
     */
    @Override
    public void checkCombinedAlerts(Patient patient) {
        combinedChecked = true;
    }

    /**
     * Checks if combined alerts have been evaluated.
     *
     * @return true if combined alerts have been checked, false otherwise
     */
    public boolean isCombinedChecked() {
        return combinedChecked;
    }
}
