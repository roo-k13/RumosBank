package com.rumos.rumosbank.domain.models;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ClientTest {
    private final Client client = new Client();

    @Test
    void setFirstName() {
        testNames("src/test/java/com/rumos/rumosbank/domain/models/valid_first_names.csv");
        assertThrows(IllegalArgumentException.class, ()
                -> testNames("src/test/java/com/rumos/rumosbank/domain/models/invalid_first_names.csv"));
    }

    @Test
    void setLastName() {
        testNames("src/test/java/com/rumos/rumosbank/domain/models/valid_last_names.csv");
        assertThrows(IllegalArgumentException.class, ()
                -> testNames("src/test/java/com/rumos/rumosbank/domain/models/invalid_last_names.csv"));
    }

    private void testNames(String filePath) {
        try (var reader = new BufferedReader(new FileReader(filePath))) {
            reader.lines().map(String::trim).forEach(client::setFirstName);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }
}
