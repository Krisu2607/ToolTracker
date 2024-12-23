package com.example.tooltracker.controller.newtoolcontrollers;

import com.example.tooltracker.controller.ToolsController;
import com.example.tooltracker.database.ActionDAO;
import com.example.tooltracker.database.EmAluDAO;
import com.example.tooltracker.database.ProducentDAO;
import com.example.tooltracker.database.VariousToolDAO;
import com.example.tooltracker.model.ToolAction;
import com.example.tooltracker.model.tools.EmAlu;
import com.example.tooltracker.model.tools.MaterialType;
import com.example.tooltracker.model.tools.ToolStatus;
import com.example.tooltracker.model.tools.VariousTool;
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

public class AddVariousToolController {
    private ToolsController toolsController;



    @FXML
    private TextField indexTextField;
    @FXML
    private TextField nameTextField;


    @FXML
    private TextField priceTextField;
    @FXML
    private TextField D1textfield;

    @FXML
    private ComboBox toolType;
    @FXML
    private ComboBox producentCB;
    @FXML
    private ListView<String> addedIndexesListView;



    @FXML
    private Button confirmButton;

    VariousToolDAO variousToolDAO = new VariousToolDAO();
    private ActionDAO actionDAO = new ActionDAO();
    private final ProducentDAO producentDA0 = new ProducentDAO();


    public void initialize() throws SQLException {
        List<String> producentsList = producentDA0.getAllProducents();
        ObservableList<String> allProducents = FXCollections.observableArrayList(producentsList);
        producentCB.setItems(allProducents);

        BooleanBinding fieldsEmpty = Bindings.createBooleanBinding(() ->
                        nameTextField.getText().isEmpty() ||
                                priceTextField.getText().isEmpty() ||
                                indexTextField.getText().isEmpty() ||
                                producentCB.getValue() == null ||
                                D1textfield.getText().isEmpty() ||

                                toolType.getValue() == null,
                nameTextField.textProperty(),
                indexTextField.textProperty(),
                priceTextField.textProperty(),
                D1textfield.textProperty(),
                toolType.valueProperty()
        );

        confirmButton.disableProperty().bind(fieldsEmpty);



        setDecNumTextField(D1textfield);
        setDecNumTextField(priceTextField);
        setTextFieldLimit(nameTextField, 26);
        setTextFieldLimit(indexTextField, 20);

    }

    @FXML
    private void handleConfirmButton() throws SQLException {






        BigDecimal price = new BigDecimal(priceTextField.getText());
        String toolName = nameTextField.getText();
        String prodName = producentCB.getValue().toString();
        String indexNum = indexTextField.getText();
        String toolDiam = D1textfield.getText();
        String toolType1 = (String) toolType.getValue();
        double D1 = Double.valueOf(D1textfield.getText());






        VariousTool variousTool = new VariousTool(toolName, indexNum, ToolStatus.W_UZYCIU, "",price, prodName,D1,  toolType1  );

        variousToolDAO.addVariousTool(variousTool);

        ToolAction toolAction = new ToolAction();
        toolAction.settAction("Nowe narzędzie");
        toolAction.settIndex(indexNum);
        actionDAO.addAction(toolAction);
        producentDA0.addCostByName(prodName, price);

        indexTextField.clear();
        nameTextField.clear();
        D1textfield.clear();
        priceTextField.clear();


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
            if (newText.matches("\\d{0,5}")) { // Akceptuj tylko cyfry i maksymalnie 5 znaków
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
