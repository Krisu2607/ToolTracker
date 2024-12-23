package com.example.tooltracker.controller.newtoolcontrollers;

import com.example.tooltracker.controller.ToolsController;
import com.example.tooltracker.database.*;
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

public class AddDrillHssController {

    private ToolsController toolsController;


    @FXML
    private TextField nameTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField D1textfield;
    @FXML
    private TextField L1textfield;
    @FXML
    private TextField L2textfield;
    @FXML
    private ComboBox producentCB;


    @FXML
    private ListView<String> addedIndexesListView;



    @FXML
    private Button confirmButton;

    DrillHssDAO drillhssDAO = new DrillHssDAO();
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
                                producentCB.getValue() == null ||
                                L2textfield.getText().isEmpty() ||
                                D1textfield.getText().isEmpty(),
                nameTextField.textProperty(),
                priceTextField.textProperty(),
                L1textfield.textProperty(),
                L2textfield.textProperty(),
                D1textfield.textProperty()
        );

        confirmButton.disableProperty().bind(fieldsEmpty);

        setThreeNumsTextField(L1textfield);
        setThreeNumsTextField(L2textfield);
        setDecNumTextField(D1textfield);
        setDecNumTextField(priceTextField);
        setTextFieldLimit(nameTextField, 23);
    }

    @FXML
    private void handleConfirmButton() throws SQLException {

        String toolName = nameTextField.getText();
        BigDecimal price = new BigDecimal(priceTextField.getText());
        double D1 = Double.valueOf(D1textfield.getText());
        String prodName = producentCB.getValue().toString();

        int L1 = Integer.valueOf(L1textfield.getText());
        int L2 = Integer.valueOf(L2textfield.getText());

        // Sprawdź, czy średnica mieści się w zakresie od 1.0 do 3.0
        if (D1 >= 1.0 && D1 <= 3.0) {
            // Aktualizuj istniejące narzędzie
            String toolIndex = "DR-HSS-" + D1;
            // Pobierz narzędzie o danym indeksie z bazy danych
            DrillHSS existingTool = drillhssDAO.getDrillHSSByIndex(toolIndex);
            if (existingTool != null) {
                // Znaleziono istniejące narzędzie, więc zaktualizuj jego ilość
                existingTool.setQty(existingTool.getQty() + 1);
                drillhssDAO.updateDrillHSS(existingTool);
                // Dodaj do listy indeksów
                addedIndexes.add(toolIndex);
            } else {
                // Jeśli narzędzie o danym indeksie nie istnieje, wyświetl komunikat błędu lub podejmij inne działania
                System.err.println("Nie znaleziono narzędzia o indeksie: " + toolIndex);
            }
        } else {
            // Dodaj nowe narzędzie
            int lastNum = drillhssDAO.getLastToolIndex();
            int newSeqNum = lastNum + 1;
            String newIndex = "DR-HSS-" + newSeqNum;
            DrillHSS newTool = new DrillHSS(toolName, newIndex, ToolType.DRILL_HSS, ToolStatus.W_UZYCIU, "", price, prodName,D1, L1, L2, 1);
            drillhssDAO.addDrillHSS(newTool);
            // Dodaj do listy indeksów
            addedIndexes.add(newIndex);
            ToolAction toolAction = new ToolAction();
            toolAction.settAction("Nowe narzędzie");
            toolAction.settIndex(newIndex);
            actionDAO.addAction(toolAction);
            producentDA0.addCostByName(prodName, price);

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
        textField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,5}")) { // Akceptuj tylko cyfry i maksymalnie 5 znaków
                return change;
            }
            return null;
        }));
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

