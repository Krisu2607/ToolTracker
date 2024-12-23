package com.example.tooltracker.controller;

import com.example.tooltracker.database.ActionDAO;
import com.example.tooltracker.database.InsertActionDAO;
import com.example.tooltracker.model.InsertAction;
import com.example.tooltracker.model.LoggedUser;
import com.example.tooltracker.model.ToolAction;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class ActionsHistoryController {


    @FXML
    private TableView<ToolAction> toolActionTable;
    @FXML
    private TableColumn<ToolAction, String> tuserColumn;
    @FXML
    private TableColumn<ToolAction, String> tindexColumn;
    @FXML
    private TableColumn<ToolAction, String> tactionMadeColumn;
    @FXML
    private TableColumn<ToolAction, LocalDateTime> tactionTimeColumn;


    @FXML
    private TableView<InsertAction> insertActionTable;
    @FXML
    private TableColumn<InsertAction, String> IuserColumn;
    @FXML
    private TableColumn<InsertAction, String> Iindex;
    @FXML
    private TableColumn<InsertAction, String> IactionMadeColumn;
    @FXML
    private TableColumn<InsertAction, LocalDateTime> IactionTimeColumn;

    private String username = LoggedUser.getUser().getUsername();













    private ActionDAO actionDAO = new ActionDAO();
    private InsertActionDAO insertActionDAO = new InsertActionDAO();


    public void initialize() {
        tuserColumn.setCellValueFactory(new PropertyValueFactory<>("user"));
        tactionTimeColumn.setCellValueFactory(new PropertyValueFactory<>("actionDateTime"));
        tactionMadeColumn.setCellValueFactory(new PropertyValueFactory<>("tAction"));
        tindexColumn.setCellValueFactory(new PropertyValueFactory<>("tIndex"));
        List<ToolAction> allActions1 = actionDAO.getAllActions();

        IuserColumn.setCellValueFactory(new PropertyValueFactory<>("user"));
        IactionTimeColumn.setCellValueFactory(new PropertyValueFactory<>("actionDateTime"));
        IactionMadeColumn.setCellValueFactory(new PropertyValueFactory<>("InsertAction"));
        Iindex.setCellValueFactory(new PropertyValueFactory<>("InsertIndex"));
        List<InsertAction> allInsertActions = insertActionDAO.getAllActions();



        Platform.runLater(() -> {
            ObservableList<ToolAction> allActions = FXCollections.observableArrayList(actionDAO.getAllActions());
            toolActionTable.setItems(allActions);
            ObservableList<InsertAction> allInsertActions2 = FXCollections.observableArrayList(insertActionDAO.getAllActions());
            insertActionTable.setItems(allInsertActions2);
        });




    }


    @FXML
    private void backToHomePage(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/ToolsView2.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root, 1100, 600));
            stage.show();



        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }




}
