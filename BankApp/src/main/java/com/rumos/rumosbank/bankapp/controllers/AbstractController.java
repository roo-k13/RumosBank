package com.rumos.rumosbank.bankapp.controllers;

import com.rumos.rumosbank.bankapp.App;
import com.rumos.rumosbank.domain.Bank;
import com.rumos.rumosbank.domain.models.BankAccount;
import com.rumos.rumosbank.domain.models.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.List;

public class AbstractController {
    private static final Bank bank = Bank.instance;
    private static Client client;

    public final Client getClient() {
        return client;
    }

    public static List<BankAccount> getBankAccounts() {
        return client.getBankAccounts();
    }

    public final void setClient(Client client) {
        AbstractController.client = client;
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
