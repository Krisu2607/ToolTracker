package com.example.tooltracker.controller;

import com.example.tooltracker.database.*;
import com.example.tooltracker.model.ToolAction;
import com.example.tooltracker.model.tools.*;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class BackFromSharpenController {

    private ToolsController toolsController;



    @FXML
    private TextField indexTextField;



    @FXML
    private TextField D1textfield;
    @FXML
    private TextField D2textfield;
    @FXML
    private TextField L1textfield;
    @FXML
    private TextField L2textfield;





    @FXML
    private Button confirmButton;

    ToolDAO toolDao = new ToolDAO();
    DrillvhmDAO drillvhmDAO = new DrillvhmDAO();
    DrillHssDAO drillHssDAO = new DrillHssDAO();
    EmMetDAO emMetDAO = new EmMetDAO();
    EmAluDAO emAluDAO = new EmAluDAO();
    EMRDAO emrdao = new EMRDAO();
    ChamferDAO chamferDAO = new ChamferDAO();

    private ActionDAO actionDAO = new ActionDAO();


    public void initialize() {
        //DEZAKTYWACJA PRZYCISKU ZATWIERZ W ZALEZNOSCI CZY JEST JAKIS TEKST W POLU CZY NIE
        BooleanBinding fieldsEmpty = Bindings.createBooleanBinding(() ->
                                L1textfield.getText().isEmpty() ||
                                indexTextField.getText().isEmpty() ||
                                L2textfield.getText().isEmpty() ||
                                D1textfield.getText().isEmpty() ||
                                D2textfield.getText().isEmpty(),
                indexTextField.textProperty(),
                L1textfield.textProperty(),
                L2textfield.textProperty(),
                D1textfield.textProperty(),
                D2textfield.textProperty()

        );

        confirmButton.disableProperty().bind(fieldsEmpty);



        setThreeNumsTextField(L1textfield);
        setThreeNumsTextField(L2textfield);
        setDecNumTextField(D1textfield);
        setDecNumTextField(D2textfield);







    }

    @FXML
    private void handleConfirmButton() throws SQLException {





        // Pobierz narzędzie z bazy danych na podstawie indeksu

        String indexName = indexTextField.getText();
        String toolDiam = D1textfield.getText();
        int L1 = Integer.valueOf(L1textfield.getText());
        int L2 = Integer.valueOf(L2textfield.getText());
        double D1 = Double.valueOf(D1textfield.getText());
        double D2 = Double.valueOf(D2textfield.getText());
        String toolIndeks = indexTextField.getText();

        if(toolIndeks.startsWith("DR-VHM")) {
            DrillVHM drillVHM = new DrillVHM("", indexName, ToolStatus.W_UZYCIU, "", BigDecimal.valueOf(0),"", D1, L1, L2, true );
            drillvhmDAO.updatePostSharpen(drillVHM);
        } else if (toolIndeks.startsWith("DR-HSS")) {
            DrillHSS drillHSS = new DrillHSS("", indexName,ToolType.DRILL_HSS, ToolStatus.W_UZYCIU, "", BigDecimal.valueOf(0),"", D1, L1, L2,0 );

            drillHssDAO.updatePostSharpen(drillHSS);

        }
        else if (toolIndeks.startsWith("EM-P")) {
            EmMet emMet = new EmMet("", indexName, ToolStatus.W_UZYCIU, "",BigDecimal.valueOf(0),"", L1, L2, D1, D2, MaterialType.VHM, 0 );
            emMetDAO.updatePostSharpen(emMet);

        }
        else if (toolIndeks.startsWith("EM-N")) {
            EmAlu emAlu = new EmAlu("", indexName, ToolStatus.W_UZYCIU, "",BigDecimal.valueOf(0),"", L1, L2, D1, D2, MaterialType.VHM, 0 );
            emAluDAO.updatePostSharpen(emAlu);

        }

        else if (toolIndeks.startsWith("CM")) {
            Chamfer chamfer = new Chamfer("", indexName, ToolStatus.W_UZYCIU, "",BigDecimal.valueOf(0),"",D1, D2, L1, L2,0,  MaterialType.VHM, 0 );
            chamferDAO.updatePostSharpen(chamfer);
        }


        ToolAction toolAction = new ToolAction();
        toolAction.settAction("Powrót z ostrzenia");
        toolAction.settIndex(indexName);
        actionDAO.addAction(toolAction);



        // Wróć do pierwszego pola tekstowego
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


    private void setDecNumTextField(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d{0,4}(\\.\\d{0,2})?")) {
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

    private void setThreeNumsTextField(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d{0,3}")) {
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
