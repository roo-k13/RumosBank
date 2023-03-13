package com.rumos.rumosbank.domain.models.movements;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class MovementTest {

    @Test
    final void testSetAmountWithBigDecimal() {
        Movement movement = new MovementImpl();
        BigDecimal amount = new BigDecimal("100.00");
        movement.setAmount(amount);
        Assertions.assertEquals(amount, movement.getAmount(), "");
    }

    @Test
    final void testSetAmountWithInt() {
        Movement movement = new MovementImpl();
        int amount = 100;
        movement.setAmount(amount);
        Assertions.assertEquals(new BigDecimal(amount), movement.getAmount(), "");
    }

    @Test
    final void testSetAmountWithZero() {
        Movement movement = new MovementImpl();
        Assertions.assertThrows(IllegalArgumentException.class, () -> movement.setAmount(0));
    }

    @Test
    final void testSetAmountWithNegativeValue() {
        Movement movement = new MovementImpl();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Integer amount = -100;
            movement.setAmount(amount);
        });
    }

    private static class MovementImpl extends Movement {
        // Empty subclass for testing purposes
    }
}