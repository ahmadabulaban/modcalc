package com.insanwalat.modcalc.business;

import com.insanwalat.modcalc.module.input.DuctSizerCalcInput;
import com.insanwalat.modcalc.module.output.DuctSizerCalcOutput;

public class DuctSizerCalcAlgorithm {

    private DuctSizerCalcEquations equations = new DuctSizerCalcEquations();
    private Double rho;
    private Double vis;
    private Double d;
    private Double dim2;
    private Double v;
    private Double pd_st;
    private DuctSizerCalcOutput output;

    public DuctSizerCalcOutput doCalculations(DuctSizerCalcInput input) {
        clearAllVariables();
        calculateDensityAndViscosity(input.getTemperature());
        calculateDuctDimensions(input);
        calculateFlowVelocity(input);
        calculatePressureDrop(input);
        generateOutput(input);
        return output;
    }

    private void generateOutput(DuctSizerCalcInput input) {
        output = new DuctSizerCalcOutput();
        output.setO1(input.getRateInput());
        output.setTx1(input.getRateUnit());
        Integer uu = input.getUu();
        Integer shp = input.getShp();
        if (shp == 2) {
            double o2;
            if (uu == 1)
                o2 = d;
            else
                o2 = d / 25.4;
            output.setO2(o2);
            output.setTx2(input.getDimensionUnit());
        }
        if (shp == 1) {
            output.setO3(input.getDimensionInput());
            double o4;
            if (uu == 1)
                o4 = dim2;
            else
                o4 = dim2 / 25.4;
            output.setO4(o4);
            output.setTx3(input.getDimensionUnit());
        }
        double o5;
        if (uu == 1)
            o5 = v;
        else
            o5 = v * 196.8;
        output.setO5(o5);
        output.setTx5(input.getVelocityUnit());
        double o6;
        if (uu == 1)
            o6 = pd_st;
        else
            o6 = pd_st / 8.17;
        output.setO6(o6);
        output.setTx6(input.getPressureUnit());
    }

    private void calculatePressureDrop(DuctSizerCalcInput input) {
        Double l = (double) 1;
        Double dh = (double) 0;
        if (input.getShp() == 1) {
            dh = 4 * (input.getDim1() - 2 * input.getThickness()) * (dim2 - 2 * input.getThickness())
                    / (2 * input.getDim1() + 2 * dim2 - 4 * input.getThickness());
        }
        if (input.getShp() == 2) {
            dh = d - 2 * input.getThickness();
        }
        pd_st = equations.str_duct_pd(rho, vis, dh, v, input.getEps(), l);
    }

    private void calculateFlowVelocity(DuctSizerCalcInput input) {
        v = equations.vel(input.getShp(), input.getQ(), input.getDim1(), dim2, d, input.getThickness());
    }

    private void calculateDuctDimensions(DuctSizerCalcInput input) {
        if (input.getSizingCriteria() == 1) {
            d = equations.size_p(input.getpMax(), input.getQ(), rho, vis, input.getEps(), input.getThickness());
            if (input.getShp() == 1)
                dim2 = equations.equ_rec(d, input.getDim1(), input.getThickness());
        } else {
            if (input.getShp() == 1)
                dim2 = equations.size_v_rec(input.getvMax(), input.getQ(), input.getDim1(), input.getThickness());
            else
                d = equations.size_v_rou(input.getvMax(), input.getQ(), input.getThickness());
        }
    }

    private void calculateDensityAndViscosity(Double temperature) {
        rho = equations.air_den(temperature);
        vis = equations.air_vis(temperature);
    }

    private void clearAllVariables() {
        rho = (double) 0;
        vis = (double) 0;
        d = (double) 0;
        dim2 = (double) 0;
        v = (double) 0;
        pd_st = (double) 0;
        output = null;
    }
}
