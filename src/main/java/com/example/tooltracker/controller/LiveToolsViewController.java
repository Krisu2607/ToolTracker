package com.example.tooltracker.controller;

import com.example.tooltracker.controller.newtoolcontrollers.newOrderController;
import com.example.tooltracker.database.CurrentDetailDAO;
import com.example.tooltracker.database.InsertActionDAO;
import com.example.tooltracker.database.ToolInsertDAO;
import com.example.tooltracker.database.ToolUsageDAO;
import com.example.tooltracker.model.InsertAction;
import com.example.tooltracker.model.ToolInsert;
import com.example.tooltracker.model.tools.CurrentDetail;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class LiveToolsViewController {


    @FXML
    TableView<ToolInsert> toolInsertTableFF;
    @FXML
    TableView<ToolInsert> toolInsertTableNM;
    @FXML
    TableView<ToolInsert> toolInsertTableThreads;
    @FXML
    TableView<ToolInsert> toolInsertTableOthers;
    @FXML
    TableView<ToolInsert> toolInsertTableDRBlades;

    @FXML
    TableView<ToolInsert> toolInsertTableFF2;
    @FXML
    TableView<ToolInsert> toolInsertTableNM2;
    @FXML
    TableView<ToolInsert> toolInsertTableThreads2;
    @FXML
    TableView<ToolInsert> toolInsertTableOthers2;
    @FXML
    TableView<ToolInsert> toolInsertTableDRBlades2;
    @FXML
    TableView<ToolInsert> toolInsertTableODLathe2;
    @FXML
    TableView<ToolInsert> toolInsertTableIDLathe2;
    @FXML
    TableView<ToolInsert> toolInsertTableGroove2;
    @FXML
    TableView<ToolInsert> toolInsertTableFaceGroove2;









    @FXML
    TableView<ToolInsert> toolInsertTableFF3;
    @FXML
    TableView<ToolInsert> toolInsertTableNM3;
    @FXML
    TableView<ToolInsert> toolInsertTableThreads3;
    @FXML
    TableView<ToolInsert> toolInsertTableOthers3;
    @FXML
    TableView<ToolInsert> toolInsertTableDRBlades3;

    @FXML
    TableView<ToolInsert> toolInsertTableFF4;
    @FXML
    TableView<ToolInsert> toolInsertTableNM4;
    @FXML
    TableView<ToolInsert> toolInsertTableThreads4;
    @FXML
    TableView<ToolInsert> toolInsertTableOthers4;
    @FXML
    TableView<ToolInsert> toolInsertTableDRBlades4;


    @FXML
    TableView<ToolInsert> toolInsertTableFF5;
    @FXML
    TableView<ToolInsert> toolInsertTableNM5;
    @FXML
    TableView<ToolInsert> toolInsertTableThreads5;
    @FXML
    TableView<ToolInsert> toolInsertTableOthers5;
    @FXML
    TableView<ToolInsert> toolInsertTableDRBlades5;
    @FXML
    TableView<ToolInsert> toolInsertTableODLathe5;
    @FXML
    TableView<ToolInsert> toolInsertTableIDLathe5;
    @FXML
    TableView<ToolInsert> toolInsertTableGroove5;
    @FXML
    TableView<ToolInsert> toolInsertTableFaceGroove5;



    @FXML
    TableView<ToolInsert> toolInsertTableFF6;
    @FXML
    TableView<ToolInsert> toolInsertTableNM6;
    @FXML
    TableView<ToolInsert> toolInsertTableThreads6;
    @FXML
    TableView<ToolInsert> toolInsertTableOthers6;
    @FXML
    TableView<ToolInsert> toolInsertTableDRBlades6;
    @FXML
    TableView<ToolInsert> toolInsertTableODLathe6;
    @FXML
    TableView<ToolInsert> toolInsertTableIDLathe6;
    @FXML
    TableView<ToolInsert> toolInsertTableGroove6;
    @FXML
    TableView<ToolInsert> toolInsertTableFaceGroove6;


    @FXML
    private TitledPane VF2PANE;
    @FXML
    private TitledPane VF4PANE;
    @FXML
    private TitledPane HQRPANE;
    @FXML
    private TitledPane HCNPANE;
    @FXML
    private TitledPane DS30PANE;
    @FXML
    private TitledPane LM2000PANE;
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


    @FXML
    private Tab hcnTab;

    private InsertActionDAO insertActionDAO = new InsertActionDAO();



    private ToolInsertDAO toolInsertDAO = new ToolInsertDAO();
    private ToolUsageDAO toolUsageDAO = new ToolUsageDAO();
    private CurrentDetailDAO currentDetailDAO = new CurrentDetailDAO();



    String partNumberVF2;

    //    private String trelloApiKey = "b8664780be7a14f072655bd1af2d6aa4";
//    StringBuilder build = new StringBuilder();
//    String listIdFV2SS = "65856bc5cba8deabd1090bed";
//    HttpClient httpClient = HttpClients.custom()
//            .setDefaultRequestConfig(RequestConfig.custom()
//                    .setCookieSpec(CookieSpecs.STANDARD)
//                    .build())
//            .build();
//    HttpGet getRequest = new HttpGet("https://api.trello.com/1/lists/" + listIdFV2SS + "/cards?key=" + trelloApiKey + "&token=" + trelloApiToken);
    String imageUrl = "file:C:\\Users\\gercu\\Downloads\\employeemanager\\ToolTracker\\src\\main\\resources\\com\\example\\tooltracker\\icons\\rem.jpg";
    List<ToolInsert> allToolInserts1 = toolInsertDAO.getAllToolInserts();
    ObservableList<ToolInsert> insertsList = FXCollections.observableArrayList(allToolInserts1);


    public void initialize() {
        setHeaderOfTiTledPanes();


        initializeTableViewForMill(toolInsertTableNM, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("GlowiceNM")); // SM PLAN

        initializeTableViewForMill(toolInsertTableFF, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("GlowiceFF")); // SM PLAN
        initializeTableViewForMill(toolInsertTableThreads, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("Gwinty")); // SM PLAN
        initializeTableViewForMill(toolInsertTableDRBlades, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("WiertlaPlytk")); // SM PLAN

        initializeTableViewForMill(toolInsertTableOthers, toolInsert ->
                !toolInsert.getToolsMatch().equalsIgnoreCase("GlowiceNM") &
                !toolInsert.getToolsMatch().equalsIgnoreCase("GlowiceFF") &
                !toolInsert.getToolsMatch().equalsIgnoreCase("Gwinty") ); // INNE



        initializeTableViewForMill(toolInsertTableNM2, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("GlowiceNM")); // SM PLAN

        initializeTableViewForMill(toolInsertTableFF2, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("GlowiceFF")); // SM PLAN
        initializeTableViewForMill(toolInsertTableIDLathe2, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("NozeTokWew")); // SM PLAN
        initializeTableViewForMill(toolInsertTableGroove2, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("PrzecLis")); // SM PLAN
        initializeTableViewForMill(toolInsertTableFaceGroove2, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("Rowkiczol")); // SM PLAN
        initializeTableViewForMill(toolInsertTableODLathe2, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("NozeTokZew")); // SM PLAN
        initializeTableViewForMill(toolInsertTableThreads2, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("Gwinty")); // SM PLAN
        initializeTableViewForMill(toolInsertTableDRBlades2, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("WiertlaPlytk")); // SM PLAN
        initializeTableViewForMill(toolInsertTableOthers2, toolInsert ->
                !toolInsert.getToolsMatch().equalsIgnoreCase("GlowiceNM") &
                        !toolInsert.getToolsMatch().equalsIgnoreCase("GlowiceFF") &
                        !toolInsert.getToolsMatch().equalsIgnoreCase("NozeTokWew") &
                        !toolInsert.getToolsMatch().equalsIgnoreCase("PrzecLis") &
                        !toolInsert.getToolsMatch().equalsIgnoreCase("RowkiCzol") &
                        !toolInsert.getToolsMatch().equalsIgnoreCase("NozeTokZew") &
                        !toolInsert.getToolsMatch().equalsIgnoreCase("WiertlaPlytk") &
                        !toolInsert.getToolsMatch().equalsIgnoreCase("Gwinty") ); // INNE


        initializeTableViewForMill(toolInsertTableNM3, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("GlowiceNM")); // SM PLAN
        initializeTableViewForMill(toolInsertTableFF3, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("GlowiceFF")); // SM PLAN
        initializeTableViewForMill(toolInsertTableThreads3, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("Gwinty")); // SM PLAN
        initializeTableViewForMill(toolInsertTableDRBlades3, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("WiertlaPlytk")); // SM PLAN
        initializeTableViewForMill(toolInsertTableOthers3, toolInsert ->
                !toolInsert.getToolsMatch().equalsIgnoreCase("GlowiceNM") &
                        !toolInsert.getToolsMatch().equalsIgnoreCase("GlowiceFF") &
                        !toolInsert.getToolsMatch().equalsIgnoreCase("Gwinty") ); // INNE

        initializeTableViewForMill(toolInsertTableNM4, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("GlowiceNM")); // SM PLAN
        initializeTableViewForMill(toolInsertTableFF4, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("GlowiceFF")); // SM PLAN
        initializeTableViewForMill(toolInsertTableThreads4, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("Gwinty")); // SM PLAN
        initializeTableViewForMill(toolInsertTableDRBlades4, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("WiertlaPlytk")); // SM PLAN
        initializeTableViewForMill(toolInsertTableOthers4, toolInsert ->
                !toolInsert.getToolsMatch().equalsIgnoreCase("GlowiceNM") &
                        !toolInsert.getToolsMatch().equalsIgnoreCase("GlowiceFF") &
                        !toolInsert.getToolsMatch().equalsIgnoreCase("Gwinty") ); // INNE



        initializeTableViewForMill(toolInsertTableFF5, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("GlowiceFF")); // SM PLAN
        initializeTableViewForMill(toolInsertTableNM5, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("GlowiceNM")); // SM PLAN
        initializeTableViewForMill(toolInsertTableIDLathe5, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("NozeTokWew")); // SM PLAN
        initializeTableViewForMill(toolInsertTableGroove5, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("PrzecLis")); // SM PLAN
        initializeTableViewForMill(toolInsertTableFaceGroove5, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("Rowkiczol")); // SM PLAN
        initializeTableViewForMill(toolInsertTableODLathe5, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("NozeTokZew")); // SM PLAN
        initializeTableViewForMill(toolInsertTableThreads5, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("Gwinty")); // SM PLAN
        initializeTableViewForMill(toolInsertTableDRBlades5, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("WiertlaPlytk")); // SM PLAN
        initializeTableViewForMill(toolInsertTableOthers5, toolInsert ->
                !toolInsert.getToolsMatch().equalsIgnoreCase("GlowiceNM") &
                        !toolInsert.getToolsMatch().equalsIgnoreCase("GlowiceFF") &
                        !toolInsert.getToolsMatch().equalsIgnoreCase("NozeTokWew") &
                        !toolInsert.getToolsMatch().equalsIgnoreCase("PrzecLis") &
                        !toolInsert.getToolsMatch().equalsIgnoreCase("RowkiCzol") &
                        !toolInsert.getToolsMatch().equalsIgnoreCase("NozeTokZew") &
                        !toolInsert.getToolsMatch().equalsIgnoreCase("WiertlaPlytk") &
                        !toolInsert.getToolsMatch().equalsIgnoreCase("Gwinty") ); // INNE


        initializeTableViewForMill(toolInsertTableFF6, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("GlowiceFF")); // SM PLAN
        initializeTableViewForMill(toolInsertTableNM6, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("GlowiceNM")); // SM PLAN
        initializeTableViewForMill(toolInsertTableIDLathe6, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("NozeTokWew")); // SM PLAN
        initializeTableViewForMill(toolInsertTableGroove6, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("PrzecLis")); // SM PLAN
        initializeTableViewForMill(toolInsertTableFaceGroove6, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("Rowkiczol")); // SM PLAN
        initializeTableViewForMill(toolInsertTableODLathe6, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("NozeTokZew")); // SM PLAN
        initializeTableViewForMill(toolInsertTableThreads6, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("Gwinty")); // SM PLAN
        initializeTableViewForMill(toolInsertTableDRBlades6, toolInsert -> toolInsert.getToolsMatch().equalsIgnoreCase("WiertlaPlytk")); // SM PLAN
        initializeTableViewForMill(toolInsertTableOthers6, toolInsert ->
                !toolInsert.getToolsMatch().equalsIgnoreCase("GlowiceNM") &
                        !toolInsert.getToolsMatch().equalsIgnoreCase("GlowiceFF") &
                        !toolInsert.getToolsMatch().equalsIgnoreCase("NozeTokWew") &
                        !toolInsert.getToolsMatch().equalsIgnoreCase("PrzecLis") &
                        !toolInsert.getToolsMatch().equalsIgnoreCase("RowkiCzol") &
                        !toolInsert.getToolsMatch().equalsIgnoreCase("NozeTokZew") &
                        !toolInsert.getToolsMatch().equalsIgnoreCase("WiertlaPlytk") &
                        !toolInsert.getToolsMatch().equalsIgnoreCase("Gwinty") ); // INNE










    }

    public void setHeaderOfTiTledPanes() {
        CurrentDetail hcnCurrDetail = currentDetailDAO.getCurrentDetail("HCN");
        CurrentDetail vf4ssCurrDetail = currentDetailDAO.getCurrentDetail("VF4SS");
        CurrentDetail vf2ssCurrDetail = currentDetailDAO.getCurrentDetail("VF2SS");
        CurrentDetail hqrCurrDetail = currentDetailDAO.getCurrentDetail("HQR");
        CurrentDetail ds30ssyCurrDetail = currentDetailDAO.getCurrentDetail("DS30SSY");
        CurrentDetail doosanCurrDetail = currentDetailDAO.getCurrentDetail("DOOSAN");
        VF2PANE.setText(vf2ssCurrDetail.getPart_num());
        VF4PANE.setText(vf2ssCurrDetail.getPart_num());
        HCNPANE.setText(vf2ssCurrDetail.getPart_num());
        HQRPANE.setText(vf2ssCurrDetail.getPart_num());
        DS30PANE.setText(vf2ssCurrDetail.getPart_num());
        LM2000PANE.setText(vf2ssCurrDetail.getPart_num());
        VF2PANE.setText(vf2ssCurrDetail.getOrder_num()+"\n"+vf2ssCurrDetail.getPart_num()+"\n"+vf2ssCurrDetail.getParts_qty());
        VF4PANE.setText(vf4ssCurrDetail.getOrder_num()+"\n"+vf4ssCurrDetail.getPart_num()+"\n"+vf4ssCurrDetail.getParts_qty());
        HCNPANE.setText(hcnCurrDetail.getOrder_num()+"\n"+hcnCurrDetail.getPart_num()+"\n"+hcnCurrDetail.getParts_qty());
        HQRPANE.setText(hqrCurrDetail.getOrder_num()+"\n"+hqrCurrDetail.getPart_num()+"\n"+hqrCurrDetail.getParts_qty());
        DS30PANE.setText(ds30ssyCurrDetail.getOrder_num()+"\n"+ds30ssyCurrDetail.getPart_num()+"\n"+ds30ssyCurrDetail.getParts_qty());
        LM2000PANE.setText(doosanCurrDetail.getOrder_num()+"\n"+doosanCurrDetail.getPart_num()+"\n"+doosanCurrDetail.getParts_qty());
    }


    public void deleteheaders() {
        VF2PANE.setText("dupa");
    }


    private void initializeTableViewForMill(TableView<ToolInsert> tableView, java.util.function.Predicate<ToolInsert> filter) {
        FilteredList<ToolInsert> filteredList = new FilteredList<>(insertsList, filter);

        TableColumn<ToolInsert, String> imageCol = new TableColumn<>("Image");
        imageCol.setCellFactory(createRemImageButtonCellFactory());


        TableColumn<ToolInsert, String> toolImageCol = new TableColumn<>("Tool Image");
        toolImageCol.setCellFactory(setToolImage);

        TableColumn<ToolInsert, String> indexCol = new TableColumn<>("Index");
        indexCol.setCellValueFactory(new PropertyValueFactory<>("insertIndex"));

        TableColumn<ToolInsert, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("insertName"));
        nameCol.setMinWidth(100);

        tableView.getColumns().setAll(imageCol, toolImageCol, indexCol, nameCol);
        tableView.setItems(filteredList);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }


