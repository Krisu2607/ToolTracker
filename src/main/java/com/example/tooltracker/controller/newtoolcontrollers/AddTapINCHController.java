package com.example.tooltracker.controller.newtoolcontrollers;

import com.example.tooltracker.controller.ToolsController;
import com.example.tooltracker.database.ActionDAO;
import com.example.tooltracker.database.ProducentDAO;
import com.example.tooltracker.database.TapInchDAO;
import com.example.tooltracker.database.TapPrDAO;
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

public class AddTapINCHController {

    private ToolsController toolsController;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField inchSizefield;

    @FXML
    private ComboBox<String> toolMatComboBox;
    @FXML
    private ComboBox producentCB;

    @FXML
    private TextField priceTextField;
    @FXML
    private ListView<String> addedIndexesListView;
    @FXML
    private Button confirmButton;

    private TapInchDAO tapInchDAO;
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
                                producentCB.getValue() == null ||
                                inchSizefield.getText().isEmpty() ||
                                toolMatComboBox.getValue() == null,
                nameTextField.textProperty(),
                inchSizefield.textProperty(),
                priceTextField.textProperty(),
                toolMatComboBox.valueProperty()
        );

        confirmButton.disableProperty().bind(fieldsEmpty);


        setDecNumTextField(priceTextField);
        setTextFieldLimit(inchSizefield, 15);
        setTextFieldLimit(nameTextField, 23);


    }




    public AddTapINCHController() {
        tapInchDAO = new TapInchDAO();
    }

    @FXML
    public void handleConfirmButton() {
        try {
            String toolName = nameTextField.getText();
            String prodName = producentCB.getValue().toString();
            String inchSize = inchSizefield.getText();
            String toolMaterial = (String) toolMatComboBox.getValue();
            BigDecimal price = new BigDecimal(priceTextField.getText());

            // Znajdź ostatni indeks
            String lastIndex = String.valueOf(tapInchDAO.getLastToolIndex());
            String newIndex = generateNewIndex(lastIndex);

            TapInch newTool = new TapInch(toolName, newIndex, ToolStatus.W_UZYCIU, "", price,prodName, MaterialType.valueOf(toolMaterial),inchSize);
            tapInchDAO.addTapInch(newTool);
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
        String prefix = "TC-P-";
        int lastNum = Integer.parseInt(lastIndex.replace(prefix, ""));
        int newNum = lastNum + 1;
        return String.format(prefix + "%04d", newNum);
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
