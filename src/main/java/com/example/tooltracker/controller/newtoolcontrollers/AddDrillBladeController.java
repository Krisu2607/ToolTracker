package com.example.tooltracker.controller.newtoolcontrollers;

import com.example.tooltracker.controller.ToolsController;
import com.example.tooltracker.database.*;
import com.example.tooltracker.model.ToolAction;
import com.example.tooltracker.model.ToolInsert;
import com.example.tooltracker.model.tools.DrillBlades;
import com.example.tooltracker.model.tools.EmAlu;
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

public class AddDrillBladeController {

    private ToolsController toolsController;



    @FXML
    private TextField nameTextField;

    @FXML
    private TextField toothsQtyTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField D1textfield;

    @FXML
    private TextField L1textfield;

    @FXML
    private ListView<String> addedIndexesListView;

    @FXML
    private ComboBox matchingInserts1CB;
    @FXML
    private ComboBox matchingInserts2CB;
    @FXML
    private ComboBox producentCB;

    @FXML
    private Button confirmButton;

    DrillBladesDAO drillBladesDAO = new DrillBladesDAO();
    private ActionDAO actionDAO = new ActionDAO();
    private final ProducentDAO producentDA0 = new ProducentDAO();
    private final ToolInsertDAO toolInsertDAO = new ToolInsertDAO();
    private ObservableList<String> addedIndexes = FXCollections.observableArrayList();


    public void initialize() throws SQLException {
        List<String> producentsList = producentDA0.getAllProducents();
        ObservableList<String> allProducents = FXCollections.observableArrayList(producentsList);
        producentCB.setItems(allProducents);


        // Wczytaj indeksy narzędzi z bazy danych i ustaw je w ComboBox-ach
        List<ToolInsert> toolInserts = toolInsertDAO.getAllToolInserts();
        ObservableList<String> insertIndexes = FXCollections.observableArrayList();
        for (ToolInsert toolInsert : toolInserts) {
            insertIndexes.add(toolInsert.getInsertIndex());
        }
        matchingInserts1CB.setItems(insertIndexes);
        matchingInserts2CB.setItems(insertIndexes);


        addedIndexesListView.setItems(addedIndexes);
        //DEZAKTYWACJA PRZYCISKU ZATWIERZ W ZALEZNOSCI CZY JEST JAKIS TEKST W POLU CZY NIE
        BooleanBinding fieldsEmpty = Bindings.createBooleanBinding(() ->
                        nameTextField.getText().isEmpty() ||
                                toothsQtyTextField.getText().isEmpty() ||
                                producentCB.getValue() == null ||
                                priceTextField.getText().isEmpty() ||
                                L1textfield.getText().isEmpty() ||
                                D1textfield.getText().isEmpty(),

                nameTextField.textProperty(),
                toothsQtyTextField.textProperty(),
                priceTextField.textProperty(),
                L1textfield.textProperty(),
                D1textfield.textProperty()
        );

        confirmButton.disableProperty().bind(fieldsEmpty);



        setTwoNumsTextField(toothsQtyTextField);
        setThreeNumsTextField(L1textfield);
        setDecNumTextField(D1textfield);
        setDecNumTextField(priceTextField);
        setTextFieldLimit(nameTextField, 23);


    }

    @FXML
    private void handleConfirmButton() throws SQLException {

        String matchingInsertsFromForm = "";
        String CB1nserts = matchingInserts1CB.getValue() != null ? matchingInserts1CB.getValue().toString() : "";
        String CB2nserts = matchingInserts2CB.getValue() != null ? matchingInserts2CB.getValue().toString() : "";
        String prodName = producentCB.getValue().toString();

        if (!CB1nserts.isEmpty() && !CB2nserts.isEmpty()) {
            matchingInsertsFromForm = CB1nserts + ", " + CB2nserts;
        } else if (!CB1nserts.isEmpty()) {
            matchingInsertsFromForm = CB1nserts;
        } else if (!CB2nserts.isEmpty()) {
            matchingInsertsFromForm = CB2nserts;
        }
        String toolName = nameTextField.getText();
        BigDecimal price = new BigDecimal(priceTextField.getText());
        int toothsQty = Integer.valueOf(toothsQtyTextField.getText());
        String toolDiam = D1textfield.getText();
        int L1 = Integer.valueOf(L1textfield.getText());
        double D1 = Double.valueOf(D1textfield.getText());
        int lastToolIndexNum = drillBladesDAO.getLastToolIndexNum(D1);
        String newIndexNumStr = String.format("%04d", lastToolIndexNum + 1);
        String newToolIndex = String.format("H-DR-%d-%s", (int) D1, newIndexNumStr);

        DrillBlades drillBlade = new DrillBlades(toolName, newToolIndex, ToolStatus.W_UZYCIU, "", price,prodName, matchingInsertsFromForm, D1, L1, toothsQty);



        drillBladesDAO.addDrillBladeTool(drillBlade);
        addedIndexes.add(newToolIndex);
        producentDA0.addCostByName(prodName, price);

        ToolAction toolAction = new ToolAction();
        toolAction.settAction("Nowe narzędzie");
        toolAction.settIndex(newToolIndex);
        actionDAO.addAction(toolAction);

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
