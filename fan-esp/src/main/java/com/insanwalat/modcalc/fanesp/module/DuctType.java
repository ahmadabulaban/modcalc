package com.insanwalat.modcalc.fanesp.module;

import com.insanwalat.modcalc.fanesp.annotations.FieldDescription;

public class DuctType {

    @FieldDescription(uiField = "i5", note = "Duct Material")
    private Double eps;

    public DuctType() {
    }

    public DuctType(Double eps) {
        this.eps = eps;
    }

    public Double getEps() {
        return eps;
    }

    public void setEps(Double eps) {
        this.eps = eps;
    }

}
