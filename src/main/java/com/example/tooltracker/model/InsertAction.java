package com.example.tooltracker.model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;

public class InsertAction {

    private SimpleStringProperty InsertIndex;
    private SimpleStringProperty InsertAction;
    private SimpleStringProperty user;
    private SimpleObjectProperty<LocalDateTime> actionDateTime;





    public InsertAction(String InsertIndex, String InsertAction, LocalDateTime actionDateTime) {
        this.InsertIndex = new SimpleStringProperty(InsertIndex);
        this.InsertAction = new SimpleStringProperty(InsertAction);
        this.actionDateTime = new SimpleObjectProperty<>(actionDateTime);
    }


    public InsertAction(String InsertIndex, String InsertAction, LocalDateTime actionDateTime, String user) {
        this.InsertIndex = new SimpleStringProperty(InsertIndex);
        this.InsertAction = new SimpleStringProperty(InsertAction);
        this.actionDateTime = new SimpleObjectProperty<>(actionDateTime);
        this.user = new SimpleStringProperty(user);

    }

    public InsertAction(String InsertIndex, String InsertAction) {
        this.InsertIndex = new SimpleStringProperty(InsertIndex);
        this.InsertAction = new SimpleStringProperty(InsertAction);
        this.actionDateTime = new SimpleObjectProperty<>();
    }

    public InsertAction() {
        this.InsertIndex = new SimpleStringProperty();
        this.InsertAction = new SimpleStringProperty();
        this.actionDateTime = new SimpleObjectProperty<>();
    }


    public String getInsertIndex() {
        return InsertIndex.get();
    }

    public SimpleStringProperty insertIndexProperty() {
        return InsertIndex;
    }

    public void setInsertIndex(String insertIndex) {
        this.InsertIndex.set(insertIndex);
    }

    public String getInsertAction() {
        return InsertAction.get();
    }

    public SimpleStringProperty insertActionProperty() {
        return InsertAction;
    }

    public void setInsertAction(String insertAction) {
        this.InsertAction.set(insertAction);
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
