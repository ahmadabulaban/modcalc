package com.insanwalat.modcalc.fanesp.mapper;

import com.insanwalat.modcalc.fanesp.module.*;
import com.insanwalat.modcalc.fanesp.module.input.*;
import com.insanwalat.modcalc.fanesp.module.lookup.FanEspCoefficientDataLookup;
import com.insanwalat.modcalc.fanesp.module.lookup.FanEspCoefficientLookup;
import com.insanwalat.modcalc.fanesp.module.lookup.FanEspLookup;
import com.insanwalat.modcalc.fanesp.module.output.*;
import com.insanwalat.modcalc.fanesp.module.request.FanEspCalcRequest;
import com.insanwalat.modcalc.fanesp.module.response.*;
import com.insanwalat.modcalc.fanesp.utils.FanEspLookupsParser;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.isNull;

@Component
public class FanEspMapper {

    @Autowired
    private FanEspLookupsParser fanEspLookupsParser;

    private FanEspCalcInput input;
    private FanEspCalcResponse response;

    public FanEspCalcInput mapRequestToInput(FanEspCalcRequest request) {
        input = new FanEspCalcInput();
        mapPumpInformation(request);
        mapUnits(request);
        mapAirTemperature(request);
        mapDuctType(request);
        mapDuctSections(request);
        mapSystemInteraction(request);
        mapAirTerminal(request);
        mapFanFlowRate(request);
        return input;
    }

    private void mapFanFlowRate(FanEspCalcRequest request) {
        Double fanRateInput = request.getFanRate().getFanRateInput();
        input.setFanFlowRateInput(fanRateInput);
        input.setQ(fanRateInput * request.getUnits().getUf());
    }

    private void mapAirTerminal(FanEspCalcRequest request) {
        List<AirTerminalInput> airTerminalInputList = new ArrayList<>();
        for (AirTerminal airTerminal : request.getAirTerminalList()) {
            AirTerminalInput airTerminalInput = new AirTerminalInput();
            airTerminalInput.setAirTerminalDescription(airTerminal.getAirTerminalDescription());
            airTerminalInput.setModel(airTerminal.getModel());
            airTerminalInput.setTerminalRateInput(airTerminal.getTerminalRateInput());
            Integer uu = request.getUnits().getUu();
            Double terminalPressureDropInput = airTerminal.getTerminalPressureDropInput();
            airTerminalInput.setTerminalPressureDropInput(terminalPressureDropInput);
            if (uu == 1)
                airTerminalInput.setPd_at(terminalPressureDropInput);
            if (uu == 2)
                airTerminalInput.setPd_at(terminalPressureDropInput * 249.174);
            airTerminalInputList.add(airTerminalInput);
        }
        input.setAirTerminalInputList(airTerminalInputList);
    }

    private void mapSystemInteraction(FanEspCalcRequest request) {
        if (!isNull(request.getFanSystemInteraction())) {
            input.setDuctSection(request.getFanSystemInteraction().getDuctSection());
            input.setFanSystemInteractionDescription(request.getFanSystemInteraction().getFanSystemInteractionDescription());
            input.setCi(request.getFanSystemInteraction().getCi());
        }
    }

