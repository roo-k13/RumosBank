package com.rumos.rumosbank.domain.repositories;

import com.rumos.rumosbank.domain.models.BankAccount;
import com.rumos.rumosbank.domain.shared.Generators;

import org.junit.jupiter.api.Test;

class BankAccountRepositoryTest {

    @Test
    void insert() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setNumber(new Generators().generateRandomNumber(9));
        new BankAccountRepository().insert(bankAccount);
    }
}