package com.rumos.rumosbank.domain.services;

import org.junit.jupiter.api.Test;

class BankTest {

    @Test
    void autenticateTest() {
        Bank.instance.authenticate("josepereira@hotmail.com", "teste");
    }
}