    private void mapDuctSections(FanEspCalcRequest request) {
        List<DuctSectionInput> ductSectionInputList = new ArrayList<>();
        for (DuctSection ductSection : request.getDuctSectionList()) {
            DuctSectionInput ductSectionInput = new DuctSectionInput();
            ductSectionInput.setStartPoint(ductSection.getStartPoint());
            ductSectionInput.setEndPoint(ductSection.getEndPoint());
            Double rateInput = ductSection.getRateInput();
            ductSectionInput.setFlowRateInput(rateInput);
            ductSectionInput.setQ(rateInput * request.getUnits().getUf());
            ductSectionInput.setRateUnit(input.getFlowRateUnit());
            Double lengthInput = ductSection.getLengthInput();
            ductSectionInput.setLengthInput(lengthInput);
            ductSectionInput.setLength(lengthInput * request.getUnits().getUl());
            ductSectionInput.setLengthUnit(input.getLengthUnit());
            Integer shp = ductSection.getShp();
            ductSectionInput.setShp(shp);
            ductSectionInput.setFun(ductSection.getFun());
            Integer uu = request.getUnits().getUu();
            if (shp == 2) {
                Double ductDiameterInput = ductSection.getDuctDiameterInput();
                ductSectionInput.setDuctDiameterInput(ductDiameterInput);
                if (uu == 1)
                    ductSectionInput.setD(ductDiameterInput);
                if (uu == 2)
                    ductSectionInput.setD(ductDiameterInput / 25.4);
            }
            if (shp == 1) {
                Double ductWidthInput = ductSection.getDuctWidthInput();
                Double ductHeightInput = ductSection.getDuctHeightInput();
                ductSectionInput.setWidthInput(ductWidthInput);
                ductSectionInput.setHeightInput(ductHeightInput);
                if (uu == 1) {
                    ductSectionInput.setW(ductWidthInput);
                    ductSectionInput.setH(ductHeightInput);
                }
                if (uu == 2) {
                    ductSectionInput.setW(ductWidthInput / 25.4);
                    ductSectionInput.setH(ductHeightInput / 25.4);
                }
            }
            String dimensionUnit = fanEspLookupsParser.getDataList().stream()
                    .filter(e -> e.getUiField().equals("dimensionUnit") && e.getGroup().equals(uu))
                    .findFirst().get().getKey();
            ductSectionInput.setDimensionUnit(dimensionUnit);
            Double ductThicknessInput = ductSection.getDuctThicknessInput();
            ductSectionInput.setThicknessInput(ductThicknessInput);
            if (uu == 1)
                ductSectionInput.setT(ductThicknessInput);
            if (uu == 2)
                ductSectionInput.setD(ductThicknessInput / 25.4);
            List<FittingInput> fittingInputList = new ArrayList<>();
            List<Fitting> fittingList = ductSection.getFittingList();
            if (!isNull(fittingList) && !fittingList.isEmpty()) {
                for (Fitting fitting : fittingList) {
                    fittingInputList.add(mapFitting(fitting));
                }
            }
            ductSectionInput.setFittingInputList(fittingInputList);
            List<DampersAndObstructionsInput> dampersAndObstructionsInputList = new ArrayList<>();
            List<DampersAndObstructions> dampersAndObstructionsList = ductSection.getDampersAndObstructionsList();
            if (!isNull(dampersAndObstructionsList) && !dampersAndObstructionsList.isEmpty()) {
                for (DampersAndObstructions dampersAndObstructions : dampersAndObstructionsList) {
                    dampersAndObstructionsInputList.add(mapDampersAndObstructions(dampersAndObstructions));
                }
            }
            ductSectionInput.setDampersAndObstructionsInputList(dampersAndObstructionsInputList);
            List<DuctMountedEquipmentInput> ductMountedEquipmentInputList = new ArrayList<>();
            List<DuctMountedEquipment> ductMountedEquipmentList = ductSection.getDuctMountedEquipmentList();
            if (!isNull(ductMountedEquipmentList) && !ductMountedEquipmentList.isEmpty()) {
                for (DuctMountedEquipment ductMountedEquipment : ductMountedEquipmentList) {
                    ductMountedEquipmentInputList.add(mapDuctMountedEquipment(ductMountedEquipment));
                }
            }
            ductSectionInput.setDuctMountedEquipmentInputList(ductMountedEquipmentInputList);
            List<SpecialComponentInput> specialComponentInputList = new ArrayList<>();
            List<SpecialComponent> specialComponentList = ductSection.getSpecialComponentList();
            if (!isNull(specialComponentList) && !specialComponentList.isEmpty()) {
                for (SpecialComponent specialComponent : specialComponentList) {
                    specialComponentInputList.add(mapSpecialComponent(specialComponent, request));
                }
            }
            ductSectionInput.setSpecialComponentInputList(specialComponentInputList);
            ductSectionInputList.add(ductSectionInput);
        }
        input.setDuctSectionInputList(ductSectionInputList);
    }

    private SpecialComponentInput mapSpecialComponent(SpecialComponent specialComponent, FanEspCalcRequest request) {
        SpecialComponentInput specialComponentInput = new SpecialComponentInput();
        specialComponentInput.setSpecialComponentDescription(specialComponent.getSpecialComponentDescription());
        Integer uu = request.getUnits().getUu();
        if (uu == 1)
            specialComponentInput.setPd_c(specialComponent.getComponentPressureDropInput());
        if (uu == 2)
            specialComponentInput.setPd_c(specialComponent.getComponentPressureDropInput() * 249.08891);
        String pressureUnit = fanEspLookupsParser.getDataList().stream()
                .filter(e -> e.getUiField().equals("pressureUnit") && e.getGroup().equals(uu))
                .findFirst().get().getKey();
        specialComponentInput.setPressureUnit(pressureUnit);
        specialComponentInput.setQc(specialComponent.getQc());
        return specialComponentInput;
    }

