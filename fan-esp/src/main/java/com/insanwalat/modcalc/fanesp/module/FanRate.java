package com.insanwalat.modcalc.fanesp.module;

import com.insanwalat.modcalc.fanesp.annotations.FieldDescription;

public class FanRate {

    @FieldDescription(uiField = "i50" , note = "Fan Flow Rate")
    private Double fanRateInput;

    @FieldDescription(uiField = "tx2" , note = "Fan Flow Rate Unit")
    private String fanRateUnit;

    public FanRate() {
    }

    public FanRate(Double fanRateInput, String fanRateUnit) {
        this.fanRateInput = fanRateInput;
        this.fanRateUnit = fanRateUnit;
    }

    public Double getFanRateInput() {
        return fanRateInput;
    }

    public void setFanRateInput(Double fanRateInput) {
        this.fanRateInput = fanRateInput;
    }

    public String getFanRateUnit() {
        return fanRateUnit;
    }

    public void setFanRateUnit(String fanRateUnit) {
        this.fanRateUnit = fanRateUnit;
    }
}
