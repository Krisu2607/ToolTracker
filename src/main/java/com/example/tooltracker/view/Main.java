package com.example.tooltracker.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }




    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/tooltracker/UserLoggin.fxml"));
        primaryStage.setScene(new Scene(root, 300, 400));
        primaryStage.setTitle("Aplikacja do zarzadzania narzedziami");
        primaryStage.setResizable(true);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/tooltracker/icons/applogo2.png")));
        primaryStage.centerOnScreen();

        primaryStage.show();
    }
}


