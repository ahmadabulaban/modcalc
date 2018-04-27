package com.insanwalat.modcalc.module.response;

public class DuctSizerLookupResponse {

    private String uiField;
    private String key;
    private Double value;
    private Boolean defaultOption;
    private Integer group;

    public DuctSizerLookupResponse() {
    }

    public DuctSizerLookupResponse(String uiField, String key, Double value, Boolean defaultOption, Integer group) {
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
