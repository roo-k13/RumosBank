package com.rumos.rumosbank.atmapp.controllers;

import com.rumos.rumosbank.atmapp.App;
import com.rumos.rumosbank.domain.models.BankAccount;
import com.rumos.rumosbank.domain.models.cards.Card;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AbstractController {
    private String selectedOperation;
    private static Card card;
    private static BankAccount bankAccount;

    public final String getSelectedOperation() {
        return selectedOperation;
    }

    public final BankAccount getBankAccount() {
        return bankAccount;
    }

    public final Card getCard() {
        return card;
    }

    public final void setSelectedOperation(String selectedOperation) {
        this.selectedOperation = selectedOperation;
    }

    public static void setBankAccount(BankAccount bankAccount) {
        AbstractController.bankAccount = bankAccount;
    }

    public static void setCard(Card card) {
        AbstractController.card = card;
    }

    protected static void changeScene(ActionEvent event, String path) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(path));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }
}
