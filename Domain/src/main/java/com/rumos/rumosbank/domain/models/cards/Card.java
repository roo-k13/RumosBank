package com.rumos.rumosbank.domain.models.cards;

import com.rumos.rumosbank.domain.models.BankAccount;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@MappedSuperclass
public abstract class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private String pin;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "default_pin_changed")
    private boolean hasPinBeenChanged;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private BankAccount bankAccount;

    public String getNumber() {
        return number;
    }

    public String getPin() {
        return pin;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public boolean hasPinBeenChanged() {
        return hasPinBeenChanged;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setNumber(String number) {
        if (number.length() != 9) {
            throw new IllegalArgumentException("Number must be exactly 9 characters long");
        }
        this.number = number;
        hasPinBeenChanged = true;
    }

    public void setPin(String pin) {
        if (pin.length() != 4) {
            throw new IllegalArgumentException("Pin must be exactly 4 characters long");
        }
        this.pin = pin;
        hasPinBeenChanged = true;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setPinBeenChanged(boolean hasPinBeenChanged) {
        this.hasPinBeenChanged = hasPinBeenChanged;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public boolean isPinCorrect(String pin) {
        return Objects.equals(this.pin, pin);
    }
}
