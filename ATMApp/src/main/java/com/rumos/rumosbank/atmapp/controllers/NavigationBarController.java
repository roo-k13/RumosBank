package com.rumos.rumosbank.atmapp.controllers;

import com.rumos.rumosbank.atmapp.App;
import javafx.event.ActionEvent;

import java.io.IOException;

public class NavigationBarController {

    protected static void logout(ActionEvent actionEvent) {
        App.setAuthenticatedAccount(null);
        App.setAuthenticatedCard(null);
        try { App.changeScene(actionEvent, "/fxml/login.fxml"); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }
}
