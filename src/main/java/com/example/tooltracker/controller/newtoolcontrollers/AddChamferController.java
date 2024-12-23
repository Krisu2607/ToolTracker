package com.example.tooltracker.controller.newtoolcontrollers;

import com.example.tooltracker.controller.ToolsController;
import com.example.tooltracker.database.ActionDAO;
import com.example.tooltracker.database.ChamferDAO;
import com.example.tooltracker.database.EmAluDAO;
import com.example.tooltracker.database.ProducentDAO;
import com.example.tooltracker.model.ToolAction;
import com.example.tooltracker.model.tools.Chamfer;
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

public class AddChamferController {

    private ToolsController toolsController;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField toothsQtyTextField;
    @FXML
    private ComboBox producentCB;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField D1textfield;
    @FXML
    private TextField D2textfield;
    @FXML
    private TextField L1textfield;
    @FXML
    private TextField L2textfield;
    @FXML
    private TextField angletextfield;
    @FXML
    private ComboBox toolMatComboBox;
    @FXML
    private ListView<String> addedIndexesListView;



    @FXML
    private Button confirmButton;

    ChamferDAO chamferDAO = new ChamferDAO();
    private ActionDAO actionDAO = new ActionDAO();
    private final ProducentDAO producentDA0 = new ProducentDAO();
    private ObservableList<String> addedIndexes = FXCollections.observableArrayList();


    public void initialize() throws SQLException {

        List<String> producentsList = producentDA0.getAllProducents();
        ObservableList<String> allProducents = FXCollections.observableArrayList(producentsList);
        producentCB.setItems(allProducents);

        addedIndexesListView.setItems(addedIndexes);
        //DEZAKTYWACJA PRZYCISKU ZATWIERZ W ZALEZNOSCI CZY JEST JAKIS TEKST W POLU CZY NIE
        BooleanBinding fieldsEmpty = Bindings.createBooleanBinding(() ->
                        nameTextField.getText().isEmpty() ||
                                toothsQtyTextField.getText().isEmpty() ||
                                priceTextField.getText().isEmpty() ||
                                producentCB.getValue() == null ||
                                L1textfield.getText().isEmpty() ||
                                L2textfield.getText().isEmpty() ||
                                D1textfield.getText().isEmpty() ||
                                D2textfield.getText().isEmpty() ||
                                angletextfield.getText().isEmpty() ||
                                toolMatComboBox.getValue() == null,
                nameTextField.textProperty(),
                toothsQtyTextField.textProperty(),
                priceTextField.textProperty(),
                L1textfield.textProperty(),
                L2textfield.textProperty(),
                D1textfield.textProperty(),
                D2textfield.textProperty(),
                angletextfield.textProperty(),
                toolMatComboBox.valueProperty()
        );

        confirmButton.disableProperty().bind(fieldsEmpty);



        setTwoNumsTextField(toothsQtyTextField);
        setThreeNumsTextField(L1textfield);
        setThreeNumsTextField(L2textfield);
        setDecNumTextField(D1textfield);
        setDecNumTextField(angletextfield);
        setDecNumTextField(D2textfield);
        setDecNumTextField(priceTextField);
        setTextFieldLimit(nameTextField, 23);




//        typeComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
//            // Dezaktywuj pole tekstowe dla średnicy, jeśli wybrany jest jeden z typów narzędzi
//            // dla których to pole ma być dezaktywowane
//            diamTextField.setDisable("Noże tokarskie".equals(newValue) || "Gwintowniki".equals(newValue));
//        });

//        typeComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
//            // Dezaktywuj pole tekstowe dla średnicy, jeśli wybrany jest jeden z typów narzędzi
//            // dla których to pole ma być dezaktywowane
//            insertTypeComboBox.setDisable("Gwintowniki".equals(newValue) || "Frezy".equals(newValue));
//        });




    }

    @FXML
    private void handleConfirmButton() throws SQLException {





        // Pobierz narzędzie z bazy danych na podstawie indeksu

//        String indexName = indexTextField.getText();
        BigDecimal price = new BigDecimal(priceTextField.getText());
        String toolName = nameTextField.getText();
        int toothsQty = Integer.valueOf(toothsQtyTextField.getText());
        String toolMaterial = (String) toolMatComboBox.getValue();
        int L1 = Integer.valueOf(L1textfield.getText());
        int L2 = Integer.valueOf(L2textfield.getText());
        double D1 = Double.valueOf(D1textfield.getText());
        double D2 = Double.valueOf(D2textfield.getText());
        double angle = Double.valueOf(angletextfield.getText());
        String prodName = producentCB.getValue().toString();


        List<String> toolIndexes = chamferDAO.getToolIndexesByToothsQty(toothsQty);

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
        String newIndex = String.format("CM-U-Z%d-%s", toothsQty, newSeqNumStr);



        Chamfer chamfer = new Chamfer(toolName, newIndex, ToolStatus.W_UZYCIU, "",price,prodName,D1, D2, L1, L2, toothsQty, MaterialType.valueOf(toolMaterial), angle );

        addedIndexes.add(newIndex);
        chamferDAO.addChamferTool(chamfer);
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

    private List<String> getToolIndex(int toothsQty) throws SQLException {
        List<String> toolIndexes = chamferDAO.getToolIndexesByToothsQty(0);
        for (String index : toolIndexes) {
            System.out.println(index);
        }
        return toolIndexes;
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
