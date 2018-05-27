package com.insanwalat.modcalc.fanesp.module;

import com.insanwalat.modcalc.fanesp.annotations.FieldDescription;

public class PumpInformation {

    @FieldDescription(uiField = "" , note = "Project")
    private String project;

    @FieldDescription(uiField = "" , note = "System")
    private String system;

    @FieldDescription(uiField = "" , note = "Pump Ref.")
    private String pumpRef;

    public PumpInformation() {
    }

    public PumpInformation(String project, String system, String pumpRef) {
        this.project = project;
        this.system = system;
        this.pumpRef = pumpRef;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getPumpRef() {
        return pumpRef;
    }

    public void setPumpRef(String pumpRef) {
        this.pumpRef = pumpRef;
    }
}
