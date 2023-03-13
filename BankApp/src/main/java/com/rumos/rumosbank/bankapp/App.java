package com.rumos.rumosbank.bankapp;

import com.rumos.rumosbank.domain.models.Client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class App extends Application {
    private static final double STAGE_WIDTH = 600.0;
    private static final double STAGE_HEIGHT = 400.0;

    @Override
    public final void start(Stage stage) throws IOException {
        URL url = App.class.getResource(Paths.LOGIN_PATH);
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        stage.setTitle("Rumos Bank");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void changeScene(ActionEvent event, String path) throws IOException {
        URL url = App.class.getResource(path);
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent, STAGE_WIDTH, STAGE_HEIGHT);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}