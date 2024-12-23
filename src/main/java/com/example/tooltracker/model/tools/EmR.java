package com.example.tooltracker.model.tools;

import javafx.beans.property.*;

import java.math.BigDecimal;

public class EmR extends Tool1 {
    private SimpleBooleanProperty isItBall;
    private SimpleDoubleProperty cornerR;
    private SimpleIntegerProperty L1;
    private SimpleIntegerProperty L2;
    private SimpleDoubleProperty d1;
    private SimpleDoubleProperty d2;
    private SimpleObjectProperty<MaterialType> material;
    private SimpleIntegerProperty toothsQty;
    private SimpleObjectProperty<ToolDestinyMat> toolDestinyMat;

    public EmR(String toolName, String toolIndex, ToolStatus toolStatus, String comment, BigDecimal price,String producent,
               boolean isItBall, double cornerR, int L1, int L2, double d1, double d2, MaterialType material, int toothsQty, ToolDestinyMat toolDestinyMat) {
        super(toolName, toolIndex, ToolType.EMR, toolStatus, comment, price, producent);
        this.isItBall = new SimpleBooleanProperty(isItBall);
        this.cornerR = new SimpleDoubleProperty(cornerR);
        this.L1 = new SimpleIntegerProperty(L1);
        this.L2 = new SimpleIntegerProperty(L2);
        this.d1 = new SimpleDoubleProperty(d1);
        this.d2 = new SimpleDoubleProperty(d2);
        this.material = new SimpleObjectProperty<>(material);
        this.toothsQty = new SimpleIntegerProperty(toothsQty);
        this.toolDestinyMat = new SimpleObjectProperty<>(toolDestinyMat);
    }

    public ToolDestinyMat getToolDestinyMat() {
        return toolDestinyMat.get();
    }

    public SimpleObjectProperty<ToolDestinyMat> toolDestinyMatProperty() {
        return toolDestinyMat;
    }

    public void setToolDestinyMat(ToolDestinyMat toolDestinyMat) {
        this.toolDestinyMat.set(toolDestinyMat);
    }

    public boolean isIsItBall() {
        return isItBall.get();
    }

    public void setIsItBall(boolean isItBall) {
        this.isItBall.set(isItBall);
    }

    public SimpleDoubleProperty cornerRProperty() {
        return cornerR;
    }

    public int getL1() {
        return L1.get();
    }

    public SimpleIntegerProperty l1Property() {
        return L1;
    }

    public void setL1(int l1) {
        this.L1.set(l1);
    }

    public int getL2() {
        return L2.get();
    }

    public SimpleIntegerProperty l2Property() {
        return L2;
    }

    public void setL2(int l2) {
        this.L2.set(l2);
    }

    public double getD1() {
        return d1.get();
    }

    public SimpleDoubleProperty d1Property() {
        return d1;
    }

    public void setD1(double d1) {
        this.d1.set(d1);
    }

    public double getD2() {
        return d2.get();
    }

    public SimpleDoubleProperty d2Property() {
        return d2;
    }

    public void setD2(double d2) {
        this.d2.set(d2);
    }

    public MaterialType getMaterial() {
        return material.get();
    }

    public SimpleObjectProperty<MaterialType> materialProperty() {
        return material;
    }

    public void setMaterial(MaterialType material) {
        this.material.set(material);
    }

    public int getToothsQty() {
        return toothsQty.get();
    }

    public SimpleIntegerProperty toothsQtyProperty() {
        return toothsQty;
    }

    public void setToothsQty(int toothsQty) {
        this.toothsQty.set(toothsQty);
    }

    // Getters and setters
    public boolean isItBall() {
        return isItBall.get();
    }

    public void setItBall(boolean isItBall) {
        this.isItBall.set(isItBall);
    }

    public SimpleBooleanProperty isItBallProperty() {
        return isItBall;
    }

    public double getCornerR() {
        return cornerR.get();
    }

    public void setCornerR(double cornerR) {
        this.cornerR.set(cornerR);
    }
}
