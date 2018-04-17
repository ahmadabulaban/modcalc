package com.insanwalat.modcalc.module;

import com.insanwalat.modcalc.annotations.FieldDescription;

public class DuctType {

    @FieldDescription(uiField = "i4", note = "Duct Material")
    private Double eps;

    @FieldDescription(uiField = "i5", note = "Duct Thickness")
    private Double thicknessInput;

    @FieldDescription(uiField = "tx2" , note = "Dimension Unit")
    private String dimensionUnit;

    public DuctType() {
    }

    public DuctType(Double eps, Double thicknessInput, String dimensionUnit) {
        this.eps = eps;
        this.thicknessInput = thicknessInput;
        this.dimensionUnit = dimensionUnit;
    }

    public Double getEps() {
        return eps;
    }

    public void setEps(Double eps) {
        this.eps = eps;
    }

    public Double getThicknessInput() {
        return thicknessInput;
    }

    public void setThicknessInput(Double thicknessInput) {
        this.thicknessInput = thicknessInput;
    }

    public String getDimensionUnit() {
        return dimensionUnit;
    }

    public void setDimensionUnit(String dimensionUnit) {
        this.dimensionUnit = dimensionUnit;
    }
}
