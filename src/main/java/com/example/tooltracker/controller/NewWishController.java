package com.example.tooltracker.controller;

import com.example.tooltracker.database.FactoryWishDAO;
import com.example.tooltracker.database.WishActionDAO;
import com.example.tooltracker.model.FactoryWish;
import com.example.tooltracker.model.FactoryWishAction;
import com.example.tooltracker.model.LoggedUser;
import com.example.tooltracker.model.ToolAction;
import com.example.tooltracker.model.tools.EmAlu;
import com.example.tooltracker.model.tools.MaterialType;
import com.example.tooltracker.model.tools.ToolStatus;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class NewWishController {

    private FactoryWishController factoryWishController;

    @FXML
    TextField urlTextField;

    @FXML
    TextArea infoTextArea;
    @FXML
    private ComboBox typeCB;

    @FXML
    private ComboBox machinesCB;

    @FXML
    private Button confirmButton;

    FactoryWishDAO factoryWishDAO = new FactoryWishDAO();

    private String username = LoggedUser.getUser().getUsername();

    WishActionDAO wishActionDAO = new WishActionDAO();



    public void initialize() {
        BooleanBinding fieldsEmpty = Bindings.createBooleanBinding(() ->
                        infoTextArea.getText().isEmpty() ||
                                machinesCB.getValue() == null ||
                                typeCB.getValue() == null,
                machinesCB.valueProperty(),
                typeCB.valueProperty(),
                infoTextArea.textProperty()
        );

        confirmButton.disableProperty().bind(fieldsEmpty);

        setTextFieldLimit(infoTextArea, 200);
    }




    @FXML
    private void handleConfirmButton() throws SQLException {





        // Pobierz narzędzie z bazy danych na podstawie indeksu

//        String indexName = indexTextField.getText();
        String wishInfo = infoTextArea.getText();
        String urlWish = urlTextField.getText();
        if(urlWish == null) {
            urlWish = "brak url";
        }
        String type = (String) typeCB.getValue();
        String machine = (String) machinesCB.getValue();



        FactoryWish factoryWish = new FactoryWish("", type, wishInfo, urlWish,"","oczekiwanie",machine,0 );


        int generatedId = factoryWishDAO.addWish(factoryWish);
        wishActionDAO.addAction(new FactoryWishAction(generatedId, "nowe zamówienie", "", username, 0, "", LocalDateTime.now(), BigDecimal.ZERO));


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("zamówienie do realizacji");
        alert.setHeaderText(null);
        alert.setContentText("Dodano zamówienie i ustawiono status: oczekiwanie");
        alert.showAndWait();


        factoryWishController.setWishTable();

        // Wróć do pierwszego pola tekstowego
        urlTextField.clear();
        infoTextArea.clear();
        typeCB.setValue(null);
        machinesCB.setValue(null);
        typeCB.requestFocus();


    }

    @FXML
    private void handleCancelButton() {
        closeStage();
    }




    private void closeStage() {
        Stage stage = (Stage) infoTextArea.getScene().getWindow();
        stage.close();
    }


    public void setFactoryWishController(FactoryWishController factoryWishController) {
        this.factoryWishController = factoryWishController;
    }






    private void setTextFieldLimit(TextArea textField, int maxLength) {
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
