package com.example.tooltracker.controller;

import com.example.tooltracker.database.ToolUsageDAO;
import com.example.tooltracker.model.ToolAction;
import com.example.tooltracker.model.ToolUsage;
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
import java.util.ResourceBundle;

public class ToolUsageController implements Initializable {
    @FXML
    private TableView<ToolUsage> toolUsageTable;
    @FXML
    private TableColumn<ToolUsage, String> partNumberColumn;
    @FXML
    private TableColumn<ToolUsage, String> orderNumberColumn;
    @FXML
    private TableColumn<ToolUsage, String> toolNumberColumn;
    @FXML
    private TableColumn<ToolUsage, Integer> quantityUsedColumn;
    @FXML
    private TableColumn<ToolUsage, Integer> quantityProducedColumn;
    @FXML
    private TextField searchField;



    ToolUsageDAO toolUsageDAO = new ToolUsageDAO();
    ObservableList<ToolUsage> allUsedTools = FXCollections.observableArrayList(toolUsageDAO.getAllUsedInserts());



//    private final StringProperty toolName = new SimpleStringProperty();
//    private StringProperty partNumber = new SimpleStringProperty();
//    private StringProperty orderNumber = new SimpleStringProperty();
//    private StringProperty insertIndex = new SimpleStringProperty();
//    private IntegerProperty insertQty = new SimpleIntegerProperty();
//    private IntegerProperty partsQty = new SimpleIntegerProperty();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partNumberColumn.setCellValueFactory(new PropertyValueFactory<>("partNumber"));
        orderNumberColumn.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        toolNumberColumn.setCellValueFactory(new PropertyValueFactory<>("insertIndex"));
        quantityUsedColumn.setCellValueFactory(new PropertyValueFactory<>("insertQty"));
        quantityProducedColumn.setCellValueFactory(new PropertyValueFactory<>("partsQty"));

            toolUsageTable.setItems(allUsedTools);


        // Ustawienie słuchacza do pola wyszukiwania
        searchField.textProperty().addListener((observable, oldValue, newValue) -> filterTable(newValue));
    }

    private void filterTable(String searchTerm) {
        ObservableList<ToolUsage> filteredData = FXCollections.observableArrayList();

        // Filtruj dane na podstawie numeru detalu
        for (ToolUsage row : allUsedTools) {
            if (row.getPartNumber().toLowerCase().contains(searchTerm.toLowerCase())) {
                filteredData.add(row);
            }
        }

        toolUsageTable.setItems(filteredData);
    }


    @FXML
    private void backToHomePage(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/StartView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root, 800, 600));
            stage.show();



        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }
}

