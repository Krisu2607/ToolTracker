package com.example.tooltracker.controller;

import com.example.tooltracker.model.tools.Tool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController  {



    @FXML
    private TableView<Tool> toolTable;

    @FXML
    private void showToolsView(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/ToolsView2.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            stage.setScene(new Scene(root, 1100, 600));
            stage.show();



        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }

    @FXML
    private void showUsageView(ActionEvent event) {

        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/ToolUsageView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root, 800, 600));
            stage.show();



        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }

    }



    @FXML
    private void showLatheView(ActionEvent event) {
        try {
            // Ładowanie widoku tokarki
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/LatheView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root, 800, 600));
            stage.show();



        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }


    @FXML
    private void showMillView(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/MillView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root, 800, 600));
            stage.show();



        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }

    @FXML
    private void showCalcView(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/CalcView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root, 800, 600));
            stage.show();



        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }



    @FXML
    private void showLiveTools(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/liveToolsView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.show();



        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }




}
