package com.insanwalat.modcalc.fanesp.module.response;

import com.insanwalat.modcalc.fanesp.module.request.FanEspCalcRequest;

public class FanEspLoadResponse {

    private String name;
    private String date;
    private FanEspCalcRequest fanEspCalcRequest;

    public FanEspLoadResponse() {
    }

    public FanEspLoadResponse(String name, String date, FanEspCalcRequest fanEspCalcRequest) {
        this.name = name;
        this.date = date;
        this.fanEspCalcRequest = fanEspCalcRequest;
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

    public FanEspCalcRequest getFanEspCalcRequest() {
        return fanEspCalcRequest;
    }

    public void setFanEspCalcRequest(FanEspCalcRequest fanEspCalcRequest) {
        this.fanEspCalcRequest = fanEspCalcRequest;
    }
}
