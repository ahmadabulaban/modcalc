package com.insanwalat.modcalc.ductsizer.module;

import com.insanwalat.modcalc.ductsizer.annotations.FieldDescription;

public class FlowRateAndSizingCriteria {

    @FieldDescription(uiField = "i6" , note = "Flow Rate")
    private Double rateInput;

    @FieldDescription(uiField = "tx3" , note = "Flow Rate Unit")
    private String rateUnit;

    @FieldDescription(uiField = "i7" , note = "Duct Shape")
    private Integer shp;

    @FieldDescription(uiField = "i8" , note = "One dimension of rectangular duct")
    private Double dimensionInput;

    @FieldDescription(uiField = "TG1/TG2" , note = "Sizing Criteria")
    private Integer sizingCriteria;

    @FieldDescription(uiField = "i9" , note = "max allowed pressure")
    private Double allowedPressureInput;

    @FieldDescription(uiField = "tx4" , note = "Pressure Units")
    private String pressureUnit;

    @FieldDescription(uiField = "i10" , note = "max allowed velocity")
    private Double allowedVelocityInput;

    @FieldDescription(uiField = "tx5" , note = "Velocity Units")
    private String velocityUnit;

    public FlowRateAndSizingCriteria() {
    }

    public FlowRateAndSizingCriteria(Double rateInput, String rateUnit, Integer shp, Double dimensionInput, Integer sizingCriteria, Double allowedPressureInput, String pressureUnit, Double allowedVelocityInput, String velocityUnit) {
        this.rateInput = rateInput;
        this.rateUnit = rateUnit;
        this.shp = shp;
        this.dimensionInput = dimensionInput;
        this.sizingCriteria = sizingCriteria;
        this.allowedPressureInput = allowedPressureInput;
        this.pressureUnit = pressureUnit;
        this.allowedVelocityInput = allowedVelocityInput;
        this.velocityUnit = velocityUnit;
    }

    public Double getRateInput() {
        return rateInput;
    }

    public void setRateInput(Double rateInput) {
        this.rateInput = rateInput;
    }

    public String getRateUnit() {
        return rateUnit;
    }

    public void setRateUnit(String rateUnit) {
        this.rateUnit = rateUnit;
    }

    public Integer getShp() {
        return shp;
    }

    public void setShp(Integer shp) {
        this.shp = shp;
    }

    public Double getDimensionInput() {
        return dimensionInput;
    }

    public void setDimensionInput(Double dimensionInput) {
        this.dimensionInput = dimensionInput;
    }

    public Integer getSizingCriteria() {
        return sizingCriteria;
    }

    public void setSizingCriteria(Integer sizingCriteria) {
        this.sizingCriteria = sizingCriteria;
    }

    public Double getAllowedPressureInput() {
        return allowedPressureInput;
    }

    public void setAllowedPressureInput(Double allowedPressureInput) {
        this.allowedPressureInput = allowedPressureInput;
    }

    public String getPressureUnit() {
        return pressureUnit;
    }

    public void setPressureUnit(String pressureUnit) {
        this.pressureUnit = pressureUnit;
    }

    public Double getAllowedVelocityInput() {
        return allowedVelocityInput;
    }

    public void setAllowedVelocityInput(Double allowedVelocityInput) {
        this.allowedVelocityInput = allowedVelocityInput;
    }

    public String getVelocityUnit() {
        return velocityUnit;
    }

    public void setVelocityUnit(String velocityUnit) {
        this.velocityUnit = velocityUnit;
    }
}
