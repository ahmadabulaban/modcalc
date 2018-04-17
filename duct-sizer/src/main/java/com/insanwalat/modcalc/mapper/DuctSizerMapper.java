package com.insanwalat.modcalc.mapper;

import com.insanwalat.modcalc.module.input.DuctSizerCalcInput;
import com.insanwalat.modcalc.module.output.DuctSizerCalcOutput;
import com.insanwalat.modcalc.module.request.DuctSizerCalcRequest;
import com.insanwalat.modcalc.module.response.DuctSizerCalcResponse;

import java.util.HashMap;
import java.util.Map;

public class DuctSizerMapper {

    private DuctSizerCalcInput input;
    private Map<Double, String> ufMap = new HashMap<>();

    {
        ufMap.put((double) 1, "l/s");
        ufMap.put((double) 1000, "m3/s");
        ufMap.put(0.277778, "m3/h");
        ufMap.put(0.4719, "ft3/m");
        ufMap.put(28.31684, "ft3/s");
        ufMap.put(0.007865, "ft3/hr");
    }

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
        double pMax;
        if (uu == 1)
            pMax = allowedPressureInput;
        else
            pMax = allowedPressureInput * 8.17;
        input.setpMax(pMax);
        String pressureUnit;
        if (uu == 1)
            pressureUnit = "Pa/m";
        else
            pressureUnit = "inch H2O/100ft";
        input.setPressureUnit(pressureUnit);
        Double allowedVelocityInput = request.getFlowRateAndSizingCriteria().getAllowedVelocityInput();
        input.setAllowedVelocityInput(allowedVelocityInput);
        double vMax;
        if (uu == 1)
            vMax = allowedVelocityInput;
        else
            vMax = allowedVelocityInput / 196.8;
        input.setvMax(vMax);
        String velocityUnit;
        if (uu == 1)
            velocityUnit = "m/s";
        else
            velocityUnit = "fpm";
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
        String dimensionUnit;
        if (uu == 1)
            dimensionUnit = "mm";
        else
            dimensionUnit = "inch";
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
        String temperatureUnit;
        if (uu == 1)
            temperatureUnit = "C";
        else
            temperatureUnit = "F";
        input.setTemperatureUnit(temperatureUnit);
    }

    private void mapRequestUnits(DuctSizerCalcRequest request) {
        input.setUu(request.getUnits().getUu());
        Double uf = request.getUnits().getUf();
        input.setUf(uf);
        input.setUfUnit(ufMap.get(uf));
    }

    public DuctSizerCalcResponse mapOutputToResponse(DuctSizerCalcOutput output) {
        DuctSizerCalcResponse response = new DuctSizerCalcResponse();
        response.setO1(output.getO1());
        response.setTx1(output.getTx1());
        response.setO2(output.getO2());
        response.setTx2(output.getTx2());
        response.setO3(output.getO3());
        response.setO4(output.getO4());
        response.setO5(output.getO5());
        response.setTx5(output.getTx5());
        response.setO6(output.getO6());
        response.setTx6(output.getTx6());
        return response;
    }
}
