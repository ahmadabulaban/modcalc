package com.insanwalat.modcalc.fanesp.business;

import com.insanwalat.modcalc.fanesp.module.input.*;
import com.insanwalat.modcalc.fanesp.module.output.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;

public class FanEspCalcAlgorithm {

    private static final double MULTIPLICATION_CONSTANT = 0.004014631;
    private FanEspCalEquations equations = new FanEspCalEquations();
    private FanEspCalcOutput output;
    private Double rho;
    private Double vis;
    private List<Double> vList;
    private List<Double> dhList;
    private List<Double> sec_pd_sdList;
    private List<List<Double>> pd_f_tList;
    private List<Double> sec_pd_fList;
    private List<List<Double>> pd_d_tList;
    private List<Double> sec_pd_dList;
    private List<List<Double>> pd_d_eList;
    private List<Double> sec_pd_eList;
    private List<Double> sec_pd_iList;
    private List<List<Double>> pd_c_tList;
    private List<Double> sec_pd_cList;
    private Double tot_pd_sd;
    private Double tot_pd_f;
    private Double tot_pd_d;
    private Double tot_pd_e;
    private Double tot_pd_c;
    private Double tot_pd_at;
    private Double fan_esp;
    private Double tot_pd_i;

    public FanEspCalcOutput doCalculations(FanEspCalcInput input) {
        clearAllVariables();
        calculateDensityAndViscosity(input.getTemperature());
        calculatePressureDropInEachSection(input.getDuctSectionInputList());
        calculateStraightDuctInEachSection(input);
        calculatePressureDropInEachFitting(input);
        calculatePressureDropInEachDampersAndObstructions(input);
        calculatePressureDropInEachDuctMountedEquipment(input);
        calculatePressureDropInEachFanSystemInteraction(input);
        calculatePressureDropInEachSpecialComponents(input);
        calculateDuctSectionsSummary(input);
        calculatePressureDropInAllAirTerminals(input);
        calculateFanEsp(input);
        generateOutput(input);
        return output;
    }

