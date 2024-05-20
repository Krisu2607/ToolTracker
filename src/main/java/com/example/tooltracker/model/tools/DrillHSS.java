package com.example.tooltracker.model.tools;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.math.BigDecimal;

public class DrillHSS extends Tool1 {
    private SimpleDoubleProperty diameter;
    private SimpleIntegerProperty length;
    private SimpleIntegerProperty qty;
    private SimpleIntegerProperty workLength;


    public DrillHSS(String toolName, String toolIndex, ToolType toolType, ToolStatus toolStatus, String comment, BigDecimal price,
                    double diameter, int length, int workLength, int qty) {
        super(toolName, toolIndex, ToolType.DRILL_HSS, toolStatus, comment, price);
        this.diameter = new SimpleDoubleProperty(diameter);
        this.length = new SimpleIntegerProperty(length);
        this.workLength = new SimpleIntegerProperty(workLength);
        this.qty = new SimpleIntegerProperty(qty);
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

    // Getters and setters
    public double getDiameter() { return diameter.get(); }
    public void setDiameter(double diameter) { this.diameter.set(diameter); }
    public SimpleDoubleProperty diameterProperty() { return diameter; }

    public int getLength() { return length.get(); }
    public void setLength(int length) { this.length.set(length); }
    public SimpleIntegerProperty lengthProperty() { return length; }

    public int getWorkLength() { return workLength.get(); }
    public void setWorkLength(int workLength) { this.workLength.set(workLength); }
    public SimpleIntegerProperty workLengthProperty() { return workLength; }
}

