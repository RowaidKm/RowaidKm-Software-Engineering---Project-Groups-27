package com.data_management;

import java.io.IOException;

/**
 * The DataReader interface defines methods for reading data from various sources.
 * It includes methods for handling both static file data and real-time data from WebSocket servers.
 */
public interface DataReader {
    
    /**
     * Reads data from the specified source.
     * 
     * @param source the data source to read from (e.g., a file path or URL).
     * @throws IOException if an I/O error occurs while reading the data.
     */
    void readData(String source) throws IOException;

    /**
     * Connects to a WebSocket server to receive real-time data.
     * 
     * @param uri the URI of the WebSocket server to connect to.
     * @throws IOException if an error occurs while attempting to connect to the WebSocket server.
     */
    void connectToWebSocket(String uri) throws IOException;

    /**
     * Closes the connection to the WebSocket server.
     * 
     * @throws IOException if an error occurs while attempting to close the WebSocket connection.
     */
    void closeWebSocket() throws IOException;
}
