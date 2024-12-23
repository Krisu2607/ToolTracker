package com.example.tooltracker.controller.newtoolcontrollers;

import com.example.tooltracker.controller.ToolsController;
import com.example.tooltracker.database.ActionDAO;
import com.example.tooltracker.database.ProducentDAO;
import com.example.tooltracker.database.TapPrDAO;
import com.example.tooltracker.model.ToolAction;
import com.example.tooltracker.model.tools.MaterialType;
import com.example.tooltracker.model.tools.TapPR;
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

public class AddTapPRController {

    private ToolsController toolsController;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField threadClassField;
    @FXML
    private TextField mFieldText;
    @FXML
    private ComboBox<String> toolMatComboBox;
    @FXML
    private ComboBox<String> threadClassCB;
    @FXML
    private ComboBox producentCB;
    @FXML
    private TextField tapScroll;
    @FXML
    private TextField priceTextField;
    @FXML
    private ListView<String> addedIndexesListView;
    @FXML
    private Button confirmButton;

    private TapPrDAO tapPrDAO;
    private ObservableList<String> addedIndexes = FXCollections.observableArrayList();
    private ActionDAO actionDAO = new ActionDAO();
    private final ProducentDAO producentDA0 = new ProducentDAO();




    public void initialize() throws SQLException {
        List<String> producentsList = producentDA0.getAllProducents();
        ObservableList<String> allProducents = FXCollections.observableArrayList(producentsList);
        producentCB.setItems(allProducents);

        addedIndexesListView.setItems(addedIndexes);
        //DEZAKTYWACJA PRZYCISKU ZATWIERZ W ZALEZNOSCI CZY JEST JAKIS TEKST W POLU CZY NIE
        BooleanBinding fieldsEmpty = Bindings.createBooleanBinding(() ->
                        nameTextField.getText().isEmpty() ||
                                priceTextField.getText().isEmpty() ||
                                mFieldText.getText().isEmpty() ||
                                producentCB.getValue() == null ||
                                threadClassCB.getValue() == null ||
                                tapScroll.getText().isEmpty() ||
                                toolMatComboBox.getValue() == null,
                nameTextField.textProperty(),
                tapScroll.textProperty(),
                threadClassCB.valueProperty(),
                mFieldText.textProperty(),
                priceTextField.textProperty(),
                toolMatComboBox.valueProperty()
        );

        confirmButton.disableProperty().bind(fieldsEmpty);


        setDecNumTextField(priceTextField);
        setDecNumTextField(tapScroll);
        setDecNumTextField(mFieldText);
        setTextFieldLimit(nameTextField, 23);

    }




    public AddTapPRController() {
        tapPrDAO = new TapPrDAO();
    }

    @FXML
    public void handleConfirmButton() {
        try {
            String toolName = nameTextField.getText();
            String prodName = producentCB.getValue().toString();
            double metricSize = Double.parseDouble(mFieldText.getText());
            String toolMaterial = (String) toolMatComboBox.getValue();
            double tapScrollValue = Double.parseDouble(tapScroll.getText());
            BigDecimal price = new BigDecimal(priceTextField.getText());
            String threadClass = threadClassCB.getValue();

            // Znajdź ostatni indeks
            String lastIndex = String.valueOf(tapPrDAO.getLastToolIndex());
            String newIndex = generateNewIndex(lastIndex);

            TapPR newTool = new TapPR(toolName, newIndex, ToolStatus.W_UZYCIU, "", price,prodName, MaterialType.valueOf(toolMaterial), threadClass, metricSize, tapScrollValue);
            tapPrDAO.addTapPR(newTool);


            ToolAction toolAction = new ToolAction();
            toolAction.settAction("Nowe narzędzie");
            toolAction.settIndex(newIndex);
            actionDAO.addAction(toolAction);
            producentDA0.addCostByName(prodName, price);

            addedIndexesListView.getItems().add(newIndex);
        } catch (SQLException e) {
            showAlert("Błąd bazy danych", "Nie udało się dodać narzędzia do bazy danych.");
        } catch (Exception e) {
            showAlert("Błąd", "Wystąpił błąd podczas przetwarzania danych.");
        }
    }

    @FXML
    public void handleCancelButton() {
        Stage stage = (Stage) nameTextField.getScene().getWindow();
        stage.close();
    }

    private String generateNewIndex(String lastIndex) {
        String prefix = "TS-P-";
        int lastNum = Integer.parseInt(lastIndex.replace(prefix, ""));
        int newNum = lastNum + 1;
        return prefix + newNum;
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


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
