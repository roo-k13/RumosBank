package com.rumos.rumosbank.atmapp.controllers;

import com.rumos.rumosbank.atmapp.App;
import com.rumos.rumosbank.domain.ATM;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController extends AbstractController {

    @FXML
    private TextField card_number_text_field;
    @FXML
    private PasswordField card_pin_password_field;

    @FXML
    private void onLoginButtonClick(ActionEvent actionEvent) {
        String cardNumber = card_number_text_field.getText();
        String cardPin = card_pin_password_field.getText();
        setCard(ATM.authenticate(cardNumber, cardPin));

        //TODO: FIX BUG WHERE I CANT LOGIN WITH CREDIT CARD

        if (getCard() != null) {
            setBankAccount(getCard().getBankAccount());
            try {
                String path;
                if (!getCard().hasPinBeenChanged()) { path = "/fxml/change_pin.fxml"; }
                else { path = "/fxml/index.fxml"; }
                changeScene(actionEvent, path);
            } catch (IOException exception) { throw new RuntimeException(exception); }
        }
    }
}