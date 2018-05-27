package com.insanwalat.modcalc.fanesp.module;

import com.insanwalat.modcalc.fanesp.annotations.FieldDescription;

public class SpecialComponent {

    @FieldDescription(uiField = "i40" , note = "Special Component Description")
    private String specialComponentDescription;

    @FieldDescription(uiField = "i41" , note = "Component pressure drop")
    private Double componentPressureDropInput;

    @FieldDescription(uiField = "tx5" , note = "Pressure Units")
    private String pressureUnit;

    @FieldDescription(uiField = "i42" , note = "Quantity")
    private Double qc;

    public SpecialComponent() {
    }

    public SpecialComponent(String specialComponentDescription, Double componentPressureDropInput, String pressureUnit, Double qc) {
        this.specialComponentDescription = specialComponentDescription;
        this.componentPressureDropInput = componentPressureDropInput;
        this.pressureUnit = pressureUnit;
        this.qc = qc;
    }

    public String getSpecialComponentDescription() {
        return specialComponentDescription;
    }

    public void setSpecialComponentDescription(String specialComponentDescription) {
        this.specialComponentDescription = specialComponentDescription;
    }

    public Double getComponentPressureDropInput() {
        return componentPressureDropInput;
    }

    public void setComponentPressureDropInput(Double componentPressureDropInput) {
        this.componentPressureDropInput = componentPressureDropInput;
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
