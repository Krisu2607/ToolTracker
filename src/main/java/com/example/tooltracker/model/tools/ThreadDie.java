package com.example.tooltracker.model.tools;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import java.math.BigDecimal;

public class ThreadDie extends Tool1 {
    private SimpleObjectProperty<MaterialType> materialType;
    private SimpleStringProperty threadClass;
    private SimpleDoubleProperty metricSize;
    private SimpleBooleanProperty isItInchThread;
    private SimpleDoubleProperty DieScroll;
    private SimpleStringProperty inchsize;


    public ThreadDie(String toolName, String toolIndex, ToolStatus toolStatus, String comment, BigDecimal price,
                     MaterialType materialType, String threadClass, double metricSize,  double tapScroll, String inchsize) {
        super(toolName, toolIndex, ToolType.THREADDIE, toolStatus, comment, price);
        this.materialType = new SimpleObjectProperty<>(materialType);
        this.threadClass = new SimpleStringProperty(threadClass);
        this.metricSize = new SimpleDoubleProperty(metricSize);
        this.DieScroll = new SimpleDoubleProperty(tapScroll);
        this.inchsize = new SimpleStringProperty(inchsize);
    }

    public String getInchsize() {
        return inchsize.get();
    }

    public SimpleStringProperty inchsizeProperty() {
        return inchsize;
    }

    public void setInchsize(String inchsize) {
        this.inchsize.set(inchsize);
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

    public boolean isItInchThread() {
        return isItInchThread.get();
    }

    public void setItInchThread(boolean isItInchThread) {
        this.isItInchThread.set(isItInchThread);
    }

    public SimpleBooleanProperty isItInchThreadProperty() {
        return isItInchThread;
    }

    public double getDieScroll() {
        return DieScroll.get();
    }

    public void setDieScroll(double dieScroll) {
        this.DieScroll.set(dieScroll);
    }

    public SimpleDoubleProperty dieScrollProperty() {
        return DieScroll;
    }
}
