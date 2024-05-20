package com.example.tooltracker.controller;

import com.example.tooltracker.database.PartDAO;
import com.example.tooltracker.database.ToolInsertDAO;
import com.example.tooltracker.model.ToolInsert;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

public class AddInsertToPartController {

    private ToolsController toolsController;

    @FXML
    private TextField indexTextField;

    @FXML
    private TextField partNum;

    @FXML
    Button confirmButton;

    @FXML
    Button cancellButton;

    @FXML
    Label indexError;




    ToolInsertDAO toolInsertDAO = new ToolInsertDAO();
    PartDAO partDAO = new PartDAO();


    public void initialize() {
        confirmButton.setDisable(true);
        indexTextField.textProperty().addListener((observable, oldValue, newValue) -> checkToolExistence(newValue));
    }


    @FXML
    private void handleCancelButton() {
        closeStage();
    }

    @FXML
    private void handleConfirmButton() {
        String indexName = indexTextField.getText();
        String partNumber = partNum.getText();
        if (toolInsertDAO.isToolInsertExists(partNumber, indexName)) {
            // ALERT O ISTNIENIU PRZYPISANYCH PLYTEK DO TEGO DETALU
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Płytki o indeksie " + indexName + " już jest przypisana do detalu: " + partNumber);
            alert.showAndWait();
        } else if (!partDAO.isPartNumExists(partNumber)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("W bazie danych nie posiadamy detalu o numerze: " + partNumber + " \nDodaj najpierw detal do bazy danych");
            alert.showAndWait();
        }
        else {
            // Dodaj płytkę do detalu
            toolInsertDAO.addInsertToPart(partNumber, indexName);

            // komunikat o prawidlowym dodaniu płytek do detalu
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Sukces");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Płytki dodane do detalu");
            successAlert.showAndWait();
        }


        indexTextField.clear();


        // Wróć do pierwszego pola tekstowego
        indexTextField.requestFocus();

    }




    private void closeStage() {
        Stage stage = (Stage) indexTextField.getScene().getWindow();
        stage.close();
    }

    public void setToolsController(ToolsController toolsController) {

        this.toolsController = toolsController;
    }



    private void checkToolExistence(String insertIndex) {
        List<ToolInsert> allInserts = toolInsertDAO.getAllToolInserts();
        Optional<ToolInsert> toolInsertOptional = allInserts.stream()
                .filter(t -> insertIndex.equalsIgnoreCase(t.getInsertIndex()))
                .findFirst();

        if (toolInsertOptional.isPresent()) {
            // Narzędzie istnieje, można aktywować przycisk "Zatwierdź"
            confirmButton.setDisable(false);
            indexError.setText("✔");
            indexError.setTextFill(Color.GREEN);


        }


        else {
            // Narzędzie nie istnieje, dezaktywuj przycisk "Zatwierdź"
            confirmButton.setDisable(true);
            indexError.setText("Nie posiadamy takich płytek");
            indexError.setTextFill(Color.RED);


        }
    }
}
