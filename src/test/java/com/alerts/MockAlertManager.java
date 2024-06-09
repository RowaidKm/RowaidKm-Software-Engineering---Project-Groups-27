package com.alerts;



/**
 * Mock implementation of the {@link AlertManager} for testing purposes.
 * This mock class simulates the behavior of the AlertManager and tracks if an alert has been triggered.
 */
public class MockAlertManager extends AlertManager {
    private boolean alertTriggered = false;

    /**
     * Simulates triggering an alert.
     *
     * @param alert the alert object containing details about the alert condition
     */
    @Override
    public void triggerAlert(Alert alert) {
        alertTriggered = true;
    }

    /**
     * Checks if an alert has been triggered.
     *
     * @return true if an alert has been triggered, false otherwise
     */
    public boolean isAlertTriggered() {
        return alertTriggered;
    }
}
