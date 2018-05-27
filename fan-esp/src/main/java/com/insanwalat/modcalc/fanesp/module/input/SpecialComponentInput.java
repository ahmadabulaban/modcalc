package com.insanwalat.modcalc.fanesp.module.input;

public class SpecialComponentInput {

    private String specialComponentDescription;
    private Double pd_c;
    private String pressureUnit;
    private Double qc;

    public SpecialComponentInput() {
    }

    public SpecialComponentInput(String specialComponentDescription, Double pd_c, String pressureUnit, Double qc) {
        this.specialComponentDescription = specialComponentDescription;
        this.pd_c = pd_c;
        this.pressureUnit = pressureUnit;
        this.qc = qc;
    }

    public String getSpecialComponentDescription() {
        return specialComponentDescription;
    }

    public void setSpecialComponentDescription(String specialComponentDescription) {
        this.specialComponentDescription = specialComponentDescription;
    }

    public Double getPd_c() {
        return pd_c;
    }

    public void setPd_c(Double pd_c) {
        this.pd_c = pd_c;
    }

    public String getPressureUnit() {
        return pressureUnit;
    }

    public void setPressureUnit(String pressureUnit) {
        this.pressureUnit = pressureUnit;
    }

    public Double getQc() {
        return qc;
    }

    public void setQc(Double qc) {
        this.qc = qc;
    }
}
