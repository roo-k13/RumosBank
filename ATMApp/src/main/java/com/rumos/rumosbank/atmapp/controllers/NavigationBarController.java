package com.rumos.rumosbank.atmapp.controllers;

import com.rumos.rumosbank.atmapp.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public abstract class NavigationBarController {

    protected static void logout(ActionEvent actionEvent) {
        App.setAuthenticatedAccount(null);
        App.setAuthenticatedCard(null);
        try { App.changeScene(actionEvent, "/fxml/login.fxml"); }
        catch (IOException exception) { throw new RuntimeException(exception); }
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
}
