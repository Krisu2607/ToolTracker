package com.example.tooltracker.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ToolUsage {
    private final StringProperty toolName = new SimpleStringProperty();
    private StringProperty partNumber = new SimpleStringProperty();
    private StringProperty orderNumber = new SimpleStringProperty();
    private StringProperty insertIndex = new SimpleStringProperty();
    private IntegerProperty insertQty = new SimpleIntegerProperty();
    private IntegerProperty partsQty = new SimpleIntegerProperty();


    public ToolUsage(){};

    public ToolUsage(String partNumber,
                     String orderNumber,
                     String insertIndex,
                     int insertQty,
                     int partsQty) {
        this.partNumber.set(partNumber);
        this.orderNumber.set(orderNumber);
        this.insertIndex.set(insertIndex);
        this.insertQty.set(insertQty);
        this.partsQty.set(partsQty);
    }

    public String getToolName() {
        return toolName.get();
    }

    public StringProperty toolNameProperty() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName.set(toolName);
    }

    public String getPartNumber() {
        return partNumber.get();
    }

    public StringProperty partNumberProperty() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber.set(partNumber);
    }

    public String getOrderNumber() {
        return orderNumber.get();
    }

    public StringProperty orderNumberProperty() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber.set(orderNumber);
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

    public int getInsertQty() {
        return insertQty.get();
    }

    public IntegerProperty insertQtyProperty() {
        return insertQty;
    }

    public void setInsertQty(int insertQty) {
        this.insertQty.set(insertQty);
    }

    public int getPartsQty() {
        return partsQty.get();
    }

    public IntegerProperty partsQtyProperty() {
        return partsQty;
    }

    public void setPartsQty(int partsQty) {
        this.partsQty.set(partsQty);
    }


    //part_number VARCHAR(15),
    //    order_number VARCHAR(11),
    //    insert_index VARCHAR(15),
    //    insert_qty int
}
