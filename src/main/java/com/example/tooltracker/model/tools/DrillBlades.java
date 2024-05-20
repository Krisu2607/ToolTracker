package com.example.tooltracker.model.tools;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.math.BigDecimal;

public class DrillBlades extends Tool1 {
    private SimpleStringProperty matchingInserts;
    private SimpleDoubleProperty diameter;
    private SimpleIntegerProperty length;
    private SimpleIntegerProperty toothsQty; // nowe pole

    public DrillBlades(String toolName, String toolIndex, ToolStatus toolStatus, String comment, BigDecimal price,
                       String matchingInserts, double diameter, int length, int toothsQty) {
        super(toolName, toolIndex, ToolType.DRILL_BLADES, toolStatus, comment, price);
        this.matchingInserts = new SimpleStringProperty(matchingInserts);
        this.diameter = new SimpleDoubleProperty(diameter);
        this.length = new SimpleIntegerProperty(length);
        this.toothsQty = new SimpleIntegerProperty(toothsQty); // inicjalizacja nowego pola
    }

    // Getters and setters
    public String getMatchingInserts() {
        return matchingInserts.get();
    }

    public void setMatchingInserts(String matchingInserts) {
        this.matchingInserts.set(matchingInserts);
    }

    public SimpleStringProperty matchingInsertsProperty() {
        return matchingInserts;
    }

    public double getDiameter() {
        return diameter.get();
    }

    public void setDiameter(double diameter) {
        this.diameter.set(diameter);
    }

    public SimpleDoubleProperty diameterProperty() {
        return diameter;
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

    public int getToothsQty() {
        return toothsQty.get();
    }

    public void setToothsQty(int toothsQty) {
        this.toothsQty.set(toothsQty);
    }

    public SimpleIntegerProperty toothsQtyProperty() {
        return toothsQty;
    }
}
