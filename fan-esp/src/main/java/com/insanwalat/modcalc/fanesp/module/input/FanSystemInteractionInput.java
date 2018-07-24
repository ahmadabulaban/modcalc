package com.insanwalat.modcalc.fanesp.module.input;

public class FanSystemInteractionInput {
    private String ductSection;
    private Integer fanSystemInteractionCriteria;
    private String fanSystemInteractionDescription;
    private Double ci;

    public FanSystemInteractionInput() {
    }

    public FanSystemInteractionInput(String ductSection, Integer fanSystemInteractionCriteria, String fanSystemInteractionDescription, Double ci) {
        this.ductSection = ductSection;
        this.fanSystemInteractionCriteria = fanSystemInteractionCriteria;
        this.fanSystemInteractionDescription = fanSystemInteractionDescription;
        this.ci = ci;
    }

    public Integer getFanSystemInteractionCriteria() {
        return fanSystemInteractionCriteria;
    }

    public void setFanSystemInteractionCriteria(Integer fanSystemInteractionCriteria) {
        this.fanSystemInteractionCriteria = fanSystemInteractionCriteria;
    }

    public String getDuctSection() {
        return ductSection;
    }

    public void setDuctSection(String ductSection) {
        this.ductSection = ductSection;
    }

    public String getFanSystemInteractionDescription() {
        return fanSystemInteractionDescription;
    }

    public void setFanSystemInteractionDescription(String fanSystemInteractionDescription) {
        this.fanSystemInteractionDescription = fanSystemInteractionDescription;
    }

    public Double getCi() {
        return ci;
    }

    public void setCi(Double ci) {
        this.ci = ci;
    }
}
