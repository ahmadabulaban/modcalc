package com.insanwalat.modcalc.fanesp.mapper;

import com.insanwalat.modcalc.fanesp.module.*;
import com.insanwalat.modcalc.fanesp.module.input.*;
import com.insanwalat.modcalc.fanesp.module.lookup.FanEspCoefficientDataLookup;
import com.insanwalat.modcalc.fanesp.module.lookup.FanEspCoefficientLookup;
import com.insanwalat.modcalc.fanesp.module.lookup.FanEspLookup;
import com.insanwalat.modcalc.fanesp.module.output.*;
import com.insanwalat.modcalc.fanesp.module.request.FanEspCalcRequest;
import com.insanwalat.modcalc.fanesp.module.request.FanEspSaveRequest;
import com.insanwalat.modcalc.fanesp.module.response.*;
import com.insanwalat.modcalc.fanesp.utils.FanEspLookupsParser;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.isNull;

@Component
public class FanEspMapper {

    @Autowired
    private FanEspLookupsParser fanEspLookupsParser;

    private FanEspCalcResponse response;

    public FanEspCalcInput mapRequestToInput(FanEspCalcRequest request) {
        FanEspCalcInput input = new FanEspCalcInput();
        mapPumpInformation(input, request);
        mapUnits(input, request);
        mapAirTemperature(input, request);
        mapDuctType(input, request);
        mapDuctSections(input, request);
        mapSystemInteraction(input, request);
        mapAirTerminal(input, request);
        mapFanFlowRate(input, request);
        return input;
    }

    private void mapFanFlowRate(FanEspCalcInput input, FanEspCalcRequest request) {
        Double fanRateInput = request.getFanRate().getFanRateInput();
        input.setFanFlowRateInput(fanRateInput);
        input.setQ(fanRateInput * request.getUnits().getUf());
    }

    private void mapAirTerminal(FanEspCalcInput input, FanEspCalcRequest request) {
        List<AirTerminalInput> airTerminalInputList = new ArrayList<>();
        if (!isNull(request.getAirTerminalList())) {
            for (AirTerminal airTerminal : request.getAirTerminalList()) {
                AirTerminalInput airTerminalInput = new AirTerminalInput();
                airTerminalInput.setAirTerminalDescription(airTerminal.getAirTerminalDescription());
                airTerminalInput.setModel(airTerminal.getModel());
                airTerminalInput.setTerminalRateInput(airTerminal.getTerminalRateInput());
                airTerminalInput.setTerminalRateUnit(input.getFlowRateUnit());
                Integer uu = request.getUnits().getUu();
                Double terminalPressureDropInput = airTerminal.getTerminalPressureDropInput();
                airTerminalInput.setTerminalPressureDropInput(terminalPressureDropInput);
                airTerminalInput.setTerminalPressureUnit(input.getFlowRateUnit());
                if (uu == 1)
                    airTerminalInput.setPd_at(terminalPressureDropInput);
                if (uu == 2)
                    airTerminalInput.setPd_at(terminalPressureDropInput * 249.174);
                airTerminalInputList.add(airTerminalInput);
            }
        }
        input.setAirTerminalInputList(airTerminalInputList);
    }

    private void mapSystemInteraction(FanEspCalcInput input, FanEspCalcRequest request) {
        List<FanSystemInteractionInput> fanSystemInteractionInputList = new ArrayList<>();
        if (!isNull(request.getFanSystemInteractionList())) {
            for (FanSystemInteraction fanSystemInteraction : request.getFanSystemInteractionList()) {
                FanSystemInteractionInput fanSystemInteractionInput = new FanSystemInteractionInput();
                fanSystemInteractionInput.setDuctSection(fanSystemInteraction.getDuctSection());
                fanSystemInteractionInput.setFanSystemInteractionCriteria(fanSystemInteraction.getFanSystemInteractionCriteria());
                fanSystemInteractionInput.setFanSystemInteractionDescription(fanSystemInteraction.getFanSystemInteractionDescription());
                fanSystemInteractionInput.setCi(fanSystemInteraction.getCi());
                fanSystemInteractionInputList.add(fanSystemInteractionInput);
            }
        }
        input.setFanSystemInteractionInputList(fanSystemInteractionInputList);
    }

