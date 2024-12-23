package com.example.tooltracker.model.tools;

import com.example.tooltracker.model.tools.ToolType;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import java.math.BigDecimal;

public class Tool1 {

    private SimpleStringProperty producent;
    private SimpleStringProperty toolName;
    private SimpleStringProperty toolIndex;
    private SimpleObjectProperty<ToolType> toolType;
    private SimpleObjectProperty<ToolStatus> toolStatus;
    private SimpleStringProperty comment;
    private SimpleObjectProperty<BigDecimal> price;

    public Tool1(String toolName, String toolIndex, ToolType toolType, ToolStatus toolStatus, String comment, BigDecimal price, String producent) {
        this.toolName = new SimpleStringProperty(toolName);
        this.toolIndex = new SimpleStringProperty(toolIndex);
        this.toolType = new SimpleObjectProperty<>(toolType);
        this.toolStatus = new SimpleObjectProperty<>(toolStatus);
        this.comment = new SimpleStringProperty(comment);
        this.price = new SimpleObjectProperty<>(price);
        this.producent = new SimpleStringProperty(producent);
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

    public SimpleStringProperty commentProperty() {
        return comment;
    }

    public SimpleObjectProperty<BigDecimal> priceProperty() {
        return price;
    }

    // Getters and setters
    public String getToolName() {
        return toolName.get();
    }

    public void setToolName(String toolName) {
        this.toolName.set(toolName);
    }

    public SimpleStringProperty toolNameProperty() {
        return toolName;
    }

    public String getToolIndex() {
        return toolIndex.get();
    }

    public void setToolIndex(String toolIndex) {
        this.toolIndex.set(toolIndex);
    }

    public SimpleStringProperty toolIndexProperty() {
        return toolIndex;
    }

    public ToolType getToolType() {
        return toolType.get();
    }

    public void setToolType(ToolType toolType) {
        this.toolType.set(toolType);
    }

    public SimpleObjectProperty<ToolType> toolTypeProperty() {
        return toolType;
    }

    public ToolStatus getToolStatus() {
        return toolStatus.get();
    }

    public void setToolStatus(ToolStatus toolStatus) {
        this.toolStatus.set(toolStatus);
    }

    public SimpleObjectProperty<ToolStatus> toolStatusProperty() {
        return toolStatus;
    }

    public String getComment() {
        return comment.get();
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public void setPrice(BigDecimal price) {
        this.price.set(price);
    }

    public BigDecimal getPrice() {
        return price.get();
    }
}