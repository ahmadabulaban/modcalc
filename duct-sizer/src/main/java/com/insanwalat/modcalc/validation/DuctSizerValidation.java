package com.insanwalat.modcalc.validation;

import com.insanwalat.modcalc.exceptions.InvalidDuctSizerCalcInputException;
import com.insanwalat.modcalc.module.request.DuctSizerCalcRequest;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class DuctSizerValidation {

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
        Double allowedVelocityInput = request.getFlowRateAndSizingCriteria().getAllowedVelocityInput();
        if (isNull(allowedVelocityInput))
            throw new InvalidDuctSizerCalcInputException("Null allowed velocity input value");
        double v_max;
        if (request.getUnits().getUu() == 1)
            v_max = allowedVelocityInput;
        else
            v_max = allowedVelocityInput / 196.8;
        if (!(v_max >= 0.1 && v_max <= 9999))
            throw new InvalidDuctSizerCalcInputException("Invalid v_max value");
    }

    private void validateAllowedPressureInput(DuctSizerCalcRequest request) {
        Double allowedPressureInput = request.getFlowRateAndSizingCriteria().getAllowedPressureInput();
        if (isNull(allowedPressureInput))
            throw new InvalidDuctSizerCalcInputException("Null allowed pressure input value");
        double p_max;
        if (request.getUnits().getUu() == 1)
            p_max = allowedPressureInput;
        else
            p_max = allowedPressureInput * 8.17;
        if (!(p_max >= 0.1 && p_max <= 9999))
            throw new InvalidDuctSizerCalcInputException("Invalid p_max value");

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
            throw new InvalidDuctSizerCalcInputException("Null shp value");
        if (!(shp == 1 || shp == 2))
            throw new InvalidDuctSizerCalcInputException("Invalid shp value");
    }

    private void validateRateInput(DuctSizerCalcRequest request) {
        Double rateInput = request.getFlowRateAndSizingCriteria().getRateInput();
        if (isNull(rateInput))
            throw new InvalidDuctSizerCalcInputException("Null rate input value");
        double q = rateInput * request.getUnits().getUf();
        if (!(q >= 0 && q <= 200000))
            throw new InvalidDuctSizerCalcInputException("Invalid q value");
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
        List<Double> validValues = new ArrayList<>();
        validValues.add(0.0000304878048780488);
        validValues.add(0.0000914634146341463);
        validValues.add(0.000152439024390244);
        validValues.add(0.000914634146341463);
        validValues.add(0.00304878048780488);
        if (!validValues.contains(eps))
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
        if (!(temperature >= -20 && temperature <= 80))
            throw new InvalidDuctSizerCalcInputException("Invalid temperature value");
    }

    private void validateUf(DuctSizerCalcRequest request) {
        Double uf = request.getUnits().getUf();
        Integer uu = request.getUnits().getUu();
        if (isNull(uf))
            throw new InvalidDuctSizerCalcInputException("Null uf value");
        List<Double> validValues = new ArrayList<>();
        validValues.add((double) 1);
        validValues.add((double) 1000);
        validValues.add(0.277778);
        if (uu == 1 && !validValues.contains(uf))
            throw new InvalidDuctSizerCalcInputException("Invalid uf value");
        validValues.clear();
        validValues.add(0.4719);
        validValues.add(28.31684);
        validValues.add(0.007865);
        if (uu == 2 && !validValues.contains(uf))
            throw new InvalidDuctSizerCalcInputException("Invalid uf value");
    }

    private void validateUu(DuctSizerCalcRequest request) {
        Integer uu = request.getUnits().getUu();
        if (isNull(uu))
            throw new InvalidDuctSizerCalcInputException("Null uu value");
        List<Integer> validValues = new ArrayList<>();
        validValues.add(1);
        validValues.add(2);
        if (!validValues.contains(uu))
            throw new InvalidDuctSizerCalcInputException("Invalid uu value");
    }
}