    private void mapDuctSections(FanEspCalcInput input, FanEspCalcRequest request) {
        List<DuctSectionInput> ductSectionInputList = new ArrayList<>();
        for (DuctSection ductSection : request.getDuctSectionList()) {
            DuctSectionInput ductSectionInput = new DuctSectionInput();
            ductSectionInput.setDuctSectionId(ductSection.getDuctSectionId());
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
                    ductSectionInput.setD(ductDiameterInput * 25.4);
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
                    ductSectionInput.setW(ductWidthInput * 25.4);
                    ductSectionInput.setH(ductHeightInput * 25.4);
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
                ductSectionInput.setT(ductThicknessInput * 25.4);
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
        specialComponentInput.setComponentPressureDropInput(specialComponent.getComponentPressureDropInput());
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
        ductMountedEquipmentInput.setDuctMountedEquipmentCriteria(ductMountedEquipment.getDuctMountedEquipmentCriteria());
        ductMountedEquipmentInput.setDuctMountedEquipmentDescription(ductMountedEquipment.getDuctMountedEquipmentDescription());
        ductMountedEquipmentInput.setCe(ductMountedEquipment.getCe());
        ductMountedEquipmentInput.setQe(ductMountedEquipment.getQe());
        return ductMountedEquipmentInput;
    }

    private DampersAndObstructionsInput mapDampersAndObstructions(DampersAndObstructions dampersAndObstructions) {
        DampersAndObstructionsInput dampersAndObstructionsInput = new DampersAndObstructionsInput();
        dampersAndObstructionsInput.setDampersAndObstructionsSizingCriteria(dampersAndObstructions.getDampersAndObstructionsSizingCriteria());
        dampersAndObstructionsInput.setDampersAndObstructionsDescription(dampersAndObstructions.getDampersAndObstructionsDescription());
        dampersAndObstructionsInput.setCd(dampersAndObstructions.getCd());
        dampersAndObstructionsInput.setQd(dampersAndObstructions.getQd());
        return dampersAndObstructionsInput;
    }

    private FittingInput mapFitting(Fitting fitting) {
        FittingInput fittingInput = new FittingInput();
        fittingInput.setFittingSizingCriteria(fitting.getFittingSizingCriteria());
        fittingInput.setCat(fitting.getCat());
        fittingInput.setFittingDescription(fitting.getFittingDescription());
        fittingInput.setCf(fitting.getCf());
        fittingInput.setQf(fitting.getQf());
        return fittingInput;
    }

    private void mapDuctType(FanEspCalcInput input, FanEspCalcRequest request) {
        input.setEps(request.getDuctType().getEps());
    }

    private void mapAirTemperature(FanEspCalcInput input, FanEspCalcRequest request) {
        Integer uu = request.getUnits().getUu();
        Double temperatureInput = request.getAirTemperature().getTemperatureInput();
        input.setTemperatureInput(temperatureInput);
        if (uu == 1)
            input.setTemperature(temperatureInput);
        if (uu == 2)
            input.setTemperature((temperatureInput - 32) / 1.8);
        String temperatureUnit = fanEspLookupsParser.getDataList().stream()
                .filter(e -> e.getUiField().equals("temperatureUnit") && e.getGroup().equals(uu))
                .findFirst().get().getKey();
        input.setTemperatureUnit(temperatureUnit);
    }

    private void mapUnits(FanEspCalcInput input, FanEspCalcRequest request) {
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

    private void mapPumpInformation(FanEspCalcInput input, FanEspCalcRequest request) {
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
        mapFanSystemInteractionOutputToResponse(output.getFanSystemInteractionOutputList());
        response.setO45(round(output.getO45(), 3));
        response.setO32(round(output.getO32(), 3));
        response.setO33(round(output.getO33(), 3));
        response.setO34(round(output.getO34(), 3));
        response.setO35(round(output.getO35(), 3));
        response.setO36(round(output.getO36(), 3));
        response.setO37(round(output.getO37(), 3));
        mapAirTerminalOutputToResponse(output.getAirTerminalOutputList());
        response.setO42(round(output.getO42(), 3));
        response.setO43(round(output.getO43(), 3));
        response.setO44(round(output.getO44(), 3));
        return response;
    }

    private void mapAirTerminalOutputToResponse(List<AirTerminalOutput> airTerminalOutputList) {
        List<AirTerminalResponse> airTerminalResponseList = new ArrayList<>();
        for (AirTerminalOutput airTerminalOutput : airTerminalOutputList) {
            AirTerminalResponse airTerminalResponse = new AirTerminalResponse();
            airTerminalResponse.setO38(airTerminalOutput.getO38());
            airTerminalResponse.setO39(airTerminalOutput.getO39());
            airTerminalResponse.setO40(round(airTerminalOutput.getO40(), 3));
            airTerminalResponse.setO41(round(airTerminalOutput.getO41(), 3));
            airTerminalResponseList.add(airTerminalResponse);
        }
        response.setAirTerminalResponseList(airTerminalResponseList);
    }

    private void mapFanSystemInteractionOutputToResponse(List<FanSystemInteractionOutput> fanSystemInteractionOutputList) {
        List<FanSystemInteractionResponse> fanSystemInteractionResponseList = new ArrayList<>();
        for (FanSystemInteractionOutput fanSystemInteractionOutput : fanSystemInteractionOutputList) {
            FanSystemInteractionResponse fanSystemInteractionResponse = new FanSystemInteractionResponse();
            fanSystemInteractionResponse.setO29(fanSystemInteractionOutput.getO29());
            fanSystemInteractionResponse.setO30(round(fanSystemInteractionOutput.getO30(), 3));
            fanSystemInteractionResponse.setO31(round(fanSystemInteractionOutput.getO31(), 3));
            fanSystemInteractionResponseList.add(fanSystemInteractionResponse);
        }
        response.setFanSystemInteractionResponseList(fanSystemInteractionResponseList);
    }

    private void mapDuctSectionOutputToResponse(List<DuctSectionOutput> ductSectionOutputList) {
        List<DuctSectionResponse> ductSectionResponseList = new ArrayList<>();
        for (DuctSectionOutput ductSectionOutput : ductSectionOutputList) {
            DuctSectionResponse ductSectionResponse = new DuctSectionResponse();
            ductSectionResponse.setO1(ductSectionOutput.getO1());
            ductSectionResponse.setO3(round(ductSectionOutput.getO3(), 3));
            ductSectionResponse.setO4(round(ductSectionOutput.getO4(), 3));
            ductSectionResponse.setO5(round(ductSectionOutput.getO5(), 3));
            ductSectionResponse.setO6(round(ductSectionOutput.getO6(), 3));
            ductSectionResponse.setTx1(ductSectionOutput.getTx1());
            ductSectionResponse.setO7(round(ductSectionOutput.getO7(), 3));
            ductSectionResponse.setTx2(ductSectionOutput.getTx2());
            ductSectionResponse.setO8(round(ductSectionOutput.getO8(), 3));
            ductSectionResponse.setTx3(ductSectionOutput.getTx3());
            ductSectionResponse.setO88(round(ductSectionOutput.getO88(), 3));
            ductSectionResponse.setTx4(ductSectionOutput.getTx4());
            ductSectionResponse.setO9(round(ductSectionOutput.getO9(), 3));
            List<FittingResponse> fittingResponseList = new ArrayList<>();
            for (FittingOutput fittingOutput : ductSectionOutput.getFittingOutputList()) {
                FittingResponse fittingResponse = new FittingResponse();
                fittingResponse.setO10(fittingOutput.getO10());
                fittingResponse.setO11(round(fittingOutput.getO11(), 3));
                fittingResponse.setO12(round(fittingOutput.getO12(), 3));
                fittingResponse.setO13(round(fittingOutput.getO13(), 3));
                fittingResponseList.add(fittingResponse);
            }
            ductSectionResponse.setFittingResponseList(fittingResponseList);
            List<DampersAndObstructionsResponse> dampersAndObstructionsResponseList = new ArrayList<>();
            for (DampersAndObstructionsOutput dampersAndObstructionsOutput : ductSectionOutput.getDampersAndObstructionsOutputList()) {
                DampersAndObstructionsResponse dampersAndObstructionsResponse = new DampersAndObstructionsResponse();
                dampersAndObstructionsResponse.setO14(dampersAndObstructionsOutput.getO14());
                dampersAndObstructionsResponse.setO15(round(dampersAndObstructionsOutput.getO15(), 3));
                dampersAndObstructionsResponse.setO16(round(dampersAndObstructionsOutput.getO16(), 3));
                dampersAndObstructionsResponse.setO17(round(dampersAndObstructionsOutput.getO17(), 3));
                dampersAndObstructionsResponseList.add(dampersAndObstructionsResponse);
            }
            ductSectionResponse.setDampersAndObstructionsResponseList(dampersAndObstructionsResponseList);
            List<DuctMountedEquipmentResponse> ductMountedEquipmentResponseList = new ArrayList<>();
            for (DuctMountedEquipmentOutput ductMountedEquipmentOutput : ductSectionOutput.getDuctMountedEquipmentOutputList()) {
                DuctMountedEquipmentResponse ductMountedEquipmentResponse = new DuctMountedEquipmentResponse();
                ductMountedEquipmentResponse.setO18(ductMountedEquipmentOutput.getO18());
                ductMountedEquipmentResponse.setO19(round(ductMountedEquipmentOutput.getO19(), 3));
                ductMountedEquipmentResponse.setO20(round(ductMountedEquipmentOutput.getO20(), 3));
                ductMountedEquipmentResponse.setO21(round(ductMountedEquipmentOutput.getO21(), 3));
                ductMountedEquipmentResponseList.add(ductMountedEquipmentResponse);
            }
            ductSectionResponse.setDuctMountedEquipmentResponseList(ductMountedEquipmentResponseList);
            List<SpecialComponentResponse> specialComponentResponseList = new ArrayList<>();
            for (SpecialComponentOutput specialComponentOutput : ductSectionOutput.getSpecialComponentOutputList()) {
                SpecialComponentResponse specialComponentResponse = new SpecialComponentResponse();
                specialComponentResponse.setO22(specialComponentOutput.getO22());
                specialComponentResponse.setO23(round(specialComponentOutput.getO23(), 3));
                specialComponentResponse.setO24(round(specialComponentOutput.getO24(), 3));
                specialComponentResponseList.add(specialComponentResponse);
            }
            ductSectionResponse.setSpecialComponentResponseList(specialComponentResponseList);
            ductSectionResponse.setO25(round(ductSectionOutput.getO25(), 3));
            ductSectionResponse.setO26(round(ductSectionOutput.getO26(), 3));
            ductSectionResponse.setO27(round(ductSectionOutput.getO27(), 3));
            ductSectionResponse.setO28(round(ductSectionOutput.getO28(), 3));
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

    public FanEspSaveInput mapSaveRequestToSaveInput(FanEspSaveRequest request) {
        FanEspSaveInput fanEspSaveInput = new FanEspSaveInput();
        fanEspSaveInput.setName(request.getName());
        FanEspCalcInput fanEspCalcInput = mapRequestToInputForSave(request.getFanEspCalcRequest());
        fanEspSaveInput.setFanEspCalcInput(fanEspCalcInput);
        return fanEspSaveInput;
    }

    private FanEspCalcInput mapRequestToInputForSave(FanEspCalcRequest request) {
        FanEspCalcInput input = new FanEspCalcInput();
        mapPumpInformationForSave(input, request);
        mapUnitsForSave(input, request);
        mapAirTemperatureForSave(input, request);
        mapDuctTypeForSave(input, request);
        mapDuctSectionsForSave(input, request);
        mapSystemInteractionForSave(input, request);
        mapAirTerminalForSave(input, request);
        mapFanFlowRateForSave(input, request);
        return input;
    }

    private void mapFanFlowRateForSave(FanEspCalcInput input, FanEspCalcRequest request) {
        Double fanRateInput = request.getFanRate().getFanRateInput();
        input.setFanFlowRateInput(fanRateInput);
    }

    private void mapAirTerminalForSave(FanEspCalcInput input, FanEspCalcRequest request) {
        List<AirTerminalInput> airTerminalInputList = new ArrayList<>();
        if (!isNull(request.getAirTerminalList())) {
            for (AirTerminal airTerminal : request.getAirTerminalList()) {
                AirTerminalInput airTerminalInput = new AirTerminalInput();
                airTerminalInput.setAirTerminalDescription(airTerminal.getAirTerminalDescription());
                airTerminalInput.setModel(airTerminal.getModel());
                airTerminalInput.setTerminalRateInput(airTerminal.getTerminalRateInput());
                airTerminalInput.setTerminalRateUnit(input.getFlowRateUnit());
                airTerminalInput.setTerminalPressureDropInput(airTerminal.getTerminalPressureDropInput());
                airTerminalInput.setTerminalPressureUnit(input.getFlowRateUnit());
                airTerminalInputList.add(airTerminalInput);
            }
        }
        input.setAirTerminalInputList(airTerminalInputList);
    }

    private void mapSystemInteractionForSave(FanEspCalcInput input, FanEspCalcRequest request) {
        List<FanSystemInteractionInput> fanSystemInteractionInputList = new ArrayList<>();
        if (!isNull(request.getFanSystemInteractionList())) {
            for (FanSystemInteraction fanSystemInteraction : request.getFanSystemInteractionList()) {
                FanSystemInteractionInput fanSystemInteractionInput = new FanSystemInteractionInput();
                fanSystemInteractionInput.setDuctSection(fanSystemInteraction.getDuctSection());
                fanSystemInteractionInput.setFanSystemInteractionCriteria(fanSystemInteraction.getFanSystemInteractionCriteria());
                fanSystemInteractionInput.setFanSystemInteractionDescription(fanSystemInteraction.getFanSystemInteractionDescription());
                fanSystemInteractionInput.setCi(fanSystemInteraction.getCi());
                fanSystemInteractionInputList.add(fanSystemInteractionInput);
            }
        }
        input.setFanSystemInteractionInputList(fanSystemInteractionInputList);
    }

    private void mapPumpInformationForSave(FanEspCalcInput input, FanEspCalcRequest request) {
        input.setProject(request.getPumpInformation().getProject());
        input.setSystem(request.getPumpInformation().getSystem());
        input.setPumpRef(request.getPumpInformation().getPumpRef());
    }

    private void mapUnitsForSave(FanEspCalcInput input, FanEspCalcRequest request) {
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

    private void mapAirTemperatureForSave(FanEspCalcInput input, FanEspCalcRequest request) {
        Integer uu = request.getUnits().getUu();
        input.setTemperatureInput(request.getAirTemperature().getTemperatureInput());
        String temperatureUnit = fanEspLookupsParser.getDataList().stream()
                .filter(e -> e.getUiField().equals("temperatureUnit") && e.getGroup().equals(uu))
                .findFirst().get().getKey();
        input.setTemperatureUnit(temperatureUnit);
    }

    private void mapDuctTypeForSave(FanEspCalcInput input, FanEspCalcRequest request) {
        input.setEps(request.getDuctType().getEps());
    }

    private void mapDuctSectionsForSave(FanEspCalcInput input, FanEspCalcRequest request) {
        List<DuctSectionInput> ductSectionInputList = new ArrayList<>();
        for (DuctSection ductSection : request.getDuctSectionList()) {
            DuctSectionInput ductSectionInput = new DuctSectionInput();
            ductSectionInput.setDuctSectionId(ductSection.getDuctSectionId());
            ductSectionInput.setFlowRateInput(ductSection.getRateInput());
            ductSectionInput.setRateUnit(input.getFlowRateUnit());
            ductSectionInput.setLengthInput(ductSection.getLengthInput());
            ductSectionInput.setLengthUnit(input.getLengthUnit());
            ductSectionInput.setShp(ductSection.getShp());
            ductSectionInput.setFun(ductSection.getFun());
            ductSectionInput.setDuctDiameterInput(ductSection.getDuctDiameterInput());
            ductSectionInput.setWidthInput(ductSection.getDuctWidthInput());
            ductSectionInput.setHeightInput(ductSection.getDuctHeightInput());
            Integer uu = input.getUu();
            String dimensionUnit = fanEspLookupsParser.getDataList().stream()
                    .filter(e -> e.getUiField().equals("dimensionUnit") && e.getGroup().equals(uu))
                    .findFirst().get().getKey();
            ductSectionInput.setDimensionUnit(dimensionUnit);
            ductSectionInput.setThicknessInput(ductSection.getDuctThicknessInput());
            List<FittingInput> fittingInputList = new ArrayList<>();
            List<Fitting> fittingList = ductSection.getFittingList();
            if (!isNull(fittingList) && !fittingList.isEmpty()) {
                for (Fitting fitting : fittingList) {
                    fittingInputList.add(mapFittingForSave(fitting));
                }
            }
            ductSectionInput.setFittingInputList(fittingInputList);
            List<DampersAndObstructionsInput> dampersAndObstructionsInputList = new ArrayList<>();
            List<DampersAndObstructions> dampersAndObstructionsList = ductSection.getDampersAndObstructionsList();
            if (!isNull(dampersAndObstructionsList) && !dampersAndObstructionsList.isEmpty()) {
                for (DampersAndObstructions dampersAndObstructions : dampersAndObstructionsList) {
                    dampersAndObstructionsInputList.add(mapDampersAndObstructionsForSave(dampersAndObstructions));
                }
            }
            ductSectionInput.setDampersAndObstructionsInputList(dampersAndObstructionsInputList);
            List<DuctMountedEquipmentInput> ductMountedEquipmentInputList = new ArrayList<>();
            List<DuctMountedEquipment> ductMountedEquipmentList = ductSection.getDuctMountedEquipmentList();
            if (!isNull(ductMountedEquipmentList) && !ductMountedEquipmentList.isEmpty()) {
                for (DuctMountedEquipment ductMountedEquipment : ductMountedEquipmentList) {
                    ductMountedEquipmentInputList.add(mapDuctMountedEquipmentForSave(ductMountedEquipment));
                }
            }
            ductSectionInput.setDuctMountedEquipmentInputList(ductMountedEquipmentInputList);
            List<SpecialComponentInput> specialComponentInputList = new ArrayList<>();
            List<SpecialComponent> specialComponentList = ductSection.getSpecialComponentList();
            if (!isNull(specialComponentList) && !specialComponentList.isEmpty()) {
                for (SpecialComponent specialComponent : specialComponentList) {
                    specialComponentInputList.add(mapSpecialComponentForSave(specialComponent, request));
                }
            }
            ductSectionInput.setSpecialComponentInputList(specialComponentInputList);
            ductSectionInputList.add(ductSectionInput);
        }
        input.setDuctSectionInputList(ductSectionInputList);
    }

    private SpecialComponentInput mapSpecialComponentForSave(SpecialComponent specialComponent, FanEspCalcRequest request) {
        SpecialComponentInput specialComponentInput = new SpecialComponentInput();
        specialComponentInput.setSpecialComponentDescription(specialComponent.getSpecialComponentDescription());
        specialComponentInput.setComponentPressureDropInput(specialComponent.getComponentPressureDropInput());
        Integer uu = request.getUnits().getUu();
        String pressureUnit = fanEspLookupsParser.getDataList().stream()
                .filter(e -> e.getUiField().equals("pressureUnit") && e.getGroup().equals(uu))
                .findFirst().get().getKey();
        specialComponentInput.setPressureUnit(pressureUnit);
        specialComponentInput.setQc(specialComponent.getQc());
        return specialComponentInput;
    }

    private DuctMountedEquipmentInput mapDuctMountedEquipmentForSave(DuctMountedEquipment ductMountedEquipment) {
        DuctMountedEquipmentInput ductMountedEquipmentInput = new DuctMountedEquipmentInput();
        ductMountedEquipmentInput.setDuctMountedEquipmentCriteria(ductMountedEquipment.getDuctMountedEquipmentCriteria());
        ductMountedEquipmentInput.setDuctMountedEquipmentDescription(ductMountedEquipment.getDuctMountedEquipmentDescription());
        ductMountedEquipmentInput.setCe(ductMountedEquipment.getCe());
        ductMountedEquipmentInput.setQe(ductMountedEquipment.getQe());
        return ductMountedEquipmentInput;
    }

    private DampersAndObstructionsInput mapDampersAndObstructionsForSave(DampersAndObstructions dampersAndObstructions) {
        DampersAndObstructionsInput dampersAndObstructionsInput = new DampersAndObstructionsInput();
        dampersAndObstructionsInput.setDampersAndObstructionsSizingCriteria(dampersAndObstructions.getDampersAndObstructionsSizingCriteria());
        dampersAndObstructionsInput.setDampersAndObstructionsDescription(dampersAndObstructions.getDampersAndObstructionsDescription());
        dampersAndObstructionsInput.setCd(dampersAndObstructions.getCd());
        dampersAndObstructionsInput.setQd(dampersAndObstructions.getQd());
        return dampersAndObstructionsInput;
    }

    private FittingInput mapFittingForSave(Fitting fitting) {
        FittingInput fittingInput = new FittingInput();
        fittingInput.setFittingSizingCriteria(fitting.getFittingSizingCriteria());
        fittingInput.setCat(fitting.getCat());
        fittingInput.setFittingDescription(fitting.getFittingDescription());
        fittingInput.setCf(fitting.getCf());
        fittingInput.setQf(fitting.getQf());
        return fittingInput;
    }

    public List<FanEspLoadResponse> mapLoadOutputToLoadResponse(List<FanEspLoadOutput> fanEspLoadOutputList) {
        List<FanEspLoadResponse> fanEspLoadResponseList = new ArrayList<>();
        for (FanEspLoadOutput fanEspLoadOutput : fanEspLoadOutputList) {
            FanEspLoadResponse fanEspLoadResponse = new FanEspLoadResponse();
            fanEspLoadResponse.setName(fanEspLoadOutput.getName());
            fanEspLoadResponse.setDate(fanEspLoadOutput.getDate());
            FanEspCalcRequest fanEspCalcRequest = mapCalcInputToCalcRequest(fanEspLoadOutput.getFanEspCalcInput());
            fanEspLoadResponse.setFanEspCalcRequest(fanEspCalcRequest);
            fanEspLoadResponseList.add(fanEspLoadResponse);
        }
        return fanEspLoadResponseList;
    }

    private FanEspCalcRequest mapCalcInputToCalcRequest(FanEspCalcInput fanEspCalcInput) {
        FanEspCalcRequest fanEspCalcRequest = new FanEspCalcRequest();
        PumpInformation pumpInformation = mapPumpInformationInputToPumpInformation(fanEspCalcInput);
        fanEspCalcRequest.setPumpInformation(pumpInformation);
        Units units = mapUnitsInputToUnits(fanEspCalcInput);
        fanEspCalcRequest.setUnits(units);
        AirTemperature airTemperature = mapAirTemperatureInputToAirTemperature(fanEspCalcInput);
        fanEspCalcRequest.setAirTemperature(airTemperature);
        DuctType ductType = mapDuctTypeInputToDuctType(fanEspCalcInput);
        fanEspCalcRequest.setDuctType(ductType);
        List<DuctSection> ductSectionList = new ArrayList<>();
        for (DuctSectionInput ductSectionInput : fanEspCalcInput.getDuctSectionInputList()) {
            DuctSection ductSection = mapDuctSectionInputToDuctSection(ductSectionInput);
            ductSectionList.add(ductSection);
        }
        fanEspCalcRequest.setDuctSectionList(ductSectionList);
        List<FanSystemInteraction> fanSystemInteractionList = new ArrayList<>();
        for (FanSystemInteractionInput fanSystemInteractionInput : fanEspCalcInput.getFanSystemInteractionInputList()) {
            FanSystemInteraction fanSystemInteraction = mapFanSystemInteractionInputToFanSystemInteraction(fanSystemInteractionInput);
            fanSystemInteractionList.add(fanSystemInteraction);
        }
        fanEspCalcRequest.setFanSystemInteractionList(fanSystemInteractionList);
        List<AirTerminal> airTerminalList = new ArrayList<>();
        for (AirTerminalInput airTerminalInput : fanEspCalcInput.getAirTerminalInputList()) {
            AirTerminal airTerminal = mapAirTerminalInputToAirTerminal(airTerminalInput);
            airTerminalList.add(airTerminal);
        }
        fanEspCalcRequest.setAirTerminalList(airTerminalList);
        FanRate fanRate = mapFanRateInputToFanRate(fanEspCalcInput);
        fanEspCalcRequest.setFanRate(fanRate);
        return fanEspCalcRequest;
    }

    private FanRate mapFanRateInputToFanRate(FanEspCalcInput fanEspCalcInput) {
        FanRate fanRate = new FanRate();
        fanRate.setFanRateInput(fanEspCalcInput.getFanFlowRateInput());
        fanRate.setFanRateUnit(fanEspCalcInput.getFlowRateUnit());
        return fanRate;
    }

    private AirTerminal mapAirTerminalInputToAirTerminal(AirTerminalInput airTerminalInput) {
        AirTerminal airTerminal = new AirTerminal();
        airTerminal.setAirTerminalDescription(airTerminalInput.getAirTerminalDescription());
        airTerminal.setModel(airTerminalInput.getModel());
        airTerminal.setTerminalRateInput(airTerminalInput.getTerminalRateInput());
        airTerminal.setTerminalRateUnit(airTerminalInput.getTerminalRateUnit());
        airTerminal.setTerminalPressureDropInput(airTerminalInput.getTerminalPressureDropInput());
        airTerminal.setTerminalPressureUnit(airTerminalInput.getTerminalPressureUnit());
        return airTerminal;
    }

    private FanSystemInteraction mapFanSystemInteractionInputToFanSystemInteraction(FanSystemInteractionInput fanSystemInteractionInput) {
        FanSystemInteraction fanSystemInteraction = new FanSystemInteraction();
        fanSystemInteraction.setDuctSection(fanSystemInteractionInput.getDuctSection());
        fanSystemInteraction.setFanSystemInteractionCriteria(fanSystemInteractionInput.getFanSystemInteractionCriteria());
        fanSystemInteraction.setFanSystemInteractionDescription(fanSystemInteractionInput.getFanSystemInteractionDescription());
        fanSystemInteraction.setCi(fanSystemInteractionInput.getCi());
        return fanSystemInteraction;
    }

    private DuctSection mapDuctSectionInputToDuctSection(DuctSectionInput ductSectionInput) {
        DuctSection ductSection = new DuctSection();
        ductSection.setDuctSectionId(ductSectionInput.getDuctSectionId());
        ductSection.setRateInput(ductSectionInput.getFlowRateInput());
        ductSection.setRateUnit(ductSectionInput.getRateUnit());
        ductSection.setLengthInput(ductSectionInput.getLengthInput());
        ductSection.setLengthUnit(ductSectionInput.getLengthUnit());
        ductSection.setShp(ductSectionInput.getShp());
        ductSection.setFun(ductSectionInput.getFun());
        ductSection.setDuctDiameterInput(ductSectionInput.getDuctDiameterInput());
        ductSection.setDuctDiameterUnit(ductSectionInput.getDimensionUnit());
        ductSection.setDuctWidthInput(ductSectionInput.getWidthInput());
        ductSection.setDuctWidthUnit(ductSectionInput.getDimensionUnit());
        ductSection.setDuctHeightInput(ductSectionInput.getHeightInput());
        ductSection.setDuctHeightUnit(ductSectionInput.getDimensionUnit());
        ductSection.setDuctThicknessInput(ductSectionInput.getThicknessInput());
        ductSection.setDuctThicknessUnit(ductSectionInput.getDimensionUnit());
        List<Fitting> fittingList = new ArrayList<>();
        for (FittingInput fittingInput : ductSectionInput.getFittingInputList()) {
            Fitting fitting = mapFittingInputToFitting(fittingInput);
            fittingList.add(fitting);
        }
        ductSection.setFittingList(fittingList);
        List<DampersAndObstructions> dampersAndObstructionsList = new ArrayList<>();
        for (DampersAndObstructionsInput dampersAndObstructionsInput : ductSectionInput.getDampersAndObstructionsInputList()) {
            DampersAndObstructions dampersAndObstructions = mapDampersAndObstructionsInputToDampersAndObstructions(dampersAndObstructionsInput);
            dampersAndObstructionsList.add(dampersAndObstructions);
        }
        ductSection.setDampersAndObstructionsList(dampersAndObstructionsList);
        List<DuctMountedEquipment> ductMountedEquipmentList = new ArrayList<>();
        for (DuctMountedEquipmentInput ductMountedEquipmentInput : ductSectionInput.getDuctMountedEquipmentInputList()) {
            DuctMountedEquipment ductMountedEquipment = mapDuctMountedEquipmentInputToDuctMountedEquipment(ductMountedEquipmentInput);
            ductMountedEquipmentList.add(ductMountedEquipment);
        }
        ductSection.setDuctMountedEquipmentList(ductMountedEquipmentList);
        List<SpecialComponent> specialComponentList = new ArrayList<>();
        for (SpecialComponentInput specialComponentInput : ductSectionInput.getSpecialComponentInputList()) {
            SpecialComponent specialComponent = mapSpecialComponentInputToSpecialComponent(specialComponentInput);
            specialComponentList.add(specialComponent);
        }
        ductSection.setSpecialComponentList(specialComponentList);
        return ductSection;
    }

    private SpecialComponent mapSpecialComponentInputToSpecialComponent(SpecialComponentInput specialComponentInput) {
        SpecialComponent specialComponent = new SpecialComponent();
        specialComponent.setSpecialComponentDescription(specialComponentInput.getSpecialComponentDescription());
        specialComponent.setComponentPressureDropInput(specialComponentInput.getComponentPressureDropInput());
        specialComponent.setPressureUnit(specialComponentInput.getPressureUnit());
        specialComponent.setQc(specialComponentInput.getQc());
        return specialComponent;
    }

    private DuctMountedEquipment mapDuctMountedEquipmentInputToDuctMountedEquipment(DuctMountedEquipmentInput ductMountedEquipmentInput) {
        DuctMountedEquipment ductMountedEquipment = new DuctMountedEquipment();
        ductMountedEquipment.setDuctMountedEquipmentCriteria(ductMountedEquipmentInput.getDuctMountedEquipmentCriteria());
        ductMountedEquipment.setDuctMountedEquipmentDescription(ductMountedEquipmentInput.getDuctMountedEquipmentDescription());
        ductMountedEquipment.setCe(ductMountedEquipmentInput.getCe());
        ductMountedEquipment.setQe(ductMountedEquipmentInput.getQe());
        return ductMountedEquipment;
    }

    private DampersAndObstructions mapDampersAndObstructionsInputToDampersAndObstructions(DampersAndObstructionsInput dampersAndObstructionsInput) {
        DampersAndObstructions dampersAndObstructions = new DampersAndObstructions();
        dampersAndObstructions.setDampersAndObstructionsSizingCriteria(dampersAndObstructionsInput.getDampersAndObstructionsSizingCriteria());
        dampersAndObstructions.setDampersAndObstructionsDescription(dampersAndObstructionsInput.getDampersAndObstructionsDescription());
        dampersAndObstructions.setCd(dampersAndObstructionsInput.getCd());
        dampersAndObstructions.setQd(dampersAndObstructionsInput.getQd());
        return dampersAndObstructions;
    }

    private Fitting mapFittingInputToFitting(FittingInput fittingInput) {
        Fitting fitting = new Fitting();
        fitting.setFittingSizingCriteria(fittingInput.getFittingSizingCriteria());
        fitting.setCat(fittingInput.getCat());
        fitting.setFittingDescription(fittingInput.getFittingDescription());
        fitting.setCf(fittingInput.getCf());
        fitting.setQf(fittingInput.getQf());
        return fitting;
    }

    private DuctType mapDuctTypeInputToDuctType(FanEspCalcInput fanEspCalcInput) {
        DuctType ductType = new DuctType();
        ductType.setEps(fanEspCalcInput.getEps());
        return ductType;
    }

    private AirTemperature mapAirTemperatureInputToAirTemperature(FanEspCalcInput fanEspCalcInput) {
        AirTemperature airTemperature = new AirTemperature();
        airTemperature.setTemperatureInput(fanEspCalcInput.getTemperatureInput());
        airTemperature.setTemperatureUnit(fanEspCalcInput.getTemperatureUnit());
        return airTemperature;
    }

    private Units mapUnitsInputToUnits(FanEspCalcInput fanEspCalcInput) {
        Units units = new Units();
        units.setUu(fanEspCalcInput.getUu());
        units.setUf(fanEspCalcInput.getUf());
        units.setUl(fanEspCalcInput.getUl());
        return units;
    }

    private PumpInformation mapPumpInformationInputToPumpInformation(FanEspCalcInput fanEspCalcInput) {
        PumpInformation pumpInformation = new PumpInformation();
        pumpInformation.setProject(fanEspCalcInput.getProject());
        pumpInformation.setSystem(fanEspCalcInput.getSystem());
        pumpInformation.setPumpRef(fanEspCalcInput.getPumpRef());
        return pumpInformation;
    }

    private Double round(Double value, Integer places) {
        if (isNull(value)) return null;
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
