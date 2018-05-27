package com.insanwalat.modcalc.ductsizer.module;

import com.insanwalat.modcalc.ductsizer.annotations.FieldDescription;

public class Units {

    @FieldDescription(uiField = "i1", note = "Unit System")
    private Integer uu;

    @FieldDescription(uiField = "i2", note = "Flow Rate")
    private Double uf;

    public Units() {
    }

    public Units(Integer uu, Double uf) {
        this.uu = uu;
        this.uf = uf;
    }

    public Integer getUu() {
        return uu;
    }

    public void setUu(Integer uu) {
        this.uu = uu;
    }

    public Double getUf() {
        return uf;
    }

    public void setUf(Double uf) {
        this.uf = uf;
    }
}