    private DuctMountedEquipmentInput mapDuctMountedEquipment(DuctMountedEquipment ductMountedEquipment) {
        DuctMountedEquipmentInput ductMountedEquipmentInput = new DuctMountedEquipmentInput();
        ductMountedEquipmentInput.setDuctMountedEquipmentDescription(ductMountedEquipment.getDuctMountedEquipmentDescription());
        ductMountedEquipmentInput.setCe(ductMountedEquipment.getCe());
        ductMountedEquipmentInput.setQe(ductMountedEquipment.getQe());
        return ductMountedEquipmentInput;
    }

    private DampersAndObstructionsInput mapDampersAndObstructions(DampersAndObstructions dampersAndObstructions) {
        DampersAndObstructionsInput dampersAndObstructionsInput = new DampersAndObstructionsInput();
        dampersAndObstructionsInput.setDampersAndObstructionsDescription(dampersAndObstructions.getDampersAndObstructionsDescription());
        dampersAndObstructionsInput.setCd(dampersAndObstructions.getCd());
        dampersAndObstructionsInput.setQd(dampersAndObstructions.getQd());
        return dampersAndObstructionsInput;
    }

    private FittingInput mapFitting(Fitting fitting) {
        FittingInput fittingInput = new FittingInput();
        fittingInput.setCat(fitting.getCat());
        fittingInput.setFittingDescription(fitting.getFittingDescription());
        fittingInput.setCf(fitting.getCf());
        fittingInput.setQf(fitting.getQf());
        return fittingInput;
    }

    private void mapDuctType(FanEspCalcRequest request) {
        input.setEps(request.getDuctType().getEps());
    }

    private void mapAirTemperature(FanEspCalcRequest request) {
        Integer uu = request.getUnits().getUu();
        Double temperatureInput = request.getAirTemperature().getTemperatureInput();
        if (uu == 1)
            input.setTemperature(temperatureInput);
        if (uu == 2)
            input.setTemperature((temperatureInput - 32) / 1.8);
        String temperatureUnit = fanEspLookupsParser.getDataList().stream()
                .filter(e -> e.getUiField().equals("temperatureUnit") && e.getGroup().equals(uu))
                .findFirst().get().getKey();
        input.setTemperatureUnit(temperatureUnit);
    }

    private void mapUnits(FanEspCalcRequest request) {
        Integer uu = request.getUnits().getUu();
        input.setUu(uu);
        String unitSystem = fanEspLookupsParser.getDataList().stream()
                .filter(e -> e.getUiField().equals("uu")
                        && e.getValue().equals(uu.doubleValue())).findFirst().get().getKey();
        input.setUnitSystem(unitSystem);
        Double uf = request.getUnits().getUf();
        input.setUf(uf);
        String flowRateUnit = fanEspLookupsParser.getDataList().stream()
                .filter(e -> e.getUiField().equals("uf")
                        && e.getValue().equals(uf)).findFirst().get().getKey();
        input.setFlowRateUnit(flowRateUnit);
        Double ul = request.getUnits().getUl();
        input.setUl(ul);
        String lengthUnit = fanEspLookupsParser.getDataList().stream()
                .filter(e -> e.getUiField().equals("ul")
                        && e.getValue().equals(ul)).findFirst().get().getKey();
        input.setLengthUnit(lengthUnit);
    }

    private void mapPumpInformation(FanEspCalcRequest request) {
        input.setProject(request.getPumpInformation().getProject());
        input.setSystem(request.getPumpInformation().getSystem());
        input.setPumpRef(request.getPumpInformation().getPumpRef());
    }

    public FanEspCalcResponse mapOutputToResponse(FanEspCalcOutput output) {
        response = new FanEspCalcResponse();
        response.setProject(output.getProject());
        response.setSystem(output.getSystem());
        response.setPumpRef(output.getPumpRef());
        response.setDate(output.getDate());
        response.setTx5(output.getTx5());
        mapDuctSectionOutputToResponse(output.getDuctSectionOutputList());
        response.setO29(output.getO29());
        response.setO30(output.getO30());
        response.setO31(output.getO31());
        response.setO32(output.getO32());
        response.setO33(output.getO33());
        response.setO34(output.getO34());
        response.setO35(output.getO35());
        response.setO36(output.getO36());
        response.setO37(output.getO37());
        mapAirTerminalOutputToResponse(output.getAirTerminalOutputList());
        response.setO42(output.getO42());
        response.setO43(output.getO43());
        response.setO44(output.getO44());
        return response;
    }

