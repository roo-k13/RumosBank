package com.rumos.rumosbank.atmapp.controllers;

import java.io.IOException;

import com.rumos.rumosbank.domain.ATM;
import com.rumos.rumosbank.domain.models.cards.Card;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class ChangePinController extends AbstractController {

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

    private static boolean isAnyFieldEmpty(CharSequence insertPin, CharSequence confirmPin) {
        return insertPin.isEmpty() || confirmPin.isEmpty();
    }

    private static boolean areFieldsMatching(String insertPin, String confirmPin) {
        return insertPin.equals(confirmPin);
    }

    private void showFeedback(String message) {
        feedback_message_label.setText(message);
    }

    private void updatePin(ActionEvent actionEvent) {
        try {
            Card authenticatedCard = getCard();
            authenticatedCard.setPin(insert_pin_text_field.getText());
            updateCardInRepository(authenticatedCard);
            navigateToMenu(actionEvent);
        } catch (IllegalArgumentException exception) {
            showFeedback(exception.getMessage());
        }
    }

    private static void updateCardInRepository(Card card) {
        ATM.updateCard(card);
    }

    private void navigateToMenu(ActionEvent actionEvent) {
        try {
            changeScene(actionEvent, "/fxml/index.fxml");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}