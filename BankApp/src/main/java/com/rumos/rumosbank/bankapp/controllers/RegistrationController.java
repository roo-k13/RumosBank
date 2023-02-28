package com.rumos.rumosbank.bankapp.controllers;

import com.rumos.rumosbank.domain.models.Client;
import com.rumos.rumosbank.domain.services.Bank;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegistrationController {

    @FXML
    private TextField first_name_text_field;
    @FXML
    private TextField last_name_text_field;
    @FXML
    private DatePicker birthdate_date_picker;
    @FXML
    private TextField email_text_field;
    @FXML
    private TextField insert_password_text_field;
    @FXML
    private TextField confirm_password_text_field;
    @FXML
    private Label feedback_message_label;

    private TextField[] textFields;

    @FXML
    private void initialize() {
        textFields = new TextField[] {
                first_name_text_field, last_name_text_field, email_text_field,
                insert_password_text_field, confirm_password_text_field
        };
    }

    private boolean isAnyTextFieldEmpty() {
        for (TextField textField : textFields) {
            if (textField.getText().isEmpty()) { return true; }
        }
        return false;
    }

    @FXML
    protected void onRegisterButtonClick() {
        if (isAnyTextFieldEmpty()) {
            feedback_message_label.setText("Please fill all the fields before confirming your registration");
        }

        else {
            Client client = new Client();
            client.setFirstName(first_name_text_field.getText());
            client.setLastName(last_name_text_field.getText());
            client.setBirthdate(birthdate_date_picker.getValue());
            client.setEmailAddress(email_text_field.getText());
            client.setPassword(insert_password_text_field.getText());
            new Bank().registerNewClient(client);
        }
    }
}