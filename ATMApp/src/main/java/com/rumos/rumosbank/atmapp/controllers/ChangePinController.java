package com.rumos.rumosbank.atmapp.controllers;

import java.io.IOException;

import com.rumos.rumosbank.atmapp.App;
import com.rumos.rumosbank.domain.models.cards.Card;
import com.rumos.rumosbank.domain.models.cards.CreditCard;
import com.rumos.rumosbank.domain.models.cards.DebitCard;
import com.rumos.rumosbank.domain.repositories.CreditCardRepository;
import com.rumos.rumosbank.domain.repositories.DebitCardRepository;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class ChangePinController {

    @FXML
    private PasswordField insert_pin_text_field;

    @FXML
    private PasswordField confirm_pin_text_field;

    @FXML
    private Label feedback_message_label;

    private boolean areFieldsEmpty() {
        return insert_pin_text_field.getText().isEmpty() || confirm_pin_text_field.getText().isEmpty();
    }

    private boolean doFieldsMatch() {
        return insert_pin_text_field.getText().equals(confirm_pin_text_field.getText());
    }

    private void showFeedback(String message) {
        feedback_message_label.setText(message);
    }

    @FXML
    private void onSavePinButtonClick(ActionEvent actionEvent) {
        if (areFieldsEmpty()) {
            showFeedback("Please fill both fields!");
            return;
        }

        if (!doFieldsMatch()) {
            showFeedback("The passwords do not match");
            return;
        }

        updatePin(actionEvent);
    }

    private void updatePin(ActionEvent actionEvent) {
        try {
            Card authenticatedCard = App.getAuthenticatedCard();
            authenticatedCard.setPin(insert_pin_text_field.getText());
            updateCardInRepository(authenticatedCard);
            navigateToMenu(actionEvent);
        } catch (IllegalArgumentException exception) {
            showFeedback(exception.getMessage());
        }
    }

    private void updateCardInRepository(Card card) {
        if (card instanceof DebitCard) {
            new DebitCardRepository().update((DebitCard) card);
        } else if (card instanceof CreditCard) {
            new CreditCardRepository().update((CreditCard) card);
        }
    }

    private void navigateToMenu(ActionEvent actionEvent) {
        try {
            App.changeScene(actionEvent, "/fxml/index.fxml");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}