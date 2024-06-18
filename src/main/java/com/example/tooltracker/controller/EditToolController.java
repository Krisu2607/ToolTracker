package com.example.tooltracker.controller;

import com.example.tooltracker.database.ActionDAO;
import com.example.tooltracker.database.ToolDAO;
import com.example.tooltracker.model.tools.Tool;
import com.example.tooltracker.model.ToolAction;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

public class EditToolController {

    private ToolsController toolsController;



    @FXML
    private TextField indexTextField;

    @FXML
    private CheckBox sharpeningCheckBox;

    @FXML
    private Button confirmButton;
    @FXML
    private Label indexError;

    private ToolDAO toolDAO = new ToolDAO();
    private ActionDAO actionDAO = new ActionDAO();


    public void initialize() {
        // Dodaj listener do pola tekstowego, aby aktywować/zdezaktywować przycisk "Zatwierdź" w zależności od zawartości pola
        indexTextField.textProperty().addListener((observable, oldValue, newValue) ->{
                checkToolExistence(newValue);
        checkSharpeningAvailability(newValue);
                });
        confirmButton.setDisable(true);
    }


    @FXML
    private void handleConfirmButton() {
        String index = indexTextField.getText();
        boolean toSharpen = sharpeningCheckBox.isSelected();
        String newStatus = toSharpen ? "W_OSTRZENIU" : "ZUZYTE";

        toolDAO.updateToolStatus(index, newStatus);
        ToolAction toolAction = new ToolAction();
        toolAction.settAction(newStatus);
        toolAction.settIndex(index);
        actionDAO.addAction(toolAction);

        indexTextField.clear();
        sharpeningCheckBox.setSelected(false);
        indexTextField.requestFocus();
    }





    @FXML
    private void handleCancelButton() {
        closeStage();
    }

    private void closeStage() {
        Stage stage = (Stage) indexTextField.getScene().getWindow();
        stage.close();
    }
    public void setToolsController(ToolsController toolsController) {
        this.toolsController = toolsController;
    }

    private void checkToolExistence(String toolIndex) {
        if (toolDAO.toolExists(toolIndex)) {
            confirmButton.setDisable(false);
            indexError.setText("✔");
            indexError.setTextFill(Color.GREEN);
        } else {
            confirmButton.setDisable(true);
            indexError.setText("Nie posiadamy takiego narzędzia");
            indexError.setTextFill(Color.RED);
        }
    }

    private void checkSharpeningAvailability(String toolIndex) {
        if (toolIndex.length() >= 2) {
            String prefix = toolIndex.substring(0, 2).toUpperCase();
            List<String> validPrefixes = List.of("CM", "DR", "EM", "BM", "CM");

            if (validPrefixes.contains(prefix)) {
                sharpeningCheckBox.setDisable(false);
            } else {
                sharpeningCheckBox.setDisable(true);
                sharpeningCheckBox.setSelected(false); // Deselect if disabled
            }
        } else {
            sharpeningCheckBox.setDisable(true);
            sharpeningCheckBox.setSelected(false); // Deselect if disabled
        }
    }

}
