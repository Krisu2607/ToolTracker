package com.example.tooltracker.model.tools;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CurrentDetail {

    private final StringProperty machineName;
    private final StringProperty orderNum;
    private final StringProperty partNum;
    private final IntegerProperty partsQty;

    public CurrentDetail(String machine_name, String order_num, String part_num, int parts_qty) {
        this.machineName = new SimpleStringProperty(machine_name);
        this.orderNum = new SimpleStringProperty(order_num);
        this.partNum = new SimpleStringProperty(part_num);
        this.partsQty = new SimpleIntegerProperty(parts_qty);
    }

    // Properties
    public StringProperty machineNameProperty() {
        return machineName;
    }

    public StringProperty orderNumProperty() {
        return orderNum;
    }

    public StringProperty partNumProperty() {
        return partNum;
    }

    public IntegerProperty partsQtyProperty() {
        return partsQty;
    }

    // Getters
    public String getMachine_name() {
        return machineName.get();
    }

    public String getOrder_num() {
        return orderNum.get();
    }

    public String getPart_num() {
        return partNum.get();
    }

    public int getParts_qty() {
        return partsQty.get();
    }

    // Setters
    public void setMachine_name(String machine_name) {
        this.machineName.set(machine_name);
    }

    public void setOrder_num(String order_num) {
        this.orderNum.set(order_num);
    }

    public void setPart_num(String part_num) {
        this.partNum.set(part_num);
    }

    public void setParts_qty(int parts_qty) {
        this.partsQty.set(parts_qty);
    }
}
