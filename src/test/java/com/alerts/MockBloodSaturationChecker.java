package com.alerts;

import com.data_management.Patient;

/**
 * Mock implementation of the {@link BloodSaturationChecker} for testing purposes.
 * This mock class simulates the behavior of the BloodSaturationChecker and tracks if saturation levels have been checked.
 */
public class MockBloodSaturationChecker extends BloodSaturationChecker {
    private boolean saturationChecked = false;

    /**
     * Constructs a new MockBloodSaturationChecker with a specified {@link AlertManager}.
     *
     * @param alertManager the alert manager used to handle alert triggering
     */
    public MockBloodSaturationChecker(AlertManager alertManager) {
        super(alertManager);
    }

    /**
     * Simulates checking blood saturation levels.
     *
     * @param patient the patient data to evaluate
     */
    @Override
    public void checkBloodSaturationLevels(Patient patient) {
        saturationChecked = true;
    }

    /**
     * Checks if blood saturation levels have been evaluated.
     *
     * @return true if saturation levels have been checked, false otherwise
     */
    public boolean isSaturationChecked() {
        return saturationChecked;
    }
}
