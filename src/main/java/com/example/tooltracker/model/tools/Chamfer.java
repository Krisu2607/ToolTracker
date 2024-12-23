package com.example.tooltracker.model.tools;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.math.BigDecimal;

public class Chamfer extends Tool1 {
    private SimpleDoubleProperty d1;
    private SimpleDoubleProperty d2;
    private SimpleDoubleProperty angle;
    private SimpleIntegerProperty l1;
    private SimpleIntegerProperty l2;
    private SimpleIntegerProperty toothsQty;

    private MaterialType materialType;

    public Chamfer(String toolName, String toolIndex, ToolStatus toolStatus, String comment, BigDecimal price,String producent,
                   double d1, double d2, int l1,int l2, int toothsQty, MaterialType materialType, double angle) {
        super(toolName, toolIndex, ToolType.CHAMFER, toolStatus, comment, price, producent);
        this.d1 = new SimpleDoubleProperty(d1);
        this.d2 = new SimpleDoubleProperty(d2);
        this.l1 = new SimpleIntegerProperty(l1);
        this.l2 = new SimpleIntegerProperty(l2);
        this.toothsQty = new SimpleIntegerProperty(toothsQty);
        this.materialType = materialType;
        this.angle = new SimpleDoubleProperty(angle);
    }

    public double getAngle() {
        return angle.get();
    }

    public SimpleDoubleProperty angleProperty() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle.set(angle);
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

    public int getL1() {
        return l1.get();
    }

    public SimpleIntegerProperty l1Property() {
        return l1;
    }

    public void setL1(int l1) {
        this.l1.set(l1);
    }

    public int getL2() {
        return l2.get();
    }

    public SimpleIntegerProperty l2Property() {
        return l2;
    }

    public void setL2(int l2) {
        this.l2.set(l2);
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

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }
}
