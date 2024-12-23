package com.example.tooltracker.controller.newtoolcontrollers;

import com.example.tooltracker.controller.ToolsController;
import com.example.tooltracker.database.ActionDAO;
import com.example.tooltracker.database.EMRDAO;
import com.example.tooltracker.database.EmAluDAO;
import com.example.tooltracker.database.ProducentDAO;
import com.example.tooltracker.model.ToolAction;
import com.example.tooltracker.model.tools.*;
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

public class AddEmRController {

    private ToolsController toolsController;



    @FXML
    private TextField indexTextField;
    @FXML
    private TextField nameTextField;

    @FXML
    private TextField toothsQtyTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField emRadius;

    @FXML
    private TextField D1textfield;
    @FXML
    private TextField D2textfield;
    @FXML
    private TextField L1textfield;
    @FXML
    private TextField L2textfield;
    @FXML
    private ComboBox toolMatComboBox;
    @FXML
    private ComboBox producentCB;
    @FXML
    private ListView<String> addedIndexesListView;
    @FXML
    private RadioButton ballYes;
    @FXML
    private RadioButton ballNo;
    @FXML
    private RadioButton ALUchoice;
    @FXML
    private RadioButton STALchoice;
    @FXML
    private ToggleGroup ballToggleGroup;

    @FXML
    private ToggleGroup materialToggleGroup;



    @FXML
    private Button confirmButton;

    EMRDAO emrdao = new EMRDAO();
    private ActionDAO actionDAO = new ActionDAO();
    private final ProducentDAO producentDA0 = new ProducentDAO();
    private ObservableList<String> addedIndexes = FXCollections.observableArrayList();


