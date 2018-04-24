package com.insanwalat.modcalc.module.lookup;

import com.opencsv.bean.CsvBindByName;

public class DuctSizerLookup {

    @CsvBindByName(column = "ui-field")
    private String uiField;

    @CsvBindByName(column = "key")
    private String key;

    @CsvBindByName(column = "value")
    private Double value;

    @CsvBindByName(column = "defaultOption")
    private Boolean defaultOption;

    @CsvBindByName(column = "group")
    private Integer group;

    public DuctSizerLookup() {
    }

    public DuctSizerLookup(String uiField, String key, Double value, Boolean defaultOption, Integer group) {
        this.uiField = uiField;
        this.key = key;
        this.value = value;
        this.defaultOption = defaultOption;
        this.group = group;
    }

    public String getUiField() {
        return uiField;
    }

    public void setUiField(String uiField) {
        this.uiField = uiField;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getDefaultOption() {
        return defaultOption;
    }

    public void setDefaultOption(Boolean defaultOption) {
        this.defaultOption = defaultOption;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }
}
