package com.rumos.rumosbank.bankapp.controllers;

import com.rumos.rumosbank.bankapp.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public abstract class NavigationBarController {

    @FXML
    protected void onAccountsButtonClick(ActionEvent actionEvent) {
        try { App.changeScene(actionEvent, "/fxml/accounts.fxml"); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }

    @FXML
    protected void onEditProfileButtonClick(ActionEvent actionEvent) {
        try { App.changeScene(actionEvent, "/fxml/update_profile.fxml"); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }

    @FXML
    protected void onChangePasswordButtonClick(ActionEvent actionEvent) {
        try { App.changeScene(actionEvent, "/fxml/login.fxml"); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }

    @FXML
    protected void onLogoutButtonClick(ActionEvent actionEvent) {
        try {
            App.setAuthenticatedClient(null);
            App.changeScene(actionEvent, "/fxml/login.fxml");
        }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }
}
