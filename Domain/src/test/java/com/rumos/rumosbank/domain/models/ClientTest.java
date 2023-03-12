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
        BankAccount bankAccount1 = new BankAccount();
        bankAccount1.setNumber("000000001");

        BankAccount bankAccount2 = new BankAccount();
        bankAccount2.setNumber("000000002");

        BankAccount bankAccount3 = new BankAccount();
        bankAccount3.setNumber("000000003");

        List<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.add(bankAccount1);
        bankAccounts.add(bankAccount2);
        bankAccounts.add(bankAccount3);

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
        BankAccount account = client.getBankAccount("000000002");
        assertNotNull(account);
        assertEquals("000000002", account.getNumber());
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
