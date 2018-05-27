package com.insanwalat.modcalc.fanesp.module.input;

public class DampersAndObstructionsInput {
    private String dampersAndObstructionsDescription;
    private Double cd;
    private Double qd;

    public DampersAndObstructionsInput() {
    }

    public DampersAndObstructionsInput(String dampersAndObstructionsDescription, Double cd, Double qd) {
        this.dampersAndObstructionsDescription = dampersAndObstructionsDescription;
        this.cd = cd;
        this.qd = qd;
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
