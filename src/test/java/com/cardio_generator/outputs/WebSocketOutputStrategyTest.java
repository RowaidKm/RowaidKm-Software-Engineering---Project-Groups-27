package com.cardio_generator.outputs;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link WebSocketOutputStrategy} class.
 */
class WebSocketOutputStrategyTest {

    private WebSocketOutputStrategy webSocketOutputStrategy;
    private TestWebSocketClient client;
    private BlockingQueue<String> messageQueue;
    private static final int TEST_PORT = 12345;

    /**
     * Sets up the test environment before each test.
     * Initializes the WebSocketOutputStrategy and connects a test WebSocket client to the server.
     *
     * @throws URISyntaxException if the URI syntax is incorrect
     * @throws InterruptedException if the thread is interrupted
     */
    @BeforeEach
    void setUp() throws URISyntaxException, InterruptedException {
        webSocketOutputStrategy = new WebSocketOutputStrategy(TEST_PORT);
        messageQueue = new LinkedBlockingQueue<>();

        client = new TestWebSocketClient(new URI("ws://localhost:" + TEST_PORT));
        client.connectBlocking(1, TimeUnit.SECONDS);
    }

    /**
     * Cleans up the test environment after each test.
     * Closes the WebSocket client.
     */
    @AfterEach
    void tearDown() {
        if (client != null) {
            client.close();
        }
    }

    /**
     * Tests the output method of the WebSocketOutputStrategy.
     * Verifies that the correct data is sent over the WebSocket connection.
     *
     * @throws InterruptedException if the thread is interrupted
     */
    @Test
    void testOutput() throws InterruptedException {
        int patientId = 1;
        long timestamp = 123456789L;
        String label = "ECG";
        String data = "85 bpm";

        webSocketOutputStrategy.output(patientId, timestamp, label, data);

        String expectedOutput = String.format("%d,%d,%s,%s", patientId, timestamp, label, data);
        String receivedOutput = messageQueue.poll(1, TimeUnit.SECONDS);
        assertEquals(expectedOutput, receivedOutput, "Expected output to match the sent data.");
    }

    /**
     * A simple WebSocket client for testing purposes.
     */
    private class TestWebSocketClient extends WebSocketClient {

        public TestWebSocketClient(URI serverUri) {
            super(serverUri);
        }

        @Override
        public void onOpen(ServerHandshake handshakedata) {
            System.out.println("Connected to server");
        }

        @Override
        public void onMessage(String message) {
            messageQueue.offer(message);
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {
            System.out.println("Connection closed: " + reason);
        }

        @Override
        public void onError(Exception ex) {
            ex.printStackTrace();
        }
    }
}
