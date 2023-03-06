package com.rumos.rumosbank.bankapp.controllers;

import com.rumos.rumosbank.bankapp.App;
import com.rumos.rumosbank.domain.models.Client;
import com.rumos.rumosbank.domain.services.Bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UpdateInfoController extends NavigationBarController {
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
    private Label feedback_message_label;

    @FXML
    private void initialize() {
        client = App.getAuthenticatedClient();
        initializeFields();
        feedback_message_label.setVisible(false);

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
        feedback_message_label.setVisible(true);
        feedback_message_label.setText("Your profile has been successfully updated");
        try { App.changeScene(actionEvent, "/fxml/login.fxml"); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }
}