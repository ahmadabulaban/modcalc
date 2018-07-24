package com.insanwalat.modcalc.fanesp.module.output;

import com.insanwalat.modcalc.fanesp.module.input.FanEspCalcInput;

public class FanEspLoadOutput {

    private String name;
    private String date;
    private FanEspCalcInput fanEspCalcInput;

    public FanEspLoadOutput() {
    }

    public FanEspLoadOutput(String name, String date, FanEspCalcInput fanEspCalcInput) {
        this.name = name;
        this.date = date;
        this.fanEspCalcInput = fanEspCalcInput;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public FanEspCalcInput getFanEspCalcInput() {
        return fanEspCalcInput;
    }

    public void setFanEspCalcInput(FanEspCalcInput fanEspCalcInput) {
        this.fanEspCalcInput = fanEspCalcInput;
    }
}
