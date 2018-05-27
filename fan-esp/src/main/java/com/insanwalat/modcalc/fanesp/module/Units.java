package com.insanwalat.modcalc.fanesp.module;

import com.insanwalat.modcalc.fanesp.annotations.FieldDescription;

public class Units {

    @FieldDescription(uiField = "i1", note = "Unit System")
    private Integer uu;

    @FieldDescription(uiField = "i2", note = "Flow Rate")
    private Double uf;

    @FieldDescription(uiField = "i3", note = "Length")
    private Double ul;

    public Units() {
    }

    public Units(Integer uu, Double uf, Double ul) {
        this.uu = uu;
        this.uf = uf;
        this.ul = ul;
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

    public Double getUl() {
        return ul;
    }

    public void setUl(Double ul) {
        this.ul = ul;
    }
}
