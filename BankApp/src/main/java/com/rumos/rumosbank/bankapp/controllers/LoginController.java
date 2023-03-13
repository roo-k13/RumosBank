package com.rumos.rumosbank.bankapp.controllers;

import com.rumos.rumosbank.bankapp.App;
import com.rumos.rumosbank.bankapp.Paths;
import com.rumos.rumosbank.domain.models.Client;
import com.rumos.rumosbank.domain.Bank;

import jakarta.persistence.NoResultException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController extends AbstractController {

    private String email;
    private String password;

    @FXML
    private TextField email_text_field;
    @FXML
    private PasswordField password_text_field;
    @FXML
    private Label feedback_message_label;

    @FXML
    private void initialize() {
        feedback_message_label.setVisible(false);
    }

    private void getDetails() {
        if (email_text_field.getText().isEmpty()) {
            throw new IllegalArgumentException("Please fill in the email field");
        }
        if (email_text_field.getText().isEmpty()) {
            throw new IllegalArgumentException("Please fill in the password field");
        }
        email = email_text_field.getText();
        password = password_text_field.getText();
    }

    @FXML
    private void onLoginButtonClick(ActionEvent event) {
        try {
            getDetails();
            Client client = Bank.instance.authenticate(email, password);
            App.setAuthenticatedClient(client);
            App.changeScene(event, "/fxml/index.fxml");
        } catch (NoResultException exception) { feedback_message_label.setText("The details you entered are not valid");
        } catch (IOException exception) { exception.printStackTrace();
        } catch (IllegalArgumentException exception) {feedback_message_label.setText(exception.getMessage()); }
    }

    @FXML
    public final void onRegisterButtonClick(ActionEvent event) {
        try {
            App.changeScene(event, Paths.REGISTRATION_PATH);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
