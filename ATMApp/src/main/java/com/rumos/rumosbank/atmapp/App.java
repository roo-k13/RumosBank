package com.rumos.rumosbank.atmapp;

import com.rumos.rumosbank.domain.models.BankAccount;
import com.rumos.rumosbank.domain.models.cards.Card;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private static BankAccount authenticatedAccount;
    private static Card authenticatedCard;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Rumos ATM");
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    /* ------------------------------------------------------------ Authenticated Account ------------------------------------------------------------ */

    public static BankAccount getAuthenticatedAccount() {
        return authenticatedAccount;
    }

    public static void setAuthenticatedAccount(BankAccount authenticatedAccount) {
        App.authenticatedAccount = authenticatedAccount;
    }

    /* ------------------------------------------------------------ Authenticated Card ------------------------------------------------------------ */

    public static Card getAuthenticatedCard() {
        return authenticatedCard;
    }

    public static void setAuthenticatedCard(Card authenticatedCard) {
        App.authenticatedCard = authenticatedCard;
    }
}