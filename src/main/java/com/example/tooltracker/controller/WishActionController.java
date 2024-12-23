package com.example.tooltracker.controller;

import com.example.tooltracker.database.WishActionDAO;
import com.example.tooltracker.model.FactoryWish;
import com.example.tooltracker.model.FactoryWishAction;
import com.example.tooltracker.model.ToolAction;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;

public class WishActionController {

    @FXML
    TableView<FactoryWishAction> actionTable;

    @FXML
    TableColumn<FactoryWishAction, String> prodColumn;
    @FXML
    TableColumn<FactoryWishAction, String> whereOrderedColumn;
    @FXML
    TableColumn<FactoryWishAction, BigDecimal> costColumn;
    @FXML
    TableColumn<FactoryWishAction, Integer> qtyColumn;
    @FXML
    TableColumn<FactoryWishAction, String> actionMadeColumn;
    @FXML
    TableColumn<FactoryWishAction, String> actionTimeColumn;
    @FXML
    TableColumn<FactoryWishAction, String> userColumn;

    WishActionDAO wishActionDAO = new WishActionDAO();
    FactoryWish selectedFacWish;
    private FactoryWishController factoryWishController;

    public void initialize() {

        prodColumn.setCellValueFactory(new PropertyValueFactory<>("producent"));
        whereOrderedColumn.setCellValueFactory(new PropertyValueFactory<>("whereOrdered"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        qtyColumn.setCellValueFactory(new PropertyValueFactory<>("qty"));
        actionMadeColumn.setCellValueFactory(new PropertyValueFactory<>("wishAction"));
        actionTimeColumn.setCellValueFactory(new PropertyValueFactory<>("actionDateTime"));
        userColumn.setCellValueFactory(new PropertyValueFactory<>("user"));

        Platform.runLater(() -> {

        ObservableList<FactoryWishAction> allActions = FXCollections.observableArrayList(wishActionDAO.getAllActionsWithWishId(selectedFacWish.getId()));
        actionTable.setItems(allActions);

        });
    }






    public void setFactoryWishController(FactoryWishController factoryWishController) {
        this.factoryWishController = factoryWishController;
    }

    public void setFactorywish(FactoryWish factoryWish) {
        this.selectedFacWish = factoryWish;

    }
}
