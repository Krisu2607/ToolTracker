package com.example.tooltracker.controller;

import com.example.tooltracker.database.ToolUsageDAO;
import com.example.tooltracker.model.ToolAction;
import com.example.tooltracker.model.ToolUsage;
import com.example.tooltracker.model.tools.Tool;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ToolUsageController implements Initializable {


    @FXML
    private TableView<ToolUsage> toolUsageTable;
    @FXML
    private TableColumn<ToolUsage, String> partNumberColumn;
    @FXML
    private TableColumn<ToolUsage, String> orderNumberColumn;

    @FXML
    private TableColumn<ToolUsage, Integer> partsQtyColumn;
    @FXML
    private TableColumn<ToolUsage, Integer> usedInsertsColumn;
    @FXML
    private TableColumn<ToolUsage, Integer> usedTapsColumn;
    @FXML
    private TableColumn<ToolUsage, Integer> usedEmsColumn;
    @FXML
    private TableColumn<ToolUsage, Integer> usedBmColumn;
    @FXML
    private TableColumn<ToolUsage, Integer> usedDRVHMColumn;
    @FXML
    private TableColumn<ToolUsage, Integer> usedDRHSSColumn;
    @FXML
    private TableColumn<ToolUsage, Integer> usedDrBladeColumn;
    @FXML
    private TableColumn<ToolUsage, Integer> usedChamferColumn;
    @FXML
    private TableColumn<ToolUsage, Integer> usedShellMillColumn;
    @FXML
    private TableColumn<ToolUsage, Integer> usedThreadDieColumn;
    @FXML
    private TableColumn<ToolUsage, Integer> usedLatheODColumn;
    @FXML
    private TableColumn<ToolUsage, Integer> usedLatheIDColumn;
    @FXML
    private TableColumn<ToolUsage, Integer> usedFaceGrooveColumn;
    @FXML
    private TableColumn<ToolUsage, Integer> usedReamerColumn;
    @FXML
    private TableColumn<ToolUsage, Integer> usedSpotDrillColumn;
    @FXML
    private TableColumn<ToolUsage, String> infoColumn;
    @FXML
    private TableColumn<ToolUsage, String> machineColumn;

    @FXML
    private TextField searchField;
    ToolUsageDAO toolUsageDAO = new ToolUsageDAO();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        partNumberColumn.setCellValueFactory(new PropertyValueFactory<>("partNumber"));
        orderNumberColumn.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        partsQtyColumn.setCellValueFactory(new PropertyValueFactory<>("partsQty"));
        usedInsertsColumn.setCellValueFactory(new PropertyValueFactory<>("insertQty"));
        usedTapsColumn.setCellValueFactory(new PropertyValueFactory<>("tap"));
        usedEmsColumn.setCellValueFactory(new PropertyValueFactory<>("edgemill"));
        usedShellMillColumn.setCellValueFactory(new PropertyValueFactory<>("shellmill"));
        usedThreadDieColumn.setCellValueFactory(new PropertyValueFactory<>("threaddie"));
        usedChamferColumn.setCellValueFactory(new PropertyValueFactory<>("chamfer"));
        usedLatheIDColumn.setCellValueFactory(new PropertyValueFactory<>("lathe_id"));
        usedLatheODColumn.setCellValueFactory(new PropertyValueFactory<>("lathe_od"));
        usedReamerColumn.setCellValueFactory(new PropertyValueFactory<>("reamer"));
        usedDRHSSColumn.setCellValueFactory(new PropertyValueFactory<>("drillhss"));
        usedDRVHMColumn.setCellValueFactory(new PropertyValueFactory<>("drillvhm"));
        usedDrBladeColumn.setCellValueFactory(new PropertyValueFactory<>("drillblades"));
        usedFaceGrooveColumn.setCellValueFactory(new PropertyValueFactory<>("facegroove"));
        usedSpotDrillColumn.setCellValueFactory(new PropertyValueFactory<>("spotdrill"));
        usedBmColumn.setCellValueFactory(new PropertyValueFactory<>("bm"));
        infoColumn.setCellValueFactory(new PropertyValueFactory<>("info"));
        machineColumn.setCellValueFactory(new PropertyValueFactory<>("machine"));



    }





    @FXML
    private void SearchForOrder() throws SQLException {
        String partNum = searchField.getText();
        System.out.println(partNum);
        ObservableList<ToolUsage> allUsedTools = FXCollections.observableArrayList(toolUsageDAO.getAllUsedToolsFromPartNum(partNum));
        System.out.println(allUsedTools);
        toolUsageTable.setItems(allUsedTools);
    }


    @FXML
    private void ShowAll() throws SQLException {

        ObservableList<ToolUsage> allUsedTools = FXCollections.observableArrayList(toolUsageDAO.getAllUsedTools());
        System.out.println(allUsedTools);
        toolUsageTable.setItems(allUsedTools);
    }

    @FXML
    private void backToHomePage(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/StartView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root, 770, 500));
            stage.centerOnScreen();
            stage.show();



        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }
}

