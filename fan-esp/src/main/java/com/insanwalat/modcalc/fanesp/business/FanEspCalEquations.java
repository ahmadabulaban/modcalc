package com.insanwalat.modcalc.fanesp.business;

import com.insanwalat.modcalc.fanesp.annotations.EquationDescription;

import static java.lang.Math.log;
import static java.lang.Math.pow;

public class FanEspCalEquations {

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

    @EquationDescription(name = "Duct Hydraulic Diameter"
            , description = "calculates duct hydraulic diameter"
            , output = "Hydraulic diameter")
    public Double hyd_dia(Integer shp, Double w, Double h, Double d, Double t) {
        Double Dh = (double) 0;
        if (shp == 1) {
            Dh = 2 * (w - 2 * t) * (h - 2 * t) / (w + h - 4 * t);
        }
        if (shp == 2) {
            Dh = d - 2 * t;
        }
        return Dh;
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

    @EquationDescription(name = "Straight duct pressure drop"
            , description = "calculates pressure drop in straight duct using Darcy-Weisbach Equation"
            , output = "Pressure drop")
    public Double str_duct_pd(Double rho, Double vis, Double dh, Double v, Double eps, Double l) {
        Double sec_pd_st;
        Double re = dh * v / (1000 * vis);
        Double f = f_factor(re, dh, eps);
        sec_pd_st = 500 * f * l * rho * pow(v, 2) / dh;
        return sec_pd_st;
    }

    @EquationDescription(name = "Fittings Friction Loss"
            , description = "calculates pressure drop for fittings, dampers … etc using loss coefficient (C)"
            , output = "Pressure drop")
    public Double fit_pd(Double v, Double rho, Double c) {
        Double pd;
        pd = 0.5 * c * rho * pow(v, 2);
        return pd;
    }
}
