package com.example.tooltracker.model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;

public class ToolAction {

    private SimpleStringProperty tIndex;
    private SimpleStringProperty tAction;
    private SimpleStringProperty user;
    private SimpleObjectProperty<LocalDateTime> actionDateTime;





    public ToolAction(String tIndex, String tAction, LocalDateTime actionDateTime) {
        this.tIndex = new SimpleStringProperty(tIndex);
        this.tAction = new SimpleStringProperty(tAction);
        this.actionDateTime = new SimpleObjectProperty<>(actionDateTime);
    }


    public ToolAction(String tIndex, String tAction, LocalDateTime actionDateTime, String user) {
        this.tIndex = new SimpleStringProperty(tIndex);
        this.tAction = new SimpleStringProperty(tAction);
        this.actionDateTime = new SimpleObjectProperty<>(actionDateTime);
        this.user = new SimpleStringProperty(user);

    }

    public ToolAction(String tIndex, String tAction) {
        this.tIndex = new SimpleStringProperty(tIndex);
        this.tAction = new SimpleStringProperty(tAction);
        this.actionDateTime = new SimpleObjectProperty<>();
    }

    public ToolAction() {
        this.tIndex = new SimpleStringProperty();
        this.tAction = new SimpleStringProperty();
        this.actionDateTime = new SimpleObjectProperty<>();
    }


    public String getUser() {
        return user.get();
    }

    public SimpleStringProperty userProperty() {
        return user;
    }

    public void setUser(String user) {
        this.user.set(user);
    }

    public String gettIndex() {
        return tIndex.get();
    }

    public SimpleStringProperty tIndexProperty() {
        return tIndex;
    }

    public void settIndex(String tIndex) {
        this.tIndex.set(tIndex);
    }

    public String gettAction() {
        return tAction.get();
    }

    public SimpleStringProperty tActionProperty() {
        return tAction;
    }

    public void settAction(String tAction) {
        this.tAction.set(tAction);
    }

    public LocalDateTime getActionDateTime() {
        return actionDateTime.get();
    }

    public SimpleObjectProperty<LocalDateTime> actionDateTimeProperty() {
        return actionDateTime;
    }

    public void setActionDateTime(LocalDateTime actionDateTime) {
        this.actionDateTime.set(actionDateTime);
    }




}
