package com.example.tooltracker.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FactoryWish {

    private SimpleIntegerProperty id;

    private SimpleStringProperty info;
    private SimpleStringProperty type;
    private SimpleStringProperty auction;
    private SimpleStringProperty producent;
    private SimpleStringProperty status;
    private SimpleStringProperty machine;
    private SimpleStringProperty link = new SimpleStringProperty("LINK");


    public FactoryWish(String name,
                       String type,
                       String info,
                       String auction,
                       String producent,
                       String status,
                       String machine,
                       int id
                       ) {

        this.info =  new SimpleStringProperty(info) ;
        this.type =  new SimpleStringProperty(type) ;
        this.auction =  new SimpleStringProperty(auction) ;
        this.status =  new SimpleStringProperty(status) ;
        this.producent =  new SimpleStringProperty(producent) ;
        this.machine =  new SimpleStringProperty(machine) ;
        this.id = new SimpleIntegerProperty(id);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getLink() {
        return "LINK";
    }

    public SimpleStringProperty linkProperty() {
        return link;
    }

    public String getProducent() {
        return producent.get();
    }

    public SimpleStringProperty producentProperty() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent.set(producent);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getMachine() {
        return machine.get();
    }

    public SimpleStringProperty machineProperty() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine.set(machine);
    }

    public String getInfo() {
        return info.get();
    }

    public SimpleStringProperty infoProperty() {
        return info;
    }

    public void setInfo(String info) {
        this.info.set(info);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getAuction() {
        return auction.get();
    }

    public SimpleStringProperty auctionProperty() {
        return auction;
    }

    public void setAuction(String auction) {
        this.auction.set(auction);
    }
}
