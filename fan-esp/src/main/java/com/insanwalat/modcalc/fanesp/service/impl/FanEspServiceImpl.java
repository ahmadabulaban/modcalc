package com.insanwalat.modcalc.fanesp.service.impl;

import com.insanwalat.modcalc.fanesp.business.FanEspCalcAlgorithm;
import com.insanwalat.modcalc.fanesp.mapper.FanEspMapper;
import com.insanwalat.modcalc.fanesp.module.input.FanEspCalcInput;
import com.insanwalat.modcalc.fanesp.module.input.FanEspSaveInput;
import com.insanwalat.modcalc.fanesp.module.output.FanEspCalcOutput;
import com.insanwalat.modcalc.fanesp.module.output.FanEspLoadOutput;
import com.insanwalat.modcalc.fanesp.module.request.FanEspCalcRequest;
import com.insanwalat.modcalc.fanesp.module.request.FanEspSaveRequest;
import com.insanwalat.modcalc.fanesp.module.response.*;
import com.insanwalat.modcalc.fanesp.repository.FanEspRepository;
import com.insanwalat.modcalc.fanesp.service.FanEspService;
import com.insanwalat.modcalc.fanesp.utils.FanEspLookupsParser;
import com.insanwalat.modcalc.fanesp.validation.FanEspValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FanEspServiceImpl implements FanEspService{

    private FanEspCalcAlgorithm algorithm = new FanEspCalcAlgorithm();

    @Autowired
    private FanEspMapper mapper;

    @Autowired
    private FanEspValidation fanEspValidation;

    @Autowired
    private FanEspLookupsParser fanEspLookupsParser;

    @Autowired
    private FanEspRepository fanEspRepository;

    @Override
    public FanEspCalcResponse calculate(FanEspCalcRequest request) {
        fanEspValidation.validateFanEspCalcRequest(request);
        FanEspCalcInput input = mapper.mapRequestToInput(request);
        FanEspCalcOutput output = algorithm.doCalculations(input);
        FanEspCalcResponse response = mapper.mapOutputToResponse(output);
        return response;
    }

    @Override
    public FanEspSaveResponse save(FanEspSaveRequest request) {
        FanEspSaveInput fanEspSaveInput = mapper.mapSaveRequestToSaveInput(request);
        fanEspRepository.save(fanEspSaveInput);
        return new FanEspSaveResponse(true);
    }

    @Override
    public List<FanEspLoadResponse> load() {
        List<FanEspLoadOutput> fanEspLoadOutputList = fanEspRepository.load();
        List<FanEspLoadResponse> fanEspLoadResponseList = mapper.mapLoadOutputToLoadResponse(fanEspLoadOutputList);
        return fanEspLoadResponseList;
    }

    @Override
    public List<FanEspLookupResponse> getFanEspLookup() {
        return fanEspLookupsParser.getDataList().stream()
                .map(e -> mapper.mapLookupToLookupResponse(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<FanEspCoefficientLookupResponse> getFanEspCoefficientLookup() {
        return fanEspLookupsParser.getDataCoefficientList().stream()
                .map(e -> mapper.mapCoefficientLookupToCoefficientLookupResponse(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<FanEspCoefficientDataLookupResponse> getFanEspCoefficientDataLookup() {
        return fanEspLookupsParser.getFanEspCoefficientDataLookupList().stream()
                .map(e -> mapper.mapCoefficientDataLookupToCoefficientDataLookupResponse(e))
                .collect(Collectors.toList());
    }
}
