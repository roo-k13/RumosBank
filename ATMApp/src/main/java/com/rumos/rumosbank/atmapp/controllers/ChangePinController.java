package com.rumos.rumosbank.atmapp.controllers;

import com.rumos.rumosbank.atmapp.App;
import com.rumos.rumosbank.domain.models.cards.CreditCard;
import com.rumos.rumosbank.domain.models.cards.DebitCard;
import com.rumos.rumosbank.domain.repositories.CreditCardRepository;
import com.rumos.rumosbank.domain.repositories.DebitCardRepository;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

public class ChangePinController {

    @FXML
    private PasswordField insert_pin_text_field;
    @FXML
    private PasswordField confirm_pin_text_field;
    @FXML
    private TextField feedback_text_field;

    @FXML
    private void onSavePinButtonClick(ActionEvent event) {
        if (Objects.equals(insert_pin_text_field.getText(), confirm_pin_text_field.getText())) {
            try {
                App.getAuthenticatedCard().setPin(insert_pin_text_field.getText());
                if (App.getAuthenticatedCard() instanceof DebitCard) { new DebitCardRepository().update((DebitCard) App.getAuthenticatedCard()); }
                else if (App.getAuthenticatedCard() instanceof CreditCard) { new CreditCardRepository().update((CreditCard) App.getAuthenticatedCard()); }
                try { App.changeScene(event, "/fxml/menu.fxml"); }
                catch (IOException exception) { throw new RuntimeException(exception); }
            } catch (IllegalArgumentException exception) { feedback_text_field.setText(exception.getMessage());}
        }
    }
}
