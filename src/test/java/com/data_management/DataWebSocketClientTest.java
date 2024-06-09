package com.data_management;

import org.java_websocket.server.WebSocketServer;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.client.WebSocketClient;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Unit tests for the {@link DataWebSocketClient} class.
 * These tests verify the functionality of the WebSocket client including connection handling and message processing.
 */
public class DataWebSocketClientTest {

    private static TestWebSocketServer server;
    private static final int PORT = 8080;

    /**
     * Starts the WebSocket server before running the tests.
     * This method is annotated with @BeforeAll to ensure it runs once before all test methods.
     */
    @BeforeAll
    public static void startServer() {
        server = new TestWebSocketServer(new InetSocketAddress("localhost", PORT));
        server.start();
    }

    /**
     * Stops the WebSocket server after all tests have run.
     * This method is annotated with @AfterAll to ensure it runs once after all test methods.
     * 
     * @throws InterruptedException if the server stop operation is interrupted.
     */
    @AfterAll
    public static void stopServer() throws InterruptedException {
        if (server != null) {
            server.stop();
        }
    }

    /**
     * Tests the DataWebSocketClient's ability to handle incoming messages.
     * Verifies that the client can receive and process a message from the server.
     * 
     * @throws URISyntaxException if the WebSocket URI syntax is incorrect.
     * @throws InterruptedException if the thread is interrupted while waiting for the message to be received.
     */
    @Test
    public void testWebSocketMessageHandling() throws URISyntaxException, InterruptedException {
        URI serverUri = new URI("ws://localhost:" + PORT);
        CountDownLatch latch = new CountDownLatch(1);
        DataStorage dataStorage = new DataStorage();

        WebSocketClient client = new DataWebSocketClient(serverUri, dataStorage) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                System.out.println("Client connected");
                // Send a test message
                send("1,161803398874989,98.6,BodyTemperature");
            }

            @Override
            public void onMessage(String message) {
                System.out.println("Received message from server: " + message);
                latch.countDown();
            }
        };

        client.connect();
        assertFalse(latch.await(5, TimeUnit.SECONDS), "Message should be received from WebSocket server");

        // Verify that the data was added to the storage
        assertEquals(1, dataStorage.getRecords(1, 161803398874989L, 161803398874989L).size());
        assertEquals(98.6, dataStorage.getRecords(1, 161803398874989L, 161803398874989L).get(0).getMeasurementValue());

        client.close();
    }

    /**
     * Mock WebSocketServer for testing purposes.
     */
    private static class TestWebSocketServer extends WebSocketServer {

        public TestWebSocketServer(InetSocketAddress address) {
            super(address);
        }

        @Override
        public void onOpen(WebSocket conn, ClientHandshake handshake) {
            System.out.println("Server: New connection from " + conn.getRemoteSocketAddress());
        }

        @Override
        public void onClose(WebSocket conn, int code, String reason, boolean remote) {
            System.out.println("Server: Closed " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
        }

        @Override
        public void onMessage(WebSocket conn, String message) {
            System.out.println("Server: Received message from " + conn.getRemoteSocketAddress() + ": " + message);
            // Echo the message back to the client
            conn.send(message);
        }

        @Override
        public void onError(WebSocket conn, Exception ex) {
            ex.printStackTrace();
        }

        @Override
        public void onStart() {
            System.out.println("Server started successfully");
        }
    }
}
