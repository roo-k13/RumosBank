package com.rumos.rumosbank.atmapp.controllers;

import com.rumos.rumosbank.domain.ATM;
import com.rumos.rumosbank.domain.Bank;

import com.rumos.rumosbank.domain.models.BankAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Objects;

public class OperationsController extends NavigationBarController {

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
        setAccountBalance();
        if (Objects.equals(getSelectedOperation(), "Withdraw")) {
            populateButtonsWithWithdrawAmounts();
        }
        else if (Objects.equals(getSelectedOperation(), "Deposit")) {
            populateButtonsWithDepositAmounts();
        }
    }

    protected final void setAccountBalance() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        BankAccount authenticatedAccount = getBankAccount();
        String balance = formatter.format(authenticatedAccount.getBalance());
        account_balance_label.setText("Balance: " + balance);
    }

    @FXML
    private void onOperationButtonClick(ActionEvent actionEvent) {
        Button buttonPressed = (Button)actionEvent.getSource();
        String amountString = buttonPressed.getText().replace("€", "");
        int amountValue = Integer.parseInt(amountString);
        if (Objects.equals(getSelectedOperation(), "Withdraw")) {
            ATM.makeWithdraw(getBankAccount(), BigDecimal.valueOf(amountValue));
        }
        else if (Objects.equals(getSelectedOperation(), "Deposit")) {
            ATM.makeDeposit(getBankAccount(), BigDecimal.valueOf(amountValue));
        }
        Bank.instance.updateMovements(getBankAccount());
        setAccountBalance();
    }

    /* ------------------------------------------------------------ Populate Buttons ------------------------------------------------------------ */

    private void populateButtonsWithWithdrawAmounts() {
        int[] amounts = ATM.getWithdrawAmounts();
        Button[] buttons = new Button[] {
                first_button, second_button, third_button, fourth_button, fifth_button,
                sixth_button, seventh_button, eight_button, ninth_button };
        for (int i = 0; i < buttons.length; i++) { buttons[i].setText(amounts[i] + "€"); }
    }

    private void populateButtonsWithDepositAmounts() {
        int[] amounts = ATM.getDepositAmounts();
        Button[] buttons = {
                first_button, second_button, third_button, fourth_button, fifth_button,
                sixth_button, seventh_button, eight_button, ninth_button };
        for (int i = 0; i < buttons.length; i++) { buttons[i].setText(amounts[i] + "€"); }
    }
}
