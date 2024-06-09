package com.alerts;

import com.data_management.Patient;

/**
 * Mock implementation of the {@link BloodPressureChecker} for testing purposes.
 * This mock class simulates the behavior of the BloodPressureChecker and tracks the checks performed.
 */
public class MockBloodPressureChecker extends BloodPressureChecker {
    private boolean trendsChecked = false;
    private boolean thresholdsChecked = false;

    /**
     * Constructs a new MockBloodPressureChecker with a specified {@link AlertManager}.
     *
     * @param alertManager the alert manager used to handle alert triggering
     */
    public MockBloodPressureChecker(AlertManager alertManager) {
        super(alertManager);
    }

    /**
     * Simulates checking blood pressure trends.
     *
     * @param patient the patient data to evaluate
     */
    @Override
    public void checkBloodPressureTrends(Patient patient) {
        trendsChecked = true;
    }

    /**
     * Simulates checking blood pressure thresholds.
     *
     * @param patient the patient data to evaluate
     */
    @Override
    public void checkBloodPressureThresholds(Patient patient) {
        thresholdsChecked = true;
    }

    /**
     * Checks if blood pressure trends have been evaluated.
     *
     * @return true if trends have been checked, false otherwise
     */
    public boolean isTrendsChecked() {
        return trendsChecked;
    }

    /**
     * Checks if blood pressure thresholds have been evaluated.
     *
     * @return true if thresholds have been checked, false otherwise
     */
    public boolean isThresholdsChecked() {
        return thresholdsChecked;
    }
}
