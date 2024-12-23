package com.example.tooltracker.controller;

import com.example.tooltracker.database.InsertActionDAO;
import com.example.tooltracker.database.ToolInsertDAO;
import com.example.tooltracker.model.InsertAction;
import com.example.tooltracker.model.ToolInsert;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class NewInsertController {

    private ToolsController toolsController;

    @FXML
    private RadioButton qty3;
    @FXML
    private RadioButton qty5;
    @FXML
    private RadioButton qty10;
    @FXML
    private RadioButton qty15;
    @FXML
    private RadioButton qty20;

    @FXML
    private ToggleGroup quantityToggleGroup;



    @FXML
    private TextField indexTextField;
    @FXML
    private  TextField qtyTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private ComboBox matchingToolsCB;

    @FXML
    private Button confirmButton;

    ToolInsertDAO toolInsertDAO = new ToolInsertDAO();
    InsertActionDAO insertActionDAO = new InsertActionDAO();

    public void initialize() {

        quantityToggleGroup = new ToggleGroup();
        qty3.setToggleGroup(quantityToggleGroup);
        qty5.setToggleGroup(quantityToggleGroup);
        qty10.setToggleGroup(quantityToggleGroup);
        qty15.setToggleGroup(quantityToggleGroup);
        qty20.setToggleGroup(quantityToggleGroup);
        //DEZAKTYWACJA PRZYCISKU ZATWIERZ W ZALEZNOSCI CZY JEST JAKIS TEKST W POLU CZY NIE
        confirmButton.disableProperty().bind(nameTextField.textProperty().isEmpty());

        BooleanBinding fieldsEmpty = Bindings.createBooleanBinding(() ->
                        nameTextField.getText().isEmpty() ||
                                indexTextField.getText().isEmpty() ||
                                priceTextField.getText().isEmpty() ||
                                qtyTextField.getText().isEmpty() ||
                                matchingToolsCB.getValue() == null ||
                                quantityToggleGroup.getSelectedToggle() == null,
                nameTextField.textProperty(),
                indexTextField.textProperty(),
                priceTextField.textProperty(),
                qtyTextField.textProperty(),
                matchingToolsCB.valueProperty(),
                quantityToggleGroup.selectedToggleProperty()

        );

        confirmButton.disableProperty().bind(fieldsEmpty);

        setTwoNumsTextField(qtyTextField);
        setDecNumTextField(priceTextField);
        setTextFieldLimit(nameTextField, 23);
        setTextFieldLimit(indexTextField, 23);
    }

    @FXML
    private void handleConfirmButton() throws SQLException {
        String indexName = indexTextField.getText();

        // Sprawdź, czy istnieje już insert o tym samym indeksie
        if (toolInsertDAO.isToolInsertWithIndexExists(indexName)) {
            // Wyświetl okienko ostrzegawcze
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Duplikat indeksu");
            alert.setHeaderText(null);
            alert.setContentText("Płytki o podanym indeksie już istnieją w bazie danych.");
            alert.showAndWait();
        } else {
            // Dodaj nowy ToolInsert do bazy danych
            BigDecimal price = new BigDecimal(priceTextField.getText());
            String insertName = nameTextField.getText();
            String insertQty = qtyTextField.getText();
            String matchingTools = (String) matchingToolsCB.getValue();
            String trueMatchingTools;
            switch (matchingTools) {
                case "Noże Tokarskie Zew":
                    trueMatchingTools = "NozeTokZew";
                    break;
                case "Wytaczaki":
                    trueMatchingTools = "NozeTokWew";
                    break;
                case "Rowki Czołowe":
                    trueMatchingTools = "RowkiCzol";
                    break;
                case "Głowice Szybkościowe":
                    trueMatchingTools = "GlowiceFF";
                    break;
                case "Głowice Planistyczne":
                    trueMatchingTools = "GlowiceNM";
                    break;
                case "INNE":
                    trueMatchingTools = "INNE";
                    break;
                case "Gwinty":
                    trueMatchingTools = "Gwinty";
                    break;
                default:
                    trueMatchingTools = "INNE";
                    break;
            }

            RadioButton selectedRadioButton = (RadioButton) quantityToggleGroup.getSelectedToggle();
            int quantityexp = Integer.parseInt(selectedRadioButton.getText());
            ToolInsert newToolInsert = new ToolInsert(insertName, indexName, "", trueMatchingTools, "", Integer.valueOf(insertQty), quantityexp, price);
            toolInsertDAO.addInserts(newToolInsert);
            InsertAction insertAction = new InsertAction(indexName, "Dodano Nowe Płytki", LocalDateTime.now());
            insertActionDAO.addAction(insertAction);


            // Wyczyść pola tekstowe
            indexTextField.clear();
            qtyTextField.clear();
            nameTextField.clear();
            priceTextField.clear();

            // Wróć do pierwszego pola tekstowego
            indexTextField.requestFocus();

            toolsController.refreshInsertTable();

            // Wyświetl okienko z informacją o dodaniu
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sukces");
            alert.setHeaderText(null);
            alert.setContentText("Płytki zostały dodane do bazy danych.");
            alert.showAndWait();
        }
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
                if (!newValue.matches("\\d{1,4}(\\.\\d{0,2})?")) {
                    textField.setText(oldValue);
                }


            }
        });
    }




    private void setTwoNumsTextField(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d{1,2}")) {
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


    private void setTextFieldLimit(TextField textField, int maxLength) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > maxLength) {
                    textField.setText(oldValue);
                }
            }
        });
    }

}
