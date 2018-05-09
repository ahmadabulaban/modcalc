package com.insanwalat.modcalc.validation;

import com.insanwalat.modcalc.exceptions.InvalidDuctSizerCalcInputException;
import com.insanwalat.modcalc.module.request.DuctSizerCalcRequest;
import com.insanwalat.modcalc.utils.DuctSizerLookupsParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class DuctSizerValidation {

    @Autowired
    private DuctSizerLookupsParser ductSizerLookupsParser;

    public void validateDuctSizerCalcRequest(DuctSizerCalcRequest request) {
        validateUu(request);
        validateUf(request);
        validateTemperatureInput(request);
        validateEps(request);
        validateThicknessInput(request);
        validateRateInput(request);
        validateShp(request);
        validateDimensionInput(request);
        validateSizingCriteria(request);
        validateAllowedPressureInput(request);
        validateAllowedVelocityInput(request);
    }

    private void validateAllowedVelocityInput(DuctSizerCalcRequest request) {
        if (request.getFlowRateAndSizingCriteria().getSizingCriteria() == 2) {
            Double allowedVelocityInput = request.getFlowRateAndSizingCriteria().getAllowedVelocityInput();
            if (isNull(allowedVelocityInput))
                throw new InvalidDuctSizerCalcInputException("Null allowed velocity input value");
            double v_max;
            if (request.getUnits().getUu() == 1)
                v_max = allowedVelocityInput;
            else
                v_max = allowedVelocityInput / 196.8;
            if (!(v_max >= 0.1 && v_max <= 9999))
                throw new InvalidDuctSizerCalcInputException("Invalid allowed velocity input value");
        }
    }

    private void validateAllowedPressureInput(DuctSizerCalcRequest request) {
        if (request.getFlowRateAndSizingCriteria().getSizingCriteria() == 1) {
            Double allowedPressureInput = request.getFlowRateAndSizingCriteria().getAllowedPressureInput();
            if (isNull(allowedPressureInput))
                throw new InvalidDuctSizerCalcInputException("Null allowed pressure input value");
            double p_max;
            if (request.getUnits().getUu() == 1)
                p_max = allowedPressureInput;
            else
                p_max = allowedPressureInput * 8.17;
            if (!(p_max >= 0.1 && p_max <= 9999))
                throw new InvalidDuctSizerCalcInputException("Invalid allowed pressure input value");
        }

    }

    private void validateSizingCriteria(DuctSizerCalcRequest request) {
        Integer sizingCriteria = request.getFlowRateAndSizingCriteria().getSizingCriteria();
        if (isNull(sizingCriteria))
            throw new InvalidDuctSizerCalcInputException("Null sizing criteria");
        if (!(sizingCriteria == 1 || sizingCriteria == 2))
            throw new InvalidDuctSizerCalcInputException("Invalid sizing criteria");
    }

    private void validateDimensionInput(DuctSizerCalcRequest request) {
        if (request.getFlowRateAndSizingCriteria().getShp() == 1) {
            Double dimensionInput = request.getFlowRateAndSizingCriteria().getDimensionInput();
            if (isNull(dimensionInput))
                throw new InvalidDuctSizerCalcInputException("Null dimension input value");
            if (request.getUnits().getUu() == 1) {
                if (!(dimensionInput % 1 == 0))
                    throw new InvalidDuctSizerCalcInputException("Invalid dimension input value");
                if (!(dimensionInput >= 50 && dimensionInput <= 4000))
                    throw new InvalidDuctSizerCalcInputException("Invalid dimension input value");
            } else {
                if (!(dimensionInput >= 2 && dimensionInput <= 160))
                    throw new InvalidDuctSizerCalcInputException("Invalid dimension input value");
                if (!((dimensionInput * 10) % 1 == 0))
                    throw new InvalidDuctSizerCalcInputException("Invalid dimension input value");
            }
        }
    }

    private void validateShp(DuctSizerCalcRequest request) {
        Integer shp = request.getFlowRateAndSizingCriteria().getShp();
        if (isNull(shp))
            throw new InvalidDuctSizerCalcInputException("Null duct shape value");
        List<Integer> ductShapeList = ductSizerLookupsParser.getDataList().stream()
                .filter(e -> e.getUiField().equals("ductShape")).mapToInt(e -> e.getValue().intValue())
                .boxed().collect(Collectors.toList());
        if (!ductShapeList.contains(shp))
            throw new InvalidDuctSizerCalcInputException("Invalid duct shape value");
    }

    private void validateRateInput(DuctSizerCalcRequest request) {
        Double rateInput = request.getFlowRateAndSizingCriteria().getRateInput();
        if (isNull(rateInput))
            throw new InvalidDuctSizerCalcInputException("Null rate input value");
        double q = rateInput * request.getUnits().getUf();
        if (!(q >= 0 && q <= 200000))
            throw new InvalidDuctSizerCalcInputException("Invalid rate input value");
    }

    private void validateThicknessInput(DuctSizerCalcRequest request) {
        Double thicknessInput = request.getDuctType().getThicknessInput();
        if (isNull(thicknessInput))
            throw new InvalidDuctSizerCalcInputException("Null thickness input value");
        double thickness;
        if (request.getUnits().getUu() == 1)
            thickness = thicknessInput;
        else
            thickness = thicknessInput * 25.4;
        if (!(thickness >= 0.1 && thickness <= 5))
            throw new InvalidDuctSizerCalcInputException("Invalid thickness value");
    }

    private void validateEps(DuctSizerCalcRequest request) {
        Double eps = request.getDuctType().getEps();
        if (isNull(eps))
            throw new InvalidDuctSizerCalcInputException("Null eps value");
        List<Double> epsList = ductSizerLookupsParser.getDataList().stream()
                .filter(e -> e.getUiField().equals("eps")).mapToDouble(e -> e.getValue())
                .boxed().collect(Collectors.toList());
        if (!epsList.contains(eps))
            throw new InvalidDuctSizerCalcInputException("Invalid eps value");
    }

    private void validateTemperatureInput(DuctSizerCalcRequest request) {
        Double temperatureInput = request.getAirTemperature().getTemperatureInput();
        Integer uu = request.getUnits().getUu();
        if (isNull(temperatureInput))
            throw new InvalidDuctSizerCalcInputException("Null temperature input value");
        double temperature;
        if (uu == 1)
            temperature = temperatureInput;
        else
            temperature = (temperatureInput - 32) / 1.8;
        if (!(temperature >= -20 && temperature <= 100))
            throw new InvalidDuctSizerCalcInputException("Invalid temperature value");
    }

    private void validateUf(DuctSizerCalcRequest request) {
        Double uf = request.getUnits().getUf();
        Integer uu = request.getUnits().getUu();
        if (isNull(uf))
            throw new InvalidDuctSizerCalcInputException("Null uf value");
        List<Double> ufGroup1List = ductSizerLookupsParser.getDataList().stream()
                .filter(e -> e.getUiField().equals("uf") && e.getGroup() == 1)
                .mapToDouble(e -> e.getValue())
                .boxed().collect(Collectors.toList());
        if (uu == 1 && !ufGroup1List.contains(uf))
            throw new InvalidDuctSizerCalcInputException("Invalid uf value");
        List<Double> ufGroup2List = ductSizerLookupsParser.getDataList().stream()
                .filter(e -> e.getUiField().equals("uf") && e.getGroup() == 2)
                .mapToDouble(e -> e.getValue())
                .boxed().collect(Collectors.toList());
        if (uu == 2 && !ufGroup2List.contains(uf))
            throw new InvalidDuctSizerCalcInputException("Invalid uf value");
    }

    private void validateUu(DuctSizerCalcRequest request) {
        Integer uu = request.getUnits().getUu();
        if (isNull(uu))
            throw new InvalidDuctSizerCalcInputException("Null uu value");
        List<Integer> uuList = ductSizerLookupsParser.getDataList().stream()
                .filter(e -> e.getUiField().equals("uu")).mapToInt(e -> e.getValue().intValue())
                .boxed().collect(Collectors.toList());
        if (!uuList.contains(uu))
            throw new InvalidDuctSizerCalcInputException("Invalid uu value");
    }
}
