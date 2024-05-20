package com.example.tooltracker.controller;

import com.example.tooltracker.database.ActionDAO;
import com.example.tooltracker.model.ToolAction;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.LocalDateTime;

public class ToolInfoController {

    private String toolIndex;



    @FXML
    private TableView<ToolAction> actionTable;
    @FXML
    private TableColumn<ToolAction, String> userColumn;
    @FXML
    private TableColumn<ToolAction, String> actionMadeColumn;
    @FXML
    private TableColumn<ToolAction, LocalDateTime> actionTimeColumn;
    @FXML
    ImageView toolImage;

    private  ActionDAO actionDAO = new ActionDAO();



    public void initialize() {
        actionTimeColumn.setCellValueFactory(new PropertyValueFactory<>("actionDateTime"));
        actionMadeColumn.setCellValueFactory(new PropertyValueFactory<>("tAction"));
        Platform.runLater(() -> {
            ObservableList<ToolAction> allActions = FXCollections.observableArrayList(actionDAO.getAllActionsWithIndex(toolIndex));
            actionTable.setItems(allActions);
            loadToolImage();


        });





    }

    private void loadToolImage() {
        // Sprawdź wartość toolIndex i wczytaj odpowiednie zdjęcie
        if ("LT-I-3022".equals(toolIndex)) {

            loadImageView("DWLNR2020K08.png");
        } else {
            // Domyślne zdjęcie lub brak zdjęcia dla innych wartości toolIndex
            loadImageView("defaultImage.png");
        }
    }

    private void loadImageView(String imageName) {
        // Załóż, że obrazy znajdują się w folderze "images" w katalogu resources
        String imagePath = "/com/example/tooltracker/icons/toolImages/" + imageName;

        // Utwórz obiekt Image i ustaw go na ImageView
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        toolImage.setImage(image);
    }





    public void setToolIndex(String toolIndex) {
        this.toolIndex = toolIndex;

    }





    }



