package com.example.tooltracker.controller.newtoolcontrollers;

import com.example.tooltracker.controller.ToolsController;
import com.example.tooltracker.database.ActionDAO;
import com.example.tooltracker.database.FaceGrooveDAO;
import com.example.tooltracker.database.IdTurningDAO;
import com.example.tooltracker.database.ToolInsertDAO;
import com.example.tooltracker.model.ToolAction;
import com.example.tooltracker.model.ToolInsert;
import com.example.tooltracker.model.tools.FaceGroove;
import com.example.tooltracker.model.tools.ToolStatus;
import com.example.tooltracker.model.tools.ToolType;
import com.example.tooltracker.model.tools.TurningID;
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

public class AddFaceGrooveToolController {
    private ToolsController toolsController;



    @FXML
    private TextField nameTextField;

    @FXML
    private TextField boltTextField;


    @FXML
    private TextField priceTextField;

    @FXML
    private TextField maxDiamTextField;

    @FXML
    private TextField minDiamTextField;

    @FXML
    private TextField maxDepthTextField;

    @FXML
    private ListView<String> addedIndexesListView;

    @FXML
    private ComboBox matchingInserts1CB;
    @FXML
    private ComboBox matchingInserts2CB;

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

    FaceGrooveDAO faceGrooveDAO = new FaceGrooveDAO();
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
                                minDiamTextField.getText().isEmpty() ||
                                maxDepthTextField.getText().isEmpty() ||
                                maxDiamTextField.getText().isEmpty() ||
                                directionToggleGroup.getSelectedToggle() == null,
                nameTextField.textProperty(),
                priceTextField.textProperty(),
                boltTextField.textProperty(),
                minDiamTextField.textProperty(),
                maxDepthTextField.textProperty(),
                maxDiamTextField.textProperty(),
                directionToggleGroup.selectedToggleProperty()
        );

        confirmButton.disableProperty().bind(fieldsEmpty);


        setDecNumTextField(priceTextField);
        setDecNumTextField(minDiamTextField);
        setDecNumTextField(maxDiamTextField);
        setDecNumTextField(maxDepthTextField);
        setTextFieldLimit(boltTextField, 25);
        setTextFieldLimit(nameTextField, 23);


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

        String toolName = nameTextField.getText();
        Double minCutDiam = Double.valueOf(minDiamTextField.getText());
        Double maxCutDiam = Double.valueOf(maxDiamTextField.getText());
        Integer maxCutDepth = Integer.valueOf(maxDepthTextField.getText());
        BigDecimal price = new BigDecimal(priceTextField.getText());
        String matchingBolt = boltTextField.getText();
        RadioButton selectedRadioButton = (RadioButton) directionToggleGroup.getSelectedToggle();
        String workDirection = selectedRadioButton.getText();

        List<String> toolIndexes = faceGrooveDAO.getLastToolIndexByDirection(workDirection);

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
        String newIndex = String.format("LT-IE-%s-%s", workDirection, newSeqNumStr);

        System.out.println(newIndex);


        FaceGroove faceGroove = new FaceGroove(toolName, newIndex, ToolType.FACE_GROOVE,
                ToolStatus.W_UZYCIU, "",
                price, minCutDiam, maxCutDiam, maxCutDepth, matchingInsertsFromForm,
                matchingBolt, workDirection);



        faceGrooveDAO.addFaceGrooveTools(faceGroove);
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
