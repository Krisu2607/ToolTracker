package com.example.tooltracker.controller.newtoolcontrollers;

import com.example.tooltracker.controller.ToolsController;
import com.example.tooltracker.database.ActionDAO;
import com.example.tooltracker.database.ChamferDAO;
import com.example.tooltracker.database.ProducentDAO;
import com.example.tooltracker.database.SpotDrillDAO;
import com.example.tooltracker.model.ToolAction;
import com.example.tooltracker.model.tools.Chamfer;
import com.example.tooltracker.model.tools.MaterialType;
import com.example.tooltracker.model.tools.SpotDrill;
import com.example.tooltracker.model.tools.ToolStatus;
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

public class AddSpotDrillController {


    private ToolsController toolsController;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField priceTextField;
    @FXML
    private TextField D1textfield;
    @FXML
    private TextField D2textfield;
    @FXML
    private TextField L1textfield;
    @FXML
    private ComboBox toolMatComboBox;
    @FXML
    private ComboBox producentCB;
    @FXML
    private ListView<String> addedIndexesListView;



    @FXML
    private Button confirmButton;

    SpotDrillDAO spotDrillDAO = new SpotDrillDAO();
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
                                priceTextField.getText().isEmpty() ||
                                L1textfield.getText().isEmpty() ||
                                D1textfield.getText().isEmpty() ||
                                producentCB.getValue() == null ||
                                D2textfield.getText().isEmpty() ||
                                toolMatComboBox.getValue() == null,
                nameTextField.textProperty(),
                priceTextField.textProperty(),
                L1textfield.textProperty(),
                D1textfield.textProperty(),
                D2textfield.textProperty(),
                toolMatComboBox.valueProperty()
        );

        confirmButton.disableProperty().bind(fieldsEmpty);



        setThreeNumsTextField(L1textfield);
        setDecNumTextField(D1textfield);
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
        String prodName = producentCB.getValue().toString();
        String toolMaterial = (String) toolMatComboBox.getValue();
        int L1 = Integer.valueOf(L1textfield.getText());
        double D1 = Double.valueOf(D1textfield.getText());
        double D2 = Double.valueOf(D2textfield.getText());


        List<String> toolIndexes = spotDrillDAO.getToolIndexesByMat(toolMaterial);

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
        String newIndex = String.format("CD-%s-%s", toolMaterial, newSeqNumStr);



        SpotDrill spotDrill = new SpotDrill(toolName, newIndex, ToolStatus.W_UZYCIU, "",price,prodName,D1, D2, L1, MaterialType.valueOf(toolMaterial) );

        addedIndexes.add(newIndex);
        spotDrillDAO.addSpotDrill(spotDrill);
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
