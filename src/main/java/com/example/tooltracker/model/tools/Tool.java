package com.example.tooltracker.model.tools;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

public class Tool {
    private final StringProperty toolName = new SimpleStringProperty();
    private final StringProperty toolIndex = new SimpleStringProperty();
    private final SimpleIntegerProperty toolQty = new SimpleIntegerProperty();
    private final StringProperty toolType = new SimpleStringProperty();
    private final SimpleDoubleProperty toolDiameter = new SimpleDoubleProperty();
    private final StringProperty toolInfo = new SimpleStringProperty();
    private final StringProperty toolStatus = new SimpleStringProperty();
    private final StringProperty insertMatching = new SimpleStringProperty();
    private final StringProperty insertQty = new SimpleStringProperty();
    private final StringProperty toolSizes = new SimpleStringProperty();





    public Tool(){}

    public Tool(String toolName,
                String toolIndex,
                int toolQty,
                String toolType,
                double toolDiameter,
                String toolInfo,
                String toolStatus,
                String insertMatching,
                String insertQty,
                String toolSizes
            ) {
        this.toolName.set(toolName);
        this.toolIndex.set(toolIndex);
        this.toolType.set(toolType);
        this.toolQty.set(toolQty);
        this.toolDiameter.set(toolDiameter);
        this.toolInfo.set(toolInfo);
        this.toolStatus.set(toolStatus);
        this.insertMatching.set(insertMatching);
        this.insertQty.set(insertQty);
        this.toolSizes.set(toolSizes);
    }

    public String getInsertQty() {
        return insertQty.get();
    }

    public StringProperty insertQtyProperty() {
        return insertQty;
    }

    public void setInsertQty(String insertQty) {
        this.insertQty.set(insertQty);
    }

    public String getInsertMatching() {
        return insertMatching.get();
    }

    public StringProperty insertMatchingProperty() {
        return insertMatching;
    }

    public void setInsertMatching(String insertMatching) {
        this.insertMatching.set(insertMatching);
    }

    public double getToolDiameter() {
        return toolDiameter.get();
    }

    public SimpleDoubleProperty toolDiameterProperty() {
        return toolDiameter;
    }

    public void setToolDiameter(double toolDiameter) {
        this.toolDiameter.set(toolDiameter);
    }

    public String getToolStatus() {
        return toolStatus.get();
    }

    public StringProperty toolStatusProperty() {
        return toolStatus;
    }

    public void setToolStatus(String toolStatus) {
        this.toolStatus.set(toolStatus);
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

    public String getToolIndex() {
        return toolIndex.get();
    }

    public StringProperty toolIndexProperty() {
        return toolIndex;
    }

    public void setToolIndex(String toolIndex) {
        this.toolIndex.set(toolIndex);
    }

    public String getToolType() {
        return toolType.get();
    }

    public StringProperty toolTypeProperty() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType.set(toolType);
    }

    public int getToolQty() {
        return toolQty.get();
    }

    public SimpleIntegerProperty toolQtyProperty() {
        return toolQty;
    }

    public void setToolQty(int toolQty) {
        this.toolQty.set(toolQty);
    }

    public String getToolInfo() {
        return toolInfo.get();
    }

    public StringProperty toolInfoProperty() {
        return toolInfo;
    }

    public void setToolInfo(String toolInfo) {
        this.toolInfo.set(toolInfo);
    }

    public String getToolSizes() {
        return toolSizes.get();
    }

    public StringProperty toolSizes() {
        return toolSizes;
    }

    public void setToolSizes(String toolSizes) {
        this.toolSizes.set(toolSizes);
    }

    @Override
    public String toString() {
        return "Tool{" +
                "toolName=" + toolName +
                ", toolIndex=" + toolIndex +
                ", toolQty=" + toolQty +
                ", toolType=" + toolType +
                ", toolDiameter=" + toolDiameter +
                ", toolInfo=" + toolInfo +
                ", toolStatus=" + toolStatus +
                ", insertMatching=" + insertMatching +
                ", insertQty=" + insertQty +
                '}';
    }



}
