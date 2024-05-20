package com.example.tooltracker.controller;

import com.example.tooltracker.database.PartDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPartController {
    @FXML
    private TextField partNum;

    @FXML
    Button confirmButton;

    @FXML
    Button cancellButton;

    PartDAO partDAO = new PartDAO();
    private ToolsController toolsController;


    @FXML
    private void handleCancelButton() {
        closeStage();
    }

    @FXML
    private void handleConfirmButton() {
        String partNumber = partNum.getText();

        String regex = "P\\d{2}-\\d{4}(?:-\\d{2})?";

        if (!partNumber.matches(regex)) {
            // ALERT O NIEPRAWIDŁOWYM FORMACIE PARTNUM
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Nieprawidłowy format numeru detalu. Przykładowe formaty: P00-0000-01 lub P00-0000");
            alert.showAndWait();


        } else if (partDAO.isPartNumExists(partNumber)) {
            // ALERT O ISTNIENIU PRZYPISANYCH PLYTEK DO TEGO DETALU
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Detal o numerze: " + partNumber + " istnieje już w bazie danych");
            alert.showAndWait();

        } else {
            // Dodaj płytkę do detalu
            partDAO.addPart(partNumber);

            // komunikat o prawidlowym dodaniu płytek do detalu
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Sukces");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Detal dodany do bazy danych");
            successAlert.showAndWait();
        }


        partNum.clear();


        // Wróć do pierwszego pola tekstowego
        partNum.requestFocus();

    }




    private void closeStage() {
        Stage stage = (Stage) partNum.getScene().getWindow();
        stage.close();
    }

    public void setToolsController(ToolsController toolsController) {

        this.toolsController = toolsController;
    }


}
