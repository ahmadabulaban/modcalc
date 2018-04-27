package com.insanwalat.modcalc.mapper;

import com.insanwalat.modcalc.module.input.DuctSizerCalcInput;
import com.insanwalat.modcalc.module.lookup.DuctSizerLookup;
import com.insanwalat.modcalc.module.output.DuctSizerCalcOutput;
import com.insanwalat.modcalc.module.request.DuctSizerCalcRequest;
import com.insanwalat.modcalc.module.response.DuctSizerCalcResponse;
import com.insanwalat.modcalc.module.response.DuctSizerLookupResponse;
import com.insanwalat.modcalc.utils.DuctSizerLookupsParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

@Component
public class DuctSizerMapper {

    @Autowired
    private DuctSizerLookupsParser ductSizerLookupsParser;

    private DuctSizerCalcInput input;

    public DuctSizerCalcInput mapRequestToInput(DuctSizerCalcRequest request) {
        input = new DuctSizerCalcInput();
        mapRequestUnits(request);
        mapRequestAirTemperature(request);
        mapRequestDuctType(request);
        mapRequestFlowRateAndSizingCriteria(request);
        return input;
    }

    private void mapRequestFlowRateAndSizingCriteria(DuctSizerCalcRequest request) {
        Double rateInput = request.getFlowRateAndSizingCriteria().getRateInput();
        input.setRateInput(rateInput);
        input.setQ(rateInput * request.getUnits().getUf());
        input.setRateUnit(input.getUfUnit());
        Integer shp = request.getFlowRateAndSizingCriteria().getShp();
        input.setShp(shp);
        Integer uu = request.getUnits().getUu();
        if (shp == 1) {
            Double dimensionInput = request.getFlowRateAndSizingCriteria().getDimensionInput();
            input.setDimensionInput(dimensionInput);
            double dim1;
            if (uu == 1)
                dim1 = dimensionInput;
            else
                dim1 = dimensionInput * 25.4;
            input.setDim1(dim1);
        }
        input.setSizingCriteria(request.getFlowRateAndSizingCriteria().getSizingCriteria());
        Double allowedPressureInput = request.getFlowRateAndSizingCriteria().getAllowedPressureInput();
        input.setAllowedPressureInput(allowedPressureInput);
        if (isNull(allowedPressureInput))
            allowedPressureInput = (double) 0;
        double pMax;
        if (uu == 1)
            pMax = allowedPressureInput;
        else
            pMax = allowedPressureInput * 8.17;
        input.setpMax(pMax);
        Map<Integer, String> pressureUnitMap = ductSizerLookupsParser.getDataList().stream()
                .filter(e -> e.getUiField().equals("pressureUnit"))
                .collect(Collectors.toMap(DuctSizerLookup::getGroup, DuctSizerLookup::getKey));
        String pressureUnit = pressureUnitMap.get(uu);
        input.setPressureUnit(pressureUnit);
        Double allowedVelocityInput = request.getFlowRateAndSizingCriteria().getAllowedVelocityInput();
        input.setAllowedVelocityInput(allowedVelocityInput);
        if (isNull(allowedVelocityInput))
            allowedVelocityInput = (double) 0;
        double vMax;
        if (uu == 1)
            vMax = allowedVelocityInput;
        else
            vMax = allowedVelocityInput / 196.8;
        input.setvMax(vMax);
        Map<Integer, String> velocityUnitMap = ductSizerLookupsParser.getDataList().stream()
                .filter(e -> e.getUiField().equals("velocityUnit"))
                .collect(Collectors.toMap(DuctSizerLookup::getGroup, DuctSizerLookup::getKey));
        String velocityUnit = velocityUnitMap.get(uu);
        input.setVelocityUnit(velocityUnit);

    }

    private void mapRequestDuctType(DuctSizerCalcRequest request) {
        input.setEps(request.getDuctType().getEps());
        Double thicknessInput = request.getDuctType().getThicknessInput();
        Integer uu = request.getUnits().getUu();
        double thickness;
        if (uu == 1)
            thickness = thicknessInput;
        else
            thickness = thicknessInput * 25.4;
        input.setThicknessInput(thicknessInput);
        input.setThickness(thickness);
        Map<Integer, String> dimensionUnitMap = ductSizerLookupsParser.getDataList().stream()
                .filter(e -> e.getUiField().equals("dimensionUnit"))
                .collect(Collectors.toMap(DuctSizerLookup::getGroup, DuctSizerLookup::getKey));
        String dimensionUnit = dimensionUnitMap.get(uu);
        input.setDimensionUnit(dimensionUnit);
    }

    private void mapRequestAirTemperature(DuctSizerCalcRequest request) {
        Double temperatureInput = request.getAirTemperature().getTemperatureInput();
        double temperature;
        Integer uu = request.getUnits().getUu();
        if (uu == 1)
            temperature = temperatureInput;
        else
            temperature = (temperatureInput - 32) / 1.8;
        input.setTemperatureInput(temperatureInput);
        input.setTemperature(temperature);
        Map<Integer, String> temperatureUnitMap = ductSizerLookupsParser.getDataList().stream()
                .filter(e -> e.getUiField().equals("temperatureUnit"))
                .collect(Collectors.toMap(DuctSizerLookup::getGroup, DuctSizerLookup::getKey));
        String temperatureUnit = temperatureUnitMap.get(uu);
        input.setTemperatureUnit(temperatureUnit);
    }

    private void mapRequestUnits(DuctSizerCalcRequest request) {
        input.setUu(request.getUnits().getUu());
        Double uf = request.getUnits().getUf();
        input.setUf(uf);
        String ufUnit = ductSizerLookupsParser.getDataList().stream().filter(e -> e.getUiField().equals("uf")
                && e.getValue().equals(uf)).findFirst().get().getKey();
        input.setUfUnit(ufUnit);
    }

    public DuctSizerCalcResponse mapOutputToResponse(DuctSizerCalcOutput output) {
        return new DuctSizerCalcResponse(output.getO1(),
                output.getTx1(),
                output.getO2(),
                output.getTx2(),
                output.getO3(),
                output.getTx3(),
                output.getO4(),
                output.getO5(),
                output.getTx5(),
                output.getO6(),
                output.getTx6());
    }

    public DuctSizerLookupResponse mapLookupToLookupResponse(DuctSizerLookup ductSizerLookup) {
        return new DuctSizerLookupResponse(ductSizerLookup.getUiField(),
                ductSizerLookup.getKey(),
                ductSizerLookup.getValue(),
                ductSizerLookup.getDefaultOption(),
                ductSizerLookup.getGroup());
    }
}
