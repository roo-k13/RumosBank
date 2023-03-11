package com.rumos.rumosbank.atmapp.controllers;

import com.rumos.rumosbank.atmapp.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.text.NumberFormat;

public class MenuController extends NavigationBarController {

    @FXML
    private Label account_balance_label;
    @FXML
    private void initialize() { setAccountBalance(); }

    /* ------------------------------------------------------------ Options ------------------------------------------------------------ */

    private void setAccountBalance() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String balance = formatter.format(App.getAuthenticatedAccount().getBalance());
        account_balance_label.setText("Balance: " + balance);
    }
}
