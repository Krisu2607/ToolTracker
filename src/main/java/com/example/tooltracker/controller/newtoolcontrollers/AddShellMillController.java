package com.example.tooltracker.controller.newtoolcontrollers;

import com.example.tooltracker.controller.ToolsController;
import com.example.tooltracker.database.*;
import com.example.tooltracker.model.ToolAction;
import com.example.tooltracker.model.ToolInsert;
import com.example.tooltracker.model.tools.ShellMill;
import com.example.tooltracker.model.tools.ToolStatus;
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

public class AddShellMillController {

    private ToolsController toolsController;



    @FXML
    private TextField nameTextField;

    @FXML
    private TextField boltTextField;


    @FXML
    private TextField diamTextField;

    @FXML
    private TextField toothsQtyTextField;




    @FXML
    private TextField priceTextField;

    @FXML
    private ListView<String> addedIndexesListView;

    @FXML
    private ComboBox matchingInserts1CB;
    @FXML
    private ComboBox producentCB;
    @FXML
    private ComboBox matchingInserts2CB;
    @FXML
    private ComboBox workType;



    @FXML
    private RadioButton FFRadio;
    @FXML
    private RadioButton NMRadio;
    @FXML
    private RadioButton yesIC;
    @FXML

    private RadioButton noIC;

    @FXML
    private ToggleGroup shellMillTypeToggleGroup;
    @FXML
    private ToggleGroup ICToggleGroup;

    @FXML
    private Button confirmButton;

    ShellMillDAO shellMillDAO = new ShellMillDAO();
    private ActionDAO actionDAO = new ActionDAO();
    private final ToolInsertDAO toolInsertDAO = new ToolInsertDAO();
    private final ProducentDAO producentDA0 = new ProducentDAO();

    private ObservableList<String> addedIndexes = FXCollections.observableArrayList();


    public void initialize() throws SQLException {

        shellMillTypeToggleGroup = new ToggleGroup();
        FFRadio.setToggleGroup(shellMillTypeToggleGroup);
        NMRadio.setToggleGroup(shellMillTypeToggleGroup);

        ICToggleGroup = new ToggleGroup();
        yesIC.setToggleGroup(ICToggleGroup);
        noIC.setToggleGroup(ICToggleGroup);

        // Wczytaj indeksy narzędzi z bazy danych i ustaw je w ComboBox-ach
        List<ToolInsert> toolInserts = toolInsertDAO.getAllToolInserts();
        ObservableList<String> insertIndexes = FXCollections.observableArrayList();
        for (ToolInsert toolInsert : toolInserts) {
            insertIndexes.add(toolInsert.getInsertIndex());
        }
        matchingInserts1CB.setItems(insertIndexes);
        matchingInserts2CB.setItems(insertIndexes);

        List<String> producentsList = producentDA0.getAllProducents();
        ObservableList<String> allProducents = FXCollections.observableArrayList(producentsList);
        producentCB.setItems(allProducents);


        addedIndexesListView.setItems(addedIndexes);
        //DEZAKTYWACJA PRZYCISKU ZATWIERZ W ZALEZNOSCI CZY JEST JAKIS TEKST W POLU CZY NIE
        BooleanBinding fieldsEmpty = Bindings.createBooleanBinding(() ->
                        nameTextField.getText().isEmpty() ||
                        diamTextField.getText().isEmpty() ||
                        toothsQtyTextField.getText().isEmpty() ||
                        producentCB.getValue() == null ||
                                priceTextField.getText().isEmpty() ||
                                boltTextField.getText().isEmpty() ||
                                shellMillTypeToggleGroup.getSelectedToggle() == null ||
                                ICToggleGroup.getSelectedToggle() == null,
                nameTextField.textProperty(),
                priceTextField.textProperty(),
                toothsQtyTextField.textProperty(),
                boltTextField.textProperty(),
                shellMillTypeToggleGroup.selectedToggleProperty(),
                ICToggleGroup.selectedToggleProperty()
        );

        confirmButton.disableProperty().bind(fieldsEmpty);


        setDecNumTextField(priceTextField);
        setTwoNumsTextField(toothsQtyTextField);
        setDecNumTextField(diamTextField);
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
        String prodName = producentCB.getValue().toString();



        String toolName = nameTextField.getText();
        Double toolDiam = Double.valueOf(diamTextField.getText());
        int toothsQty = Integer.valueOf(toothsQtyTextField.getText());
        BigDecimal price = new BigDecimal(priceTextField.getText());
        String matchingBolt = boltTextField.getText();
        RadioButton selectedRadioButton = (RadioButton) shellMillTypeToggleGroup.getSelectedToggle();
        String shellMillType = selectedRadioButton.getText();
        RadioButton selectedRadioButton2 = (RadioButton) ICToggleGroup.getSelectedToggle();
        Boolean isItCooledIn = selectedRadioButton2.getText().equals("Tak") ;
        System.out.println(selectedRadioButton2.getText());



        List<String> toolIndexes = shellMillDAO.getLastToolIndexByShellMillType(shellMillType);

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
        String newIndex = String.format("H-%s-%s", shellMillType, newSeqNumStr);



        ShellMill shellMill = new ShellMill(toolName, newIndex,
                ToolStatus.W_UZYCIU, "",
                price,prodName, matchingInsertsFromForm,
                toolDiam, toothsQty,isItCooledIn, shellMillType,  matchingBolt);



        shellMillDAO.addShellMills(shellMill);
        addedIndexes.add(newIndex);
        producentDA0.addCostByName(prodName, price);

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
