package com.example.tooltracker.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ToolInsert {
    private final StringProperty insertName = new SimpleStringProperty();
    private final StringProperty insertIndex = new SimpleStringProperty();
    private final StringProperty insertType = new SimpleStringProperty();
    private final StringProperty toolsMatch = new SimpleStringProperty();
    private final StringProperty additionalInfo = new SimpleStringProperty();
    private final SimpleIntegerProperty insertQty = new SimpleIntegerProperty();

    public ToolInsert() {

    }

    public ToolInsert(String insertName,
                      String insertIndex,
                      String insertType,
                      String toolsMatch,
                      String additionalInfo,
                      int insertQty){
        this.insertName.set(insertName);
        this.insertIndex.set(insertIndex);
        this.insertType.set(insertType);
        this.toolsMatch.set(toolsMatch);
        this.additionalInfo.set(additionalInfo);
        this.insertQty.set(insertQty);
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

    public String getAdditionalInfo() {
        return additionalInfo.get();
    }

    public StringProperty additionalInfoProperty() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo.set(additionalInfo);
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
                ", additionalInfo=" + additionalInfo +
                ", insertQty=" + insertQty +
                '}';
    }
}
