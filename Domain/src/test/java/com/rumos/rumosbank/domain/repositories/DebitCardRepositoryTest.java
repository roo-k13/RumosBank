package com.rumos.rumosbank.domain.repositories;

import com.rumos.rumosbank.domain.models.cards.DebitCard;
import com.rumos.rumosbank.domain.repositories.DebitCardRepository;
import org.junit.jupiter.api.Test;

class DebitCardRepositoryTest {

    @Test
    void get() {
        String cardNumber = "547896547";
        DebitCard debitCard = new DebitCardRepository().getByNumber(cardNumber);
    }
}