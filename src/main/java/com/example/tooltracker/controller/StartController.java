package com.example.tooltracker.controller;

import com.example.tooltracker.database.MessageDAO;
import com.example.tooltracker.model.LoggedUser;
import com.example.tooltracker.model.Message;
import com.example.tooltracker.model.tools.Tool;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class StartController  {


    @FXML
    private TextArea chatArea;
    @FXML
    private TextField messageField;
    @FXML
    private Button sendButton;


    @FXML
    HBox log;



    @FXML
    private TableView<Tool> toolTable;

    private MessageDAO messageDAO = new MessageDAO();
    private String username = LoggedUser.getUser().getUsername();


    public void initialize() {


//        loadMessages();
        Label welcomeLab = new Label("Witaj, " + LoggedUser.getUser().getUsername());
        welcomeLab.setMinHeight(40);
        welcomeLab.setTextFill(Paint.valueOf("white"));
        log.getChildren().add(welcomeLab);
    }


//    private void loadMessages() {
//        List<Message> messages = messageDAO.getAllMessages();
//        StringBuilder chatContent = new StringBuilder();
//        for (Message message : messages) {
//            chatContent.append("[").append(message.getTimestamp()).append("] ")
//                    .append(message.getUsername()).append(": ")
//                    .append(message.getMessage()).append("\n");
//        }
//        chatArea.setText(chatContent.toString());
//        scrollToBottom();
//    }



//    @FXML
//    private void sendMessage() {
//        String messageText = messageField.getText();
//        if (!messageText.isEmpty()) {
//            messageDAO.addMessage(username, messageText);
//            loadMessages(); // Odswież chat po wysłaniu wiadomości
//            messageField.clear();
//            scrollToBottom();
//        }
//    }
//
//    private void scrollToBottom() {
//        // Opóźnij przewijanie, aby upewnić się, że chatArea zostało zaktualizowane
//        Platform.runLater(() -> {
//            chatArea.setScrollTop(Double.MAX_VALUE);
//        });
//    }

    @FXML
    private void showToolsView(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/ToolsView2.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root, 1100, 600));
            stage.centerOnScreen();
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
            stage.centerOnScreen();

            stage.setScene(new Scene(root, 800, 600));
            stage.centerOnScreen();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/wishView.fxml"));
            Parent root = loader.load();

            // Pobranie obecnej sceny z aktualnego okna
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Tworzenie nowej sceny z ładowanym widokiem
            Scene scene = new Scene(root, 1200, 450);

            // Dodanie pliku CSS do nowej sceny
            String css = this.getClass().getResource("/com/example/tooltracker/wishview.css").toExternalForm();
            scene.getStylesheets().add(css);

            // Ustawienie nowej sceny w oknie
            stage.setScene(scene);
            stage.centerOnScreen();
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

            // Pobieranie aktualnej sceny i okna
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Ustawienie nowej sceny
            stage.setScene(new Scene(root, 700, 600));

            // Wyśrodkowanie okna na ekranie
            stage.centerOnScreen();

            // Wyświetlenie okna
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }


    @FXML
    private void showLogPage(ActionEvent event) throws IOException {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/UserLoggin.fxml"));
            Parent root = loader.load();

            // Pobieranie aktualnej sceny i okna
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Ustawienie nowej sceny
            stage.setScene(new Scene(root, 300, 400));

            // Wyśrodkowanie okna na ekranie
            stage.centerOnScreen();
            stage.setTitle("Aplikacja do zarzadzania narzedziami");

            // Wyświetlenie okna
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }









}
