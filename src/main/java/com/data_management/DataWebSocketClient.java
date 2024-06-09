package com.data_management;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * WebSocketClient for receiving real-time data and storing it in a DataStorage instance.
 * It extends the WebSocketClient from the org.java_websocket library.
 */
public class DataWebSocketClient extends WebSocketClient {

    private DataStorage dataStorage;

    /**
     * Constructs a new DataWebSocketClient.
     *
     * @param serverUri   the URI of the WebSocket server to connect to.
     * @param dataStorage the DataStorage instance where received data will be stored.
     */
    public DataWebSocketClient(URI serverUri, DataStorage dataStorage) {
        super(serverUri);
        this.dataStorage = dataStorage;
    }

    /**
     * Called when the connection to the WebSocket server is opened.
     *
     * @param handshakedata the handshake data received from the server.
     */
    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connected to WebSocket server.");
    }

    /**
     * Called when a message is received from the WebSocket server.
     * Parses the message and adds the data to the DataStorage.
     *
     * @param message the message received from the server, expected in the format: "patientId,timestamp,measurementValue,recordType".
     */
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

    /**
     * Called when the connection to the WebSocket server is closed.
     *
     * @param code   the status code indicating the reason for closure.
     * @param reason the reason for closure.
     * @param remote whether the closure was initiated by the remote host.
     */
    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Disconnected from WebSocket server.");
    }

    /**
     * Called when an error occurs.
     *
     * @param ex the exception that occurred.
     */
    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    /**
     * The main method to create and start the DataWebSocketClient.
     *
     * @param args command line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            DataStorage storage = new DataStorage();
            DataWebSocketClient client = new DataWebSocketClient(new URI("ws://localhost:8080"), storage);
            client.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
