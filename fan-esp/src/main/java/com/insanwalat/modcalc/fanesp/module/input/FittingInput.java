package com.insanwalat.modcalc.fanesp.module.input;

import com.insanwalat.modcalc.fanesp.annotations.FieldDescription;

public class FittingInput {
    private Integer fittingSizingCriteria;
    private Integer cat;
    private String fittingDescription;
    private Double cf;
    private Double qf;

    public FittingInput() {
    }

    public FittingInput(Integer fittingSizingCriteria, Integer cat, String fittingDescription, Double cf, Double qf) {
        this.fittingSizingCriteria = fittingSizingCriteria;
        this.cat = cat;
        this.fittingDescription = fittingDescription;
        this.cf = cf;
        this.qf = qf;
    }

    public Integer getFittingSizingCriteria() {
        return fittingSizingCriteria;
    }

    public void setFittingSizingCriteria(Integer fittingSizingCriteria) {
        this.fittingSizingCriteria = fittingSizingCriteria;
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
