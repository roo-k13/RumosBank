package com.rumos.rumosbank.domain.models.cards;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "creditcards")
public class CreditCard extends Card {

    @Override
    public String toString() {
        return "Debit Card Nº: " + getNumber();
    }
}
