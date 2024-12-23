package com.example.tooltracker.controller;

import com.example.tooltracker.database.CurrentDetailDAO;
import com.example.tooltracker.database.ToolUsageDAO;
import com.example.tooltracker.model.tools.CurrentDetail;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;

public class DetailErrController {


    @FXML
    private TextArea messageTextField;

    @FXML
    private Button confirmButton;
    @FXML
    private TabPane MainTabs;
    private Tab selectedTab;


    private LiveToolsViewController liveToolsViewController;

    public DetailErrController() {
        // Domyślny konstruktor
    }



    private CurrentDetailDAO currentDetailDAO = new CurrentDetailDAO();
    ToolUsageDAO toolUsageDAO = new ToolUsageDAO();



    public void initialize() {



        BooleanBinding fieldsEmpty = Bindings.createBooleanBinding(() ->
                        messageTextField.getText().isEmpty(),

                messageTextField.textProperty()

        );

        confirmButton.disableProperty().bind(fieldsEmpty);


        setTextFieldLimit(messageTextField, 80);











    }


    @FXML
    private void handleConfirmButton() throws SQLException {


        String message = messageTextField.getText();

        String orderNumber = currentDetailDAO.getCurrentDetail(liveToolsViewController.getSelectedTabName()).getOrder_num();
        toolUsageDAO.updateOrderInfo(orderNumber, message);

        closeStage();



    }


    @FXML
    private void handleCancelButton() {
        closeStage();
    }
    private void closeStage() {
        Stage stage = (Stage) messageTextField.getScene().getWindow();
        stage.close();
    }



    public void setSelectedTab(Tab selectedTab) {
        this.selectedTab = selectedTab;
    }

    public void setLiveToolsViewController(LiveToolsViewController liveToolsViewController) {
        this.liveToolsViewController = liveToolsViewController;
    }



//






    private void setFiveNumsTextField(TextField textField) {
        textField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,5}")) { // Akceptuj tylko cyfry i maksymalnie 5 znaków
                return change;
            }
            return null;
        }));
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

    public String getSelectedTabName() {
        Tab selectedTab = MainTabs.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            return selectedTab.getText();
        } else {
            return null;
        }
    }


}
