package com.example.tooltracker.model.tools;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import java.math.BigDecimal;

public class TurningID extends Tool1 {
    private SimpleStringProperty matchingInserts;
    private SimpleStringProperty workType;
    private SimpleStringProperty cutDirection;
    private SimpleStringProperty matchingBolt;
    private SimpleDoubleProperty cutAngle;



    public TurningID(String toolName, String toolIndex, ToolStatus toolStatus, String comment, BigDecimal price,String producent,
                     String matchingInserts, String workType, String cutDirection, String matchingBolt) {
        super(toolName, toolIndex, ToolType.TURNING_OD, toolStatus, comment, price, producent);
        this.matchingInserts = new SimpleStringProperty(matchingInserts);
        this.workType = new SimpleStringProperty(workType);
        this.cutDirection = new SimpleStringProperty(cutDirection);
        this.matchingBolt = new SimpleStringProperty(matchingBolt);
    }

    // Getters and setters


    public String getWorkType() {
        return workType.get();
    }

    public SimpleStringProperty workTypeProperty() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType.set(workType);
    }

    public String getCutDirection() {
        return cutDirection.get();
    }

    public SimpleStringProperty cutDirectionProperty() {
        return cutDirection;
    }

    public void setCutDirection(String cutDirection) {
        this.cutDirection.set(cutDirection);
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

    public String getMatchingInserts() {
        return matchingInserts.get();
    }

    public void setMatchingInserts(String matchingInserts) {
        this.matchingInserts.set(matchingInserts);
    }

    public SimpleStringProperty matchingInsertsProperty() {
        return matchingInserts;
    }

    public double getCutAngle() {
        return cutAngle.get();
    }

    public void setCutAngle(double cutAngle) {
        this.cutAngle.set(cutAngle);
    }

    public SimpleDoubleProperty cutAngleProperty() {
        return cutAngle;
    }
}
