package com.rumos.rumosbank.domain.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    private Client client;

    @BeforeEach
    public void setUp() {
        List<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.add(new BankAccount());
        bankAccounts.add(new BankAccount());
        bankAccounts.add(new BankAccount());

        client = new Client();
        client.setFirstName("John");
        client.setLastName("Doe");
        client.setBirthdate(LocalDate.of(1990, 1, 1));
        client.setNif("123456789");
        client.setPhone("123456789");
        client.setMobilePhone("912345678");
        client.setProfession("Developer");
        client.setEmailAddress("john.doe@example.com");
        client.setPassword("secret");
        client.setBankAccounts(bankAccounts);
    }

    @Test
    public void testGetName() {
        assertEquals("John Doe", client.getName());
    }

    @Test
    public void testGetBankAccount() {
        BankAccount account = client.getBankAccount("001");
        assertNotNull(account);
        assertEquals("002", account.getNumber());
        assertEquals(2000, account.getBalance());
    }


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
