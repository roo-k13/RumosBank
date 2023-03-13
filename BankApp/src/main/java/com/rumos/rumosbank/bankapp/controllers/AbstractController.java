package com.rumos.rumosbank.bankapp.controllers;

import com.rumos.rumosbank.bankapp.App;
import com.rumos.rumosbank.domain.Bank;
import com.rumos.rumosbank.domain.models.BankAccount;
import com.rumos.rumosbank.domain.models.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class AbstractController {
    private static final Bank bank = Bank.instance;
    private Client client;
    private List<BankAccount> bankAccounts;

    public final Client getClient() {
        return client;
    }

    public final List<BankAccount> getBankAccounts() {
        return Collections.unmodifiableList(bankAccounts);
    }

    public final void setClient(Client client) {
        this.client = client;
    }

    public final void setBankAccounts() {
        bankAccounts = client.getBankAccounts();
    }

    public static Bank getBank() {
        return bank;
    }

    @FXML
    protected final void onAccountsButtonClick(ActionEvent actionEvent) {
        try {
            App.changeScene(actionEvent, "/fxml/accounts.fxml");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    protected final void onCardsButtonClick(ActionEvent actionEvent) {
        try {
            App.changeScene(actionEvent, "/fxml/cards.fxml");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    protected final void onEditProfileButtonClick(ActionEvent actionEvent) {
        try {
            App.changeScene(actionEvent, "/fxml/update_profile.fxml");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    protected final void onChangePasswordButtonClick(ActionEvent actionEvent) {
        try {
            App.changeScene(actionEvent, "/fxml/login.fxml");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    protected final void onLogoutButtonClick(ActionEvent actionEvent) {
        try {
            client = null;
            App.changeScene(actionEvent, "/fxml/login.fxml");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
