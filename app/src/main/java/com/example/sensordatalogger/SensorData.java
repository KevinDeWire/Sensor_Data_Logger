package com.example.sensordatalogger;

public class SensorData {
    public String value;
    public String label;

    public SensorData(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public SensorData() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
