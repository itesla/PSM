package org.power_systems_modelica.psm.gui.utils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AutoFillTextBox<T> extends Control {

    private TextField textbox;

    private ListView<T> listview;

    private ObservableList<T> data = FXCollections.observableArrayList();

    private boolean filterMode;

    public AutoFillTextBox(ObservableList<T> data) {
        //this();
        init();
        this.data = data;
    }

    public AutoFillTextBox() {
        init();
    }

    private void init() {
    	getStyleClass().setAll("autofill-text");
        textbox = new TextField();
        listview = new ListView<>();
        filterMode = false;

        listen();

    }

    public void requestFocus() {
        super.requestFocus();
        textbox.requestFocus();
    }

    public T getItem() {
        return listview.getSelectionModel().getSelectedItem();
    }

    public String getText() {
        return textbox.getText();
    }

    public void addData(T data) {
        this.data.add(data);

    }

    public void setData(ObservableList<T> data) {
    	this.data.clear();
        this.data.addAll(data);
    }

    public ObservableList<T> getData() {
        return data;
    }

    public ListView<T> getListview() {
        return listview;
    }

    public TextField getTextbox() {
        return textbox;
    }

    public void setFilterMode(boolean filter) {
        filterMode = filter;
    }

    public boolean getFilterMode() {
        return filterMode;
    }

    @Override
    public void setMinSize(double d, double d1) {
        super.setMinSize(d, d1);
        textbox.setMinSize(d, d1);
    }

    @Override
    public void setPrefSize(double d, double d1) {
        super.setPrefSize(d, d1);
        textbox.setPrefSize(d, d1);
    }

    @Override
    public void resize(double d, double d1) {
        super.resize(d, d1);
        textbox.resize(d, d1);
    }

    @Override
    public void setMaxSize(double d, double d1) {
        super.setMaxSize(d, d1);
        textbox.setMaxSize(d, d1);

    }

    private void listen() {
        this.prefHeightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                textbox.setPrefHeight(t1.doubleValue());
            }

        });
        this.prefWidthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                textbox.setPrefWidth(t1.doubleValue());
            }

        });
        this.minHeightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                textbox.setMinHeight(t1.doubleValue());
            }

        });
        this.maxHeightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                textbox.setMaxHeight(t1.doubleValue());
            }

        });
        this.minWidthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                textbox.setMinWidth(t1.doubleValue());
            }

        });
        this.maxWidthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                textbox.setMaxWidth(t1.doubleValue());
            }

        });
    }

    @Override
    protected double computeMaxHeight(double width) {
        return Math.max(22.0d, textbox.getHeight());
    }

    @Override
    protected double computePrefHeight(double width) {
        return Math.max(22.0d, textbox.getPrefHeight());
    }

    @Override
    protected double computeMinHeight(double width) {
        return Math.max(22.0d, textbox.getPrefHeight());
    }

    @Override
    protected double computePrefWidth(double height) {
        return Math.max(100.0d, textbox.getPrefWidth());
    }

    @Override
    protected double computeMaxWidth(double height) {
        return Math.max(100.0d, textbox.getPrefWidth());
    }

    @Override
    protected double computeMinWidth(double height) {
        return Math.max(100.0d, textbox.getPrefWidth());
    }
}
