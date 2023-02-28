package com.rumos.rumosbank.domain.models;

import com.rumos.rumosbank.domain.models.BankAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

class BankAccountTest {

    @Test
    void setInvalidBankAccountNumbers() {
        try {
            new BankAccount().setNumber("54785632S");
            new BankAccount().setNumber("4785789");
            new BankAccount().setNumber("36547887897");
            fail("Expected exception was not thrown");
        } catch (IllegalArgumentException exception) { assertNotNull(exception); }
    }
}