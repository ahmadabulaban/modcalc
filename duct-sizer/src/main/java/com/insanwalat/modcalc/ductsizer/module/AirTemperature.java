package com.insanwalat.modcalc.ductsizer.module;

import com.insanwalat.modcalc.ductsizer.annotations.FieldDescription;

public class AirTemperature {

    @FieldDescription(uiField = "i3", note = "Fluid Temperature")
    private Double temperatureInput;

    @FieldDescription(uiField = "tx1", note = "Temperature Unit")
    private String temperatureUnit;

    public AirTemperature() {
    }

    public AirTemperature(Double temperatureInput, String temperatureUnit) {
        this.temperatureInput = temperatureInput;
        this.temperatureUnit = temperatureUnit;
    }

    public Double getTemperatureInput() {
        return temperatureInput;
    }

    public void setTemperatureInput(Double temperatureInput) {
        this.temperatureInput = temperatureInput;
    }

    public String getTemperatureUnit() {
        return temperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }
}
