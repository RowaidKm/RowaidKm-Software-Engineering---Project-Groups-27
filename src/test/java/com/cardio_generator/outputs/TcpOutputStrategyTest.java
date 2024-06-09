package com.cardio_generator.outputs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link TcpOutputStrategy} class.
 */
class TcpOutputStrategyTest {

    private TcpOutputStrategy tcpOutputStrategy;
    private Socket clientSocket;
    private BufferedReader in;

    private static final int TEST_PORT = 12345;

    /**
     * Sets up the test environment before each test.
     * Initializes the TcpOutputStrategy and connects a client socket to the server.
     *
     * @throws IOException if an I/O error occurs
     */
    @BeforeEach
    void setUp() throws IOException {
        tcpOutputStrategy = new TcpOutputStrategy(TEST_PORT);

        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                // Wait for the server to start and accept a connection
                TimeUnit.MILLISECONDS.sleep(500);
                clientSocket = new Socket("localhost", TEST_PORT);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Wait for the client to connect
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Interrupted while waiting for client to connect", e);
        }
    }

    /**
     * Cleans up the test environment after each test.
     * Closes the client socket.
     *
     * @throws IOException if an I/O error occurs
     */
    @AfterEach
    void tearDown() throws IOException {
        if (clientSocket != null) {
            clientSocket.close();
        }
    }

    /**
     * Tests the output method of the TcpOutputStrategy.
     * Verifies that the correct data is sent over the TCP connection.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    void testOutput() throws IOException {
        int patientId = 1;
        long timestamp = 123456789L;
        String label = "ECG";
        String data = "85 bpm";

        tcpOutputStrategy.output(patientId, timestamp, label, data);

        String expectedOutput = String.format("%d,%d,%s,%s", patientId, timestamp, label, data);
        String receivedOutput = in.readLine();
        assertEquals(expectedOutput, receivedOutput, "Expected output to match the sent data.");
    }
}
