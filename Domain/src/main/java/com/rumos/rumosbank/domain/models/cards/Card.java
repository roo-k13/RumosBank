package com.rumos.rumosbank.domain.models.cards;

import com.rumos.rumosbank.domain.models.BankAccount;
import jakarta.persistence.*;

import java.util.Objects;

@MappedSuperclass
public abstract class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    protected String number;
    private String pin;
    private boolean hasPinBeenChanged;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bankaccount_id", referencedColumnName = "id")
    private BankAccount bankAccount;

    /* ------------------------------------------------------------ Pin ------------------------------------------------------------ */

    public boolean hasPinBeenChanged() {
        return hasPinBeenChanged;
    }

    public boolean isPinCorrect(String pin) {
        return Objects.equals(this.pin, pin);
    }

    public void setPin(String pin) {
        if (pin.length() != 4)
            throw new IllegalArgumentException("The pin must be exactly 4 digits long");
        this.pin = pin;
        hasPinBeenChanged = true;
    }

    /* ------------------------------------------------------------ Bank Account ------------------------------------------------------------ */

    public BankAccount getBankAccount() {
        return bankAccount;
    }
}
