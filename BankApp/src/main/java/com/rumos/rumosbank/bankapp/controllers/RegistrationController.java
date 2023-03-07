package com.rumos.rumosbank.bankapp.controllers;

import com.rumos.rumosbank.domain.models.Client;
import com.rumos.rumosbank.domain.services.Bank;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController extends NavigationBarController {

    @FXML
    private TextField first_name_text_field;
    @FXML
    private TextField last_name_text_field;
    @FXML
    private DatePicker birthdate_date_picker;
    @FXML
    private TextField email_text_field;
    @FXML
    public TextField nif_text_field;
    @FXML
    public TextField phone_text_field;
    @FXML
    public TextField mobile_phone_text_field;
    @FXML
    public TextField profession_text_field;
    @FXML
    private PasswordField insert_password_password_field;
    @FXML
    private PasswordField confirm_password_password_field;
    @FXML
    private Label feedback_message_label;

    private TextField[] textFields;
    private PasswordField[] passwordFields;

    @FXML
    private void initialize() {
        textFields = new TextField[] {
                first_name_text_field, last_name_text_field, email_text_field, nif_text_field, phone_text_field,
                mobile_phone_text_field, profession_text_field,
        };
        passwordFields = new PasswordField[] { insert_password_password_field, confirm_password_password_field};
        feedback_message_label.setVisible(false);
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
            client.setNif(nif_text_field.getText());
            client.setEmailAddress(email_text_field.getText());
            client.setPhone(phone_text_field.getText());
            client.setMobilePhone(mobile_phone_text_field.getText());
            client.setProfession(profession_text_field.getText());
            client.setPassword(insert_password_password_field.getText());
            new Bank().registerNewClient(client);
        }
    }
}