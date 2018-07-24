package com.insanwalat.modcalc.fanesp.module.input;

import java.util.List;

public class FanEspCalcInput {

    private String project;
    private String system;
    private String pumpRef;

    private Integer uu;
    private String unitSystem;
    private Double uf;
    private String flowRateUnit;
    private Double ul;
    private String lengthUnit;

    private Double temperature;
    private Double temperatureInput;
    private String temperatureUnit;

    private Double eps;

    private List<DuctSectionInput> ductSectionInputList;

    private List<FanSystemInteractionInput> fanSystemInteractionInputList;

    private List<AirTerminalInput> airTerminalInputList;

    private Double fanFlowRateInput;
    private Double q;

    public FanEspCalcInput() {
    }

    public FanEspCalcInput(String project, String system, String pumpRef, Integer uu, String unitSystem, Double uf, String flowRateUnit, Double ul, String lengthUnit, Double temperature, Double temperatureInput, String temperatureUnit, Double eps, List<DuctSectionInput> ductSectionInputList, List<FanSystemInteractionInput> fanSystemInteractionInputList, List<AirTerminalInput> airTerminalInputList, Double fanFlowRateInput, Double q) {
        this.project = project;
        this.system = system;
        this.pumpRef = pumpRef;
        this.uu = uu;
        this.unitSystem = unitSystem;
        this.uf = uf;
        this.flowRateUnit = flowRateUnit;
        this.ul = ul;
        this.lengthUnit = lengthUnit;
        this.temperature = temperature;
        this.temperatureInput = temperatureInput;
        this.temperatureUnit = temperatureUnit;
        this.eps = eps;
        this.ductSectionInputList = ductSectionInputList;
        this.fanSystemInteractionInputList = fanSystemInteractionInputList;
        this.airTerminalInputList = airTerminalInputList;
        this.fanFlowRateInput = fanFlowRateInput;
        this.q = q;
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

    public Integer getUu() {
        return uu;
    }

    public void setUu(Integer uu) {
        this.uu = uu;
    }

    public String getUnitSystem() {
        return unitSystem;
    }

    public void setUnitSystem(String unitSystem) {
        this.unitSystem = unitSystem;
    }

    public Double getUf() {
        return uf;
    }

    public void setUf(Double uf) {
        this.uf = uf;
    }

    public String getFlowRateUnit() {
        return flowRateUnit;
    }

    public void setFlowRateUnit(String flowRateUnit) {
        this.flowRateUnit = flowRateUnit;
    }

    public Double getUl() {
        return ul;
    }

    public void setUl(Double ul) {
        this.ul = ul;
    }

    public String getLengthUnit() {
        return lengthUnit;
    }

    public void setLengthUnit(String lengthUnit) {
        this.lengthUnit = lengthUnit;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getTemperatureInput() {
        return temperatureInput;
    }

    public void setTemperatureInput(Double temperatureInput) {
        this.temperatureInput = temperatureInput;
    }

    public String getTemperatureUnit() {
        return temperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public Double getEps() {
        return eps;
    }

    public void setEps(Double eps) {
        this.eps = eps;
    }

    public List<DuctSectionInput> getDuctSectionInputList() {
        return ductSectionInputList;
    }

    public void setDuctSectionInputList(List<DuctSectionInput> ductSectionInputList) {
        this.ductSectionInputList = ductSectionInputList;
    }

    public List<FanSystemInteractionInput> getFanSystemInteractionInputList() {
        return fanSystemInteractionInputList;
    }

    public void setFanSystemInteractionInputList(List<FanSystemInteractionInput> fanSystemInteractionInputList) {
        this.fanSystemInteractionInputList = fanSystemInteractionInputList;
    }

    public List<AirTerminalInput> getAirTerminalInputList() {
        return airTerminalInputList;
    }

    public void setAirTerminalInputList(List<AirTerminalInput> airTerminalInputList) {
        this.airTerminalInputList = airTerminalInputList;
    }

    public Double getFanFlowRateInput() {
        return fanFlowRateInput;
    }

    public void setFanFlowRateInput(Double fanFlowRateInput) {
        this.fanFlowRateInput = fanFlowRateInput;
    }

    public Double getQ() {
        return q;
    }

    public void setQ(Double q) {
        this.q = q;
    }
}
