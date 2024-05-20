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
        indexTextField.textProperty().addListener((observable, oldValue, newValue) -> checkToolExistence(newValue));
        confirmButton.setDisable(true);
    }


    @FXML
    private void handleConfirmButton() {
        String index = indexTextField.getText();
        boolean toSharpen = sharpeningCheckBox.isSelected();

        // Pobierz narzędzie z bazy danych na podstawie indeksu
        List<Tool> allTools = toolDAO.getAllTools();
        Optional<Tool> toolx = allTools.stream()
                .filter(t -> index.equalsIgnoreCase(t.getToolIndex()))
                .findFirst();
        Tool tool = toolx.get();
        ToolAction toolAction = new ToolAction();


        if (tool != null) {
            // Ustaw nowy status na podstawie zaznaczenia pola "Do ostrzenia"
            tool.setToolStatus(toSharpen ? "Do ostrzenia" : "Zużyte");
            toolAction.settAction(toSharpen ? "Do ostrzenia" : "Zużyte");
            toolAction.settIndex(tool.getToolIndex());




            // Zapisz zmiany w bazie danych
            toolDAO.updateToolStatus(tool);
            actionDAO.addAction(toolAction);

            indexTextField.clear();
            sharpeningCheckBox.setSelected(false);

            // Wróć do pierwszego pola tekstowego
            indexTextField.requestFocus();

        }

        toolsController.refreshToolTable();


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
        List<Tool> allTools = toolDAO.getAllTools();
        Optional<Tool> toolOptional = allTools.stream()
                .filter(t -> toolIndex.equalsIgnoreCase(t.getToolIndex()))
                .findFirst();

        if (toolOptional.isPresent()) {
            // Narzędzie istnieje, można aktywować przycisk "Zatwierdź"
            confirmButton.setDisable(false);
            indexError.setText("✔");
            indexError.setTextFill(Color.GREEN);


        } else {
            // Narzędzie nie istnieje, dezaktywuj przycisk "Zatwierdź"
            confirmButton.setDisable(true);
            indexError.setText("Nie posiadamy takiego narzędzia");
            indexError.setTextFill(Color.RED);


        }
    }

}
