package com.data_management;

import java.io.IOException;

public interface DataReader {
    /**
     * Reads data from a specified source and stores it in the data storage.
     * 
     * @param dataStorage the storage where data will be stored
     * @throws IOException if there is an error reading the data
     */
<<<<<<< HEAD
    void readData(DataStorage dataStorage) throws IOException;
=======
    
     void readData(String file) throws IOException;
>>>>>>> 12fcfcb (Implementing Missing Functionality and Some Test Cases,Running Junit Test for some Classes and generating the Coverage REport)
}
