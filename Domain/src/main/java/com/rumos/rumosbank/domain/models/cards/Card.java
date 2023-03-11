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
    @Column(name = "default_pin_changed")
    private boolean hasPinBeenChanged;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private BankAccount bankAccount;

    public String getNumber() {
        return number;
    }

    public boolean getHasPinBeenChanged() {
        return hasPinBeenChanged;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPin(String pin) {
        if (pin.length() != 4) throw new IllegalArgumentException("The pin must be exactly 4 digits long");
        this.pin = pin;
        hasPinBeenChanged = true;
    }

    public void setHasPinBeenChanged(boolean hasPinBeenChanged) {
        this.hasPinBeenChanged = hasPinBeenChanged;
    }

    public boolean isPinCorrect(String pin) {
        return Objects.equals(this.pin, pin);
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
