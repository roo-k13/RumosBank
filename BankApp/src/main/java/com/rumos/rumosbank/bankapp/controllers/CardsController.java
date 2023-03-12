package com.rumos.rumosbank.bankapp.controllers;

import com.rumos.rumosbank.bankapp.App;
import com.rumos.rumosbank.domain.models.BankAccount;
import com.rumos.rumosbank.domain.models.cards.CreditCard;
import com.rumos.rumosbank.domain.models.cards.DebitCard;
import com.rumos.rumosbank.domain.Bank;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

import java.util.List;

public class CardsController extends NavigationBarController {
    private BankAccount selectedAccount;

    @FXML
    private ChoiceBox<BankAccount> bank_accounts_choice_box;
    @FXML
    private ListView<DebitCard> debit_cards_list_view;
    @FXML
    private ListView<CreditCard> credit_cards_list_view;

    @FXML
    private void initialize() {
        initializeAccountsChoiceBox();
    }

    @FXML
    private void initializeAccountsChoiceBox() {
        bank_accounts_choice_box.setOnAction(this::onSelectingBankAccount);
        List<BankAccount> bankAccounts = App.getAuthenticatedClient().getBankAccounts();
        bank_accounts_choice_box.setItems(FXCollections.observableList(bankAccounts));
    }

    private void onSelectingBankAccount(ActionEvent actionEvent) {
        selectedAccount = bank_accounts_choice_box.getValue();
        updateCards();
    }

    private void updateCards() {
        updateDebitCards();
        updateCreditCards();
    }

    private void updateDebitCards() {
        ObservableList<DebitCard> debitCards = FXCollections.observableArrayList(selectedAccount.getDebitCards());
        debit_cards_list_view.setItems(debitCards);
    }

    private void updateCreditCards() {
        ObservableList<CreditCard> creditCards = FXCollections.observableArrayList(selectedAccount.getCreditCards());
        credit_cards_list_view.setItems(creditCards);
    }

    @FXML
    private void onCreateNewDebitCardButtonClick() {
        Bank.instance.registerDebitCard(selectedAccount);
    }

    @FXML
    private void onCreateNewCreditCardButtonClick() {
        BankAccount bankAccount = new BankAccount();
        Bank.instance.registerCreditCard(selectedAccount);
    }
}
