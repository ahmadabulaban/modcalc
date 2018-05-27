package com.insanwalat.modcalc.fanesp.module;

import com.insanwalat.modcalc.fanesp.annotations.FieldDescription;

public class FanSystemInteraction {

    @FieldDescription(uiField = "i52" , note = "Duct Section")
    private String ductSection;

    @FieldDescription(uiField = "TG7/TG8" , note = "Fan System Interaction Criteria")
    private Integer fanSystemInteractionCriteria;

    @FieldDescription(uiField = "i32/i34" , note = "Fan System Interaction Type/Designation")
    private String fanSystemInteractionDescription;

    @FieldDescription(uiField = "i320/i35" , note = "Fan System Interaction Coefficient")
    private Double ci;

    public FanSystemInteraction() {
    }

    public FanSystemInteraction(String ductSection, Integer fanSystemInteractionCriteria, String fanSystemInteractionDescription, Double ci) {
        this.ductSection = ductSection;
        this.fanSystemInteractionCriteria = fanSystemInteractionCriteria;
        this.fanSystemInteractionDescription = fanSystemInteractionDescription;
        this.ci = ci;
    }

    public String getDuctSection() {
        return ductSection;
    }

    public void setDuctSection(String ductSection) {
        this.ductSection = ductSection;
    }

    public Integer getFanSystemInteractionCriteria() {
        return fanSystemInteractionCriteria;
    }

    public void setFanSystemInteractionCriteria(Integer fanSystemInteractionCriteria) {
        this.fanSystemInteractionCriteria = fanSystemInteractionCriteria;
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
