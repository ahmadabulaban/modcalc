package com.insanwalat.modcalc.fanesp.module.input;

import com.insanwalat.modcalc.fanesp.module.request.FanEspCalcRequest;

public class FanEspSaveInput {

    private String name;
    private FanEspCalcInput fanEspCalcInput;

    public FanEspSaveInput() {
    }

    public FanEspSaveInput(String name, FanEspCalcInput fanEspCalcInput) {
        this.name = name;
        this.fanEspCalcInput = fanEspCalcInput;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FanEspCalcInput getFanEspCalcInput() {
        return fanEspCalcInput;
    }

    public void setFanEspCalcInput(FanEspCalcInput fanEspCalcInput) {
        this.fanEspCalcInput = fanEspCalcInput;
    }
}
