import com.data_management.DataStorage;
import java.io.IOException;
import com.cardio_generator.HealthDataSimulator;

/**
 * The Main class serves as the entry point for the application.
 * It decides which main class to run based on the provided command-line arguments.
 */
public class Main {

    /**
     * The main method of the application.
     * 
     * @param args Command-line arguments to determine the main class to run.
     *             If "DataStorage" is provided as the first argument, it runs DataStorage.main().
     *             Otherwise, it runs HealthDataSimulator.main().
     * @throws IOException If an I/O error occurs.
     */
    public static void main(String[] args) throws IOException {
        if (args.length > 0 && args[0].equals("DataStorage")) {
            DataStorage.main(new String[]{});
        } else {
            HealthDataSimulator.main(new String[]{});
        }
    }
}
