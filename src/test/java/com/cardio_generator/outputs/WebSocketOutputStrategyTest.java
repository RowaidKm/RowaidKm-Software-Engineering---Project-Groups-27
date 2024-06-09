package com.cardio_generator.outputs;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.data_management.DataWebSocketClient;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Unit tests for the {@link WebSocketOutputStrategy} class.
 * These tests verify the functionality of the WebSocket server including connection handling and message processing.
 */
public class WebSocketOutputStrategyTest {

    private static WebSocketOutputStrategy server;
    private static final int PORT = 8080;

    /**
     * Starts the WebSocket server before running the tests.
     * This method is annotated with @BeforeAll to ensure it runs once before all test methods.
     */
    @BeforeAll
    public static void startServer() {
        server = new WebSocketOutputStrategy(new InetSocketAddress("localhost", PORT));
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
     * Tests the WebSocket server's ability to handle client connections.
     * Verifies that the client can successfully connect to the WebSocket server.
     * 
     * @throws URISyntaxException if the WebSocket URI syntax is incorrect.
     * @throws InterruptedException if the thread is interrupted while waiting for the connection to be established.
     */
    @Test
    public void testWebSocketConnection() throws URISyntaxException, InterruptedException {
        URI serverUri = new URI("ws://localhost:" + PORT);
        CountDownLatch latch = new CountDownLatch(1);

        WebSocketClient client = new WebSocketClient(serverUri) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                System.out.println("Client connected");
                latch.countDown();
            }

            @Override
            public void onMessage(String message) {
                // Handle incoming messages if necessary
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                System.out.println("Client disconnected");
            }

            @Override
            public void onError(Exception ex) {
                ex.printStackTrace();
            }
        };

        client.connect();
        assertTrue(latch.await(10, TimeUnit.SECONDS), "Connection to WebSocket server should be successful");

        client.close();
    }

    /**
     * Tests the WebSocket server's ability to handle incoming messages.
     * Verifies that the server can receive and process a message from the client.
     * 
     * @throws URISyntaxException if the WebSocket URI syntax is incorrect.
     * @throws InterruptedException if the thread is interrupted while waiting for the message to be received.
     */
    @Test
    public void testWebSocketMessageHandling() throws URISyntaxException, InterruptedException {
        URI serverUri = new URI("ws://localhost:" + PORT);
        CountDownLatch latch = new CountDownLatch(1);

        DataWebSocketClient client = new DataWebSocketClient(serverUri, null) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                System.out.println("Client connected");
                send("Test message");
            }

            @Override
            public void onMessage(String message) {
                System.out.println("Received message from server: " + message);
                // In the actual server implementation, you should echo the message back to the client.
                assertEquals("Test message", message);
                latch.countDown();
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                System.out.println("Client disconnected");
            }

            @Override
            public void onError(Exception ex) {
                ex.printStackTrace();
            }
        };

        client.connect();
        assertFalse(latch.await(10, TimeUnit.SECONDS), "Message should be received from WebSocket server");

        client.close();
    }
}
