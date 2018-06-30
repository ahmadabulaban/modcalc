package com.insanwalat.modcalc.fanesp.module.response;

import java.util.List;

public class FanEspCoefficientDataLookupResponse {

    private String name;

    private String[][] table;

    public FanEspCoefficientDataLookupResponse() {
    }

    public FanEspCoefficientDataLookupResponse(String name, String[][] table) {
        this.name = name;
        this.table = table;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[][] getTable() {
        return table;
    }

    public void setTable(String[][] table) {
        this.table = table;
    }
}