package com.rumos.rumosbank.bankapp.controllers;

import com.rumos.rumosbank.bankapp.App;
import com.rumos.rumosbank.domain.models.Client;
import com.rumos.rumosbank.domain.services.Bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UpdateInfoController {
    private Client client;

    @FXML
    private TextField first_name_text_field;
    @FXML
    private TextField last_name_text_field;
    @FXML
    private TextField birthdate_text_field;
    @FXML
    private TextField nif_text_field;
    @FXML
    private TextField phone_text_field;
    @FXML
    private TextField mobile_phone_text_field;
    @FXML
    private TextField profession_text_field;
    @FXML
    private TextField email_text_field;

    @FXML
    private void initialize() {
        client = App.getAuthenticatedClient();
        initializeFields();

    }

    /* ----------------------------------------------------- Navigation Bar ----------------------------------------------------- */

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

    @FXML
    private void onLogoutButtonClick(ActionEvent actionEvent) {
        NavigationBarController.logout(actionEvent);
    }

    /* ---------------------------------------------------------- Form ---------------------------------------------------------- */

    private void initializeFields() {
        first_name_text_field.setText(client.getFirstName());
        last_name_text_field.setText(client.getLastName());
        birthdate_text_field.setText(client.getBirthdate().toString());
        nif_text_field.setText(client.getNif());
        phone_text_field.setText(client.getPhone());
        mobile_phone_text_field.setText(client.getMobilePhone());
        profession_text_field.setText(client.getProfession());
        email_text_field.setText(client.getEmailAddress());
    }

    @FXML
    private void onSaveChangesButtonClick(ActionEvent actionEvent) {
        client.setFirstName(first_name_text_field.getText());
        client.setLastName(last_name_text_field.getText());
        client.setPhone(phone_text_field.getText());
        client.setMobilePhone(mobile_phone_text_field.getText());
        client.setProfession(profession_text_field.getText());
        client.setEmailAddress(email_text_field.getText());
        Bank.instance.updateClient(client);
        try { App.changeScene(actionEvent, "/fxml/login.fxml"); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }
}