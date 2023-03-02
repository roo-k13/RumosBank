package com.rumos.rumosbank.bankapp.controllers;

import com.rumos.rumosbank.bankapp.App;
import com.rumos.rumosbank.domain.models.Client;
import com.rumos.rumosbank.domain.services.Bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UpdateInfoController {
    @FXML
    private TextField first_name_text_field;
    @FXML
    private TextField last_name_text_field;
    @FXML
    private TextField birthdate_text_field;
    @FXML
    private TextField nif_text_field;
    @FXML
    private TextField email_text_field;

    @FXML
    void initialize() {
        Client client = App.getAuthenticatedClient();
        first_name_text_field.setText(client.getFirstName());
        last_name_text_field.setText(client.getLastName());
        birthdate_text_field.setText(client.getBirthdate().toString());
        nif_text_field.setText(client.getNif());
        email_text_field.setText(client.getEmailAddress());
    }

    @FXML
    protected void onSaveChangesButtonClick(ActionEvent actionEvent) {
        App.getAuthenticatedClient().setFirstName(first_name_text_field.getText());
        App.getAuthenticatedClient().setLastName(last_name_text_field.getText());
        App.getAuthenticatedClient().setEmailAddress(email_text_field.getText());
        Bank.instance.updateClient(App.getAuthenticatedClient());
        try { App.changeScene(actionEvent, "/fxml/login.fxml"); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }
}