package com.cardio_generator.outputs;

import org.java_websocket.WebSocket;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

/**
 * WebSocketOutputStrategy is a WebSocket server that handles incoming WebSocket connections and messages.
 * It extends the WebSocketServer from the org.java_websocket library.
 */
public class WebSocketOutputStrategy extends WebSocketServer implements OutputStrategy {

    /**
     * Constructs a new WebSocketOutputStrategy.
     *
     * @param address the address to bind the WebSocket server to.
     */
    public WebSocketOutputStrategy(InetSocketAddress address) {
        super(address);
    }

    /**
     * Called when a new WebSocket connection is opened.
     *
     * @param conn       the WebSocket connection object.
     * @param handshake  the handshake data received from the client.
     */
    @Override
    public void onOpen(WebSocket conn, org.java_websocket.handshake.ClientHandshake handshake) {
        System.out.println("New connection from " + conn.getRemoteSocketAddress());
    }

    /**
     * Called when a WebSocket connection is closed.
     *
     * @param conn       the WebSocket connection object.
     * @param code       the status code indicating the reason for closure.
     * @param reason     the reason for closure.
     * @param remote     whether the closure was initiated by the remote host.
     */
    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Closed " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
    }

    /**
     * Called when a message is received from a WebSocket connection.
     *
     * @param conn    the WebSocket connection object.
     * @param message the message received from the client.
     */
    @Override
    public void onMessage(WebSocket conn, String message) {
        // Not used in this context
    }

    /**
     * Called when an error occurs.
     *
     * @param conn the WebSocket connection object, if the error is associated with a specific connection.
     * @param ex   the exception that occurred.
     */
    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if (conn != null) {
            // some errors like port binding failed may not be assignable to a specific websocket
        }
    }

    /**
     * Called when the WebSocket server starts successfully.
     */
    @Override
    public void onStart() {
        System.out.println("Server started successfully");
    }

    /**
     * Outputs the specified data for the patient to connected WebSocket clients.
     *
     * @param patientId The ID of the patient.
     * @param timestamp The timestamp of the data.
     * @param label     The label or type of the data.
     * @param data      The actual data to be outputted.
     */
    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        String message = String.format("%d,%d,%s,%s", patientId, timestamp, label, data);
        // Broadcast the message to all connected clients
        for (WebSocket conn : getConnections()) {
            conn.send(message);
        }
    }

    /**
     * The main method to create and start the WebSocketOutputStrategy server.
     *
     * @param args command line arguments (not used).
     */
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8080;
        WebSocketOutputStrategy server = new WebSocketOutputStrategy(new InetSocketAddress(host, port));
        server.start();
        System.out.println("WebSocket server started on port: " + server.getPort());
    }
}
