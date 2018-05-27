package com.insanwalat.modcalc.fanesp.module.input;

public class AirTerminalInput {

    private String airTerminalDescription;
    private String model;
    private Double terminalRateInput;
    private Double terminalPressureDropInput;
    private Double pd_at;

    public AirTerminalInput() {
    }

    public AirTerminalInput(String airTerminalDescription, String model, Double terminalRateInput, Double terminalPressureDropInput, Double pd_at) {
        this.airTerminalDescription = airTerminalDescription;
        this.model = model;
        this.terminalRateInput = terminalRateInput;
        this.terminalPressureDropInput = terminalPressureDropInput;
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

    public Double getTerminalPressureDropInput() {
        return terminalPressureDropInput;
    }

    public void setTerminalPressureDropInput(Double terminalPressureDropInput) {
        this.terminalPressureDropInput = terminalPressureDropInput;
    }

    public Double getPd_at() {
        return pd_at;
    }

    public void setPd_at(Double pd_at) {
        this.pd_at = pd_at;
    }
}
