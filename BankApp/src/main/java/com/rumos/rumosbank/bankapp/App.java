package com.rumos.rumosbank.bankapp;

import com.rumos.rumosbank.domain.models.Client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private static Client authenticatedClient;

    public static Client getAuthenticatedClient() {
        return authenticatedClient;
    }

    public static void setAuthenticatedClient(Client authenticatedClient) {
        App.authenticatedClient = authenticatedClient;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Rumos Bank");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void changeScene(ActionEvent event, String path) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(path));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}