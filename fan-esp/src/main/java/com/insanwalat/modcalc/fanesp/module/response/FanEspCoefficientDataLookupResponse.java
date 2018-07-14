package com.insanwalat.modcalc.fanesp.module.response;

import java.util.List;

public class FanEspCoefficientDataLookupResponse {

    private String name;

    private List<String[][]> tables;

    public FanEspCoefficientDataLookupResponse() {
    }

    public FanEspCoefficientDataLookupResponse(String name, List<String[][]> tables) {
        this.name = name;
        this.tables = tables;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String[][]> getTables() {
        return tables;
    }

    public void setTable(List<String[][]> tables) {
        this.tables = tables;
    }
}
