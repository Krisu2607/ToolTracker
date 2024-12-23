package com.example.tooltracker.controller.newtoolcontrollers;

import com.example.tooltracker.controller.ToolsController;
import com.example.tooltracker.database.ActionDAO;
import com.example.tooltracker.database.DrillvhmDAO;
import com.example.tooltracker.database.EmMetDAO;
import com.example.tooltracker.database.ProducentDAO;
import com.example.tooltracker.model.ToolAction;
import com.example.tooltracker.model.tools.DrillVHM;
import com.example.tooltracker.model.tools.EmMet;
import com.example.tooltracker.model.tools.MaterialType;
import com.example.tooltracker.model.tools.ToolStatus;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class AddDrillVhmController {

    private ToolsController toolsController;


    @FXML
    private TextField nameTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField D1textfield;
    @FXML
    private TextField L1textfield;
    @FXML
    private TextField L2textfield;
    @FXML
    private RadioButton icYes;
    @FXML
    private RadioButton icNo;
    @FXML
    private ComboBox producentCB;
    @FXML
    private ToggleGroup icToggleGroup;


    @FXML
    private ListView<String> addedIndexesListView;



    @FXML
    private Button confirmButton;

    DrillvhmDAO drillvhmDAO = new DrillvhmDAO();
    private ActionDAO actionDAO = new ActionDAO();
    private final ProducentDAO producentDA0 = new ProducentDAO();
    private ObservableList<String> addedIndexes = FXCollections.observableArrayList();


    public void initialize() throws SQLException {
        List<String> producentsList = producentDA0.getAllProducents();
        ObservableList<String> allProducents = FXCollections.observableArrayList(producentsList);
        producentCB.setItems(allProducents);

        icToggleGroup = new ToggleGroup();
        icYes.setToggleGroup(icToggleGroup);
        icNo.setToggleGroup(icToggleGroup);
        addedIndexesListView.setItems(addedIndexes);
        //DEZAKTYWACJA PRZYCISKU ZATWIERZ W ZALEZNOSCI CZY JEST JAKIS TEKST W POLU CZY NIE

        BooleanBinding fieldsEmpty = Bindings.createBooleanBinding(() ->
                        nameTextField.getText().isEmpty() ||
                                priceTextField.getText().isEmpty() ||
                                L1textfield.getText().isEmpty() ||
                                producentCB.getValue() == null ||
                                L2textfield.getText().isEmpty() ||
                                D1textfield.getText().isEmpty() ||
                                icToggleGroup.getSelectedToggle() == null,
                nameTextField.textProperty(),
                priceTextField.textProperty(),
                L1textfield.textProperty(),
                L2textfield.textProperty(),
                D1textfield.textProperty(),
                icToggleGroup.selectedToggleProperty()
        );

        confirmButton.disableProperty().bind(fieldsEmpty);
        System.out.println(fieldsEmpty.get());

        setThreeNumsTextField(L1textfield);
        setThreeNumsTextField(L2textfield);
        setDecNumTextField(D1textfield);
        setDecNumTextField(priceTextField);
        setTextFieldLimit(nameTextField, 23);




    }

    @FXML
    private void handleConfirmButton() throws SQLException {





        // Pobierz narzędzie z bazy danych na podstawie indeksu

        String toolName = nameTextField.getText();
        BigDecimal price = new BigDecimal(priceTextField.getText());
        String prodName = producentCB.getValue().toString();

        int L1 = Integer.valueOf(L1textfield.getText());
        int L2 = Integer.valueOf(L2textfield.getText());
        double D1 = Double.valueOf(D1textfield.getText());
        RadioButton selectedisIcOption = (RadioButton) icToggleGroup.getSelectedToggle();
        boolean isIc = "Tak".equals(selectedisIcOption.getText());


        int lastNum = drillvhmDAO.getLastToolIndexNum(isIc);
        int newSeqNum = lastNum + 1;

        // Formatowanie nowego indeksu
        String newSeqNumStr;
        if (newSeqNum < 10000) {
            newSeqNumStr = String.format("%04d", newSeqNum);
        } else {
            newSeqNumStr = String.format("%05d", newSeqNum);
        }
        String newIndex = isIc ? String.format("DR-VHM-IC-%s", newSeqNumStr) : String.format("DR-VHM-%s", newSeqNumStr);
        DrillVHM drillVHM = new DrillVHM(toolName, newIndex, ToolStatus.W_UZYCIU, "",price,prodName,D1, L1, L2,isIc  );

        drillvhmDAO.addDrillVHM(drillVHM);
        addedIndexes.add(newIndex);


        ToolAction toolAction = new ToolAction();
        toolAction.settAction("Nowe narzędzie");
        toolAction.settIndex(newIndex);
        actionDAO.addAction(toolAction);
        producentDA0.addCostByName(prodName, price);

//        indexTextField.clear();
//        qtyTextField.clear();
//        nameTextField.clear();
//        diamTextField.clear();

        // Wróć do pierwszego pola tekstowego
        nameTextField.requestFocus();

//        toolsController.refreshInsertTable();
//        toolsController.refreshToolTable();
    }




    @FXML
    private void handleCancelButton() {
        closeStage();
    }


    private void closeStage() {
        Stage stage = (Stage) nameTextField.getScene().getWindow();
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
                if (!newValue.matches("\\d{0,2}")) {
                    textField.setText(oldValue);
                }


            }
        });
    }

    private void setThreeNumsTextField(TextField textField) {
        textField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,3}")) { // Akceptuj tylko cyfry i maksymalnie 5 znaków
                return change;
            }
            return null;
        }));
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

