package com.rumos.rumosbank.bankapp.controllers;

import com.rumos.rumosbank.bankapp.App;
import com.rumos.rumosbank.domain.models.Client;
import com.rumos.rumosbank.domain.Bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController extends NavigationBarController {
    private TextField[] textFields;
    private PasswordField[] passwordFields;

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

    @FXML
    private void initialize() {
        initializeTextFields();
        initializePasswordFields();
        feedback_message_label.setVisible(false);
    }

    private void initializeTextFields() {
        textFields = new TextField[] { first_name_text_field, last_name_text_field, email_text_field,
                nif_text_field, phone_text_field, mobile_phone_text_field, profession_text_field };
    }

    private void initializePasswordFields() {
        passwordFields = new PasswordField[] { insert_password_password_field, confirm_password_password_field };
    }

    private boolean isAnyTextFieldEmpty() {
        for (TextField textField : textFields) {
            if (textField.getText().isEmpty()) return true; }
        return false;
    }

    private boolean isAnyPasswordFieldEmpty() {
        for (PasswordField passwordField : passwordFields) {
            if (passwordField.getText().isEmpty()) return true; }
        return false;
    }

    private void trimTextFields() {
        for (TextField textField : textFields) {
            textField.setText(textField.getText().trim());
        }
    }

    private Client buildClient()  {
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
        return client;
    }

    @FXML
    protected void onRegisterButtonClick(ActionEvent actionEvent) {
        if (isAnyTextFieldEmpty() || isAnyPasswordFieldEmpty()) {
            feedback_message_label.setText("Please fill all the fields before confirming your registration");
            return;
        }

        trimTextFields();

        try {
            new Bank().registerClient(buildClient());
        } catch (Exception exception) {
            feedback_message_label.setVisible(true);
            feedback_message_label.setText(exception.getMessage());
            return;
        }

        try { App.changeScene(actionEvent, "/fxml/login.fxml"); }
        catch (Exception exception) { exception.printStackTrace(); }
    }
}