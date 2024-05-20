package com.example.tooltracker.model.tools;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import java.math.BigDecimal;

public class TapPR extends Tool1 {
    private SimpleObjectProperty<MaterialType> materialType;
    private SimpleStringProperty threadClass;
    private SimpleDoubleProperty metricSize;
    private SimpleDoubleProperty tapScroll;


    public TapPR(String toolName, String toolIndex, ToolStatus toolStatus, String comment, BigDecimal price,
                 MaterialType materialType, String threadClass, double metricSize, double tapScroll) {
        super(toolName, toolIndex, ToolType.TAPPR, toolStatus, comment, price);
        this.materialType = new SimpleObjectProperty<>(materialType);
        this.threadClass = new SimpleStringProperty(threadClass);
        this.metricSize = new SimpleDoubleProperty(metricSize);
        this.tapScroll = new SimpleDoubleProperty(tapScroll);
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

    public String getThreadClass() {
        return threadClass.get();
    }

    public void setThreadClass(String threadClass) {
        this.threadClass.set(threadClass);
    }

    public SimpleStringProperty threadClassProperty() {
        return threadClass;
    }

    public double getMetricSize() {
        return metricSize.get();
    }

    public void setMetricSize(double metricSize) {
        this.metricSize.set(metricSize);
    }

    public SimpleDoubleProperty metricSizeProperty() {
        return metricSize;
    }



    public double getTapScroll() {
        return tapScroll.get();
    }

    public void setTapScroll(double tapScroll) {
        this.tapScroll.set(tapScroll);
    }

    public SimpleDoubleProperty tapScrollProperty() {
        return tapScroll;
    }
}