    public void initialize() throws SQLException {
        List<String> producentsList = producentDA0.getAllProducents();
        ObservableList<String> allProducents = FXCollections.observableArrayList(producentsList);
        producentCB.setItems(allProducents);

        addedIndexesListView.setItems(addedIndexes);

        ballToggleGroup = new ToggleGroup();
        ballYes.setToggleGroup(ballToggleGroup);
        ballNo.setToggleGroup(ballToggleGroup);

        materialToggleGroup = new ToggleGroup();
        STALchoice.setToggleGroup(materialToggleGroup);
        ALUchoice.setToggleGroup(materialToggleGroup);

        BooleanBinding fieldsEmpty = Bindings.createBooleanBinding(() ->
                        nameTextField.getText().isEmpty() ||
                                toothsQtyTextField.getText().isEmpty() ||
                                priceTextField.getText().isEmpty() ||
                                L1textfield.getText().isEmpty() ||
                                producentCB.getValue() == null ||
                                L2textfield.getText().isEmpty() ||
                                D1textfield.getText().isEmpty() ||
                                D2textfield.getText().isEmpty() ||
                                (ballNo.isSelected() && emRadius.getText().isEmpty()) ||
                                toolMatComboBox.getValue() == null ||
                                ballToggleGroup.getSelectedToggle() == null ||
                                materialToggleGroup.getSelectedToggle() == null,
                nameTextField.textProperty(),
                toothsQtyTextField.textProperty(),
                priceTextField.textProperty(),
                L1textfield.textProperty(),
                L2textfield.textProperty(),
                D1textfield.textProperty(),
                D2textfield.textProperty(),
                emRadius.textProperty(),
                ballNo.selectedProperty(),
                toolMatComboBox.valueProperty(),
                ballToggleGroup.selectedToggleProperty(),
                materialToggleGroup.selectedToggleProperty()
        );

            confirmButton.disableProperty().bind(fieldsEmpty);
        System.out.println(fieldsEmpty.get());


//        if (nameTextField.getText().isEmpty() ||
//                toothsQtyTextField.getText().isEmpty() ||
//                priceTextField.getText().isEmpty() ||
//                D1textfield.getText().isEmpty() ||
//                D2textfield.getText().isEmpty() ||
//                L1textfield.getText().isEmpty() ||
//                L2textfield.getText().isEmpty() ||
//                toolMatComboBox.getValue() == null ||
//                ballToggleGroup.getSelectedToggle() == null ||
//                materialToggleGroup.getSelectedToggle() == null ||
//                (!ballYes.isSelected() && emRadius.getText().isEmpty())) {
//
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Błąd");
//            alert.setHeaderText("Brakujące dane");
//            alert.setContentText("Proszę wypełnić wszystkie wymagane pola.");
//            alert.showAndWait();



        setTwoNumsTextField(toothsQtyTextField);
        setThreeNumsTextField(L1textfield);
        setThreeNumsTextField(L2textfield);
        setDecNumTextField(D1textfield);
        setDecNumTextField(D2textfield);
        setDecNumTextField(priceTextField);
        setDecNumTextField(emRadius);
        setTextFieldLimit(nameTextField, 23);

        ballToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && "Tak".equals(((RadioButton) newValue).getText())) {
                emRadius.setText("0");
                emRadius.setDisable(true);
            } else {
                emRadius.setDisable(false);
            }
        });
    }

    @FXML
    private void handleConfirmButton() throws SQLException {
        BigDecimal price = new BigDecimal(priceTextField.getText());
        String toolName = nameTextField.getText();
        int toothsQty = Integer.parseInt(toothsQtyTextField.getText());
        String toolMaterial = (String) toolMatComboBox.getValue();
        int L1 = Integer.parseInt(L1textfield.getText());
        int L2 = Integer.parseInt(L2textfield.getText());
        double D1 = Double.parseDouble(D1textfield.getText());
        double D2 = Double.parseDouble(D2textfield.getText());
        double radius = Double.parseDouble(emRadius.getText());
        String prodName = producentCB.getValue().toString();

        RadioButton selectedBallOption = (RadioButton) ballToggleGroup.getSelectedToggle();
        boolean isBall = "Tak".equals(selectedBallOption.getText());

        RadioButton selectedMaterialOption = (RadioButton) materialToggleGroup.getSelectedToggle();
        boolean isStal = "STAL".equals(selectedMaterialOption.getText());

        String materialCode = isStal ? "P" : "N";
        double radius1 = 0.0;
        if (!isBall) {
            radius1 = Double.parseDouble(emRadius.getText());
        }

        List<String> toolIndexes = emrdao.getToolIndexesByCriteria(toothsQty, isBall, radius, materialCode);




        int maxSeqNum = toolIndexes.stream()
                .map(index -> {
                    String[] parts = index.split("-");
                    return Integer.parseInt(parts[parts.length - 1]);
                })
                .max(Integer::compare)
                .orElse(0);

        int newSeqNum = maxSeqNum + 1;

        String newSeqNumStr = (newSeqNum < 10000) ? String.format("%04d", newSeqNum) : String.valueOf(newSeqNum);

        String newIndex;
        if (isBall) {
            newIndex = String.format("BM-%s-%d-%s", materialCode, toothsQty, newSeqNumStr);
        } else {
            double toolradius = Double.parseDouble(emRadius.getText());
            newIndex = String.format("EM-R%.1f-%s-%d-%s", toolradius, materialCode, toothsQty, newSeqNumStr);
        }

        EmR emR = new EmR(toolName, newIndex, ToolStatus.W_UZYCIU, "", price,prodName, isBall,radius, L1, L2, D1, D2, MaterialType.valueOf(toolMaterial), toothsQty, ToolDestinyMat.valueOf(selectedMaterialOption.getText()));
        emrdao.addEmrTool(emR);
        ToolAction toolAction = new ToolAction();
        toolAction.settAction("Nowe narzędzie");
        toolAction.settIndex(newIndex);
        actionDAO.addAction(toolAction);
        producentDA0.addCostByName(prodName, price);



        addedIndexes.add(String.format( newIndex));

//        nameTextField.clear();
//        toothsQtyTextField.clear();
//        priceTextField.clear();
//        D1textfield.clear();
//        D2textfield.clear();
//        L1textfield.clear();
//        L2textfield.clear();
//        emRadius.clear();
//        toolMatComboBox.setValue(null);
//        ballToggleGroup.selectToggle(null);
//        materialToggleGroup.selectToggle(null);

//        nameTextField.requestFocus();



        // WALIDACJA
        if (nameTextField.getText().isEmpty() ||
                toothsQtyTextField.getText().isEmpty() ||
                priceTextField.getText().isEmpty() ||
                D1textfield.getText().isEmpty() ||
                D2textfield.getText().isEmpty() ||
                L1textfield.getText().isEmpty() ||
                L2textfield.getText().isEmpty() ||
                toolMatComboBox.getValue() == null ||
                ballToggleGroup.getSelectedToggle() == null ||
                materialToggleGroup.getSelectedToggle() == null ||
                (!ballYes.isSelected() && emRadius.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Brakujące dane");
            alert.setContentText("Proszę wypełnić wszystkie wymagane pola.");
            alert.showAndWait();
            return;
        }
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


//        EM-R1.0-P-4-0017   frez z promieniem 1.0
//        BM-P-2-0000 FREZ KULISTY DO STALI 2 ZEBOWY
