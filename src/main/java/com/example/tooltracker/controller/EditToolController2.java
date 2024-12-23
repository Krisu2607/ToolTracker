package com.example.tooltracker.controller;

import com.example.tooltracker.database.ActionDAO;
import com.example.tooltracker.database.CurrentDetailDAO;
import com.example.tooltracker.database.ToolDAO;
import com.example.tooltracker.database.ToolUsageDAO;
import com.example.tooltracker.model.ToolAction;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class EditToolController2 {


    private ToolsController toolsController;

    private LiveToolsViewController liveToolsViewController;



    @FXML
    private TextField indexTextField;

    @FXML
    private CheckBox sharpeningCheckBox;

    @FXML
    private Button confirmButton;
    @FXML
    private Label indexError;

    private ToolDAO toolDAO = new ToolDAO();
    private ActionDAO actionDAO = new ActionDAO();
    ToolUsageDAO toolUsageDAO = new ToolUsageDAO();
    CurrentDetailDAO currentDetailDAO = new CurrentDetailDAO();



    public void initialize() {
        // Dodaj listener do pola tekstowego, aby aktywować/zdezaktywować przycisk "Zatwierdź" w zależności od zawartości pola
        indexTextField.textProperty().addListener((observable, oldValue, newValue) ->{
            checkToolExistence(newValue);
            checkSharpeningAvailability(newValue);
        });
        confirmButton.setDisable(true);
    }


    @FXML
    private void handleConfirmButton() throws SQLException {
        String index = indexTextField.getText();
        boolean toSharpen = sharpeningCheckBox.isSelected();
        String newStatus = toSharpen ? "W_OSTRZENIU" : "ZUZYTE";

        toolDAO.updateToolStatus(index, newStatus);
        addToToolUsage(index);
        ToolAction toolAction = new ToolAction();
        toolAction.settAction(newStatus);
        toolAction.settIndex(index);
        actionDAO.addAction(toolAction);

        indexTextField.clear();
        sharpeningCheckBox.setSelected(false);
        indexTextField.requestFocus();


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Potwierdzenie");
        alert.setHeaderText(null);
        alert.setContentText("Udało się odpisać narzędzie o numerze indeksu: " + index);
        alert.showAndWait();
//        toolsController.refreshToolTable(toolsController.getToolNameFromIndex(index));
    }





    @FXML
    private void handleCancelButton() {
        closeStage();
    }

    private void closeStage() {
        Stage stage = (Stage) indexTextField.getScene().getWindow();
        stage.close();
    }
    public void setToolsController(ToolsController toolsController) {
        this.toolsController = toolsController;
    }
    public void setLiveToolsViewController(LiveToolsViewController liveToolsViewController) {
        this.liveToolsViewController = liveToolsViewController;
    }

    private void checkToolExistence(String toolIndex) {
        if (toolDAO.toolExists(toolIndex)) {
            confirmButton.setDisable(false);
            indexError.setText("✔");
            indexError.setTextFill(Color.GREEN);
        } else {
            confirmButton.setDisable(true);
            indexError.setText("Nie posiadamy takiego narzędzia");
            indexError.setTextFill(Color.RED);
        }
    }


    private void addToToolUsage(String toolIndex) throws SQLException {
        String partNumber = currentDetailDAO.getCurrentDetail(liveToolsViewController.getSelectedTabName()).getPart_num();
        String orderNumber = currentDetailDAO.getCurrentDetail(liveToolsViewController.getSelectedTabName()).getOrder_num();
        int partsQty = currentDetailDAO.getCurrentDetail(liveToolsViewController.getSelectedTabName()).getParts_qty();
        int insertQty = 1;

        if (toolUsageDAO.isOrderNumPresent(orderNumber)) {

            if (toolIndex.startsWith("EM")) {
                toolUsageDAO.incrementToolUsageColumn( "EM", orderNumber);
            } else if (toolIndex.startsWith("H-FF") || toolIndex.startsWith("H-NM") ) {
                toolUsageDAO.incrementToolUsageColumn("SM", orderNumber);
            } else if (toolIndex.startsWith("CM")) {
                toolUsageDAO.incrementToolUsageColumn("CM", orderNumber);
            } else if (toolIndex.startsWith("H-FF") || toolIndex.startsWith("H-NM") ) {
                toolUsageDAO.incrementToolUsageColumn("SM", orderNumber);
            }
            else if (toolIndex.startsWith("TS") || toolIndex.startsWith("TT") || toolIndex.startsWith("TC") ) {
                toolUsageDAO.incrementToolUsageColumn("TAP", orderNumber);
            }
            else if (toolIndex.startsWith("H-DR")  ) {
                toolUsageDAO.incrementToolUsageColumn("DRILL_BLADES", orderNumber);
            }
            else if (toolIndex.startsWith("DM")  ) {
                toolUsageDAO.incrementToolUsageColumn("THREADDIE", orderNumber);
            }
            else if (toolIndex.startsWith("LT-E-")  ) {
                toolUsageDAO.incrementToolUsageColumn("LATHE_OD", orderNumber);
            }
            else if (toolIndex.startsWith("LT-I-")  ) {
                toolUsageDAO.incrementToolUsageColumn("LATHE_ID", orderNumber);
            }
            else if (toolIndex.startsWith("LT-IE-")  ) {
                toolUsageDAO.incrementToolUsageColumn("FACE_GROOVE", orderNumber);
            }
            else if (toolIndex.startsWith("RD")  ) {
                toolUsageDAO.incrementToolUsageColumn("REAMER", orderNumber);
            }
            else if (toolIndex.startsWith("DR-HSS")  ) {
                toolUsageDAO.incrementToolUsageColumn("DRILL_HSS", orderNumber);
            }
            else if (toolIndex.startsWith("DR-VHM")  ) {
                toolUsageDAO.incrementToolUsageColumn("DRILL_VHM", orderNumber);
            }
            else if (toolIndex.startsWith("CD")  ) {
                toolUsageDAO.incrementToolUsageColumn("spotdrill", orderNumber);
            }

            else if (toolIndex.startsWith("BM")  ) {
                toolUsageDAO.incrementToolUsageColumn("BM", orderNumber);
            }


            else {
                // Handle cases where the prefix doesn't match any column
            }

            toolUsageDAO.addExpenseToOrder(orderNumber, toolDAO.getCostByToolIndex(toolIndex));



        } else {
            // Add new entry if it doesn't exist
            toolUsageDAO.addToolUsage(orderNumber, partNumber, partsQty, liveToolsViewController.getSelectedTabName());
            toolUsageDAO.addExpenseToOrder(orderNumber, toolDAO.getCostByToolIndex(toolIndex));

            if (toolIndex.startsWith("EM")) {
                toolUsageDAO.incrementToolUsageColumn( "EM", orderNumber);
            } else if (toolIndex.startsWith("H-FF") || toolIndex.startsWith("H-NM") ) {
                toolUsageDAO.incrementToolUsageColumn("SM", orderNumber);
            } else if (toolIndex.startsWith("CM")) {
                toolUsageDAO.incrementToolUsageColumn("CM", orderNumber);
            } else if (toolIndex.startsWith("H-FF") || toolIndex.startsWith("H-NM") ) {
                toolUsageDAO.incrementToolUsageColumn("SM", orderNumber);
            }
            else if (toolIndex.startsWith("TS") || toolIndex.startsWith("TT") ) {
                toolUsageDAO.incrementToolUsageColumn("TAP", orderNumber);
            }
            else if (toolIndex.startsWith("H-DR")  ) {
                toolUsageDAO.incrementToolUsageColumn("DRILL_BLADES", orderNumber);
            }
            else if (toolIndex.startsWith("DM")  ) {
                toolUsageDAO.incrementToolUsageColumn("THREADDIE", orderNumber);
            }
            else if (toolIndex.startsWith("LT-E")  ) {
                toolUsageDAO.incrementToolUsageColumn("LATHE_OD", orderNumber);
            }
            else if (toolIndex.startsWith("LT-I-")  ) {
                toolUsageDAO.incrementToolUsageColumn("LATHE_ID", orderNumber);
            }
            else if (toolIndex.startsWith("LT-IE-")  ) {
                toolUsageDAO.incrementToolUsageColumn("FACE_GROOVE", orderNumber);
            }
            else if (toolIndex.startsWith("RD-")  ) {
                toolUsageDAO.incrementToolUsageColumn("REAMER", orderNumber);
            }
            else if (toolIndex.startsWith("DR-HSS")  ) {
                toolUsageDAO.incrementToolUsageColumn("DRILL_HSS", orderNumber);
            }
            else if (toolIndex.startsWith("DR-VHM")  ) {
                toolUsageDAO.incrementToolUsageColumn("DRILL_VHM", orderNumber);
            }
            else if (toolIndex.startsWith("CD") ) {
                toolUsageDAO.incrementToolUsageColumn("spotdrill", orderNumber);
            }
            else if (toolIndex.startsWith("BM")  ) {
                toolUsageDAO.incrementToolUsageColumn("BM", orderNumber);
            }


            else {
                // Handle cases where the prefix doesn't match any column
            }
        }




    }

    private void checkSharpeningAvailability(String toolIndex) {
        if (toolIndex.length() >= 2) {
            String prefix = toolIndex.substring(0, 2).toUpperCase();
            List<String> validPrefixes = List.of("CM", "DR", "EM", "BM", "CM");

            if (validPrefixes.contains(prefix)) {
                sharpeningCheckBox.setDisable(false);
            } else {
                sharpeningCheckBox.setDisable(true);
                sharpeningCheckBox.setSelected(false); // Deselect if disabled
            }
        } else {
            sharpeningCheckBox.setDisable(true);
            sharpeningCheckBox.setSelected(false); // Deselect if disabled
        }
    }
}
