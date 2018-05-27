package com.insanwalat.modcalc.fanesp.module;

import com.insanwalat.modcalc.fanesp.annotations.FieldDescription;

public class AirTerminal {

    @FieldDescription(uiField = "i45" , note = "Air Terminal Description")
    private String airTerminalDescription;

    @FieldDescription(uiField = "i46" , note = "Model")
    private String model;

    @FieldDescription(uiField = "i47" , note = "Terminal Flow Rate")
    private Double terminalRateInput;

    @FieldDescription(uiField = "tx2" , note = "Terminal Flow Rate Unit")
    private String terminalRateUnit;

    @FieldDescription(uiField = "i48" , note = "Terminal pressure drop")
    private Double terminalPressureDropInput;

    @FieldDescription(uiField = "tx5" , note = "Terminal Pressure Units")
    private String terminalPressureUnit;

    public AirTerminal() {
    }

    public AirTerminal(String airTerminalDescription, String model, Double terminalRateInput, String terminalRateUnit, Double terminalPressureDropInput, String terminalPressureUnit) {
        this.airTerminalDescription = airTerminalDescription;
        this.model = model;
        this.terminalRateInput = terminalRateInput;
        this.terminalRateUnit = terminalRateUnit;
        this.terminalPressureDropInput = terminalPressureDropInput;
        this.terminalPressureUnit = terminalPressureUnit;
    }

    public String getAirTerminalDescription() {
        return airTerminalDescription;
    }

    public void setAirTerminalDescription(String airTerminalDescription) {
        this.airTerminalDescription = airTerminalDescription;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getTerminalRateInput() {
        return terminalRateInput;
    }

    public void setTerminalRateInput(Double terminalRateInput) {
        this.terminalRateInput = terminalRateInput;
    }

    public String getTerminalRateUnit() {
        return terminalRateUnit;
    }

    public void setTerminalRateUnit(String terminalRateUnit) {
        this.terminalRateUnit = terminalRateUnit;
    }

    public Double getTerminalPressureDropInput() {
        return terminalPressureDropInput;
    }

    public void setTerminalPressureDropInput(Double terminalPressureDropInput) {
        this.terminalPressureDropInput = terminalPressureDropInput;
    }

    public String getTerminalPressureUnit() {
        return terminalPressureUnit;
    }

    public void setTerminalPressureUnit(String terminalPressureUnit) {
        this.terminalPressureUnit = terminalPressureUnit;
    }
}