    private void generateOutput(FanEspCalcInput input) {
        Integer uu = input.getUu();
        output = new FanEspCalcOutput();
        output.setProject(input.getProject());
        output.setSystem(input.getSystem());
        output.setPumpRef(input.getPumpRef());
        output.setDate(new Date().toString());
        if (uu == 1)
            output.setTx5("Pa");
        if (uu == 2)
            output.setTx5("inch H2O");
        List<DuctSectionOutput> ductSectionOutputList = new ArrayList<>();
        int cnt = 0;
        for (DuctSectionInput ductSectionInput : input.getDuctSectionInputList()) {
            DuctSectionOutput ductSectionOutput = new DuctSectionOutput();
            ductSectionOutput.setO1(ductSectionInput.getDuctSectionId());
            ductSectionOutput.setTx1(ductSectionInput.getDimensionUnit());
            if (ductSectionInput.getShp() == 1) {
                ductSectionOutput.setO3(ductSectionInput.getWidthInput());
                ductSectionOutput.setO4(ductSectionInput.getHeightInput());
            }
            ductSectionOutput.setTx2(input.getFlowRateUnit());
            if (ductSectionInput.getShp() == 2) {
                ductSectionOutput.setO5(ductSectionInput.getDuctDiameterInput());
            }
            ductSectionOutput.setO6(ductSectionInput.getThicknessInput());
            ductSectionOutput.setO7(ductSectionInput.getFlowRateInput());
            if (uu == 1)
                ductSectionOutput.setO8(vList.get(cnt));
            if (uu == 2)
                ductSectionOutput.setO8(vList.get(cnt) * 196.85039);
            if (uu == 1)
                ductSectionOutput.setTx3("m/s");
            if (uu == 2)
                ductSectionOutput.setTx3("fpm");
            ductSectionOutput.setO88(ductSectionInput.getLengthInput());
            ductSectionOutput.setTx4(input.getLengthUnit());
            Double sec_pd_sd = sec_pd_sdList.get(cnt);
            if (uu == 1)
                ductSectionOutput.setO9(sec_pd_sd);
            if (uu == 2)
                ductSectionOutput.setO9(sec_pd_sd * MULTIPLICATION_CONSTANT);
            List<FittingOutput> fittingOutputList = new ArrayList<>();
            List<Double> pd_f_tInnerList = pd_f_tList.get(cnt);
            int innerCnt = 0;
            for (FittingInput fittingInput : ductSectionInput.getFittingInputList()) {
                FittingOutput fittingOutput = new FittingOutput();
                fittingOutput.setO10(fittingInput.getFittingDescription());
                fittingOutput.setO11(fittingInput.getCf());
                fittingOutput.setO12(fittingInput.getQf());
                Double pd_f_t = pd_f_tInnerList.get(innerCnt);
                if (uu == 1)
                    fittingOutput.setO13(pd_f_t);
                if (uu == 2)
                    fittingOutput.setO13(pd_f_t * MULTIPLICATION_CONSTANT);
                fittingOutputList.add(fittingOutput);
                ++innerCnt;
            }
            ductSectionOutput.setFittingOutputList(fittingOutputList);
            List<DampersAndObstructionsOutput> dampersAndObstructionsOutputList = new ArrayList<>();
            List<Double> pd_d_tInnerList = pd_d_tList.get(cnt);
            innerCnt = 0;
            for (DampersAndObstructionsInput dampersAndObstructionsInput : ductSectionInput.getDampersAndObstructionsInputList()) {
                DampersAndObstructionsOutput dampersAndObstructionsOutput = new DampersAndObstructionsOutput();
                dampersAndObstructionsOutput.setO14(dampersAndObstructionsInput.getDampersAndObstructionsDescription());
                dampersAndObstructionsOutput.setO15(dampersAndObstructionsInput.getCd());
                dampersAndObstructionsOutput.setO16(dampersAndObstructionsInput.getQd());
                Double pd_d_t = pd_d_tInnerList.get(innerCnt);
                if (uu == 1)
                    dampersAndObstructionsOutput.setO17(pd_d_t);
                if (uu == 2)
                    dampersAndObstructionsOutput.setO17(pd_d_t * MULTIPLICATION_CONSTANT);
                dampersAndObstructionsOutputList.add(dampersAndObstructionsOutput);
                ++innerCnt;
            }
            ductSectionOutput.setDampersAndObstructionsOutputList(dampersAndObstructionsOutputList);
            List<DuctMountedEquipmentOutput> ductMountedEquipmentOutputList = new ArrayList<>();
            List<Double> pd_d_eInnerList = pd_d_eList.get(cnt);
            innerCnt = 0;
            for (DuctMountedEquipmentInput ductMountedEquipmentInput : ductSectionInput.getDuctMountedEquipmentInputList()) {
                DuctMountedEquipmentOutput ductMountedEquipmentOutput = new DuctMountedEquipmentOutput();
                ductMountedEquipmentOutput.setO18(ductMountedEquipmentInput.getDuctMountedEquipmentDescription());
                ductMountedEquipmentOutput.setO19(ductMountedEquipmentInput.getCe());
                ductMountedEquipmentOutput.setO20(ductMountedEquipmentInput.getQe());
                Double pd_d_e = pd_d_eInnerList.get(innerCnt);
                if (uu == 1)
                    ductMountedEquipmentOutput.setO21(pd_d_e);
                if (uu == 2)
                    ductMountedEquipmentOutput.setO21(pd_d_e * MULTIPLICATION_CONSTANT);
                ductMountedEquipmentOutputList.add(ductMountedEquipmentOutput);
                ++innerCnt;
            }
            ductSectionOutput.setDuctMountedEquipmentOutputList(ductMountedEquipmentOutputList);
            List<SpecialComponentOutput> specialComponentOutputList = new ArrayList<>();
            List<Double> pd_c_tInnerList = pd_c_tList.get(cnt);
            innerCnt = 0;
            for (SpecialComponentInput specialComponentInput : ductSectionInput.getSpecialComponentInputList()) {
                SpecialComponentOutput specialComponentOutput = new SpecialComponentOutput();
                specialComponentOutput.setO22(specialComponentInput.getSpecialComponentDescription());
                specialComponentOutput.setO23(specialComponentInput.getQc());
                Double pd_c_t = pd_c_tInnerList.get(innerCnt);
                if (uu == 1)
                    specialComponentOutput.setO24(pd_c_t);
                if (uu == 2)
                    specialComponentOutput.setO24(pd_c_t * MULTIPLICATION_CONSTANT);
                specialComponentOutputList.add(specialComponentOutput);
                ++innerCnt;
            }
            ductSectionOutput.setSpecialComponentOutputList(specialComponentOutputList);
            Double sec_pd_f = sec_pd_fList.get(cnt);
            if (uu == 1)
                ductSectionOutput.setO25(sec_pd_f);
            if (uu == 2)
                ductSectionOutput.setO25(sec_pd_f * MULTIPLICATION_CONSTANT);
            Double sec_pd_d = sec_pd_dList.get(cnt);
            if (uu == 1)
                ductSectionOutput.setO26(sec_pd_d);
            if (uu == 2)
                ductSectionOutput.setO26(sec_pd_d * MULTIPLICATION_CONSTANT);
            Double sec_pd_e = sec_pd_eList.get(cnt);
            if (uu == 1)
                ductSectionOutput.setO27(sec_pd_e);
            if (uu == 2)
                ductSectionOutput.setO27(sec_pd_e * MULTIPLICATION_CONSTANT);
            Double sec_pd_c = sec_pd_cList.get(cnt);
            if (uu == 1)
                ductSectionOutput.setO28(sec_pd_c);
            if (uu == 2)
                ductSectionOutput.setO28(sec_pd_c * MULTIPLICATION_CONSTANT);
            ductSectionOutputList.add(ductSectionOutput);
            ++cnt;
        }
        output.setDuctSectionOutputList(ductSectionOutputList);
        List<FanSystemInteractionOutput> fanSystemInteractionOutputList = new ArrayList<>();
        cnt = 0;
        for (FanSystemInteractionInput fanSystemInteractionInput : input.getFanSystemInteractionInputList()) {
            FanSystemInteractionOutput fanSystemInteractionOutput = new FanSystemInteractionOutput();
            fanSystemInteractionOutput.setO29(fanSystemInteractionInput.getFanSystemInteractionDescription());
            fanSystemInteractionOutput.setO30(fanSystemInteractionInput.getCi());
            if (uu == 1)
                fanSystemInteractionOutput.setO31(sec_pd_iList.get(cnt));
            if (uu == 2)
                fanSystemInteractionOutput.setO31(sec_pd_iList.get(cnt) * MULTIPLICATION_CONSTANT);
            fanSystemInteractionOutputList.add(fanSystemInteractionOutput);
            ++cnt;
        }
        output.setFanSystemInteractionOutputList(fanSystemInteractionOutputList);
        if (uu == 1)
            output.setO45(tot_pd_i);
        if (uu == 2)
            output.setO45(tot_pd_i * MULTIPLICATION_CONSTANT);
        if (uu == 1)
            output.setO32(tot_pd_sd);
        if (uu == 2)
            output.setO32(tot_pd_sd * MULTIPLICATION_CONSTANT);
        if (uu == 1)
            output.setO33(tot_pd_f);
        if (uu == 2)
            output.setO33(tot_pd_f * MULTIPLICATION_CONSTANT);
        if (uu == 1)
            output.setO34(tot_pd_d);
        if (uu == 2)
            output.setO34(tot_pd_d * MULTIPLICATION_CONSTANT);
        if (uu == 1)
            output.setO35(tot_pd_e);
        if (uu == 2)
            output.setO35(tot_pd_e * MULTIPLICATION_CONSTANT);
        if (uu == 1)
            output.setO36(tot_pd_c);
        if (uu == 2)
            output.setO36(tot_pd_c * MULTIPLICATION_CONSTANT);
        output.setO37(output.getO32() + output.getO33() + output.getO34() + output.getO35() + output.getO36());
        List<AirTerminalOutput> airTerminalOutputList = new ArrayList<>();
        for (AirTerminalInput airTerminalInput : input.getAirTerminalInputList()) {
            AirTerminalOutput airTerminalOutput = new AirTerminalOutput();
            airTerminalOutput.setO38(airTerminalInput.getAirTerminalDescription());
            airTerminalOutput.setO39(airTerminalInput.getModel());
            airTerminalOutput.setO40(airTerminalInput.getTerminalRateInput());
            airTerminalOutput.setO41(airTerminalInput.getTerminalPressureDropInput());
            airTerminalOutputList.add(airTerminalOutput);
        }
        output.setAirTerminalOutputList(airTerminalOutputList);
        if (uu == 1)
            output.setO42(tot_pd_at);
        if (uu == 2)
            output.setO42(tot_pd_at * MULTIPLICATION_CONSTANT);
        output.setO43(input.getFanFlowRateInput());
        if (uu == 1)
            output.setO44(fan_esp);
        if (uu == 2)
            output.setO44(fan_esp * MULTIPLICATION_CONSTANT);
    }

