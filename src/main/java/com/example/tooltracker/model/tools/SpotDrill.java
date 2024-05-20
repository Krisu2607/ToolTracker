package com.example.tooltracker.model.tools;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.math.BigDecimal;

public class SpotDrill extends Tool1 {
    private SimpleDoubleProperty d1;
    private SimpleDoubleProperty d2;
    private SimpleIntegerProperty length;
    private MaterialType materialType;

    public SpotDrill(String toolName, String toolIndex, ToolStatus toolStatus, String comment, BigDecimal price,
                     double d1, double d2, int length, MaterialType materialType) {
        super(toolName, toolIndex, ToolType.SPOTDRILL, toolStatus, comment, price);
        this.d1 = new SimpleDoubleProperty(d1);
        this.d2 = new SimpleDoubleProperty(d2);
        this.length = new SimpleIntegerProperty(length);
        this.materialType = materialType;
    }

    // Getters and setters
    public double getD1() {
        return d1.get();
    }

    public void setD1(double d1) {
        this.d1.set(d1);
    }

    public SimpleDoubleProperty d1Property() {
        return d1;
    }

    public double getD2() {
        return d2.get();
    }

    public void setD2(double d2) {
        this.d2.set(d2);
    }

    public SimpleDoubleProperty d2Property() {
        return d2;
    }

    public int getLength() {
        return length.get();
    }

    public void setLength(int length) {
        this.length.set(length);
    }

    public SimpleIntegerProperty lengthProperty() {
        return length;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }
}