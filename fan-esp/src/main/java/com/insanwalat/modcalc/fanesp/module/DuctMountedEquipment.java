package com.insanwalat.modcalc.fanesp.module;

import com.insanwalat.modcalc.fanesp.annotations.FieldDescription;

public class DuctMountedEquipment {

    @FieldDescription(uiField = "TG5/TG6" , note = "Duct-Mounted Equipment Criteria")
    private Integer ductMountedEquipmentCriteria;

    @FieldDescription(uiField = "i27/i29" , note = "Duct-Mounted Equipment Type/Designation")
    private String ductMountedEquipmentDescription;

    @FieldDescription(uiField = "i270/i30" , note = "Duct-Mounted Equipment Coefficient")
    private Double ce;

    @FieldDescription(uiField = "i28/i31" , note = "Duct-Mounted Equipment qty")
    private Double qe;

    public DuctMountedEquipment() {
    }

    public DuctMountedEquipment(Integer ductMountedEquipmentCriteria, String ductMountedEquipmentDescription, Double ce, Double qe) {
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
