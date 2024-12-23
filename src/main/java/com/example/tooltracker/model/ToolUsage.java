package com.example.tooltracker.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ToolUsage {
    private StringProperty partNumber = new SimpleStringProperty();
    private StringProperty orderNumber = new SimpleStringProperty();
    private IntegerProperty insertQty = new SimpleIntegerProperty();
    private IntegerProperty partsQty = new SimpleIntegerProperty();
    private IntegerProperty tap = new SimpleIntegerProperty();
    private IntegerProperty edgemill = new SimpleIntegerProperty();
    private IntegerProperty shellmill = new SimpleIntegerProperty();
    private IntegerProperty threaddie = new SimpleIntegerProperty();
    private IntegerProperty chamfer = new SimpleIntegerProperty();
    private IntegerProperty lathe_od = new SimpleIntegerProperty();
    private IntegerProperty lathe_id = new SimpleIntegerProperty();
    private IntegerProperty reamer = new SimpleIntegerProperty();
    private IntegerProperty drillhss = new SimpleIntegerProperty();
    private IntegerProperty drillvhm = new SimpleIntegerProperty();
    private IntegerProperty drillblades = new SimpleIntegerProperty();
    private IntegerProperty facegroove = new SimpleIntegerProperty();
    private IntegerProperty spotdrill = new SimpleIntegerProperty();
    private IntegerProperty bm = new SimpleIntegerProperty();
    private StringProperty info = new SimpleStringProperty();
    private StringProperty machine = new SimpleStringProperty();





    public ToolUsage(){};

    public ToolUsage(String partNumber,
                     String orderNumber,
                     int insertQty,
                     int partsQty,
                     int tap,
                     int edgemill,
                     int shellmill,
                     int threadie,
                     int chamfer,
                     int lathe_id,
                     int lathe_od,
                     int reamer,
                     int drillhss,
                     int drillvhm,
                     int drillblades,
                     int facegroove,
                     int spotdrill,
                     int bm,
                     String machine,
                     String info)   {
        this.partNumber.set(partNumber);
        this.orderNumber.set(orderNumber);
        this.insertQty.set(insertQty);
        this.partsQty.set(partsQty);
        this.edgemill.set(edgemill);
        this.shellmill.set(shellmill);
        this.threaddie.set(threadie);
        this.chamfer.set(chamfer);
        this.lathe_id.set(lathe_id);
        this.lathe_od.set(lathe_od);
        this.reamer.set(reamer);
        this.drillhss.set(drillhss);
        this.drillvhm.set(drillvhm);
        this.drillblades.set(drillblades);
        this.facegroove.set(facegroove);
        this.spotdrill.set(spotdrill);
        this.tap.set(tap);
        this.bm.set(bm);
        this.machine.set(machine);
        this.info.set(info);

    }

    public String getInfo() {
        return info.get();
    }

    public StringProperty infoProperty() {
        return info;
    }

    public void setInfo(String info) {
        this.info.set(info);
    }

    public String getMachine() {
        return machine.get();
    }

    public StringProperty machineProperty() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine.set(machine);
    }

    public int getBm() {
        return bm.get();
    }

    public IntegerProperty bmProperty() {
        return bm;
    }

    public void setBm(int bm) {
        this.bm.set(bm);
    }

    public int getTap() {
        return tap.get();
    }

    public IntegerProperty tapProperty() {
        return tap;
    }

    public void setTap(int tap) {
        this.tap.set(tap);
    }

    public int getEdgemill() {
        return edgemill.get();
    }

    public IntegerProperty edgemillProperty() {
        return edgemill;
    }

    public void setEdgemill(int edgemill) {
        this.edgemill.set(edgemill);
    }

    public int getShellmill() {
        return shellmill.get();
    }

    public IntegerProperty shellmillProperty() {
        return shellmill;
    }

    public void setShellmill(int shellmill) {
        this.shellmill.set(shellmill);
    }

    public int getThreaddie() {
        return threaddie.get();
    }

    public IntegerProperty threaddieProperty() {
        return threaddie;
    }

    public void setThreaddie(int threaddie) {
        this.threaddie.set(threaddie);
    }

    public int getChamfer() {
        return chamfer.get();
    }

    public IntegerProperty chamferProperty() {
        return chamfer;
    }

    public void setChamfer(int chamfer) {
        this.chamfer.set(chamfer);
    }

    public int getLathe_od() {
        return lathe_od.get();
    }

    public IntegerProperty lathe_odProperty() {
        return lathe_od;
    }

    public void setLathe_od(int lathe_od) {
        this.lathe_od.set(lathe_od);
    }

    public int getLathe_id() {
        return lathe_id.get();
    }

    public IntegerProperty lathe_idProperty() {
        return lathe_id;
    }

    public void setLathe_id(int lathe_id) {
        this.lathe_id.set(lathe_id);
    }

    public int getReamer() {
        return reamer.get();
    }

    public IntegerProperty reamerProperty() {
        return reamer;
    }

    public void setReamer(int reamer) {
        this.reamer.set(reamer);
    }

    public int getDrillhss() {
        return drillhss.get();
    }

    public IntegerProperty drillhssProperty() {
        return drillhss;
    }

    public void setDrillhss(int drillhss) {
        this.drillhss.set(drillhss);
    }

    public int getDrillvhm() {
        return drillvhm.get();
    }

    public IntegerProperty drillvhmProperty() {
        return drillvhm;
    }

    public void setDrillvhm(int drillvhm) {
        this.drillvhm.set(drillvhm);
    }

    public int getDrillblades() {
        return drillblades.get();
    }

    public IntegerProperty drillbladesProperty() {
        return drillblades;
    }

    public void setDrillblades(int drillblades) {
        this.drillblades.set(drillblades);
    }

    public int getFacegroove() {
        return facegroove.get();
    }

    public IntegerProperty facegrooveProperty() {
        return facegroove;
    }

    public void setFacegroove(int facegroove) {
        this.facegroove.set(facegroove);
    }

    public int getSpotdrill() {
        return spotdrill.get();
    }

    public IntegerProperty spotdrillProperty() {
        return spotdrill;
    }

    public void setSpotdrill(int spotdrill) {
        this.spotdrill.set(spotdrill);
    }

    public String getPartNumber() {
        return partNumber.get();
    }

    public StringProperty partNumberProperty() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber.set(partNumber);
    }

    public String getOrderNumber() {
        return orderNumber.get();
    }

    public StringProperty orderNumberProperty() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber.set(orderNumber);
    }

    public int getInsertQty() {
        return insertQty.get();
    }

    public IntegerProperty insertQtyProperty() {
        return insertQty;
    }

    public void setInsertQty(int insertQty) {
        this.insertQty.set(insertQty);
    }

    public int getPartsQty() {
        return partsQty.get();
    }

    public IntegerProperty partsQtyProperty() {
        return partsQty;
    }

    public void setPartsQty(int partsQty) {
        this.partsQty.set(partsQty);
    }



}
