package com.rumos.rumosbank.atmapp.controllers;

import com.rumos.rumosbank.atmapp.App;
import com.rumos.rumosbank.domain.services.ATM;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField card_number_text_field;
    @FXML
    private PasswordField card_pin_password_field;

    @FXML
    private void onLoginButtonClick(ActionEvent actionEvent) {
        String cardNumber = card_number_text_field.getText();
        String cardPin = card_pin_password_field.getText();
        App.authenticatedCard = ATM.instance.authenticate(cardNumber, cardPin);

        //TODO: FIX BUG WHERE I CANT LOGIN WITH CREDIT CARD

        if (App.authenticatedCard != null) {
            App.authenticatedAccount = App.authenticatedCard.getBankAccount();
            try {
                String path;
                if (!App.authenticatedCard.hasPinBeenChanged()) { path = "/fxml/change_pin.fxml"; }
                else { path = "/fxml/menu.fxml"; }
                App.changeScene(actionEvent, path);
            } catch (IOException exception) { throw new RuntimeException(exception); }
        }
    }
}