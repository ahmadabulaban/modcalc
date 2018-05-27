package com.insanwalat.modcalc.fanesp.module.input;

import com.insanwalat.modcalc.fanesp.annotations.FieldDescription;

public class FittingInput {
    private Integer cat;
    private String fittingDescription;
    private Double cf;
    private Double qf;

    public FittingInput() {
    }

    public FittingInput(Integer cat, String fittingDescription, Double cf, Double qf) {
        this.cat = cat;
        this.fittingDescription = fittingDescription;
        this.cf = cf;
        this.qf = qf;
    }

    public Integer getCat() {
        return cat;
    }

    public void setCat(Integer cat) {
        this.cat = cat;
    }

    public String getFittingDescription() {
        return fittingDescription;
    }

    public void setFittingDescription(String fittingDescription) {
        this.fittingDescription = fittingDescription;
    }

    public Double getCf() {
        return cf;
    }

    public void setCf(Double cf) {
        this.cf = cf;
    }

    public Double getQf() {
        return qf;
    }

    public void setQf(Double qf) {
        this.qf = qf;
    }
}
