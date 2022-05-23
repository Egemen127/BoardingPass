package com.example.boardingpass;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
    //loading the fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        AnchorPane root = fxmlLoader.load();
        //adding root to the scene
        Scene scene = new Scene(root);
        //setting the stage
        stage.setTitle("Ticket Generator");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/img.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}