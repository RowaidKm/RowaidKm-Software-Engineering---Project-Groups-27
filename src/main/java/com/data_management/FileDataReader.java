package com.data_management;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * The FileDataReader class is responsible for reading patient data from a file and storing it into the DataStorage.
 * It also supports connecting to a WebSocket server to receive real-time data.
 */
public class FileDataReader implements DataReader {

    private DataStorage dataStorage;
    private WebSocketClient webSocketClient;

    /**
     * Constructs a FileDataReader with the specified DataStorage.
     * 
     * @param storage The DataStorage instance where the read data will be stored.
     */
    public FileDataReader(DataStorage storage) {
        this.dataStorage = storage;
    }

    /**
     * Reads data from the specified file and stores it into the DataStorage.
     * 
     * @param source The path to the file containing the data.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    @Override
    public void readData(String source) throws IOException {
        Path path = Paths.get(source);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(this::processLine);
        }
    }

    /**
     * Processes a single line of data and stores it into the DataStorage.
     * 
     * @param line A comma-separated string containing patient data in the format: "patientId,timestamp,value,type".
     */
    void processLine(String line) {
        // Assuming the line contains comma-separated values like "patientId,timestamp,value,type"
        String[] parts = line.split(",");
        if (parts.length == 4) {
            int patientId = Integer.parseInt(parts[0]);
            long timestamp = Long.parseLong(parts[1]);
            double measurementValue = Double.parseDouble(parts[2]);
            String recordType = parts[3];
            // Call DataStorage to handle the parsed data and store it
            dataStorage.addPatientData(patientId, measurementValue, recordType, timestamp);
        }
    }

    /**
     * Connects to a WebSocket server to receive real-time data.
     * 
     * @param uri The URI of the WebSocket server.
     * @throws IOException If an error occurs while attempting to connect to the WebSocket server.
     */
    @Override
    public void connectToWebSocket(String uri) throws IOException {
        try {
            webSocketClient = new DataWebSocketClient(new URI(uri), dataStorage);
            webSocketClient.connect();
        } catch (URISyntaxException e) {
            throw new IOException("Invalid URI syntax: " + uri, e);
        }
    }

    /**
     * Closes the connection to the WebSocket server.
     * 
     * @throws IOException If an error occurs while attempting to close the WebSocket connection.
     */
    @Override
    public void closeWebSocket() throws IOException {
        if (webSocketClient != null) {
            webSocketClient.close();
        }
    }

    /**
     * Inner class extending WebSocketClient to handle WebSocket events.
     */
    private static class DataWebSocketClient extends WebSocketClient {

        private DataStorage dataStorage;

        public DataWebSocketClient(URI serverUri, DataStorage dataStorage) {
            super(serverUri);
            this.dataStorage = dataStorage;
        }

        @Override
        public void onOpen(ServerHandshake handshakedata) {
            System.out.println("Connected to WebSocket server.");
        }

        @Override
        public void onMessage(String message) {
            // Parse the message and add to DataStorage
            // Assuming message format: "patientId,timestamp,measurementValue,recordType"
            String[] parts = message.split(",");
            if (parts.length == 4) {
                int patientId = Integer.parseInt(parts[0]);
                long timestamp = Long.parseLong(parts[1]);
                double measurementValue = Double.parseDouble(parts[2]);
                String recordType = parts[3];

                dataStorage.addPatientData(patientId, measurementValue, recordType, timestamp);
            }
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {
            System.out.println("Disconnected from WebSocket server.");
        }

        @Override
        public void onError(Exception ex) {
            ex.printStackTrace();
        }
    }
}
