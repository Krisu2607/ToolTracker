package com.example.tooltracker.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FactoryWishAction {

    private SimpleIntegerProperty wishId;
    private SimpleStringProperty wishAction;
    private SimpleStringProperty producent;
    private SimpleStringProperty whereOrdered;
    private SimpleStringProperty user;
    private SimpleIntegerProperty qty;
    private SimpleObjectProperty<BigDecimal> price;
    private SimpleObjectProperty<LocalDateTime> actionDateTime;

    public FactoryWishAction(int wishId,
                             String wishAction,
                             String producent,
                             String user,
                             int qty,
                             String whereOrdered,
                             LocalDateTime actionDateTime,
                             BigDecimal price) {
        this.wishId = new SimpleIntegerProperty(wishId);
        this.wishAction = new SimpleStringProperty(wishAction);
        this.producent = new SimpleStringProperty(producent);
        this.whereOrdered = new SimpleStringProperty(whereOrdered);
        this.user = new SimpleStringProperty(user);
        this.qty = new SimpleIntegerProperty(qty);
        this.actionDateTime = new SimpleObjectProperty<>(actionDateTime);
        this.price = new SimpleObjectProperty<>(price);

    }

    public String getWhereOrdered() {
        return whereOrdered.get();
    }

    public SimpleStringProperty whereOrderedProperty() {
        return whereOrdered;
    }

    public void setWhereOrdered(String whereOrdered) {
        this.whereOrdered.set(whereOrdered);
    }

    public BigDecimal getPrice() {
        return price.get();
    }

    public SimpleObjectProperty<BigDecimal> priceProperty() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price.set(price);
    }

    public int getWishId() {
        return wishId.get();
    }

    public SimpleIntegerProperty wishIdProperty() {
        return wishId;
    }

    public void setWishId(int wishId) {
        this.wishId.set(wishId);
    }

    public String getWishAction() {
        return wishAction.get();
    }

    public SimpleStringProperty wishActionProperty() {
        return wishAction;
    }

    public void setWishAction(String wishAction) {
        this.wishAction.set(wishAction);
    }

    public String getProducent() {
        return producent.get();
    }

    public SimpleStringProperty producentProperty() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent.set(producent);
    }

    public String getUser() {
        return user.get();
    }

    public SimpleStringProperty userProperty() {
        return user;
    }

    public void setUser(String user) {
        this.user.set(user);
    }

    public int getQty() {
        return qty.get();
    }

    public SimpleIntegerProperty qtyProperty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty.set(qty);
    }

    public LocalDateTime getActionDateTime() {
        return actionDateTime.get();
    }

    public SimpleObjectProperty<LocalDateTime> actionDateTimeProperty() {
        return actionDateTime;
    }

    public void setActionDateTime(LocalDateTime actionDateTime) {
        this.actionDateTime.set(actionDateTime);
    }
}