//        hcnTab.setStyle("-fx-background-color: white; -fx-font-weight: bold; -fx-text-fill: #FF6600;");


//        BooleanBinding fieldsEmpty = Bindings.createBooleanBinding(() ->
//                        partNumTextField.getText().isEmpty() ||
//                                orderNumTextField.getText().isEmpty() ||
//                                partsQtyTextField.getText().isEmpty(),
//                partNumTextField.textProperty(),
//                orderNumTextField.textProperty(),
//                partsQtyTextField.textProperty()
//        );
//
//        confirmButton.disableProperty().bind(fieldsEmpty);


//        name.setCellValueFactory(new PropertyValueFactory<>("insertName"));
//        index.setCellValueFactory(new PropertyValueFactory<>("insertIndex"));
//
//        name1.setCellValueFactory(new PropertyValueFactory<>("insertName"));
//        index1.setCellValueFactory(new PropertyValueFactory<>("insertIndex"));
//
//        name2.setCellValueFactory(new PropertyValueFactory<>("insertName"));
//        index2.setCellValueFactory(new PropertyValueFactory<>("insertIndex"));
//
//        name3.setCellValueFactory(new PropertyValueFactory<>("insertName"));
//        index3.setCellValueFactory(new PropertyValueFactory<>("insertIndex"));
//
//        name4.setCellValueFactory(new PropertyValueFactory<>("insertName"));
//        index4.setCellValueFactory(new PropertyValueFactory<>("insertIndex"));
//
//        name5.setCellValueFactory(new PropertyValueFactory<>("insertName"));
//        index5.setCellValueFactory(new PropertyValueFactory<>("insertIndex"));


