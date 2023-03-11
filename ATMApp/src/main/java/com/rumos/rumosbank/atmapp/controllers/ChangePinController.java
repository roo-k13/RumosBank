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

    @FXML
    private void onSavePinButtonClick(ActionEvent actionEvent) {
        if (areFieldsValid()) {
            updatePin(actionEvent);
        }
    }

    private boolean areFieldsValid() {
        String insertPin = insert_pin_text_field.getText();
        String confirmPin = confirm_pin_text_field.getText();

        if (isAnyFieldEmpty(insertPin, confirmPin)) {
            showFeedback("Please fill both fields!");
            return false;
        }

        if (!areFieldsMatching(insertPin, confirmPin)) {
            showFeedback("The passwords do not match");
            return false;
        }

        return true;
    }

    private boolean isAnyFieldEmpty(String insertPin, String confirmPin) {
        return insertPin.isEmpty() || confirmPin.isEmpty();
    }

    private boolean areFieldsMatching(String insertPin, String confirmPin) {
        return insertPin.equals(confirmPin);
    }

    private void showFeedback(String message) {
        feedback_message_label.setText(message);
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