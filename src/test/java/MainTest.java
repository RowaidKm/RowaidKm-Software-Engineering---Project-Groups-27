import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MainTest {

    @Test
    public void testMainWithDataStorage() {
        // Setting up arguments to run DataStorage
        String[] args = {"DataStorage"};

        // Call the main method with DataStorage argument and assert that it does not throw an exception
        assertDoesNotThrow(() -> Main.main(args));
    }

    @Test
    public void testMainWithHealthDataSimulator() {
        // Setting up arguments to run HealthDataSimulator
        String[] args = {};

        // Call the main method with no arguments (default to HealthDataSimulator) and assert that it does not throw an exception
        assertDoesNotThrow(() -> Main.main(args));
    }
}
