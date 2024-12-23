package com.example.tooltracker.model.tools;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.math.BigDecimal;

public class DrillVHM extends Tool1 {
    private SimpleBooleanProperty isInternalCooled;
    private SimpleDoubleProperty diameter;
    private SimpleIntegerProperty length;
    private SimpleIntegerProperty workLength;

    public DrillVHM(String toolName, String toolIndex, ToolStatus toolStatus,  String comment, BigDecimal price,String producent,
                    double diameter, int length, int workLength, boolean isInternalCooled) {
        super(toolName, toolIndex, ToolType.DRILL_VHM ,toolStatus,  comment, price, producent);
        this.isInternalCooled = new SimpleBooleanProperty(isInternalCooled);
        this.diameter = new SimpleDoubleProperty(diameter);
        this.length = new SimpleIntegerProperty(length);
        this.workLength = new SimpleIntegerProperty(workLength);
    }

    public boolean isIsInternalCooled() {
        return isInternalCooled.get();
    }

    public void setIsInternalCooled(boolean isInternalCooled) {
        this.isInternalCooled.set(isInternalCooled);
    }

    public double getDiameter() {
        return diameter.get();
    }

    public SimpleDoubleProperty diameterProperty() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter.set(diameter);
    }

    public int getLength() {
        return length.get();
    }

    public SimpleIntegerProperty lengthProperty() {
        return length;
    }

    public void setLength(int length) {
        this.length.set(length);
    }

    public int getWorkLength() {
        return workLength.get();
    }

    public SimpleIntegerProperty workLengthProperty() {
        return workLength;
    }

    public void setWorkLength(int workLength) {
        this.workLength.set(workLength);
    }

    // Getters and setters
    public boolean isInternalCooled() { return isInternalCooled.get(); }
    public void setInternalCooled(boolean isInternalCooled) { this.isInternalCooled.set(isInternalCooled); }
    public SimpleBooleanProperty isInternalCooledProperty() { return isInternalCooled; }
}

