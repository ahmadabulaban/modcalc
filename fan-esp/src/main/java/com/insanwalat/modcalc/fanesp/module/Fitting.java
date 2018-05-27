package com.insanwalat.modcalc.fanesp.module;

import com.insanwalat.modcalc.fanesp.annotations.FieldDescription;

public class Fitting {

    @FieldDescription(uiField = "TG1/TG2" , note = "Fitting Criteria")
    private Integer fittingSizingCriteria;

    @FieldDescription(uiField = "i16" , note = "Fittings Category")
    private Integer cat;

    @FieldDescription(uiField = "i17/i19" , note = "Fitting Type/Description")
    private String fittingDescription;

    @FieldDescription(uiField = "i170/i20" , note = "Fitting Coefficient")
    private Double cf;

    @FieldDescription(uiField = "i18/i21" , note = "Fitting qty")
    private Double qf;

    public Fitting() {
    }

    public Fitting(Integer fittingSizingCriteria, Integer cat, String fittingDescription, Double cf, Double qf) {
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
