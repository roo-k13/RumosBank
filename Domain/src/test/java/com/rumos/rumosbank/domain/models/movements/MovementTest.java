package com.rumos.rumosbank.domain.models.movements;

import org.junit.jupiter.api.Test;

class MovementTest {

    @Test
    void testToString() {
        Deposit deposit = new Deposit();
        deposit.setAmount(500);
        System.out.println(deposit);
    }
}