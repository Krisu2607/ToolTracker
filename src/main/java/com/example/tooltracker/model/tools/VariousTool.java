package com.example.tooltracker.model.tools;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;

public class VariousTool extends Tool1{
    private SimpleDoubleProperty diameter;
    private SimpleStringProperty toolWorkType;



    public VariousTool(String toolName, String toolIndex, ToolStatus toolStatus, String comment, BigDecimal price, String producent,
                      double diameter, String toolWorkType) {
        super(toolName, toolIndex, ToolType.OTHERS, toolStatus, comment, price,producent);
        this.diameter = new SimpleDoubleProperty(diameter);
        this.toolWorkType = new SimpleStringProperty(toolWorkType);

    }

    public String getToolWorkType() {
        return toolWorkType.get();
    }

    public SimpleStringProperty toolWorkTypeProperty() {
        return toolWorkType;
    }

    public void setToolWorkType(String toolWorkType) {
        this.toolWorkType.set(toolWorkType);
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
}
