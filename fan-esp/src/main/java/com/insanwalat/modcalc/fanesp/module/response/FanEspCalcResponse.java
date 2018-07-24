package com.insanwalat.modcalc.fanesp.module.response;

import java.util.List;

public class FanEspCalcResponse {

    private String project;
    private String system;
    private String pumpRef;
    private String date;
    private String tx5;
    private List<DuctSectionResponse> ductSectionResponseList;
    private List<FanSystemInteractionResponse> fanSystemInteractionResponseList;
    private Double o32;
    private Double o33;
    private Double o34;
    private Double o35;
    private Double o36;
    private Double o37;
    private List<AirTerminalResponse> airTerminalResponseList;
    private Double o42;
    private Double o43;
    private Double o44;
    private Double o45;

    public FanEspCalcResponse() {
    }

    public FanEspCalcResponse(String project, String system, String pumpRef, String date, String tx5, List<DuctSectionResponse> ductSectionResponseList, List<FanSystemInteractionResponse> fanSystemInteractionResponseList, Double o32, Double o33, Double o34, Double o35, Double o36, Double o37, List<AirTerminalResponse> airTerminalResponseList, Double o42, Double o43, Double o44, Double o45) {
        this.project = project;
        this.system = system;
        this.pumpRef = pumpRef;
        this.date = date;
        this.tx5 = tx5;
        this.ductSectionResponseList = ductSectionResponseList;
        this.fanSystemInteractionResponseList = fanSystemInteractionResponseList;
        this.o32 = o32;
        this.o33 = o33;
        this.o34 = o34;
        this.o35 = o35;
        this.o36 = o36;
        this.o37 = o37;
        this.airTerminalResponseList = airTerminalResponseList;
        this.o42 = o42;
        this.o43 = o43;
        this.o44 = o44;
        this.o45 = o45;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTx5() {
        return tx5;
    }

    public void setTx5(String tx5) {
        this.tx5 = tx5;
    }

    public List<DuctSectionResponse> getDuctSectionResponseList() {
        return ductSectionResponseList;
    }

    public void setDuctSectionResponseList(List<DuctSectionResponse> ductSectionResponseList) {
        this.ductSectionResponseList = ductSectionResponseList;
    }

    public List<FanSystemInteractionResponse> getFanSystemInteractionResponseList() {
        return fanSystemInteractionResponseList;
    }

    public void setFanSystemInteractionResponseList(List<FanSystemInteractionResponse> fanSystemInteractionResponseList) {
        this.fanSystemInteractionResponseList = fanSystemInteractionResponseList;
    }

    public Double getO32() {
        return o32;
    }

    public void setO32(Double o32) {
        this.o32 = o32;
    }

    public Double getO33() {
        return o33;
    }

    public void setO33(Double o33) {
        this.o33 = o33;
    }

    public Double getO34() {
        return o34;
    }

    public void setO34(Double o34) {
        this.o34 = o34;
    }

    public Double getO35() {
        return o35;
    }

    public void setO35(Double o35) {
        this.o35 = o35;
    }

    public Double getO36() {
        return o36;
    }

    public void setO36(Double o36) {
        this.o36 = o36;
    }

    public Double getO37() {
        return o37;
    }

    public void setO37(Double o37) {
        this.o37 = o37;
    }

    public List<AirTerminalResponse> getAirTerminalResponseList() {
        return airTerminalResponseList;
    }

    public void setAirTerminalResponseList(List<AirTerminalResponse> airTerminalResponseList) {
        this.airTerminalResponseList = airTerminalResponseList;
    }

    public Double getO42() {
        return o42;
    }

    public void setO42(Double o42) {
        this.o42 = o42;
    }

    public Double getO43() {
        return o43;
    }

    public void setO43(Double o43) {
        this.o43 = o43;
    }

    public Double getO44() {
        return o44;
    }

    public void setO44(Double o44) {
        this.o44 = o44;
    }

    public Double getO45() {
        return o45;
    }

    public void setO45(Double o45) {
        this.o45 = o45;
    }
}
