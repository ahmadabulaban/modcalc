package com.insanwalat.modcalc.fanesp.module.input;

public class AirTerminalInput {

    private String airTerminalDescription;
    private String model;
    private Double terminalRateInput;
    private String terminalRateUnit;
    private Double terminalPressureDropInput;
    private String terminalPressureUnit;
    private Double pd_at;

    public AirTerminalInput() {
    }

    public AirTerminalInput(String airTerminalDescription, String model, Double terminalRateInput, String terminalRateUnit, Double terminalPressureDropInput, String terminalPressureUnit, Double pd_at) {
        this.airTerminalDescription = airTerminalDescription;
        this.model = model;
        this.terminalRateInput = terminalRateInput;
        this.terminalRateUnit = terminalRateUnit;
        this.terminalPressureDropInput = terminalPressureDropInput;
        this.terminalPressureUnit = terminalPressureUnit;
        this.pd_at = pd_at;
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

    public Double getPd_at() {
        return pd_at;
    }

    public void setPd_at(Double pd_at) {
        this.pd_at = pd_at;
    }
}
