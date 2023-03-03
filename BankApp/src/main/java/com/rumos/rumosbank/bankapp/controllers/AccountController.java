package com.rumos.rumosbank.bankapp.controllers;

import com.rumos.rumosbank.bankapp.App;
import com.rumos.rumosbank.domain.models.BankAccount;
import com.rumos.rumosbank.domain.models.movements.Movement;
import com.rumos.rumosbank.domain.services.Bank;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.util.List;

public class AccountController {
    private BankAccount selectedAccount;

    @FXML
    private void initialize() {
        initializeAccountsChoiceBox();
        initializeMovementsTable();
    }

    /* ------------------------------------------------------------ Navigation Bar ------------------------------------------------------------ */

    @FXML
    private void onLogoutButtonClick(ActionEvent actionEvent) {
        NavigationBarController.logout(actionEvent);
    }

    @FXML
    private void onAccountsButtonClick(ActionEvent actionEvent) {
        NavigationBarController.accounts(actionEvent);
    }

    @FXML
    private void onEditProfileButtonClick(ActionEvent actionEvent) {
        NavigationBarController.profile(actionEvent);
    }

    @FXML
    private void onChangePasswordButtonClick(ActionEvent actionEvent) {
        NavigationBarController.password(actionEvent);
    }

    /* ------------------------------------------------------------ Accounts Choice Box ------------------------------------------------------------ */

    @FXML
    private ChoiceBox<BankAccount> bank_accounts_choice_box;

    @FXML
    private void initializeAccountsChoiceBox() {
        bank_accounts_choice_box.setOnAction((this::onSelectingBankAccount));
        List<BankAccount> bankAccounts = App.getAuthenticatedClient().getBankAccounts();
        bank_accounts_choice_box.setItems(FXCollections.observableList(bankAccounts));
    }

    /* ------------------------------------------------------------ Movements Table ------------------------------------------------------------ */

    @FXML
    private TableView<Movement> movements_table_view;
    @FXML
    private TableColumn<Movement, String> movement_type;
    @FXML
    private TableColumn<Movement, String> movement_amount;
    @FXML
    private TableColumn<Movement, String> movement_date;

    @FXML
    private void initializeMovementsTable() {
        movement_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        movement_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        movement_date.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
    }

    @FXML
    private void onSelectingBankAccount(ActionEvent actionEvent) {
        selectedAccount = bank_accounts_choice_box.getValue();
        ObservableList<Movement> movements = FXCollections.observableArrayList(selectedAccount.getMovements());
        movements_table_view.setItems(movements);
    }

    /* ------------------------------------------------------------ Transfers ------------------------------------------------------------ */

    @FXML
    private TextField transfer_amount_text_field;
    @FXML
    private TextField transfer_receiver_text_field;

    @FXML
    private void onTransferButtonClick() {
        BigDecimal amount = new BigDecimal(transfer_amount_text_field.getText());
        String receiverAccountNumber = transfer_receiver_text_field.getText();
        Bank.instance.transfer(selectedAccount, receiverAccountNumber, amount);
    }
}
