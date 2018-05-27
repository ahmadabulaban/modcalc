package com.insanwalat.modcalc.fanesp.module.lookup;

import java.util.List;

public class FanEspCoefficientDataLookup {

    private String[][] table;

    private List<Double> values;

    public FanEspCoefficientDataLookup() {
    }

    public FanEspCoefficientDataLookup(String[][] table, List<Double> values) {
        this.table = table;
        this.values = values;
    }

    public String[][] getTable() {
        return table;
    }

    public void setTable(String[][] table) {
        this.table = table;
    }

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }
}
