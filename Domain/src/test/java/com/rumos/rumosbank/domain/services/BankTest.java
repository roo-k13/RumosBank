package com.rumos.rumosbank.domain.services;

import com.rumos.rumosbank.domain.Bank;
import org.junit.jupiter.api.Test;

class BankTest {

    @Test
    void autenticateTest() {
        Bank.authenticate("josepereira@hotmail.com", "teste");
    }
}