package com.example.tooltracker.model.tools;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.math.BigDecimal;

public class Reamer extends Tool1 {
    private SimpleDoubleProperty diameter;
    private SimpleObjectProperty<MaterialType> material;

    private SimpleIntegerProperty length;

    public Reamer(String toolName, String toolIndex, ToolStatus toolStatus, String comment, BigDecimal price,
                  double diameter, MaterialType material, int length) {
        super(toolName, toolIndex, ToolType.REAMER, toolStatus, comment, price);
        this.diameter = new SimpleDoubleProperty(diameter);
        this.material = new SimpleObjectProperty<>(material);
        this.length = new SimpleIntegerProperty(length);
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

    // Getters and setters
    public double getDiameter() {
        return diameter.get();
    }

    public void setDiameter(double diameter) {
        this.diameter.set(diameter);
    }

    public SimpleDoubleProperty diameterProperty() {
        return diameter;
    }

    public MaterialType getMaterial() { return material.get(); }
    public void setMaterial(MaterialType material) { this.material.set(material); }
    public SimpleObjectProperty<MaterialType> materialProperty() { return material; }
}
