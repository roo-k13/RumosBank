package com.rumos.rumosbank.bankapp.controllers;

import com.rumos.rumosbank.bankapp.App;
import com.rumos.rumosbank.domain.models.BankAccount;
import com.rumos.rumosbank.domain.models.movements.Movement;
import com.rumos.rumosbank.domain.services.Bank;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.util.List;

public class AccountController extends NavigationBarController {
    private BankAccount selectedAccount;

    @FXML
    private void initialize() {
        initializeAccountsChoiceBox();
        initializeMovementsTable();
        feedback_message_label.setVisible(false);
    }

    /* --------------------------------------------------------- Accounts Choice Box ------------------------------------------------------- */

    @FXML
    private ChoiceBox<BankAccount> bank_accounts_choice_box;

    @FXML
    private void initializeAccountsChoiceBox() {
        bank_accounts_choice_box.setOnAction((this::onSelectingBankAccount));
        List<BankAccount> bankAccounts = App.getAuthenticatedClient().getBankAccounts();
        bank_accounts_choice_box.setItems(FXCollections.observableList(bankAccounts));
    }

    /* --------------------------------------------------------- Movements Table --------------------------------------------------------- */

    @FXML
    private TableView<Movement> movements_table_view;
    @FXML
    private TableColumn<Movement, String> movement_type;
    @FXML
    private TableColumn<Movement, String> movement_amount;
    @FXML
    private TableColumn<Movement, String> movement_date;

    @FXML
    private void onSelectingBankAccount(ActionEvent actionEvent) {
        selectedAccount = bank_accounts_choice_box.getValue();
        updateMovements();
    }

    private void initializeMovementsTable() {
        movement_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        movement_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        movement_date.setCellValueFactory(new PropertyValueFactory<>("longDate"));
    }

    private void updateMovements() {
        ObservableList<Movement> movements = FXCollections.observableArrayList(selectedAccount.getMovements());
        movements_table_view.setItems(movements);
    }

    /* ------------------------------------------------------------ Transfers ------------------------------------------------------------ */

    @FXML
    private TextField transfer_account_number_text_field;
    @FXML
    private TextField transfer_amount_text_field;
    @FXML
    private Label feedback_message_label;

    @FXML
    private void onMakeTransferButtonClick() {
        BigDecimal amount = new BigDecimal(transfer_amount_text_field.getText());
        String receiverAccountNumber = transfer_account_number_text_field.getText();
        try {
            Bank.instance.makeTransfer(selectedAccount, receiverAccountNumber, amount);
            Bank.instance.updateMovements(selectedAccount);
            updateMovements();
        } catch (Exception exception) { feedback_message_label.setText(exception.getMessage()); }
    }
}
