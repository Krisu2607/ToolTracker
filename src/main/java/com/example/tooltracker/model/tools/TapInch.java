package com.example.tooltracker.model.tools;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;

public class TapInch extends Tool1 {
    private SimpleObjectProperty<MaterialType> materialType;

    private SimpleStringProperty inchSize;


    public TapInch(String toolName, String toolIndex, ToolStatus toolStatus, String comment, BigDecimal price,String producent,
                 MaterialType materialType, String inchSize) {
        super(toolName, toolIndex, ToolType.TAPPR, toolStatus, comment, price, producent);
        this.materialType = new SimpleObjectProperty<>(materialType);
        this.inchSize = new SimpleStringProperty(inchSize);
    }

    public String getInchSize() {
        return inchSize.get();
    }

    public SimpleStringProperty inchSizeProperty() {
        return inchSize;
    }

    public void setInchSize(String inchSize) {
        this.inchSize.set(inchSize);
    }

    // Getters and setters
    public MaterialType getMaterialType() {
        return materialType.get();
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType.set(materialType);
    }

    public SimpleObjectProperty<MaterialType> materialTypeProperty() {
        return materialType;
    }


}