    private void calculateFanEsp(FanEspCalcInput input) {
        fan_esp = tot_pd_sd + tot_pd_f + tot_pd_d + tot_pd_e + tot_pd_i + tot_pd_c + tot_pd_at;
    }

    private void calculatePressureDropInAllAirTerminals(FanEspCalcInput input) {
        for (AirTerminalInput airTerminalInput : input.getAirTerminalInputList()) {
            tot_pd_at = tot_pd_at + airTerminalInput.getPd_at();
        }
    }

    private void calculateDuctSectionsSummary(FanEspCalcInput input) {
        for (int i = 0; i < input.getDuctSectionInputList().size(); ++i) {
            tot_pd_sd = tot_pd_sd + sec_pd_sdList.get(i);
            tot_pd_f = tot_pd_f + sec_pd_fList.get(i);
            tot_pd_d = tot_pd_d + sec_pd_dList.get(i);
            tot_pd_e = tot_pd_e + sec_pd_eList.get(i);
            tot_pd_c = tot_pd_c + sec_pd_cList.get(i);
        }
    }

    private void calculatePressureDropInEachSpecialComponents(FanEspCalcInput input) {
        for (DuctSectionInput ductSectionInput : input.getDuctSectionInputList()) {
            List<Double> pd_c_tInnerList = new ArrayList<>();
            Double sec_pd_c = (double) 0;
            for (SpecialComponentInput specialComponentInput : ductSectionInput.getSpecialComponentInputList()) {
                Double pd_c_t = specialComponentInput.getPd_c() * specialComponentInput.getQc();
                pd_c_tInnerList.add(pd_c_t);
                sec_pd_c = sec_pd_c + pd_c_t;
            }
            pd_c_tList.add(pd_c_tInnerList);
            sec_pd_cList.add(sec_pd_c);
        }
    }

