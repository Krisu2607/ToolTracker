package com.example.tooltracker.model.tools;

import javafx.beans.property.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;

public class FaceGroove extends Tool1 {

    private SimpleDoubleProperty minDiamCut;
    private SimpleDoubleProperty maxDiamCut;

    private SimpleIntegerProperty maxDepth;
    private SimpleStringProperty matchingInserts;
    private SimpleStringProperty matchingBolt;
    private SimpleStringProperty workDirection;

    public FaceGroove(String toolName, String toolIndex, ToolType toolType,
                      ToolStatus toolStatus, String comment,
                      BigDecimal price,String producent, double minDiamCut, double maxDiamCut,
                      int maxDepth, String matchingInserts, String matchingBolt, String workDirection) {
        super(toolName, toolIndex, toolType, toolStatus, comment, price, producent);

        this.minDiamCut = new SimpleDoubleProperty(minDiamCut);
        this.maxDiamCut = new SimpleDoubleProperty(maxDiamCut);
        this.maxDepth = new SimpleIntegerProperty(maxDepth);
        this.matchingInserts = new SimpleStringProperty(matchingInserts);
        this.matchingBolt = new SimpleStringProperty(matchingBolt);
        this.workDirection = new SimpleStringProperty(workDirection);
    }


    public String getWorkDirection() {
        return workDirection.get();
    }

    public SimpleStringProperty workDirectionProperty() {
        return workDirection;
    }

    public void setWorkDirection(String workDirection) {
        this.workDirection.set(workDirection);
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

    public double getMinDiamCut() {
        return minDiamCut.get();
    }

    public SimpleDoubleProperty minDiamCutProperty() {
        return minDiamCut;
    }

    public void setMinDiamCut(double minDiamCut) {
        this.minDiamCut.set(minDiamCut);
    }

    public double getMaxDiamCut() {
        return maxDiamCut.get();
    }

    public SimpleDoubleProperty maxDiamCutProperty() {
        return maxDiamCut;
    }

    public void setMaxDiamCut(double maxDiamCut) {
        this.maxDiamCut.set(maxDiamCut);
    }

    public int getMaxDepth() {
        return maxDepth.get();
    }

    public SimpleIntegerProperty maxDepthProperty() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth.set(maxDepth);
    }

    public String getMatchingInserts() {
        return matchingInserts.get();
    }

    public SimpleStringProperty matchingInsertsProperty() {
        return matchingInserts;
    }

    public void setMatchingInserts(String matchingInserts) {
        this.matchingInserts.set(matchingInserts);
    }
}
