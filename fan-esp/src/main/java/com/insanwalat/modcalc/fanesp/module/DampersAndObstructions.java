package com.insanwalat.modcalc.fanesp.module;

import com.insanwalat.modcalc.fanesp.annotations.FieldDescription;

public class DampersAndObstructions {

    @FieldDescription(uiField = "TG3/TG4" , note = "Dampers & Obstructions Criteria")
    private Integer dampersAndObstructionsSizingCriteria;

    @FieldDescription(uiField = "i22/i24" , note = "Dampers & Obstructions Type/Designation")
    private String dampersAndObstructionsDescription;

    @FieldDescription(uiField = "i220/i25" , note = "Dampers & Obstructions Coefficient")
    private Double cd;

    @FieldDescription(uiField = "i23/i26" , note = "Dampers & Obstructions qty")
    private Double qd;

    public DampersAndObstructions() {
    }

    public DampersAndObstructions(Integer dampersAndObstructionsSizingCriteria, String dampersAndObstructionsDescription, Double cd, Double qd) {
        this.dampersAndObstructionsSizingCriteria = dampersAndObstructionsSizingCriteria;
        this.dampersAndObstructionsDescription = dampersAndObstructionsDescription;
        this.cd = cd;
        this.qd = qd;
    }

    public Integer getDampersAndObstructionsSizingCriteria() {
        return dampersAndObstructionsSizingCriteria;
    }

    public void setDampersAndObstructionsSizingCriteria(Integer dampersAndObstructionsSizingCriteria) {
        this.dampersAndObstructionsSizingCriteria = dampersAndObstructionsSizingCriteria;
    }

    public String getDampersAndObstructionsDescription() {
        return dampersAndObstructionsDescription;
    }

    public void setDampersAndObstructionsDescription(String dampersAndObstructionsDescription) {
        this.dampersAndObstructionsDescription = dampersAndObstructionsDescription;
    }

    public Double getCd() {
        return cd;
    }

    public void setCd(Double cd) {
        this.cd = cd;
    }

    public Double getQd() {
        return qd;
    }

    public void setQd(Double qd) {
        this.qd = qd;
    }
}
