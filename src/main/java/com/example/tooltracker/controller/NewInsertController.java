package com.example.tooltracker.controller;

import com.example.tooltracker.database.ToolInsertDAO;
import com.example.tooltracker.model.ToolInsert;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class NewInsertController {

    private ToolsController toolsController;



    @FXML
    private TextField indexTextField;
    @FXML
    private  TextField qtyTextField;
    @FXML
    private TextField nameTextField;

    @FXML
    private Button confirmButton;

    ToolInsertDAO toolInsertDAO = new ToolInsertDAO();

    public void initialize() {
        //DEZAKTYWACJA PRZYCISKU ZATWIERZ W ZALEZNOSCI CZY JEST JAKIS TEKST W POLU CZY NIE
        confirmButton.disableProperty().bind(indexTextField.textProperty().isEmpty());
        confirmButton.disableProperty().bind(qtyTextField.textProperty().isEmpty());
        confirmButton.disableProperty().bind(nameTextField.textProperty().isEmpty());
        setSingNumTextField(qtyTextField);
    }

    @FXML
    private void handleConfirmButton() throws SQLException {




        String indexName = indexTextField.getText();
        String insertName = nameTextField.getText();
        String insertQty = qtyTextField.getText();
        ToolInsert newToolInsert = new ToolInsert(insertName, indexName, "w", "Noże tokarskie", "", Integer.valueOf(insertQty));
        toolInsertDAO.addInserts(newToolInsert);


        indexTextField.clear();
        qtyTextField.clear();
        nameTextField.clear();


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


    private void setDecNumTextField(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    textField.setText(oldValue);
                }


            }
        });
    }


    private void setSingNumTextField(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(oldValue);
                }


            }
        });
    }
}
