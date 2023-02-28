package com.rumos.rumosbank.bankapp.controllers;

import com.rumos.rumosbank.bankapp.App;
import com.rumos.rumosbank.domain.models.BankAccount;
import com.rumos.rumosbank.domain.models.movements.Movement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class AccountController {

    @FXML
    private void initialize() {
        initializeAccountsChoiceBox();
        initializeMovementsTable();
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
    private void onSelectingBankAccount(@SuppressWarnings("unused") ActionEvent actionEvent) {
        BankAccount bankAccount = bank_accounts_choice_box.getValue();
        ObservableList<Movement> movements = FXCollections.observableArrayList(bankAccount.getMovements());
        movements_table_view.setItems(movements);
    }

    @FXML
    private void onReturnButtonClick(ActionEvent actionEvent) {
        try { App.changeScene(actionEvent, "/fxml/menu.fxml"); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }
}