    private void mapAirTerminalOutputToResponse(List<AirTerminalOutput> airTerminalOutputList) {
        List<AirTerminalResponse> airTerminalResponseList = new ArrayList<>();
        for (AirTerminalOutput airTerminalOutput : airTerminalOutputList) {
            AirTerminalResponse airTerminalResponse = new AirTerminalResponse();
            airTerminalResponse.setO38(airTerminalOutput.getO38());
            airTerminalResponse.setO39(airTerminalOutput.getO39());
            airTerminalResponse.setO40(airTerminalOutput.getO40());
            airTerminalResponse.setO41(airTerminalOutput.getO41());
            airTerminalResponseList.add(airTerminalResponse);
        }
        response.setAirTerminalResponseList(airTerminalResponseList);
    }

    private void mapDuctSectionOutputToResponse(List<DuctSectionOutput> ductSectionOutputList) {
        List<DuctSectionResponse> ductSectionResponseList = new ArrayList<>();
        for (DuctSectionOutput ductSectionOutput : ductSectionOutputList) {
            DuctSectionResponse ductSectionResponse = new DuctSectionResponse();
            ductSectionResponse.setO1(ductSectionOutput.getO1());
            ductSectionResponse.setO2(ductSectionOutput.getO2());
            ductSectionResponse.setO3(ductSectionOutput.getO3());
            ductSectionResponse.setO4(ductSectionOutput.getO4());
            ductSectionResponse.setO5(ductSectionOutput.getO5());
            ductSectionResponse.setO6(ductSectionOutput.getO6());
            ductSectionResponse.setTx1(ductSectionOutput.getTx1());
            ductSectionResponse.setO7(ductSectionOutput.getO7());
            ductSectionResponse.setTx2(ductSectionOutput.getTx2());
            ductSectionResponse.setO8(ductSectionOutput.getO8());
            ductSectionResponse.setTx3(ductSectionOutput.getTx3());
            ductSectionResponse.setO88(ductSectionOutput.getO88());
            ductSectionResponse.setTx4(ductSectionOutput.getTx4());
            ductSectionResponse.setO9(ductSectionOutput.getO9());
            List<FittingResponse> fittingResponseList = new ArrayList<>();
            for (FittingOutput fittingOutput : ductSectionOutput.getFittingOutputList()) {
                FittingResponse fittingResponse = new FittingResponse();
                fittingResponse.setO10(fittingOutput.getO10());
                fittingResponse.setO11(fittingOutput.getO11());
                fittingResponse.setO12(fittingOutput.getO12());
                fittingResponse.setO13(fittingOutput.getO13());
                fittingResponseList.add(fittingResponse);
            }
            ductSectionResponse.setFittingResponseList(fittingResponseList);
            List<DampersAndObstructionsResponse> dampersAndObstructionsResponseList = new ArrayList<>();
            for (DampersAndObstructionsOutput dampersAndObstructionsOutput : ductSectionOutput.getDampersAndObstructionsOutputList()) {
                DampersAndObstructionsResponse dampersAndObstructionsResponse = new DampersAndObstructionsResponse();
                dampersAndObstructionsResponse.setO14(dampersAndObstructionsOutput.getO14());
                dampersAndObstructionsResponse.setO15(dampersAndObstructionsOutput.getO15());
                dampersAndObstructionsResponse.setO16(dampersAndObstructionsOutput.getO16());
                dampersAndObstructionsResponse.setO17(dampersAndObstructionsOutput.getO17());
                dampersAndObstructionsResponseList.add(dampersAndObstructionsResponse);
            }
            ductSectionResponse.setDampersAndObstructionsResponseList(dampersAndObstructionsResponseList);
            List<DuctMountedEquipmentResponse> ductMountedEquipmentResponseList = new ArrayList<>();
            for (DuctMountedEquipmentOutput ductMountedEquipmentOutput : ductSectionOutput.getDuctMountedEquipmentOutputList()) {
                DuctMountedEquipmentResponse ductMountedEquipmentResponse = new DuctMountedEquipmentResponse();
                ductMountedEquipmentResponse.setO18(ductMountedEquipmentOutput.getO18());
                ductMountedEquipmentResponse.setO19(ductMountedEquipmentOutput.getO19());
                ductMountedEquipmentResponse.setO20(ductMountedEquipmentOutput.getO20());
                ductMountedEquipmentResponse.setO21(ductMountedEquipmentOutput.getO21());
                ductMountedEquipmentResponseList.add(ductMountedEquipmentResponse);
            }
            ductSectionResponse.setDuctMountedEquipmentResponseList(ductMountedEquipmentResponseList);
            List<SpecialComponentResponse> specialComponentResponseList = new ArrayList<>();
            for (SpecialComponentOutput specialComponentOutput : ductSectionOutput.getSpecialComponentOutputList()) {
                SpecialComponentResponse specialComponentResponse = new SpecialComponentResponse();
                specialComponentResponse.setO22(specialComponentOutput.getO22());
                specialComponentResponse.setO23(specialComponentOutput.getO23());
                specialComponentResponse.setO24(specialComponentOutput.getO24());
                specialComponentResponseList.add(specialComponentResponse);
            }
            ductSectionResponse.setSpecialComponentResponseList(specialComponentResponseList);
            ductSectionResponse.setO25(ductSectionOutput.getO25());
            ductSectionResponse.setO26(ductSectionOutput.getO26());
            ductSectionResponse.setO27(ductSectionOutput.getO27());
            ductSectionResponse.setO28(ductSectionOutput.getO28());
            ductSectionResponseList.add(ductSectionResponse);
        }
        response.setDuctSectionResponseList(ductSectionResponseList);
    }

