package com.example.tooltracker.model.tools;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.math.BigDecimal;

public class ShellMill extends Tool1 {
    private SimpleStringProperty matchingInserts;
    private SimpleDoubleProperty diameter;
    private SimpleIntegerProperty toothsQty;
    private SimpleBooleanProperty isItIc;

    private SimpleStringProperty shellMillType;
    private SimpleStringProperty matchingBolt;



    public ShellMill(String toolName, String toolIndex, ToolStatus toolStatus, String comment, BigDecimal price,String producent,
                     String matchingInserts, double diameter, int toothsQty, boolean isItIc, String shellMillType, String matchingBolt) {
        super(toolName, toolIndex, ToolType.SHELL_MILL, toolStatus, comment, price, producent);
        this.matchingInserts = new SimpleStringProperty(matchingInserts);
        this.diameter = new SimpleDoubleProperty(diameter);
        this.toothsQty = new SimpleIntegerProperty(toothsQty);
        this.isItIc = new SimpleBooleanProperty(isItIc);
        this.shellMillType = new SimpleStringProperty(shellMillType);
        this.matchingBolt = new SimpleStringProperty(matchingBolt);
    }


    public String getMatchingBolt() {
        return matchingBolt.get();
    }

    public SimpleStringProperty matchingBoltProperty() {
        return matchingBolt;
    }

    public void setMatchingBolt(String matchingBolt) {
        this.matchingBolt.set(matchingBolt);
    }

    public boolean isIsItIc() {
        return isItIc.get();
    }

    public SimpleBooleanProperty isItIcProperty() {
        return isItIc;
    }

    public void setIsItIc(boolean isItIc) {
        this.isItIc.set(isItIc);
    }

    public String getShellMillType() {
        return shellMillType.get();
    }

    public SimpleStringProperty shellMillTypeProperty() {
        return shellMillType;
    }

    public void setShellMillType(String shellMillType) {
        this.shellMillType.set(shellMillType);
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
