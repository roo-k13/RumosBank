package com.rumos.rumosbank.atmapp.controllers;

import com.rumos.rumosbank.atmapp.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.text.NumberFormat;

public class MenuController {

    @FXML
    private Label account_balance_label;
    @FXML
    private void initialize() { setAccountBalance(); }

    /* ------------------------------------------------------------ Options ------------------------------------------------------------ */

    private void setAccountBalance() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String balance = formatter.format(App.authenticatedAccount.getBalance());
        account_balance_label.setText("Balance: " + balance);
    }

    @FXML
    private void onWithdrawButtonClick(ActionEvent actionEvent) {
        OperationsController.selectedOperation = "Withdraw";
        try { App.changeScene(actionEvent, "/fxml/operation.fxml"); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }

    @FXML
    private void onDepositButtonClick(ActionEvent actionEvent) {
        OperationsController.selectedOperation = "Deposit";
        try { App.changeScene(actionEvent, "/fxml/operation.fxml"); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }

    @FXML
    private void onChangePinButtonClick(ActionEvent actionEvent) {
        try { App.changeScene(actionEvent, "/fxml/change_pin.fxml"); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }

    @FXML
    private void onLogoutButtonClick(ActionEvent actionEvent) {
        App.authenticatedAccount = null;
        App.authenticatedCard = null;
        try { App.changeScene(actionEvent, "/fxml/login.fxml"); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }
}
