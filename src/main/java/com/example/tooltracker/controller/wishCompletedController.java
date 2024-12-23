package com.example.tooltracker.controller;

import com.example.tooltracker.database.FactoryWishDAO;
import com.example.tooltracker.database.InsertActionDAO;
import com.example.tooltracker.database.WishActionDAO;
import com.example.tooltracker.model.FactoryWish;
import com.example.tooltracker.model.FactoryWishAction;
import com.example.tooltracker.model.LoggedUser;
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

public class wishCompletedController {

    FactoryWish selectedFacWish;

    private FactoryWishController factoryWishController;


    @FXML
    private TextField whereOrderedTextField;
    @FXML
    private TextField costTextField;
    @FXML
    private TextField producentTextField;

    @FXML
    private Button confirmButton;

    FactoryWishDAO factoryWishDAO = new FactoryWishDAO();
    InsertActionDAO insertActionDAO = new InsertActionDAO();
    private String username = LoggedUser.getUser().getUsername();

    WishActionDAO wishActionDAO = new WishActionDAO();

    public void initialize() {


        BooleanBinding fieldsEmpty = Bindings.createBooleanBinding(() ->
                        whereOrderedTextField.getText().isEmpty() ||
                                costTextField.getText().isEmpty() ||
                                producentTextField.getText().isEmpty(),
                whereOrderedTextField.textProperty(),
                costTextField.textProperty(),
                producentTextField.textProperty()

        );

        confirmButton.disableProperty().bind(fieldsEmpty);

        setDecNumTextField(costTextField);
        setTextFieldLimit(producentTextField, 25);
        setTextFieldLimit(whereOrderedTextField, 30);
    }

    @FXML
    private void handleConfirmButton() throws SQLException {
        String whereOrdered = whereOrderedTextField.getText();
        BigDecimal price = new BigDecimal(costTextField.getText());
        String producent = producentTextField.getText();


        factoryWishDAO.updateOrderdedWaitingWish(selectedFacWish.getId(), "zrealizowane");
        factoryWishController.setWishTable();

        FactoryWishAction factoryWishAction = new FactoryWishAction(selectedFacWish.getId(),"zrealizowane", producent,
                username, 0, whereOrdered, LocalDateTime.now(), price);

        wishActionDAO.addAction(factoryWishAction);


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Zamówienie zaktualizowane");
        alert.setHeaderText(null);
        alert.setContentText("Zamówienie o numerze " + selectedFacWish.getId() + " otrzymało status: ZREALIZOWANE");
        alert.showAndWait();

    }


    @FXML
    private void handleCancelButton() {
        closeStage();
    }


    private void closeStage() {
        Stage stage = (Stage) producentTextField.getScene().getWindow();
        stage.close();
    }

    public void setFactoryWishController(FactoryWishController factoryWishController) {
        this.factoryWishController = factoryWishController;
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
                if (!newValue.matches("\\d{1,2}")) {
                    textField.setText(oldValue);
                }


            }
        });
    }


    private void setSingNumTextField(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
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


    public void setFactorywish(FactoryWish factoryWish) {
        this.selectedFacWish = factoryWish;

    }
}

