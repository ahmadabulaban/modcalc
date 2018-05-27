package com.insanwalat.modcalc.ductsizer.service.impl;

import com.insanwalat.modcalc.ductsizer.module.response.DuctSizerLookupResponse;
import com.insanwalat.modcalc.ductsizer.utils.DuctSizerLookupsParser;
import com.insanwalat.modcalc.ductsizer.business.DuctSizerCalcAlgorithm;
import com.insanwalat.modcalc.ductsizer.mapper.DuctSizerMapper;
import com.insanwalat.modcalc.ductsizer.module.input.DuctSizerCalcInput;
import com.insanwalat.modcalc.ductsizer.module.output.DuctSizerCalcOutput;
import com.insanwalat.modcalc.ductsizer.module.request.DuctSizerCalcRequest;
import com.insanwalat.modcalc.ductsizer.module.response.DuctSizerCalcResponse;
import com.insanwalat.modcalc.ductsizer.service.DuctSizerService;
import com.insanwalat.modcalc.ductsizer.validation.DuctSizerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DuctSizerServiceImpl implements DuctSizerService {

    private DuctSizerCalcAlgorithm algorithm = new DuctSizerCalcAlgorithm();

    @Autowired
    private DuctSizerMapper mapper;

    @Autowired
    private DuctSizerValidation ductSizerValidation;

    @Autowired
    private DuctSizerLookupsParser ductSizerLookupsParser;

    @Override
    public DuctSizerCalcResponse calculate(DuctSizerCalcRequest request) {
        ductSizerValidation.validateDuctSizerCalcRequest(request);
        DuctSizerCalcInput input = mapper.mapRequestToInput(request);
        DuctSizerCalcOutput output = algorithm.doCalculations(input);
        DuctSizerCalcResponse response = mapper.mapOutputToResponse(output);
        return response;
    }

    @Override
    public List<DuctSizerLookupResponse> getDuctSizerLookup() {
        return ductSizerLookupsParser.getDataList().stream()
                .map(e -> mapper.mapLookupToLookupResponse(e))
                .collect(Collectors.toList());
    }
}
