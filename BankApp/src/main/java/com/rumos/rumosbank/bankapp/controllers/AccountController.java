package com.rumos.rumosbank.bankapp.controllers;

import java.math.BigDecimal;
import java.util.List;

import com.rumos.rumosbank.bankapp.App;
import com.rumos.rumosbank.domain.models.BankAccount;
import com.rumos.rumosbank.domain.models.Client;
import com.rumos.rumosbank.domain.models.movements.Movement;
import com.rumos.rumosbank.domain.Bank;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public final class AccountController extends NavigationBarController {

    // Fields
    private BankAccount selectedAccount;

    // JavaFX Controls
    @FXML
    private ChoiceBox<BankAccount> bank_accounts_choice_box;
    @FXML
    private TableView<Movement> movements_table_view;
    @FXML
    private TableColumn<Movement, String> movement_type;
    @FXML
    private TableColumn<Movement, String> movement_amount;
    @FXML
    private TableColumn<Movement, String> movement_date;
    @FXML
    private TextField transfer_account_number_text_field;
    @FXML
    private TextField transfer_amount_text_field;
    @FXML
    private Label feedback_message_label;

    // Initialization methods
    @FXML
    private void initialize() {
        Client authenticatedClient = App.getAuthenticatedClient();
        List<BankAccount> bankAccounts = authenticatedClient.getBankAccounts();
        initializeAccountsChoiceBox(bankAccounts);
        initializeMovementsTable();
        feedback_message_label.setVisible(false);
    }

    private void initializeAccountsChoiceBox(List<BankAccount> bankAccounts) {
        bank_accounts_choice_box.setOnAction(this::onSelectingBankAccount);
        ObservableList<BankAccount> observableList = FXCollections.observableList(bankAccounts);
        bank_accounts_choice_box.setItems(observableList);
    }

    private void initializeMovementsTable() {
        movement_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        movement_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        movement_date.setCellValueFactory(new PropertyValueFactory<>("longDate"));
    }

    // Event handler methods
    @FXML
    private void onSelectingBankAccount(ActionEvent actionEvent) {
        selectedAccount = bank_accounts_choice_box.getValue();
        updateMovements();
    }

    @FXML
    private void onMakeTransferButtonClick() {
        BigDecimal amount = getTransferAmount();
        if (amount == null) {
            feedback_message_label.setVisible(true);
            feedback_message_label.setText("Invalid transfer amount.");
            return;
        }

        String receiverAccountNumber = getReceiverAccountNumber();
        if (receiverAccountNumber == null) {
            feedback_message_label.setVisible(true);
            feedback_message_label.setText("Receiver account number is required.");
            return;
        }

        try {
            Bank.instance.makeTransfer(selectedAccount, receiverAccountNumber, amount);
            Bank.instance.updateMovements(selectedAccount);
            updateMovements();
            transferSuccessful();
        } catch (Exception exception) {
            feedback_message_label.setText(exception.getMessage());
        }
    }

    // Helper methods
    private BigDecimal getTransferAmount() {
        try {
            BigDecimal amount = new BigDecimal(transfer_amount_text_field.getText());
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Transfer amount must be a positive number.");
            }
            return amount;
        } catch (NumberFormatException exception) {
            return null;
        }
    }

    private String getReceiverAccountNumber() {
        String receiverAccountNumber = transfer_account_number_text_field.getText().trim();
        if (receiverAccountNumber.isEmpty()) {
            return null;
        }
        return receiverAccountNumber;
    }

    private void transferSuccessful() {
        feedback_message_label.setVisible(true);
        feedback_message_label.setText("Transfer successful.");
        transfer_amount_text_field.clear();
        transfer_account_number_text_field.clear();
    }

    private void updateMovements() {
        ObservableList<Movement> movements = FXCollections.observableArrayList(selectedAccount.getMovements());
        movements_table_view.setItems(movements);
    }
}