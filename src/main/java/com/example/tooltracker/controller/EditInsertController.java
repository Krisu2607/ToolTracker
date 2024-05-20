package com.example.tooltracker.controller;

import com.example.tooltracker.database.ToolInsertDAO;
import com.example.tooltracker.model.ToolInsert;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

public class EditInsertController {



    private ToolsController toolsController;



    @FXML
    private TextField indexTextField;
    @FXML
    private  TextField qtyTextField;

    @FXML
    private CheckBox sharpeningCheckBox;

    @FXML
    private Button confirmButton;
    @FXML
            private Label indexError;

    ToolInsertDAO toolInsertDAO = new ToolInsertDAO();

    public void initialize() {
        //DEZAKTYWACJA PRZYCISKU ZATWIERZ W ZALEZNOSCI CZY JEST JAKIS TEKST W POLU CZY NIE
        indexTextField.textProperty().addListener((observable, oldValue, newValue) -> checkToolExistence(newValue));
        confirmButton.setDisable(true);
        qtyTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    qtyTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @FXML
    private void handleConfirmButton() {
        String index = indexTextField.getText();


        // Pobierz narzędzie z bazy danych na podstawie indeksu
        List<ToolInsert> allToolInserts = toolInsertDAO.getAllToolInserts();
        Optional<ToolInsert> toolInsertx = allToolInserts.stream()
                .filter(t -> index.equalsIgnoreCase(t.getInsertIndex()))
                .findFirst();
        ToolInsert toolInsert = toolInsertx.get();
        int currentQty = toolInsert.getInsertQty();
        int reducingQty = Integer.parseInt(qtyTextField.getText());
        toolInsert.setInsertQty(currentQty-reducingQty);




        toolInsertDAO.updateToolInsertQty(toolInsert);

            indexTextField.clear();
            qtyTextField.clear();

            // Wróć do pierwszego pola tekstowego
            indexTextField.requestFocus();

        toolsController.refreshInsertTable();
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


        } else {
            // Narzędzie nie istnieje, dezaktywuj przycisk "Zatwierdź"
            confirmButton.setDisable(true);
            indexError.setText("Nie posiadamy takich płytek");
            indexError.setTextFill(Color.RED);


        }
    }
}
