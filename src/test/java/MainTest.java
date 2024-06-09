import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Unit tests for the Main class.
 */
public class MainTest {

    /**
     * Tests the main method of the Main class with the DataStorage argument.
     * Verifies that the main method does not throw an exception when running DataStorage.
     */
    @Test
    public void testMainWithDataStorage() {
        // Setting up arguments to run DataStorage
        String[] args = {"DataStorage"};

        // Call the main method with DataStorage argument and assert that it does not throw an exception
        assertDoesNotThrow(() -> Main.main(args));
    }

    /**
     * Tests the main method of the Main class with no arguments.
     * Verifies that the main method does not throw an exception when running HealthDataSimulator by default.
     */
    @Test
    public void testMainWithHealthDataSimulator() {
        // Setting up arguments to run HealthDataSimulator
        String[] args = {};

        // Call the main method with no arguments (default to HealthDataSimulator) and assert that it does not throw an exception
        assertDoesNotThrow(() -> Main.main(args));
    }

    /**
     * Tests the main method of the Main class with an unknown argument.
     * Verifies that the main method defaults to HealthDataSimulator and does not throw an exception.
     */
    @Test
    public void testMainWithUnknownArgument() {
        // Setting up arguments to run an unknown argument
        String[] args = {"Unknown"};

        // Call the main method with an unknown argument and assert that it does not throw an exception
        assertDoesNotThrow(() -> Main.main(args));
    }

    /**
     * Tests the main method directly without any arguments.
     * Verifies that the main method does not throw an exception.
     */
    @Test
    public void testMainMethodDirectly() {
        // Call the main method directly and assert that it does not throw an exception
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }
}
