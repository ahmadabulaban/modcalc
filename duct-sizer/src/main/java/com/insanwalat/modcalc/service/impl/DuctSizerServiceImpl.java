package com.insanwalat.modcalc.service.impl;

import com.insanwalat.modcalc.module.response.DuctSizerLookupResponse;
import com.insanwalat.modcalc.utils.DuctSizerLookupsParser;
import com.insanwalat.modcalc.business.DuctSizerCalcAlgorithm;
import com.insanwalat.modcalc.mapper.DuctSizerMapper;
import com.insanwalat.modcalc.module.input.DuctSizerCalcInput;
import com.insanwalat.modcalc.module.output.DuctSizerCalcOutput;
import com.insanwalat.modcalc.module.request.DuctSizerCalcRequest;
import com.insanwalat.modcalc.module.response.DuctSizerCalcResponse;
import com.insanwalat.modcalc.service.DuctSizerService;
import com.insanwalat.modcalc.validation.DuctSizerValidation;
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
