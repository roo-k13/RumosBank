package com.rumos.rumosbank.bankapp.controllers;

import com.rumos.rumosbank.domain.models.BankAccount;
import com.rumos.rumosbank.domain.services.Bank;
import javafx.fxml.FXML;

public class CardsController {
    @FXML
    private void onCreateNewDebitCardButtonClick() {
        BankAccount bankAccount = new BankAccount();
        Bank.instance.createNewDebitCard(bankAccount);
    }
}
