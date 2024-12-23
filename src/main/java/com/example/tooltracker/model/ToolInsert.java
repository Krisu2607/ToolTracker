package com.example.tooltracker.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.math.BigDecimal;

public class ToolInsert {
    private final StringProperty insertName = new SimpleStringProperty();
    private final StringProperty insertIndex = new SimpleStringProperty();
    private final StringProperty insertType = new SimpleStringProperty();
    private final StringProperty toolsMatch = new SimpleStringProperty();
    private final StringProperty comment = new SimpleStringProperty();
    private final SimpleIntegerProperty insertQty = new SimpleIntegerProperty();
    private final SimpleIntegerProperty expectedQty = new SimpleIntegerProperty();

    private SimpleObjectProperty<BigDecimal> price;

    public ToolInsert() {
        this.price.set(BigDecimal.ZERO);
    }

    public ToolInsert(String insertName,
                      String insertIndex,
                      String insertType,
                      String toolsMatch,
                      String additionalInfo,
                      int insertQty,
                      int expectedQty,
                      BigDecimal price){
        this.insertName.set(insertName);
        this.insertIndex.set(insertIndex);
        this.insertType.set(insertType);
        this.toolsMatch.set(toolsMatch);
        this.comment.set(additionalInfo);
        this.insertQty.set(insertQty);
        this.expectedQty.set(expectedQty);
        this.price = new SimpleObjectProperty<>(price);
    }


    public int getExpectedQty() {
        return expectedQty.get();
    }

    public SimpleIntegerProperty expectedQtyProperty() {
        return expectedQty;
    }

    public void setExpectedQty(int expectedQty) {
        this.expectedQty.set(expectedQty);
    }

    public SimpleObjectProperty<BigDecimal> priceProperty() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price.set(price);
    }

    public BigDecimal getPrice() {
        return price.get();
    }

    public String getInsertName() {
        return insertName.get();
    }

    public StringProperty insertNameProperty() {
        return insertName;
    }

    public void setInsertName(String insertName) {
        this.insertName.set(insertName);
    }

    public String getInsertIndex() {
        return insertIndex.get();
    }

    public StringProperty insertIndexProperty() {
        return insertIndex;
    }

    public void setInsertIndex(String insertIndex) {
        this.insertIndex.set(insertIndex);
    }

    public String getInsertType() {
        return insertType.get();
    }

    public StringProperty insertTypeProperty() {
        return insertType;
    }

    public void setInsertType(String insertType) {
        this.insertType.set(insertType);
    }

    public String getToolsMatch() {
        return toolsMatch.get();
    }

    public StringProperty toolsMatchProperty() {
        return toolsMatch;
    }

    public void setToolsMatch(String toolsMatch) {
        this.toolsMatch.set(toolsMatch);
    }

    public String getComment() {
        return comment.get();
    }

    public StringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public int getInsertQty() {
        return insertQty.get();
    }

    public SimpleIntegerProperty insertQtyProperty() {
        return insertQty;
    }

    public void setInsertQty(int insertQty) {
        this.insertQty.set(insertQty);
    }

    @Override
    public String toString() {
        return "ToolInsert{" +
                "insertName=" + insertName +
                ", insertIndex=" + insertIndex +
                ", insertType=" + insertType +
                ", toolsMatch=" + toolsMatch +
                ", additionalInfo=" + comment +
                ", insertQty=" + insertQty +
                '}';
    }
}
