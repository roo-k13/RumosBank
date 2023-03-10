package com.rumos.rumosbank.domain.models;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class ClientTest {

    @Test
    void setFirstName() throws FileNotFoundException {
        Client client = new Client();
        try {
            FileReader fileReader = new FileReader("src/test/java/com/rumos/rumosbank/domain/models/first_names.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String name = line.trim();
                System.out.println(name);
                client.setFirstName(name);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
