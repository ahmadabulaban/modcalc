package com.insanwalat.modcalc.ductsizer.module.request;

import com.insanwalat.modcalc.ductsizer.module.AirTemperature;
import com.insanwalat.modcalc.ductsizer.module.DuctType;
import com.insanwalat.modcalc.ductsizer.module.FlowRateAndSizingCriteria;
import com.insanwalat.modcalc.ductsizer.module.Units;

public class DuctSizerCalcRequest {

    private Units units;

    private AirTemperature airTemperature;

    private DuctType ductType;

    private FlowRateAndSizingCriteria flowRateAndSizingCriteria;

    public DuctSizerCalcRequest() {
    }

    public DuctSizerCalcRequest(Units units, AirTemperature airTemperature, DuctType ductType, FlowRateAndSizingCriteria flowRateAndSizingCriteria) {
        this.units = units;
        this.airTemperature = airTemperature;
        this.ductType = ductType;
        this.flowRateAndSizingCriteria = flowRateAndSizingCriteria;
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

    public FlowRateAndSizingCriteria getFlowRateAndSizingCriteria() {
        return flowRateAndSizingCriteria;
    }

    public void setFlowRateAndSizingCriteria(FlowRateAndSizingCriteria flowRateAndSizingCriteria) {
        this.flowRateAndSizingCriteria = flowRateAndSizingCriteria;
    }
}
