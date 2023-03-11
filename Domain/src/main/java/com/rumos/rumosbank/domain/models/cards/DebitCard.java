package com.rumos.rumosbank.domain.models.cards;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "debitcards")
public class DebitCard extends Card {

    @Override
    public String toString() {
        return "Debit Card Nº: " + getNumber();
    }
}
