import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Unit tests for the {@link Main} class.
 */
public class MainTest {

    /**
     * Tests the main method of the Main class with DataStorage as the argument.
     * Verifies that no exceptions are thrown when running with the DataStorage argument.
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
     * Verifies that no exceptions are thrown when running with the default behavior (HealthDataSimulator).
     */
    @Test
    public void testMainWithHealthDataSimulator() {
        // Setting up arguments to run HealthDataSimulator
        String[] args = {};

        // Call the main method with no arguments (default to HealthDataSimulator) and assert that it does not throw an exception
        assertDoesNotThrow(() -> Main.main(args));
    }
}
