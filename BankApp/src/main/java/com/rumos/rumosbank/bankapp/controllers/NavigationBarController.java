package com.rumos.rumosbank.bankapp.controllers;

import com.rumos.rumosbank.bankapp.App;

import javafx.event.ActionEvent;

import java.io.IOException;

public class NavigationBarController {

    protected static void accounts(ActionEvent actionEvent) {
        try { App.changeScene(actionEvent, "/fxml/accounts.fxml"); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }

    protected static void profile(ActionEvent actionEvent) {
        try { App.changeScene(actionEvent, "/fxml/update_profile.fxml"); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }

    protected static void password(ActionEvent actionEvent) {
        try { App.changeScene(actionEvent, "/fxml/login.fxml"); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }

    protected static void logout(ActionEvent actionEvent) {
        try {
            App.setAuthenticatedClient(null);
            App.changeScene(actionEvent, "/fxml/login.fxml");
        }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }
}
