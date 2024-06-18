package com.example.tooltracker.controller;

import com.example.tooltracker.database.ActionDAO;
import com.example.tooltracker.database.ToolDAO;
import com.example.tooltracker.model.tools.Tool;
import com.example.tooltracker.model.ToolAction;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class NewToolController {
    private ToolsController toolsController;



    @FXML
    private TextField indexTextField;
    @FXML
    private  TextField qtyTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField diamTextField;
    @FXML
    private ComboBox typeComboBox;

    @FXML
    private ComboBox insertTypeComboBox;

    @FXML
    private Button confirmButton;

    ToolDAO toolDao = new ToolDAO();
    private ActionDAO actionDAO = new ActionDAO();


    public void initialize() {
        //DEZAKTYWACJA PRZYCISKU ZATWIERZ W ZALEZNOSCI CZY JEST JAKIS TEKST W POLU CZY NIE
        confirmButton.disableProperty().bind(indexTextField.textProperty().isEmpty());


        setSingNumTextField(qtyTextField);
        setDecNumTextField(diamTextField);




        typeComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Dezaktywuj pole tekstowe dla średnicy, jeśli wybrany jest jeden z typów narzędzi
            // dla których to pole ma być dezaktywowane
            diamTextField.setDisable("Noże tokarskie".equals(newValue) || "Gwintowniki".equals(newValue));
        });

        typeComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Dezaktywuj pole tekstowe dla średnicy, jeśli wybrany jest jeden z typów narzędzi
            // dla których to pole ma być dezaktywowane
            insertTypeComboBox.setDisable("Gwintowniki".equals(newValue) || "Frezy".equals(newValue));
        });




    }

    @FXML
    private void handleConfirmButton() throws SQLException {



        // Pobierz narzędzie z bazy danych na podstawie indeksu

       String indexName = indexTextField.getText();
       String toolName = nameTextField.getText();
       String toolQty = qtyTextField.getText();
       String toolDiam = diamTextField.getText();
       if(toolDiam.isEmpty())
           toolDiam = "0";


        String toolType = (String) typeComboBox.getValue();
        String matchingInserts = (String) insertTypeComboBox.getValue();
        if (matchingInserts == null){
            matchingInserts = "";
        }

        String toolInfo = "";
        String toolStatus = "W użyciu";
        Tool newTool = new Tool(toolName, indexName, Integer.valueOf(toolQty), toolType, Double.valueOf(toolDiam),toolInfo, toolStatus,matchingInserts,"","" );
        toolDao.addTool(newTool);


        ToolAction toolAction = new ToolAction();
        toolAction.settAction("Nowe narzędzie");
        toolAction.settIndex(indexTextField.getText());
        actionDAO.addAction(toolAction);

        indexTextField.clear();
        qtyTextField.clear();
        nameTextField.clear();
        diamTextField.clear();

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


//"[^\\d+(\\.\\d+)?]"
    //

    // if (!newValue.matches("\\d{1,2}(\\.\\d{1,2})?$")) {
    //                    textField.setText(newValue.replaceAll("[^\\d.]", ""));
    //                }
}
