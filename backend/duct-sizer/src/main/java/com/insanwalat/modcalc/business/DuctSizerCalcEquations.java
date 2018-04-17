package com.insanwalat.modcalc.business;

import com.insanwalat.modcalc.annotations.EquationDescription;

import static java.lang.Math.*;

public class DuctSizerCalcEquations {

    @EquationDescription(name = "Air Density"
            , description = "calculates density of air at working temperature"
            , output = "Liquid density")
    public Double air_den(Double temperature) {
        Double rho = -0.000000048343 * pow(temperature, 3)
                + 0.000017795392 * pow(temperature, 2)
                - 0.004766279597 * temperature + 1.291983692302;
        return rho;
    }

    @EquationDescription(name = "Kinematic Viscosity"
            , description = "calculates kinematic viscosity of air at working"
            , output = "Liquid dynamic viscosity")
    public Double air_vis(Double temperature) {
        Double vis = -0.000000000000074 * pow(temperature, 3)
                + 0.000000000106944 * pow(temperature, 2)
                + 0.000000086830800 * temperature + 0.000013380258970;
        return vis;
    }

    @EquationDescription(name = "Friction Factor"
            , description = "calculates pipe friction factor using Churchill equation"
            , output = "Friction factor")
    public Double f_factor(Double re, Double dh, Double eps) {
        Double f;
        if (re < 2320)
            f = 64 / re;
        else if (re >= 2320 && re <= 4000) {
            Double B = pow((37530 / 4000), 16);
            Double a1 = pow((7 / 4000), 0.9);
            Double a2 = 0.27 * eps / (dh / 1000);
            Double a = 1 / (a1 + a2);
            Double A = pow((2.457 * log(a)), 16);
            Double f1 = pow((8 / 4000), 12);
            Double f2 = 1 / pow((A + B), 1.5);
            f = 8 * pow((f1 + f2), (1.0 / 12.0));
        } else {
            Double B = pow((37530 / re), 16);
            Double a1 = pow((7 / re), 0.9);
            Double a2 = 0.27 * eps / (dh / 1000);
            Double a = 1 / (a1 + a2);
            Double A = pow((2.457 * log(a)), 16);
            Double f1 = pow((8 / re), 12);
            Double f2 = 1 / pow((A + B), 1.5);
            f = 8 * pow((f1 + f2), (1.0 / 12.0));
        }
        return f;
    }

    @EquationDescription(name = "Duct Velocity"
            , description = "calculates air velocity in duct"
            , output = "Velocity")
    public Double vel(Integer shp, Double q, Double w, Double h, Double d, Double t) {
        Double v = (double) 0;
        if (shp == 1)
            v = (q / 1000) / ((w - 2 * t) * (h - 2 * t) / 1000000);
        if (shp == 2) {
            Double dh = d - 2 * t;
            v = 1.273 * (q / 1000) / pow((dh / 1000), 2);
        }
        return v;
    }

    @EquationDescription(name = "Straight duct pressure drop"
            , description = "calculates pressure drop in straight duct using Darcy-Weisbach Equation"
            , output = "Pressure drop")
    public Double str_duct_pd(Double rho, Double vis, Double dh, Double v, Double eps, Double l) {
        Double pd_st;
        Double re = dh * v / (1000 * vis);
        Double f = f_factor(re, dh, eps);
        pd_st = 500 * f * l * rho * pow(v, 2) / dh;
        return pd_st;
    }

    @EquationDescription(name = "Duct Sizing- Pressure Drop Criteria"
            , description = "calculates duct size using pressure criteria"
            , output = "diameter")
    public Double size_p(Double p_max, Double q, Double rho, Double vis, Double eps, Double t) {
        Double d = (double) 0;
        Double dh = 50 - 2 * t;
        boolean repeat = true;
        while (repeat) {
            Double v = 1.273 * (q / 1000) / pow((dh / 1000), 2);
            Double re = dh * v / (1000 * vis);
            Double f = f_factor(re, dh, eps);
            Double pd = 500 * f * rho * pow(v, 2) / dh;
            if (pd <= p_max) {
                d = dh + 2 * t;
                repeat = false;
            } else
                dh = dh + 10;
        }
        return d;
    }

    @EquationDescription(name = "Duct Sizing- Velocity Criteria for round duct"
            , description = "calculates duct size using velocity criteria"
            , output = "diameter")
    public Double size_v_rou(Double v_max, Double q, Double t) {
        Double d;
        Double dh = (sqrt(1273 * q / v_max));
        Double diameter = dh + 2 * t;
        d = ceil(diameter / 10.0) * 10;
        return d;
    }

    @EquationDescription(name = "Duct Sizing- Velocity Criteria for rectangular duct"
            , description = "calculates duct size using velocity criteria"
            , output = "Second dimension")
    public Double size_v_rec(Double v_max, Double q, Double dim1, Double t) {
        Double dim2;
        Double dimension1 = ceil(dim1 / 10.0) * 10;
        Double d1 = dimension1 - t;
        Double d2 = 1000 * q / (v_max * d1);
        Double dimension2 = d2 + t;
        dim2 = ceil(dimension2 / 10.0) * 10;
        return dim2;
    }

    @EquationDescription(name = "Equivalent rectangular duct"
            , description = "calculates equivalent rectangular duct dimensions"
            , output = "Second dimension")
    public Double equ_rec(Double d, Double dim1, Double t) {
        Double dh = d - 2 * t;
        Double A = (22.0 / 28.0) * pow(dh, 2);
        Double dimension1 = dim1 - 2 * t;
        Double dimension2 = A / dimension1;
        Double D = 1.3 * pow(dimension1 * dimension2, 0.625) / pow(dimension1 + dimension2, 0.25);
        double diff = D - dh;
        double dim2;
        if (diff < -0.2) {
            while (diff < -0.2) {
                dimension2 = dimension2 + 1;
                D = 1.3 * pow(dimension1 * dimension2, 0.625) / pow(dimension1 + dimension2, 0.25);
                diff = D - dh;
            }
            dim2 = dimension2 + 2 * t;
        }
        else if(diff>0.2){
            while (diff > 0.2) {
                dimension2 = dimension2 - 1;
                D = 1.3 * pow(dimension1 * dimension2, 0.625) / pow(dimension1 + dimension2, 0.25);
                diff = D - dh;
            }
            dim2 = dimension2 + 2 * t;
        }
        else
            dim2 = dimension2 + 2 * t;
        dim2=ceil(dim2);
        return dim2;
    }
}
