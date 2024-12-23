package com.example.tooltracker.controller;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

public class LimitedTextFieldTableCell<S, T> extends TextFieldTableCell<S, T> {
    private final int maxLength;

    public LimitedTextFieldTableCell(StringConverter<T> converter, int maxLength) {
        super(converter);
        this.maxLength = maxLength;
    }

    @Override
    public void startEdit() {
        super.startEdit();
        TextField textField = (TextField) getGraphic();
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > maxLength) {
                textField.setText(oldValue);
            }
        });
    }

    public static <S> Callback<TableColumn<S, String>, TableCell<S, String>> forTableColumn(int maxLength) {
        return param -> new LimitedTextFieldTableCell<>(new DefaultStringConverter(), maxLength);
    }
}
