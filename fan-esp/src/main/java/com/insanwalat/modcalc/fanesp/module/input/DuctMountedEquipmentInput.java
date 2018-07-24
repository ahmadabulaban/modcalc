package com.insanwalat.modcalc.fanesp.module.input;

public class DuctMountedEquipmentInput {
    private Integer ductMountedEquipmentCriteria;
    private String ductMountedEquipmentDescription;
    private Double ce;
    private Double qe;

    public DuctMountedEquipmentInput() {
    }

    public DuctMountedEquipmentInput(Integer ductMountedEquipmentCriteria, String ductMountedEquipmentDescription, Double ce, Double qe) {
        this.ductMountedEquipmentCriteria = ductMountedEquipmentCriteria;
        this.ductMountedEquipmentDescription = ductMountedEquipmentDescription;
        this.ce = ce;
        this.qe = qe;
    }

    public Integer getDuctMountedEquipmentCriteria() {
        return ductMountedEquipmentCriteria;
    }

    public void setDuctMountedEquipmentCriteria(Integer ductMountedEquipmentCriteria) {
        this.ductMountedEquipmentCriteria = ductMountedEquipmentCriteria;
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