//        ObservableList<ToolInsert> toolInserts = FXCollections.observableArrayList(toolInsertDAO.getAllToolInsertsFromPart(hcnCurrDetail.getPart_num()));
//        ObservableList<ToolInsert> toolInserts1 = FXCollections.observableArrayList(toolInsertDAO.getAllToolInsertsFromPart(hqrCurrDetail.getPart_num()));
//        ObservableList<ToolInsert> toolInserts2 = FXCollections.observableArrayList(toolInsertDAO.getAllToolInsertsFromPart(vf2ssCurrDetail.getPart_num()));
//        ObservableList<ToolInsert> toolInserts3 = FXCollections.observableArrayList(toolInsertDAO.getAllToolInsertsFromPart(vf4ssCurrDetail.getPart_num()));
//        ObservableList<ToolInsert> toolInserts4 = FXCollections.observableArrayList(toolInsertDAO.getAllToolInsertsFromPart(ds30ssyCurrDetail.getPart_num()));
//        ObservableList<ToolInsert> toolInserts5 = FXCollections.observableArrayList(toolInsertDAO.getAllToolInsertsFromPart(doosanCurrDetail.getPart_num()));


//        partToolInsert.setItems(toolInserts);
//        partToolInsert1.setItems(toolInserts1);
//        partToolInsert2.setItems(toolInserts2);
//        partToolInsert3.setItems(toolInserts3);
//        partToolInsert4.setItems(toolInserts4);
//        partToolInsert5.setItems(toolInserts5);
//        toolImage.setCellFactory(setToolImage);
//        toolImage1.setCellFactory(setToolImage);
//        toolImage2.setCellFactory(setToolImage);
//        toolImage3.setCellFactory(setToolImage);
//        toolImage4.setCellFactory(setToolImage);
//        toolImage5.setCellFactory(setToolImage);







    private Callback<TableColumn<ToolInsert, String>, TableCell<ToolInsert, String>> createRemImageButtonCellFactory() {
        return column -> new TableCell<>() {
            final Button imageButton = new Button();

            {
                imageButton.setPrefSize(50, 50);
                imageButton.setGraphic(new ImageView(new Image("file:C:\\Users\\gercu\\Downloads\\employeemanager\\ToolTracker\\src\\main\\resources\\com\\example\\tooltracker\\icons\\rem.jpg")));
                imageButton.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> imageButton.getScene().setCursor(javafx.scene.Cursor.HAND));
                imageButton.addEventHandler(MouseEvent.MOUSE_EXITED, event -> imageButton.getScene().setCursor(javafx.scene.Cursor.DEFAULT));
                imageButton.setOnAction(event -> {
                    if (!isEmpty()) {
                        ToolInsert selectedToolInsert = getTableRow().getItem();
                        boolean confirmed = showConfirmationDialog(selectedToolInsert.getInsertIndex());
                        if (confirmed) {
                            List<ToolInsert> allToolInserts = toolInsertDAO.getAllToolInserts();
                            String insertIndex = selectedToolInsert.getInsertIndex();
                            BigDecimal price = selectedToolInsert.getPrice();
                            System.out.println(price);
                            Optional<ToolInsert> toolInsertOptional = allToolInserts.stream()
                                    .filter(t -> insertIndex.equalsIgnoreCase(t.getInsertIndex()))
                                    .findFirst();
                            String partNumber = currentDetailDAO.getCurrentDetail(getSelectedTabName()).getPart_num();
                            String orderNumber = currentDetailDAO.getCurrentDetail(getSelectedTabName()).getOrder_num();
                            int partsQty = currentDetailDAO.getCurrentDetail(getSelectedTabName()).getParts_qty();
                            int insertQty = 1;

                            if (toolUsageDAO.isOrderNumPresent(orderNumber)) {
                                toolUsageDAO.incrementToolInsertsByOrder(orderNumber);
                                toolUsageDAO.addExpenseToOrder(orderNumber, price);
                            } else {
                                // Add new entry if it doesn't exist
                                toolUsageDAO.addToolUsage(orderNumber, partNumber, partsQty, getSelectedTabName());
                                toolUsageDAO.incrementToolInsertsByOrder(orderNumber);
                                toolUsageDAO.addExpenseToOrder(orderNumber, price);
                            }

                            toolInsertOptional.ifPresent(toolInsert -> {
                                int currentQty = toolInsert.getInsertQty();
                                toolInsert.setInsertQty(currentQty - 1);
                                toolInsertDAO.updateToolInsertQty(toolInsert);

                                InsertAction insertAction = new InsertAction(insertIndex, "Odpisano 1 szt.", LocalDateTime.now());
                                insertActionDAO.addAction(insertAction);

                                // Refresh table data
                                getTableView().refresh();
                            });
                        }
                    }
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(imageButton);
                }
            }
        };
    }


    //KLAMRA KOŃCZĄCA INITIALIZE






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


