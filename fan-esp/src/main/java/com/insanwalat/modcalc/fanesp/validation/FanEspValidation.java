package com.insanwalat.modcalc.fanesp.validation;

import com.insanwalat.modcalc.fanesp.exceptions.InvalidFanEspCalcInputException;
import com.insanwalat.modcalc.fanesp.module.*;
import com.insanwalat.modcalc.fanesp.module.lookup.FanEspCoefficientDataLookup;
import com.insanwalat.modcalc.fanesp.module.lookup.FanEspCoefficientLookup;
import com.insanwalat.modcalc.fanesp.module.request.FanEspCalcRequest;
import com.insanwalat.modcalc.fanesp.utils.FanEspLookupsParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class FanEspValidation {

    @Autowired
    private FanEspLookupsParser fanEspLookupsParser;

    public void validateFanEspCalcRequest(FanEspCalcRequest request) {
        validateUu(request);
        validateUf(request);
        validateUl(request);
        validateTemperatureInput(request);
        validateEps(request);
        validateDuctSectionList(request);
        validateStartAndEndPoints(request);
        validateEachDuctSection(request);
        validateFanSystemInteraction(request);
    }

    private void validateFanSystemInteraction(FanEspCalcRequest request) {
        String ductSection = request.getFanSystemInteraction().getDuctSection();
        if (request.getDuctSectionList().stream()
                .noneMatch(e -> ductSection.equals(e.getStartPoint() + ":" + e.getEndPoint())))
            throw new InvalidFanEspCalcInputException("Invalid Fan System Interaction duct section");
        Integer sizingCriteria = request.getFanSystemInteraction().getFanSystemInteractionCriteria();
        if (isNull(sizingCriteria))
            throw new InvalidFanEspCalcInputException("Null Fan System Interaction sizing criteria");
        if (!(sizingCriteria == 1 || sizingCriteria == 2))
            throw new InvalidFanEspCalcInputException("Invalid Fan System Interaction sizing criteria");
        //validateFanSystemInteractionDescriptionAndCi(request);
    }

//    private void validateFanSystemInteractionDescriptionAndCi(FanEspCalcRequest request) {
//
//    }

    private void validateEachDuctSection(FanEspCalcRequest request) {
        List<DuctSection> ductSectionList = request.getDuctSectionList();
        for (DuctSection ductSection : ductSectionList) {
            validateRateInput(request, ductSection);
            validateLengthInput(request, ductSection);
            validateShp(ductSection);
            validateFun(ductSection);
            validateDuctDiameterInput(request, ductSection);
            validateDuctWidthInput(request, ductSection);
            validateDuctHeightInput(request, ductSection);
            validateDuctThicknessInput(request, ductSection);
            List<Fitting> fittingList = ductSection.getFittingList();
            if (!isNull(fittingList) && !fittingList.isEmpty()) {
                for (Fitting fitting : fittingList) {
                    validateFittingSizingCriteria(fitting);
                    validateCat(fitting, ductSection);
                    validateFittingDescriptionAndCf(fitting, ductSection);
                }
            }
            List<DampersAndObstructions> dampersAndObstructionsList = ductSection.getDampersAndObstructionsList();
            if (!isNull(dampersAndObstructionsList) && !dampersAndObstructionsList.isEmpty()) {
                for (DampersAndObstructions dampersAndObstructions : dampersAndObstructionsList) {
                    validateDampersAndObstructionsSizingCriteria(dampersAndObstructions);
                    //validateDampersAndObstructionsDescriptionAndCd(dampersAndObstructions);
                }
            }
            List<DuctMountedEquipment> ductMountedEquipmentList = ductSection.getDuctMountedEquipmentList();
            if (!isNull(ductMountedEquipmentList) && !ductMountedEquipmentList.isEmpty()) {
                for (DuctMountedEquipment ductMountedEquipment : ductMountedEquipmentList) {
                    validateDuctMountedEquipmentSizingCriteria(ductMountedEquipment);
                    //validateDuctMountedEquipmentDescriptionAndCe(ductMountedEquipment);
                }
            }
        }
    }

//    private void validateDuctMountedEquipmentDescriptionAndCe(DuctMountedEquipment ductMountedEquipment) {
//
//    }

    private void validateDuctMountedEquipmentSizingCriteria(DuctMountedEquipment ductMountedEquipment) {
        Integer sizingCriteria = ductMountedEquipment.getDuctMountedEquipmentCriteria();
        if (isNull(sizingCriteria))
            throw new InvalidFanEspCalcInputException("Null Dampers And Obstructions sizing criteria");
        if (!(sizingCriteria == 1 || sizingCriteria == 2))
            throw new InvalidFanEspCalcInputException("Invalid Dampers And Obstructions sizing criteria");

    }

//    private void validateDampersAndObstructionsDescriptionAndCd(DampersAndObstructions dampersAndObstructions) {
//
//    }

    private void validateDampersAndObstructionsSizingCriteria(DampersAndObstructions dampersAndObstructions) {
        Integer sizingCriteria = dampersAndObstructions.getDampersAndObstructionsSizingCriteria();
        if (isNull(sizingCriteria))
            throw new InvalidFanEspCalcInputException("Null Dampers And Obstructions sizing criteria");
        if (!(sizingCriteria == 1 || sizingCriteria == 2))
            throw new InvalidFanEspCalcInputException("Invalid Dampers And Obstructions sizing criteria");
    }

    private void validateFittingDescriptionAndCf(Fitting fitting, DuctSection ductSection) {
        if (fitting.getFittingSizingCriteria() == 1) {
            String fittingDescription = fitting.getFittingDescription();
            Integer shp = ductSection.getShp();
            Integer fun = ductSection.getFun();
            Integer cat = fitting.getCat();
            if (isNull(fittingDescription))
                throw new InvalidFanEspCalcInputException("Null fitting description");
            Integer group = shp * 100 + fun * 10 + cat;
            List<String> fittingDescriptions = Arrays.asList(fanEspLookupsParser.getDataList().stream()
                    .filter(e -> e.getUiField().equals("fittingDescription")
                            && e.getGroup().equals(group))
                    .map(e -> e.getKey())
                    .findFirst()
                    .get().split(","));
            List<FanEspCoefficientLookup> fanEspCoefficientLookupList = new ArrayList<>();
            for (String description : fittingDescriptions) {
                fanEspCoefficientLookupList.addAll(fanEspLookupsParser
                        .getDataCoefficientList(description));
            }
            if (fanEspCoefficientLookupList.stream().noneMatch(e -> e.getTypeName().equals(fittingDescription)))
                throw new InvalidFanEspCalcInputException("Invalid fitting description");
            FanEspCoefficientLookup fanEspCoefficientLookup = fanEspCoefficientLookupList.stream()
                    .filter(e -> e.getTypeName().equals(fittingDescription))
                    .findFirst().get();
            FanEspCoefficientDataLookup fanEspCoefficientDataLookup =
                    fanEspLookupsParser.getFanEspCoefficientDataLookup(fanEspCoefficientLookup.getTypeName());
            Double cf = fitting.getCf();
            if (!fanEspCoefficientDataLookup.getValues().contains(cf))
                throw new InvalidFanEspCalcInputException("Invalid cf");
        }
    }

    private void validateCat(Fitting fitting, DuctSection ductSection) {
        if (fitting.getFittingSizingCriteria() == 1) {
            Integer cat = fitting.getCat();
            Integer fun = ductSection.getFun();
            if (isNull(cat))
                throw new InvalidFanEspCalcInputException("Null cat value");
            List<Integer> catGroupList = fanEspLookupsParser.getDataList().stream()
                    .filter(e -> e.getUiField().equals("cat") && e.getGroup().equals(fun))
                    .mapToInt(e -> e.getValue().intValue())
                    .boxed().collect(Collectors.toList());
            if (!catGroupList.contains(cat))
                throw new InvalidFanEspCalcInputException("Invalid cat value");
        }
    }

    private void validateFittingSizingCriteria(Fitting fitting) {
        Integer sizingCriteria = fitting.getFittingSizingCriteria();
        if (isNull(sizingCriteria))
            throw new InvalidFanEspCalcInputException("Null fitting sizing criteria");
        if (!(sizingCriteria == 1 || sizingCriteria == 2))
            throw new InvalidFanEspCalcInputException("Invalid fitting sizing criteria");
    }

    private void validateDuctThicknessInput(FanEspCalcRequest request, DuctSection ductSection) {
        Double ductThicknessInput = ductSection.getDuctThicknessInput();
        if (isNull(ductThicknessInput))
            throw new InvalidFanEspCalcInputException("Null duct thickness input value");
        double t;
        if (request.getUnits().getUu() == 1)
            t = ductThicknessInput;
        else
            t = ductThicknessInput / 25.4;
        if (!(t >= 0.1 && t <= 5))
            throw new InvalidFanEspCalcInputException("Invalid duct thickness input value");
    }

    private void validateDuctHeightInput(FanEspCalcRequest request, DuctSection ductSection) {
        if (ductSection.getShp() == 1) {
            Double ductHeightInput = ductSection.getDuctHeightInput();
            if (isNull(ductHeightInput))
                throw new InvalidFanEspCalcInputException("Null duct height input value");
            double h;
            if (request.getUnits().getUu() == 1)
                h = ductHeightInput;
            else
                h = ductHeightInput / 25.4;
            if (!(h >= 10 && h <= 4000))
                throw new InvalidFanEspCalcInputException("Invalid duct height input value");
        }
    }

    private void validateDuctWidthInput(FanEspCalcRequest request, DuctSection ductSection) {
        if (ductSection.getShp() == 1) {
            Double ductWidthInput = ductSection.getDuctWidthInput();
            if (isNull(ductWidthInput))
                throw new InvalidFanEspCalcInputException("Null duct width input value");
            double w;
            if (request.getUnits().getUu() == 1)
                w = ductWidthInput;
            else
                w = ductWidthInput / 25.4;
            if (!(w >= 10 && w <= 4000))
                throw new InvalidFanEspCalcInputException("Invalid duct width input value");
        }
    }

    private void validateDuctDiameterInput(FanEspCalcRequest request, DuctSection ductSection) {
        if (ductSection.getShp() == 2) {
            Double ductDiameterInput = ductSection.getDuctDiameterInput();
            if (isNull(ductDiameterInput))
                throw new InvalidFanEspCalcInputException("Null duct diameter input value");
            double d;
            if (request.getUnits().getUu() == 1)
                d = ductDiameterInput;
            else
                d = ductDiameterInput / 25.4;
            if (!(d >= 10 && d <= 3000))
                throw new InvalidFanEspCalcInputException("Invalid duct diameter input value");
        }
    }

    private void validateFun(DuctSection ductSection) {
        Integer fun = ductSection.getFun();
        if (isNull(fun))
            throw new InvalidFanEspCalcInputException("Null section function value");
        List<Integer> ductShapeList = fanEspLookupsParser.getDataList().stream()
                .filter(e -> e.getUiField().equals("fun")).mapToInt(e -> e.getValue().intValue())
                .boxed().collect(Collectors.toList());
        if (!ductShapeList.contains(fun))
            throw new InvalidFanEspCalcInputException("Invalid section function value");
    }

    private void validateShp(DuctSection ductSection) {
        Integer shp = ductSection.getShp();
        if (isNull(shp))
            throw new InvalidFanEspCalcInputException("Null duct shape value");
        List<Integer> ductShapeList = fanEspLookupsParser.getDataList().stream()
                .filter(e -> e.getUiField().equals("shp")).mapToInt(e -> e.getValue().intValue())
                .boxed().collect(Collectors.toList());
        if (!ductShapeList.contains(shp))
            throw new InvalidFanEspCalcInputException("Invalid duct shape value");
    }

    private void validateLengthInput(FanEspCalcRequest request, DuctSection ductSection) {
        Double lengthInput = ductSection.getLengthInput();
        if (isNull(lengthInput))
            throw new InvalidFanEspCalcInputException("Null length input value");
        double q = lengthInput * request.getUnits().getUl();
        if (!(q >= 0 && q <= 99999))
            throw new InvalidFanEspCalcInputException("Invalid length input value");
    }

    private void validateRateInput(FanEspCalcRequest request, DuctSection ductSection) {
        Double rateInput = ductSection.getRateInput();
        if (isNull(rateInput))
            throw new InvalidFanEspCalcInputException("Null rate input value");
        double q = rateInput * request.getUnits().getUf();
        if (!(q >= 0 && q <= 99999))
            throw new InvalidFanEspCalcInputException("Invalid rate input value");
    }

    private void validateStartAndEndPoints(FanEspCalcRequest request) {
        List<DuctSection> ductSectionList = request.getDuctSectionList();
        Set<String> startPoints = new HashSet<>();
        ductSectionList.forEach(d -> startPoints.add(d.getStartPoint()));
        if (startPoints.size() != ductSectionList.size())
            throw new InvalidFanEspCalcInputException("Similar start points");
        Set<String> endPoints = new HashSet<>();
        ductSectionList.forEach(d -> endPoints.add(d.getEndPoint()));
        if (endPoints.size() != ductSectionList.size())
            throw new InvalidFanEspCalcInputException("Similar end points");
    }

    private void validateDuctSectionList(FanEspCalcRequest request) {
        if (isNull(request.getDuctSectionList()))
            throw new InvalidFanEspCalcInputException("Null duct section list");
        if (request.getDuctSectionList().isEmpty())
            throw new InvalidFanEspCalcInputException("Empty duct section list");
    }

    private void validateEps(FanEspCalcRequest request) {
        Double eps = request.getDuctType().getEps();
        if (isNull(eps))
            throw new InvalidFanEspCalcInputException("Null eps value");
        List<Double> epsList = fanEspLookupsParser.getDataList().stream()
                .filter(e -> e.getUiField().equals("eps")).mapToDouble(e -> e.getValue())
                .boxed().collect(Collectors.toList());
        if (!epsList.contains(eps))
            throw new InvalidFanEspCalcInputException("Invalid eps value");
    }

    private void validateTemperatureInput(FanEspCalcRequest request) {
        Double temperatureInput = request.getAirTemperature().getTemperatureInput();
        Integer uu = request.getUnits().getUu();
        if (isNull(temperatureInput))
            throw new InvalidFanEspCalcInputException("Null temperature input value");
        double temperature;
        if (uu == 1)
            temperature = temperatureInput;
        else
            temperature = (temperatureInput - 32) / 1.8;
        if (!(temperature >= -20 && temperature <= 80))
            throw new InvalidFanEspCalcInputException("Invalid temperature value");
    }

    private void validateUl(FanEspCalcRequest request) {
        Double ul = request.getUnits().getUl();
        Integer uu = request.getUnits().getUu();
        if (isNull(ul))
            throw new InvalidFanEspCalcInputException("Null ul value");
        List<Double> ulGroup1List = fanEspLookupsParser.getDataList().stream()
                .filter(e -> e.getUiField().equals("ul") && e.getGroup() == 1)
                .mapToDouble(e -> e.getValue())
                .boxed().collect(Collectors.toList());
        if (uu == 1 && !ulGroup1List.contains(ul))
            throw new InvalidFanEspCalcInputException("Invalid ul value");
        List<Double> ulGroup2List = fanEspLookupsParser.getDataList().stream()
                .filter(e -> e.getUiField().equals("ul") && e.getGroup() == 2)
                .mapToDouble(e -> e.getValue())
                .boxed().collect(Collectors.toList());
        if (uu == 2 && !ulGroup2List.contains(ul))
            throw new InvalidFanEspCalcInputException("Invalid ul value");
    }

    private void validateUf(FanEspCalcRequest request) {
        Double uf = request.getUnits().getUf();
        Integer uu = request.getUnits().getUu();
        if (isNull(uf))
            throw new InvalidFanEspCalcInputException("Null uf value");
        List<Double> ufGroup1List = fanEspLookupsParser.getDataList().stream()
                .filter(e -> e.getUiField().equals("uf") && e.getGroup() == 1)
                .mapToDouble(e -> e.getValue())
                .boxed().collect(Collectors.toList());
        if (uu == 1 && !ufGroup1List.contains(uf))
            throw new InvalidFanEspCalcInputException("Invalid uf value");
        List<Double> ufGroup2List = fanEspLookupsParser.getDataList().stream()
                .filter(e -> e.getUiField().equals("uf") && e.getGroup() == 2)
                .mapToDouble(e -> e.getValue())
                .boxed().collect(Collectors.toList());
        if (uu == 2 && !ufGroup2List.contains(uf))
            throw new InvalidFanEspCalcInputException("Invalid uf value");
    }

    private void validateUu(FanEspCalcRequest request) {
        Integer uu = request.getUnits().getUu();
        if (isNull(uu))
            throw new InvalidFanEspCalcInputException("Null uu value");
        List<Integer> uuList = fanEspLookupsParser.getDataList().stream()
                .filter(e -> e.getUiField().equals("uu")).mapToInt(e -> e.getValue().intValue())
                .boxed().collect(Collectors.toList());
        if (!uuList.contains(uu))
            throw new InvalidFanEspCalcInputException("Invalid uu value");
    }
}
