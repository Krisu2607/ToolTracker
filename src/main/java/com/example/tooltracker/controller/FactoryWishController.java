package com.example.tooltracker.controller;

import com.example.tooltracker.database.FactoryWishDAO;
import com.example.tooltracker.database.WishActionDAO;
import com.example.tooltracker.model.FactoryWish;
import com.example.tooltracker.model.FactoryWishAction;
import com.example.tooltracker.model.LoggedUser;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class FactoryWishController {

    FactoryWishController factoryWishController1 = this;



    @FXML
    TableView<FactoryWish> wishTable;
    @FXML
    private TableColumn<FactoryWish, String> wishInfo;
    @FXML
    private TableColumn<FactoryWish, String> butts;

    @FXML
    private TableColumn<FactoryWish, String> infoBut;
    @FXML
    private TableColumn<FactoryWish, String> butts1;
    @FXML
    private TableColumn<FactoryWish, String> butts2;
    @FXML
    private TableColumn<FactoryWish, String> wishType;
    @FXML
    private TableColumn<FactoryWish, String> wishMachine;
    @FXML
    private TableColumn<FactoryWish, String> wishProducent;
    @FXML
    private TableColumn<FactoryWish, String> wishStatus;
    @FXML
    private TableColumn<FactoryWish, String> wishUrl;
    @FXML
    private TableColumn<FactoryWish, Integer> idNum;

    private String username = LoggedUser.getUser().getUsername();

    WishActionDAO wishActionDAO = new WishActionDAO();



    FactoryWishDAO factoryWishDAO = new FactoryWishDAO();

    public void initialize() {

        wishInfo.setCellValueFactory(new PropertyValueFactory<>("info"));
        wishType.setCellValueFactory(new PropertyValueFactory<>("type"));
        wishMachine.setCellValueFactory(new PropertyValueFactory<>("machine"));
        wishProducent.setCellValueFactory(new PropertyValueFactory<>("producent"));
        wishStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        wishUrl.setCellValueFactory(new PropertyValueFactory<>("link"));
        wishInfo.setCellValueFactory(new PropertyValueFactory<>("info"));
        idNum.setCellValueFactory(new PropertyValueFactory<>("id"));
        butts.setCellFactory(setToolInfoBtn);
        butts1.setCellFactory(setToolInfoBtn2);
        butts2.setCellFactory(setToolInfoBtn1);
        infoBut.setCellFactory(setToolInfoBtn3);



        setWishTable();


        // Ustawienie komórki jako klikalnego tekstu
        wishUrl.setCellFactory(column -> {
            TextFieldTableCell<FactoryWish, String> cell = new TextFieldTableCell<>();


            cell.setOnMouseEntered(event -> {
                if (!cell.isEmpty()) {
                    cell.setCursor(Cursor.HAND);
                    cell.setStyle("-fx-background-color: lightgrey;");
                }
            });

            cell.setOnMouseExited(event -> {
                if (!cell.isEmpty()) {
                    cell.setCursor(Cursor.DEFAULT);
                    cell.setStyle("");
                }
            });


            cell.setOnMouseClicked(event -> {
                if (!cell.isEmpty()) {
                    FactoryWish factoryWish = cell.getTableRow().getItem();
                    String originalLink = factoryWish.getAuction();

                    // Kopiowanie linku do schowka
                    Clipboard clipboard = Clipboard.getSystemClipboard();
                    ClipboardContent content = new ClipboardContent();
                    content.putString(originalLink);
                    clipboard.setContent(content);


                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Link skopiowany");
                    alert.setHeaderText(null);
                    alert.setContentText("Skopiowano link do schowka:\n" + originalLink);
                    alert.showAndWait();
                }
            });
            return cell;
        });


        wishStatus.setCellFactory(setBackgroundOnStatus);





    }

    public void setWishTable() {
        List<FactoryWish> factoryWishList = factoryWishDAO.getAllWishes();

        ObservableList<FactoryWish> factoryWishOL = FXCollections.observableArrayList(factoryWishList);
        wishTable.setItems(factoryWishOL);
    }


    Callback<TableColumn<FactoryWish, String>, TableCell<FactoryWish, String>> setBackgroundOnStatus
            = new Callback<TableColumn<FactoryWish, String>, TableCell<FactoryWish, String>>() {
        @Override
        public TableCell<FactoryWish, String> call(final TableColumn<FactoryWish, String> param) {
            final TableCell<FactoryWish, String> cell = new TableCell<FactoryWish, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        // Clear the cell if empty or item is null
                        setText("");
                        setStyle("");
                    } else {
                        setText(String.valueOf(item));

                        // Set default style for border and padding
                        setStyle("-fx-padding: 3; " +
                                "-fx-border-color: black; " + // Set the border color
                                "-fx-border-width: 0.5; " +     // Set the border width
                                "-fx-border-style: solid; " + // Set the border style
                                "-fx-background-color: white;"); // Background color for padding/border area

                        // Apply different background color based on item value
                        if (item.equals("zamówione")) {
                            setStyle(getStyle() + "-fx-background-color: white, #2147cf; " +
                                    "-fx-background-insets: 0, 3;");
                        } else if (item.equals("zrealizowane")) {
                            setStyle(getStyle() + "-fx-background-color: white, #26e026; " +
                                    "-fx-background-insets: 0, 3;");
                        } else if (item.equals("oczekiwanie")) {
                            setStyle(getStyle() + "-fx-background-color: white, #d8db30; " +
                                    "-fx-background-insets: 0, 3;");
                        }
                    }
                }
            };
            return cell;
        }
    };




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

    Callback<TableColumn<FactoryWish, String>, TableCell<FactoryWish, String>> setToolInfoBtn =
            new Callback<TableColumn<FactoryWish, String>, TableCell<FactoryWish, String>>() {
                @Override
                public TableCell<FactoryWish, String> call(final TableColumn<FactoryWish, String> param) {
                    final TableCell<FactoryWish, String> cell = new TableCell<FactoryWish, String>() {

                        final Button btn1 = new Button();
                        final Pane yellowDot = new Pane();

                        {
                            // Ustawienie stylu CSS dla przycisku
                            btn1.getStyleClass().add("yellow-dot-button");
                            yellowDot.getStyleClass().add("dot");

                            // Dodanie kropki jako grafiki przycisku
                            btn1.setGraphic(yellowDot);

                            btn1.setOnAction(event -> {
                                FactoryWish selectedwish = getTableView().getItems().get(getIndex());
                                factoryWishDAO.updateOrderdedWaitingWish(selectedwish.getId(), "oczekiwanie");
                                wishActionDAO.addAction(new FactoryWishAction(selectedwish.getId(), "oczekiwanie", "", username, 0, "", LocalDateTime.now(), BigDecimal.ZERO));
                                setWishTable();


                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Zamówienie zaktualizowane");
                                alert.setHeaderText(null);
                                alert.setContentText("Zamówienie o numerze " + selectedwish.getId() + " otrzymało status: OCZEKIWANIE");
                                alert.showAndWait();

                            });
                        }

                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                setGraphic(btn1);
                                setText(null);
                            }
                        }
                    };
                    return cell;
                }
            };



    Callback<TableColumn<FactoryWish, String>, TableCell<FactoryWish, String>> setToolInfoBtn1 =
            new Callback<TableColumn<FactoryWish, String>, TableCell<FactoryWish, String>>() {
                @Override
                public TableCell<FactoryWish, String> call(final TableColumn<FactoryWish, String> param) {
                    final TableCell<FactoryWish, String> cell = new TableCell<FactoryWish, String>() {

                        final Button btn1 = new Button();
                        final Pane greenDot = new Pane();

                        {
                            // Ustawienie stylu CSS dla przycisku
                            btn1.getStyleClass().add("green-dot-button");
                            greenDot.getStyleClass().add("dot");

                            // Dodanie kropki jako grafiki przycisku
                            btn1.setGraphic(greenDot);

                            btn1.setOnAction(event -> {
                                try {
                                    // Ładowanie widoku narzędzi
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/wishCompleteView.fxml"));
                                    Parent root = loader.load();
                                    FactoryWish selectedwish = getTableView().getItems().get(getIndex());
                                    wishCompletedController wishCompletedController = loader.getController();
                                    wishCompletedController.setFactorywish(selectedwish);
                                    wishCompletedController.setFactoryWishController(factoryWishController1);


                                    Stage stage = new Stage();
                                    stage.setScene(new Scene(root, 350, 200));
                                    stage.setTitle("zamówienie nr:  " + selectedwish.getId());
                                    stage.show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    // Obsługa błędów ładowania widoku
                                }
                            });
                        }

                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                setGraphic(btn1);
                                setText(null);
                            }
                        }
                    };
                    return cell;
                }
            };




    Callback<TableColumn<FactoryWish, String>, TableCell<FactoryWish, String>> setToolInfoBtn3 =
            new Callback<TableColumn<FactoryWish, String>, TableCell<FactoryWish, String>>() {
                @Override
                public TableCell<FactoryWish, String> call(final TableColumn<FactoryWish, String> param) {
                    final TableCell<FactoryWish, String> cell = new TableCell<FactoryWish, String>() {

                        final Button btn1 = new Button();
                        final Label infoLabel = new Label("i");

                        {
                            // Setting the CSS style for the button
                            btn1.getStyleClass().add("grey-info-button");
                            infoLabel.getStyleClass().add("info-label");

                            // Adding the label as the graphic of the button
                            btn1.setGraphic(infoLabel);

                            btn1.setOnAction(event -> {
                                try {
                                    // Load the wish action view
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/wishActionView.fxml"));
                                    Parent root = loader.load();
                                    FactoryWish selectedwish = getTableView().getItems().get(getIndex());
                                    WishActionController wishActionController = loader.getController();
                                    wishActionController.setFactorywish(selectedwish);
                                    wishActionController.setFactoryWishController(factoryWishController1);

                                    Stage stage = new Stage();
                                    stage.centerOnScreen();
                                    stage.setScene(new Scene(root, 750, 200));
                                    stage.setTitle("Zamówienie nr: " + selectedwish.getId());
                                    stage.show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    // Handle errors when loading the view
                                }
                            });
                        }

                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                setGraphic(btn1);
                                setText(null);
                            }
                        }
                    };
                    return cell;
                }
            };







    Callback<TableColumn<FactoryWish, String>, TableCell<FactoryWish, String>> setToolInfoBtn2 =
            new Callback<TableColumn<FactoryWish, String>, TableCell<FactoryWish, String>>() {
                @Override
                public TableCell<FactoryWish, String> call(final TableColumn<FactoryWish, String> param) {
                    final TableCell<FactoryWish, String> cell = new TableCell<FactoryWish, String>() {

                        final Button btn1 = new Button();
                        final Pane bluedot = new Pane();

                        {
                            // Ustawienie stylu CSS dla przycisku
                            btn1.getStyleClass().add("blue-dot-button");
                            bluedot.getStyleClass().add("dot");

                            // Dodanie kropki jako grafiki przycisku
                            btn1.setGraphic(bluedot);

                            btn1.setOnAction(event -> {

                                    // Ładowanie widoku narzędzi
                                    FactoryWish selectedwish = getTableView().getItems().get(getIndex());
                                    factoryWishDAO.updateOrderdedWaitingWish(selectedwish.getId(), "zamówione");
                                wishActionDAO.addAction(new FactoryWishAction(selectedwish.getId(), "zamówione", "", username, 0, "", LocalDateTime.now(), BigDecimal.ZERO));

                                    setWishTable();

                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Zamówienie zaktualizowane");
                                alert.setHeaderText(null);
                                alert.setContentText("Zamówienie o numerze " + selectedwish.getId() + " otrzymało status: ZAMÓWIONE");
                                alert.showAndWait();



                            });
                        }

                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                setGraphic(btn1);
                                setText(null);
                            }
                        }
                    };
                    return cell;
                }
            };




    @FXML
    private void putWishView(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/newWishView.fxml"));
            Parent root = loader.load();
            NewWishController newWishController = loader.getController();
            newWishController.setFactoryWishController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 570,390));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }







}
