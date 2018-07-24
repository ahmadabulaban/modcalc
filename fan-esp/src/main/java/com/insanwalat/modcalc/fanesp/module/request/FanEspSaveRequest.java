package com.insanwalat.modcalc.fanesp.module.request;

public class FanEspSaveRequest {

    private String name;
    private FanEspCalcRequest fanEspCalcRequest;

    public FanEspSaveRequest() {
    }

    public FanEspSaveRequest(String name, FanEspCalcRequest fanEspCalcRequest) {
        this.name = name;
        this.fanEspCalcRequest = fanEspCalcRequest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FanEspCalcRequest getFanEspCalcRequest() {
        return fanEspCalcRequest;
    }

    public void setFanEspCalcRequest(FanEspCalcRequest fanEspCalcRequest) {
        this.fanEspCalcRequest = fanEspCalcRequest;
    }
}
