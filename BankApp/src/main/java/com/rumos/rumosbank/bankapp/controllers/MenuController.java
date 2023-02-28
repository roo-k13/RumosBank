package com.rumos.rumosbank.bankapp.controllers;

import com.rumos.rumosbank.bankapp.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class MenuController {

    @FXML
    private void onAccountsButtonClick(ActionEvent actionEvent) {
        try { App.changeScene(actionEvent, "/fxml/account.fxml"); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }

    @FXML
    private void onUpdateInformationButtonClick(ActionEvent actionEvent) {
        try { App.changeScene(actionEvent, "/fxml/update_info.fxml"); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }

    @FXML
    private void onChangePasswordButtonClick(ActionEvent actionEvent) {
        try { App.changeScene(actionEvent, "/fxml/change_password.fxml"); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }

    @FXML
    private void onLogoutButtonClick(ActionEvent event) {
        App.setAuthenticatedClient(null);
        try { App.changeScene(event, "/fxml/login.fxml"); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }
}
