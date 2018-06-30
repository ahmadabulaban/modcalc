package com.insanwalat.modcalc.fanesp.module.lookup;

import java.util.List;

public class FanEspCoefficientDataLookup {

    private String name;

    private String[][] table;

    private List<Double> values;

    public FanEspCoefficientDataLookup() {
    }

    public FanEspCoefficientDataLookup(String name, String[][] table, List<Double> values) {
        this.name = name;
        this.table = table;
        this.values = values;
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

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }
}
