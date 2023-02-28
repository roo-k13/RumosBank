package com.rumos.rumosbank.domain.models.movements;

import com.rumos.rumosbank.domain.models.BankAccount;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Operation extends Movement {

    @ManyToOne
    @JoinColumn(name = "account_id")
    private BankAccount bankAccount;

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
