package com.rumos.rumosbank.atmapp.controllers;

import com.rumos.rumosbank.atmapp.App;
import com.rumos.rumosbank.domain.services.ATM;
import com.rumos.rumosbank.domain.services.Bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.naming.OperationNotSupportedException;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Objects;

public class OperationsController {
    @FXML
    protected static String selectedOperation;

    @FXML
    private Label account_balance_label;
    @FXML
    private Button first_button;
    @FXML
    private Button second_button;
    @FXML
    private Button third_button;
    @FXML
    private Button fourth_button;
    @FXML
    private Button fifth_button;
    @FXML
    private Button sixth_button;
    @FXML
    private Button seventh_button;
    @FXML
    private Button eight_button;
    @FXML
    private Button ninth_button;

    @FXML
    private void initialize() {
        if (Objects.equals(selectedOperation, "Withdraw")) { populateButtonsWithWithdrawAmounts(); }
        else if (Objects.equals(selectedOperation, "Deposit")) { populateButtonsWithDepositAmounts(); }
    }

    protected static void setAccountBalance(Label label) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String balance = formatter.format(App.authenticatedAccount.getBalance());
        label.setText("Balance: " + balance);
    }

    @FXML
    private void onOperationButtonClick(ActionEvent actionEvent) throws OperationNotSupportedException {
        Button buttonPressed = (Button)actionEvent.getSource();
        String amountString = buttonPressed.getText().replace("€", "");
        int amountValue = Integer.parseInt(amountString);
        if (Objects.equals(selectedOperation, "Withdraw")) {
            ATM.instance.makeWithdraw(App.authenticatedAccount, BigDecimal.valueOf(amountValue));
        }
        else if (Objects.equals(selectedOperation, "Deposit")) {
            ATM.instance.makeDeposit(App.authenticatedAccount, BigDecimal.valueOf(amountValue));
        }
        setAccountBalance(account_balance_label);
        Bank.instance.updateMovements(App.authenticatedAccount);
    }

    /* ------------------------------------------------------------ Populate Buttons ------------------------------------------------------------ */

    private void populateButtonsWithWithdrawAmounts() {
        int[] amounts = ATM.instance.getWithdrawAmounts();
        Button[] buttons = new Button[] {
                first_button, second_button, third_button, fourth_button, fifth_button,
                sixth_button, seventh_button, eight_button, ninth_button };
        for (int i = 0; i < buttons.length; i++) { buttons[i].setText(amounts[i] + "€"); }
    }

    private void populateButtonsWithDepositAmounts() {
        int[] amounts = ATM.instance.getDepositAmounts();
        Button[] buttons = new Button[] {
                first_button, second_button, third_button, fourth_button, fifth_button,
                sixth_button, seventh_button, eight_button, ninth_button };
        for (int i = 0; i < buttons.length; i++) { buttons[i].setText(amounts[i] + "€"); }
    }
}
