package com.example.tooltracker.controller.newtoolcontrollers;

import com.example.tooltracker.controller.EditToolController;
import com.example.tooltracker.controller.LiveToolsViewController;
import com.example.tooltracker.database.CurrentDetailDAO;
import com.example.tooltracker.database.ToolInsertDAO;
import com.example.tooltracker.database.ToolUsageDAO;
import com.example.tooltracker.model.ToolInsert;
import com.example.tooltracker.model.tools.CurrentDetail;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class newOrderController {

    @FXML
    private TextField partNumTextField;
    @FXML
    private TextField orderNumTextField;
    @FXML
    private TextField partsQtyTextField;
    @FXML
    private Button confirmButton;
    @FXML
    private TabPane MainTabs;
    private Tab selectedTab;


    private LiveToolsViewController liveToolsViewController;

    public newOrderController() {
        // Domyślny konstruktor
    }



    private CurrentDetailDAO currentDetailDAO = new CurrentDetailDAO();


    public void initialize() {



        BooleanBinding fieldsEmpty = Bindings.createBooleanBinding(() ->
                        partNumTextField.getText().isEmpty() ||
                                orderNumTextField.getText().isEmpty() ||
                                partsQtyTextField.getText().isEmpty(),
                partNumTextField.textProperty(),
                orderNumTextField.textProperty(),
                partsQtyTextField.textProperty()
        );

        confirmButton.disableProperty().bind(fieldsEmpty);

        setFiveNumsTextField(partsQtyTextField);
        setTextFieldLimit(partNumTextField, 16);
        setTextFieldLimit(orderNumTextField, 16);










    }//KLAMRA KOŃCZĄCA INITIALIZE


    @FXML
    private void handleConfirmButton() throws SQLException {


        String partNum = partNumTextField.getText();
        String orderNum = orderNumTextField.getText();
        int partsQty = Integer.parseInt(partsQtyTextField.getText());


        CurrentDetail currentDetail = new CurrentDetail(selectedTab.getText(), orderNum, partNum, partsQty);
        currentDetailDAO.updateCurrentDetail(currentDetail);
        liveToolsViewController.setHeaderOfTiTledPanes();
        closeStage();

    }


    @FXML
    private void handleCancelButton() {
        closeStage();
    }
    private void closeStage() {
        Stage stage = (Stage) orderNumTextField.getScene().getWindow();
        stage.close();
    }

    public newOrderController(LiveToolsViewController liveToolsViewController, Tab selectedTab) {
        this.liveToolsViewController = liveToolsViewController;
        this.selectedTab = selectedTab;


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

    public String getSelectedTabName() {
        Tab selectedTab = MainTabs.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            return selectedTab.getText();
        } else {
            return null;
        }
    }





}