    public FanEspLookupResponse mapLookupToLookupResponse(FanEspLookup fanEspLookup) {
        return new FanEspLookupResponse(fanEspLookup.getUiField(),
                fanEspLookup.getKey(),
                fanEspLookup.getValue(),
                fanEspLookup.getDefaultOption(),
                fanEspLookup.getGroup());
    }

    public FanEspCoefficientLookupResponse mapCoefficientLookupToCoefficientLookupResponse(FanEspCoefficientLookup fanEspCoefficientLookup) {
        String[] fixedHeaderHeightString = fanEspCoefficientLookup.getFixedHeaderHeight().split(",");
        String[] fixedBodyHeightString = fanEspCoefficientLookup.getFixedBodyHeight().split(",");
        Integer[] fixedHeaderHeight = ArrayUtils.toObject(Arrays.stream(fixedHeaderHeightString).mapToInt(Integer::valueOf).toArray());
        Integer[] fixedBodyHeight = ArrayUtils.toObject(Arrays.stream(fixedBodyHeightString).mapToInt(Integer::valueOf).toArray());
        return new FanEspCoefficientLookupResponse(fanEspCoefficientLookup.getName(),
                fanEspCoefficientLookup.getDocumentRelated(),
                fanEspCoefficientLookup.getTypeName(),
                fanEspCoefficientLookup.getDocument(),
                fanEspCoefficientLookup.getHeight(),
                fanEspCoefficientLookup.getWidth(),
                fanEspCoefficientLookup.getImage(),
                fanEspCoefficientLookup.getTableSource(),
                fixedHeaderHeight,
                fixedBodyHeight);
    }

    public FanEspCoefficientDataLookupResponse mapCoefficientDataLookupToCoefficientDataLookupResponse(FanEspCoefficientDataLookup fanEspCoefficientDataLookup) {
        String name = fanEspCoefficientDataLookup.getName();
        String[][] originalTable = fanEspCoefficientDataLookup.getTable();
        FanEspCoefficientLookup fanEspCoefficientLookup = fanEspLookupsParser.getDataCoefficientList().stream().filter(e -> e.getName().equals(name)).findFirst().get();
        Integer width = fanEspCoefficientLookup.getWidth();
        String[] fixedHeaderHeightString = fanEspCoefficientLookup.getFixedHeaderHeight().split(",");
        String[] fixedBodyHeightString = fanEspCoefficientLookup.getFixedBodyHeight().split(",");
        Integer[] fixedHeaderHeight = ArrayUtils.toObject(Arrays.stream(fixedHeaderHeightString).mapToInt(Integer::valueOf).toArray());
        Integer[] fixedBodyHeight = ArrayUtils.toObject(Arrays.stream(fixedBodyHeightString).mapToInt(Integer::valueOf).toArray());
        List<String[][]> tables = new ArrayList<>();
        int currentRow = 0;
        for (int i = 0; i < fixedHeaderHeight.length; i++) {
            int height = fixedHeaderHeight[i] + fixedBodyHeight[i];
            String[][] table = new String[height][width];
            for (int j = currentRow, h = 0; j < currentRow + height; j++, h++) {
                for (int w = 0; w < width; w++) {
                    table[h][w] = originalTable[j][w];
                }
            }
            tables.add(table);
            currentRow = currentRow + height;
        }
        return new FanEspCoefficientDataLookupResponse(fanEspCoefficientDataLookup.getName()
                , tables);
    }
}