    private void calculatePressureDropInEachFanSystemInteraction(FanEspCalcInput input) {
        if (!isNull(input.getFanSystemInteractionInputList())) {
            for (FanSystemInteractionInput fanSystemInteractionInput : input.getFanSystemInteractionInputList()) {
                int cnt = 0;
                double vFanSystemInteraction = (double) 0;
                for (DuctSectionInput ductSectionInput : input.getDuctSectionInputList()) {
                    if (fanSystemInteractionInput.getDuctSection()
                            .equals(ductSectionInput.getDuctSectionId())) {
                        vFanSystemInteraction = vList.get(cnt);
                        break;
                    }
                    ++cnt;
                }
                double sec_pd_i = equations.fit_pd(vFanSystemInteraction, rho, fanSystemInteractionInput.getCi());
                tot_pd_i += sec_pd_i;
                sec_pd_iList.add(sec_pd_i);
            }
        }
    }

    private void calculatePressureDropInEachDuctMountedEquipment(FanEspCalcInput input) {
        int cnt = 0;
        for (DuctSectionInput ductSectionInput : input.getDuctSectionInputList()) {
            List<Double> pd_d_eInnerList = new ArrayList<>();
            Double sec_pd_e = (double) 0;
            for (DuctMountedEquipmentInput ductMountedEquipmentInput : ductSectionInput.getDuctMountedEquipmentInputList()) {
                Double pd_e = equations.fit_pd(vList.get(cnt), rho, ductMountedEquipmentInput.getCe());
                Double pd_d_e = pd_e * ductMountedEquipmentInput.getQe();
                pd_d_eInnerList.add(pd_d_e);
                sec_pd_e = sec_pd_e + pd_d_e;
            }
            pd_d_eList.add(pd_d_eInnerList);
            sec_pd_eList.add(sec_pd_e);
            ++cnt;
        }
    }

