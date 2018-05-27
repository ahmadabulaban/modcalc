package com.insanwalat.modcalc.fanesp.module.input;

public class DuctMountedEquipmentInput {
    private String ductMountedEquipmentDescription;
    private Double ce;
    private Double qe;

    public DuctMountedEquipmentInput() {
    }

    public DuctMountedEquipmentInput(String ductMountedEquipmentDescription, Double ce, Double qe) {
        this.ductMountedEquipmentDescription = ductMountedEquipmentDescription;
        this.ce = ce;
        this.qe = qe;
    }

    public String getDuctMountedEquipmentDescription() {
        return ductMountedEquipmentDescription;
    }

    public void setDuctMountedEquipmentDescription(String ductMountedEquipmentDescription) {
        this.ductMountedEquipmentDescription = ductMountedEquipmentDescription;
    }

    public Double getCe() {
        return ce;
    }

    public void setCe(Double ce) {
        this.ce = ce;
    }

    public Double getQe() {
        return qe;
    }

    public void setQe(Double qe) {
        this.qe = qe;
    }
}
