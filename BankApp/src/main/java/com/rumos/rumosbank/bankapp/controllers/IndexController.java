package com.rumos.rumosbank.bankapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class IndexController {
    @FXML
    private void onLogoutButtonClick(ActionEvent actionEvent) {
        NavigationBarController.logout(actionEvent);
    }
}
