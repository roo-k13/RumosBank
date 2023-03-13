package com.rumos.rumosbank.atmapp.controllers;

import com.rumos.rumosbank.atmapp.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class AbstractController {
    private String selectedOperation;

    protected static void changeScene(ActionEvent event, String path) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(path));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    public final String getSelectedOperation() {
        return selectedOperation;
    }

    public final void setSelectedOperation(String selectedOperation) {
        this.selectedOperation = selectedOperation;
    }
}
