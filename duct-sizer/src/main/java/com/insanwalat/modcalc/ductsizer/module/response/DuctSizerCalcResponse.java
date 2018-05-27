package com.insanwalat.modcalc.ductsizer.module.response;

import com.insanwalat.modcalc.ductsizer.annotations.FieldDescription;

public class DuctSizerCalcResponse {

    @FieldDescription(uiField = "o1", note = "Flow Rate")
    private Double o1;

    @FieldDescription(uiField = "tx1", note = "Flow Units")
    private String tx1;

    @FieldDescription(uiField = "o2", note = "Round duct size (active only if shp = 2)")
    private Double o2;

    @FieldDescription(uiField = "tx2", note = "Flow Units")
    private String tx2;

    @FieldDescription(uiField = "o3", note = "duct dimension 1 (active only if shp = 1)")
    private Double o3;

    @FieldDescription(uiField = "tx3", note = "Flow Units")
    private String tx3;

    @FieldDescription(uiField = "o4", note = "duct dimension 2 (active only if shp = 1)")
    private Double o4;

    @FieldDescription(uiField = "o5", note = "Flow Velocity")
    private Double o5;

    @FieldDescription(uiField = "tx5", note = "Velocity Units")
    private String tx5;

    @FieldDescription(uiField = "o6", note = "Pressure Drop")
    private Double o6;

    @FieldDescription(uiField = "tx6", note = "Pressure Units")
    private String tx6;

    public DuctSizerCalcResponse() {
    }

    public DuctSizerCalcResponse(Double o1, String tx1, Double o2, String tx2, Double o3, String tx3, Double o4, Double o5, String tx5, Double o6, String tx6) {
        this.o1 = o1;
        this.tx1 = tx1;
        this.o2 = o2;
        this.tx2 = tx2;
        this.o3 = o3;
        this.tx3 = tx3;
        this.o4 = o4;
        this.o5 = o5;
        this.tx5 = tx5;
        this.o6 = o6;
        this.tx6 = tx6;
    }

    public Double getO1() {
        return o1;
    }

    public void setO1(Double o1) {
        this.o1 = o1;
    }

    public String getTx1() {
        return tx1;
    }

    public void setTx1(String tx1) {
        this.tx1 = tx1;
    }

    public Double getO2() {
        return o2;
    }

    public void setO2(Double o2) {
        this.o2 = o2;
    }

    public String getTx2() {
        return tx2;
    }

    public void setTx2(String tx2) {
        this.tx2 = tx2;
    }

    public Double getO3() {
        return o3;
    }

    public void setO3(Double o3) {
        this.o3 = o3;
    }

    public String getTx3() {
        return tx3;
    }

    public void setTx3(String tx3) {
        this.tx3 = tx3;
    }

    public Double getO4() {
        return o4;
    }

    public void setO4(Double o4) {
        this.o4 = o4;
    }

    public Double getO5() {
        return o5;
    }

    public void setO5(Double o5) {
        this.o5 = o5;
    }

    public String getTx5() {
        return tx5;
    }

    public void setTx5(String tx5) {
        this.tx5 = tx5;
    }

    public Double getO6() {
        return o6;
    }

    public void setO6(Double o6) {
        this.o6 = o6;
    }

    public String getTx6() {
        return tx6;
    }

    public void setTx6(String tx6) {
        this.tx6 = tx6;
    }
}
