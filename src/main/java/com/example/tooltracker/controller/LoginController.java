package com.example.tooltracker.controller;

import com.example.tooltracker.controller.newtoolcontrollers.AddVariousToolController;
import com.example.tooltracker.database.UsersDAO;
import com.example.tooltracker.model.LoggedUser;
import com.example.tooltracker.model.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorMessage;

    private UsersDAO userDAO = new UsersDAO();

    @FXML
    private void handleLogin() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        Users user = userDAO.getUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            // Ustaw użytkownika jako zalogowanego
            LoggedUser.setUser(user);
            // Zamknij okno logowania
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.close();
            // Otwórz główne okno aplikacji
            openMainWindow();
        } else {
            errorMessage.setText("Nieprawidłowy login lub hasło");
        }
    }

    private void openMainWindow( ) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/tooltracker/StartView.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 770, 500));
        stage.setTitle("Aplikacja do zarzadzania narzedziami");
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();
    }
}
