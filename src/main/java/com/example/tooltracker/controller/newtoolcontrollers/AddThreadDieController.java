package com.example.tooltracker.controller.newtoolcontrollers;

import com.example.tooltracker.controller.ToolsController;
import com.example.tooltracker.database.ActionDAO;
import com.example.tooltracker.database.ProducentDAO;
import com.example.tooltracker.database.TapSkDAO;
import com.example.tooltracker.database.ThreadDieDAO;
import com.example.tooltracker.model.ToolAction;
import com.example.tooltracker.model.tools.MaterialType;
import com.example.tooltracker.model.tools.TapSK;
import com.example.tooltracker.model.tools.ThreadDie;
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

public class AddThreadDieController {
    private ToolsController toolsController;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField mFieldText;
    @FXML
    private TextField inchSizeTextField;
    @FXML
    private ComboBox<String> toolMatComboBox;
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

    private ThreadDieDAO threadDieDAO;

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
                                producentCB.getValue() == null ||
                                priceTextField.getText().isEmpty(),
                nameTextField.textProperty(),
                priceTextField.textProperty()
        );

        confirmButton.disableProperty().bind(fieldsEmpty);


        setDecNumTextField(priceTextField);
        setDecNumTextField(tapScroll);
        setDecNumTextField(mFieldText);
        setTextFieldLimit(nameTextField, 23);

    }




    public AddThreadDieController() {
        threadDieDAO = new ThreadDieDAO();
    }

    @FXML
    public void handleConfirmButton() {
        try {
            String toolName = nameTextField.getText();
            String prodName = producentCB.getValue().toString();
            String inchSize = inchSizeTextField.getText().isEmpty() ? "0" : inchSizeTextField.getText();
            double metricSize = mFieldText.getText().isEmpty() ? 0.0 : Double.parseDouble(mFieldText.getText());
            double tapScrollValue = tapScroll.getText().isEmpty() ? 0.0 : Double.parseDouble(tapScroll.getText());
            BigDecimal price = new BigDecimal(priceTextField.getText());

            // Znajdź ostatni indeks
            String lastIndex = String.valueOf(threadDieDAO.getLastToolIndex());
            String newIndex = generateNewIndex(lastIndex);

            ThreadDie newTool = new ThreadDie(toolName, newIndex, ToolStatus.W_UZYCIU, "", price,prodName, MaterialType.HSS, "H", metricSize, tapScrollValue, inchSize);
            threadDieDAO.addThreadDie(newTool);
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
        String prefix = "DM-";
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