//            public String getPartNumberFromQue(HttpGet que) {
//                {
//
//
//                    HttpResponse response = null;
//                    try {
//                        response = httpClient.execute(que);
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//
//                    BufferedReader reader = null;
//                    try {
//                        reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                    StringBuilder result = new StringBuilder();
//                    String line;
//
//                    while (true) {
//                        try {
//                            if (!((line = reader.readLine()) != null)) break;
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//
//                        result.append(line);
//                    }
//                    JSONArray cards = new JSONArray(result.toString());
//
//
//                    for (int i = 0; i < cards.length(); i++) {
//                        JSONObject card = cards.getJSONObject(i);
//                        System.out.println(card.getString("name"));
//                        partNumberVF2 = card.getString("name");
//
//
//                    }
//
//
//                }
//                return partNumberVF2;
//            }


    private boolean showConfirmationDialog(String insertIndex) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potwierdzenie");
        alert.setHeaderText("Czy na pewno chcesz odpisać zużytą płytkę o numerze: " + insertIndex);
        alert.setContentText("Wybierz opcję:");

        ButtonType buttonTypeYes = new ButtonType("Tak");
        ButtonType buttonTypeNo = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == buttonTypeYes) {
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Sukces");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Odpisano 1 płytkę o numerze: " + insertIndex);

            successAlert.showAndWait();

            // Zwróć true po potwierdzeniu
            return true;
        } else {
            // Zwróć false w pozostałych przypadkach
            return false;
        }
    }


    Callback<TableColumn<ToolInsert, String>, TableCell<ToolInsert, String>> setToolImage = new Callback<TableColumn<ToolInsert, String>, TableCell<ToolInsert, String>>() {
        @Override
        public TableCell<ToolInsert, String> call(final TableColumn<ToolInsert, String> param) {
            final TableCell<ToolInsert, String> cell = new TableCell<ToolInsert, String>() {

                final ImageView imageView = new ImageView(); // Create ImageView

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        ToolInsert selectedTool = getTableView().getItems().get(getIndex());
                        String toolIndex = selectedTool.getInsertIndex();
                        File imageFile = new File("C:\\Users\\gercu\\Pulpit\\Zdjecia plytek\\" + toolIndex + ".png");



                        // Check if the image file exists
                        if (imageFile.exists()) {
                            Image image = new Image(imageFile.toURI().toString());
                            imageView.setImage(image);
                            imageView.setPreserveRatio(true);
                            imageView.setSmooth(false);

                        } else {
                            // Set default image or null if file does not exist
                            imageView.setImage(null);
                        }

                        setGraphic(imageView);
                        setText(null);
                    }
                }
            };
            return cell;
        }
    };


    private void setFiveNumsTextField(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d{1,4}")) {
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

    public String getSelectedTabName() {
        Tab selectedTab = MainTabs.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            return selectedTab.getText();
        } else {
            return null;
        }
    }


        @FXML
        private void showEditToolWindow (ActionEvent event){
            try {
                // Ładowanie widoku narzędzi
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/EditToolView2.fxml"));
                Parent root = loader.load();
                EditToolController2 editToolController = loader.getController();
                editToolController.setLiveToolsViewController(this);
                Stage stage = new Stage();
                stage.setScene(new Scene(root, 300, 150));

                // Ustawienie okna na zawsze na wierzchu
                stage.setAlwaysOnTop(true);

                // Ustawienie okna z prawej strony ekranu
                Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                double windowWidth = 300;
                double windowHeight = 150;

                double x = primaryScreenBounds.getMaxX() - windowWidth - 80;
                double y = primaryScreenBounds.getMinY() + (primaryScreenBounds.getHeight() - windowHeight) / 2;

                stage.setX(x);
                stage.setY(y);

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                // Obsługa błędów ładowania widoku
            }
        }

    @FXML
    private void showNewOrder (ActionEvent event){
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/NewOrderForm.fxml"));
            Parent root = loader.load();
            Tab selectedTab = MainTabs.getSelectionModel().getSelectedItem();
            newOrderController newOrderController = loader.getController();
            newOrderController.setLiveToolsViewController(this);
            newOrderController.setSelectedTab(selectedTab);

            Stage stage = new Stage();
            stage.setScene(new Scene(root, 400, 300));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }

    @FXML
    private void showOrderInfoWindow (ActionEvent event){
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/detailErrorsForm.fxml"));
            Parent root = loader.load();
            Tab selectedTab = MainTabs.getSelectionModel().getSelectedItem();
            DetailErrController detailErrController = loader.getController();
            detailErrController.setLiveToolsViewController(this);
            detailErrController.setSelectedTab(selectedTab);

            Stage stage = new Stage();
            stage.setScene(new Scene(root, 480, 300));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }








    public void reloadView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/liveToolsView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root, 1000, 700));
            stage.show();

            // Ustawienie okna na środku ekranu
            stage.setOnShown(event -> stage.centerOnScreen());

            // Zamknięcie starego okna
            Stage currentStage = (Stage) MainTabs.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }


    }

