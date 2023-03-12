package com.rumos.rumosbank.domain.models.cards;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rumos.rumosbank.domain.models.BankAccount;

class CardTest {

    private Card card;
    private BankAccount bankAccount;

    @BeforeEach
    void setUp() {
        card = new DebitCard();
        bankAccount = new BankAccount();
        card.setBankAccount(bankAccount);
        card.setExpirationDate(LocalDate.now().plusYears(5));
        card.setNumber("123456789");
        card.setPin("1234");
    }

    @Test
    void testSetNumber() {
        assertThrows(IllegalArgumentException.class, () -> card.setNumber("12345678"));
        assertThrows(IllegalArgumentException.class, () -> card.setNumber("1234567890"));
        assertDoesNotThrow(() -> card.setNumber("123456789"));
        assertEquals("123456789", card.getNumber());
    }

    @Test
    void testSetPin() {
        assertThrows(IllegalArgumentException.class, () -> card.setPin("123"));
        assertThrows(IllegalArgumentException.class, () -> card.setPin("12345"));
        assertDoesNotThrow(() -> card.setPin("5678"));
        assertEquals("5678", card.getPin());
        assertTrue(card.hasPinBeenChanged());
    }

    @Test
    void testIsPinCorrect() {
        assertTrue(card.isPinCorrect("1234"));
        assertFalse(card.isPinCorrect("4321"));
    }

    @Test
    void testGetBankAccount() {
        assertSame(bankAccount, card.getBankAccount());
    }

    @Test
    void testGetExpirationDate() {
        assertEquals(LocalDate.now().plusYears(5), card.getExpirationDate());
    }

}