    private void calculatePressureDropInEachDampersAndObstructions(FanEspCalcInput input) {
        int cnt = 0;
        for (DuctSectionInput ductSectionInput : input.getDuctSectionInputList()) {
            List<Double> pd_d_tInnerList = new ArrayList<>();
            Double sec_pd_d = (double) 0;
            for (DampersAndObstructionsInput dampersAndObstructionsInput : ductSectionInput.getDampersAndObstructionsInputList()) {
                Double pd_d = equations.fit_pd(vList.get(cnt), rho, dampersAndObstructionsInput.getCd());
                Double pd_d_t = pd_d * dampersAndObstructionsInput.getQd();
                pd_d_tInnerList.add(pd_d_t);
                sec_pd_d = sec_pd_d + pd_d_t;
            }
            pd_d_tList.add(pd_d_tInnerList);
            sec_pd_dList.add(sec_pd_d);
            ++cnt;
        }
    }

    private void calculatePressureDropInEachFitting(FanEspCalcInput input) {
        int cnt = 0;
        for (DuctSectionInput ductSectionInput : input.getDuctSectionInputList()) {
            List<Double> pd_f_tInnerList = new ArrayList<>();
            Double sec_pd_f = (double) 0;
            for (FittingInput fittingInput : ductSectionInput.getFittingInputList()) {
                Double pd_f = equations.fit_pd(vList.get(cnt), rho, fittingInput.getCf());
                Double pd_f_t = pd_f * fittingInput.getQf();
                pd_f_tInnerList.add(pd_f_t);
                sec_pd_f = sec_pd_f + pd_f_t;
            }
            pd_f_tList.add(pd_f_tInnerList);
            sec_pd_fList.add(sec_pd_f);
            ++cnt;
        }
    }

    private void calculateStraightDuctInEachSection(FanEspCalcInput input) {
        Double eps = input.getEps();
        int cnt = 0;
        for (DuctSectionInput ductSectionInput : input.getDuctSectionInputList()) {
            Double sec_pd_sd = equations.str_duct_pd(rho, vis, dhList.get(cnt), vList.get(cnt),
                    eps, ductSectionInput.getLength());
            sec_pd_sdList.add(sec_pd_sd);
            ++cnt;
        }
    }

    private void calculatePressureDropInEachSection(List<DuctSectionInput> ductSectionInputList) {
        for (DuctSectionInput ductSectionInput : ductSectionInputList) {
            int shp = isNull(ductSectionInput.getShp()) ? 0 : ductSectionInput.getShp();
            double q = isNull(ductSectionInput.getQ()) ? 0 : ductSectionInput.getQ();
            double w = isNull(ductSectionInput.getW()) ? 0 : ductSectionInput.getW();
            double h = isNull(ductSectionInput.getH()) ? 0 : ductSectionInput.getH();
            double d = isNull(ductSectionInput.getD()) ? 0 : ductSectionInput.getD();
            double t = isNull(ductSectionInput.getT()) ? 0 : ductSectionInput.getT();
            Double v = equations.vel(shp, q, w, h, d, t);
            Double dh = equations.hyd_dia(shp, w, h, d, t);
            vList.add(v);
            dhList.add(dh);
        }
    }

    private void calculateDensityAndViscosity(Double temperature) {
        rho = equations.air_den(temperature);
        vis = equations.air_vis(temperature);
    }

    private void clearAllVariables() {
        rho = (double) 0;
        vis = (double) 0;
        vList = new ArrayList<>();
        dhList = new ArrayList<>();
        sec_pd_sdList = new ArrayList<>();
        pd_f_tList = new ArrayList<>();
        sec_pd_fList = new ArrayList<>();
        pd_d_tList = new ArrayList<>();
        sec_pd_dList = new ArrayList<>();
        pd_d_eList = new ArrayList<>();
        sec_pd_eList = new ArrayList<>();
        sec_pd_iList = new ArrayList<>();
        pd_c_tList = new ArrayList<>();
        sec_pd_cList = new ArrayList<>();
        tot_pd_sd = (double) 0;
        tot_pd_f = (double) 0;
        tot_pd_d = (double) 0;
        tot_pd_e = (double) 0;
        tot_pd_c = (double) 0;
        tot_pd_at = (double) 0;
        fan_esp = (double) 0;
        tot_pd_i = (double) 0;
    }
}
