package com.insanwalat.modcalc.fanesp.module.request;

import com.insanwalat.modcalc.fanesp.module.*;

import java.util.List;

public class FanEspCalcRequest {

    private PumpInformation pumpInformation;

    private Units units;

    private AirTemperature airTemperature;

    private DuctType ductType;

    private List<DuctSection> ductSectionList;

    private List<FanSystemInteraction> fanSystemInteractionList;

    private List<AirTerminal> airTerminalList;

    private FanRate fanRate;

    public FanEspCalcRequest() {
    }

    public FanEspCalcRequest(PumpInformation pumpInformation, Units units, AirTemperature airTemperature, DuctType ductType, List<DuctSection> ductSectionList, List<FanSystemInteraction> fanSystemInteractionList, List<AirTerminal> airTerminalList, FanRate fanRate) {
        this.pumpInformation = pumpInformation;
        this.units = units;
        this.airTemperature = airTemperature;
        this.ductType = ductType;
        this.ductSectionList = ductSectionList;
        this.fanSystemInteractionList = fanSystemInteractionList;
        this.airTerminalList = airTerminalList;
        this.fanRate = fanRate;
    }

    public PumpInformation getPumpInformation() {
        return pumpInformation;
    }

    public void setPumpInformation(PumpInformation pumpInformation) {
        this.pumpInformation = pumpInformation;
    }

    public Units getUnits() {
        return units;
    }

    public void setUnits(Units units) {
        this.units = units;
    }

    public AirTemperature getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(AirTemperature airTemperature) {
        this.airTemperature = airTemperature;
    }

    public DuctType getDuctType() {
        return ductType;
    }

    public void setDuctType(DuctType ductType) {
        this.ductType = ductType;
    }

    public List<DuctSection> getDuctSectionList() {
        return ductSectionList;
    }

    public void setDuctSectionList(List<DuctSection> ductSectionList) {
        this.ductSectionList = ductSectionList;
    }

    public List<FanSystemInteraction> getFanSystemInteractionList() {
        return fanSystemInteractionList;
    }

    public void setFanSystemInteractionList(List<FanSystemInteraction> fanSystemInteractionList) {
        this.fanSystemInteractionList = fanSystemInteractionList;
    }

    public List<AirTerminal> getAirTerminalList() {
        return airTerminalList;
    }

    public void setAirTerminalList(List<AirTerminal> airTerminalList) {
        this.airTerminalList = airTerminalList;
    }

    public FanRate getFanRate() {
        return fanRate;
    }

    public void setFanRate(FanRate fanRate) {
        this.fanRate = fanRate;
    }
}
