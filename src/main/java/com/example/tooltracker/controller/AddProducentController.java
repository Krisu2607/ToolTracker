package com.example.tooltracker.controller;

import com.example.tooltracker.database.PartDAO;
import com.example.tooltracker.database.ProducentDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AddProducentController {

    @FXML
    private TextField producentTF;

    @FXML
    Button confirmButton;

    @FXML
    Button cancellButton;

    ProducentDAO producentDAO = new ProducentDAO();
    private ToolsController toolsController;


    @FXML
    private void handleCancelButton() {
        closeStage();
    }

    @FXML
    private void handleConfirmButton() throws SQLException {
        String producent = producentTF.getText();


            producentDAO.addProducent(producent);

            // komunikat o prawidlowym dodaniu płytek do detalu
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Sukces");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Producent dodany do bazy danych");
            successAlert.showAndWait();



        producentTF.clear();


        producentTF.requestFocus();

    }




    private void closeStage() {
        Stage stage = (Stage) producentTF.getScene().getWindow();
        stage.close();
    }

    public void setToolsController(ToolsController toolsController) {

        this.toolsController = toolsController;
    }
}
