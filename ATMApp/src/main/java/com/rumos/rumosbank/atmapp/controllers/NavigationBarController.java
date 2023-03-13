package com.rumos.rumosbank.atmapp.controllers;

import com.rumos.rumosbank.atmapp.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class NavigationBarController extends AbstractController {
    private static final String LOGIN_PATH = "/fxml/login.fxml";
    private static final String OPERATIONS_PATH = "/fxml/operations.fxml";
    private static final String CHANGE_PIN_PATH = "/fxml/change_pin.fxml";

    @FXML
    private void onWithdrawButtonClick(ActionEvent actionEvent) {
        setSelectedOperation("Withdraw");
        try {
            changeScene(actionEvent, OPERATIONS_PATH);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    private void onDepositButtonClick(ActionEvent actionEvent) {
        setSelectedOperation("Deposit");
        try {
            changeScene(actionEvent, OPERATIONS_PATH);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    private void onChangePinButtonClick(ActionEvent actionEvent) {
        try {
            changeScene(actionEvent, CHANGE_PIN_PATH);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    protected final void onLogoutButtonClick(ActionEvent actionEvent) {
        App.setAuthenticatedAccount(null);
        App.setAuthenticatedCard(null);
        try {
            changeScene(actionEvent, LOGIN_PATH);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
