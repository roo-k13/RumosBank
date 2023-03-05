package com.rumos.rumosbank.bankapp.controllers;

import com.rumos.rumosbank.domain.models.BankAccount;
import com.rumos.rumosbank.domain.models.cards.Card;
import com.rumos.rumosbank.domain.services.Bank;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CardsController extends NavigationBarController{

    @FXML
    private ChoiceBox<BankAccount> bank_accounts_choice_box;
    @FXML
    private TableView<Card> accounts_table_view;
    @FXML
    private TableColumn<Card, String> card_type;
    @FXML
    private TableColumn<Card, String> card_date;

    @FXML
    private void onCreateNewDebitCardButtonClick() {
        BankAccount bankAccount = new BankAccount();
        Bank.instance.createNewDebitCard(bankAccount);
    }

    @FXML
    private void onCreateNewCreditCardButtonClick() {
        BankAccount bankAccount = new BankAccount();
        Bank.instance.createNewCreditCard(bankAccount);
    }
}
