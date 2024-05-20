package com.example.tooltracker.model.tools;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import java.math.BigDecimal;

public class EmMet extends Tool1 {
    private SimpleIntegerProperty L1;
    private SimpleIntegerProperty L2;
    private SimpleDoubleProperty d1;
    private SimpleDoubleProperty d2;
    private SimpleObjectProperty<MaterialType> material;
    private SimpleIntegerProperty toothsQty;

    public EmMet(String toolName, String toolIndex,  ToolStatus toolStatus, String comment, BigDecimal price,
                 int L1, int L2, double d1, double d2, MaterialType material, int toothsQty) {
        super(toolName, toolIndex, ToolType.EMMET, toolStatus, comment, price);
        this.L1 = new SimpleIntegerProperty(L1);
        this.L2 = new SimpleIntegerProperty(L2);
        this.d1 = new SimpleDoubleProperty(d1);
        this.d2 = new SimpleDoubleProperty(d2);
        this.material = new SimpleObjectProperty<>(material);
        this.toothsQty = new SimpleIntegerProperty(toothsQty);
    }

    // Getters and setters
    public int getL1() { return L1.get(); }
    public void setL1(int L1) { this.L1.set(L1); }
    public SimpleIntegerProperty L1Property() { return L1; }

    public int getL2() { return L2.get(); }
    public void setL2(int L2) { this.L2.set(L2); }
    public SimpleIntegerProperty L2Property() { return L2; }

    public double getD1() { return d1.get(); }
    public void setD1(double d1) { this.d1.set(d1); }
    public SimpleDoubleProperty d1Property() { return d1; }

    public double getD2() { return d2.get(); }
    public void setD2(double d2) { this.d2.set(d2); }
    public SimpleDoubleProperty d2Property() { return d2; }

    public MaterialType getMaterial() { return material.get(); }
    public String getMaterialName() { return material.getName(); }
    public void setMaterial(MaterialType material) { this.material.set(material); }
    public SimpleObjectProperty<MaterialType> materialProperty() { return material; }

    public int getToothsQty() { return toothsQty.get(); }
    public void setToothsQty(int toothsQty) { this.toothsQty.set(toothsQty); }
    public SimpleIntegerProperty toothsQtyProperty() { return toothsQty; }
}

