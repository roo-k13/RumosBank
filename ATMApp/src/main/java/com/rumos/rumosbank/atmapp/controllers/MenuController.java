package com.rumos.rumosbank.atmapp.controllers;

import com.rumos.rumosbank.atmapp.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class MenuController {

    @FXML
    private Label account_balance_label;
    @FXML
    private void initialize() { OperationsController.setAccountBalance(account_balance_label); }

    /* ------------------------------------------------------------ Options ------------------------------------------------------------ */

    @FXML
    private void onWithdrawButtonClick(ActionEvent event) {
        OperationsController.selectedOperation = "Withdraw";
        try { App.changeScene(event, "/fxml/operation.fxml"); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }

    @FXML
    private void onDepositButtonClick(ActionEvent event) {
        OperationsController.selectedOperation = "Deposit";
        try { App.changeScene(event, "/fxml/operation.fxml"); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }

    @FXML
    private void onChangePinButtonClick(ActionEvent event) {
        try { App.changeScene(event, "/fxml/change_pin.fxml"); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }

    @FXML
    private void onLogoutButtonClick(ActionEvent event) {
        App.authenticatedAccount = null;
        App.authenticatedCard = null;
        try { App.changeScene(event, "/fxml/login.fxml"); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }
}
