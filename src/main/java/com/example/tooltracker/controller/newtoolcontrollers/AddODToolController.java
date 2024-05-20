package com.example.tooltracker.controller.newtoolcontrollers;

import com.example.tooltracker.controller.ToolsController;
import com.example.tooltracker.database.ActionDAO;
import com.example.tooltracker.database.DrillBladesDAO;
import com.example.tooltracker.database.OdTurningDAO;
import com.example.tooltracker.database.ToolInsertDAO;
import com.example.tooltracker.model.ToolAction;
import com.example.tooltracker.model.ToolInsert;
import com.example.tooltracker.model.tools.DrillBlades;
import com.example.tooltracker.model.tools.ToolStatus;
import com.example.tooltracker.model.tools.TurningOD;
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

public class AddODToolController {

    private ToolsController toolsController;



    @FXML
    private TextField nameTextField;

    @FXML
    private TextField boltTextField;


    @FXML
    private TextField priceTextField;

    @FXML
    private ListView<String> addedIndexesListView;

    @FXML
    private ComboBox matchingInserts1CB;
    @FXML
    private ComboBox matchingInserts2CB;
    @FXML
    private ComboBox workType;


    @FXML
    private RadioButton nDirection;
    @FXML
    private RadioButton pDirection;
    @FXML
    private RadioButton lDirection;

    @FXML
    private ToggleGroup directionToggleGroup;

    @FXML
    private Button confirmButton;

    OdTurningDAO odTurningDAO = new OdTurningDAO();
    private ActionDAO actionDAO = new ActionDAO();
    private final ToolInsertDAO toolInsertDAO = new ToolInsertDAO();
    private ObservableList<String> addedIndexes = FXCollections.observableArrayList();


    public void initialize() {

        directionToggleGroup = new ToggleGroup();
        nDirection.setToggleGroup(directionToggleGroup);
        lDirection.setToggleGroup(directionToggleGroup);
        pDirection.setToggleGroup(directionToggleGroup);

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
                                priceTextField.getText().isEmpty() ||
                                boltTextField.getText().isEmpty() ||
                                workType.getValue() == null ||
                                directionToggleGroup.getSelectedToggle() == null,
                nameTextField.textProperty(),
                priceTextField.textProperty(),
                boltTextField.textProperty(),
                workType.valueProperty(),
                directionToggleGroup.selectedToggleProperty()
        );

        confirmButton.disableProperty().bind(fieldsEmpty);


        setDecNumTextField(priceTextField);
        setTextFieldLimit(nameTextField, 23);
        setTextFieldLimit(boltTextField, 25);


    }

    @FXML
    private void handleConfirmButton() throws SQLException {

        String matchingInsertsFromForm = "";
        String CB1nserts = matchingInserts1CB.getValue() != null ? matchingInserts1CB.getValue().toString() : "";
        String CB2nserts = matchingInserts2CB.getValue() != null ? matchingInserts2CB.getValue().toString() : "";

        if (!CB1nserts.isEmpty() && !CB2nserts.isEmpty()) {
            matchingInsertsFromForm = CB1nserts + ", " + CB2nserts;
        } else if (!CB1nserts.isEmpty()) {
            matchingInsertsFromForm = CB1nserts;
        } else if (!CB2nserts.isEmpty()) {
            matchingInsertsFromForm = CB2nserts;
        }
        String workType1 = (String) workType.getValue();

        String toolName = nameTextField.getText();
        BigDecimal price = new BigDecimal(priceTextField.getText());
        String matchingBolt = boltTextField.getText();
        RadioButton selectedRadioButton = (RadioButton) directionToggleGroup.getSelectedToggle();
        String workDirection = selectedRadioButton.getText();

        List<String> toolIndexes = odTurningDAO.getLastToolIndexByDirection(workDirection);

        int maxSeqNum = toolIndexes.stream()
                .map(index -> {
                    String[] parts = index.split("-");
                    return Integer.parseInt(parts[parts.length - 1]);
                })
                .max(Integer::compare)
                .orElse(0);

        int newSeqNum = maxSeqNum + 1;

        // Formatowanie nowego indeksu
        String newSeqNumStr;
        if (newSeqNum < 10000) {
            newSeqNumStr = String.format("%04d", newSeqNum);
        } else {
            newSeqNumStr = String.valueOf(newSeqNum);
        }
        String newIndex = String.format("LT-E-%s-%s", workDirection, newSeqNumStr);

        System.out.println(newIndex);


        TurningOD turningOD = new TurningOD(toolName, newIndex,
                ToolStatus.W_UZYCIU, "",
                price, matchingInsertsFromForm,
                workType1, workDirection, matchingBolt);



        odTurningDAO.addOdTools(turningOD);
        addedIndexes.add(newIndex);

        ToolAction toolAction = new ToolAction();
        toolAction.settAction("Nowe narzędzie");
        toolAction.settIndex(newIndex);
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

    private void setThreeNumsTextField(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d{1,3}")) {
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
