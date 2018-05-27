package com.insanwalat.modcalc.fanesp.service.impl;

import com.insanwalat.modcalc.fanesp.business.FanEspCalcAlgorithm;
import com.insanwalat.modcalc.fanesp.mapper.FanEspMapper;
import com.insanwalat.modcalc.fanesp.module.input.FanEspCalcInput;
import com.insanwalat.modcalc.fanesp.module.output.FanEspCalcOutput;
import com.insanwalat.modcalc.fanesp.module.request.FanEspCalcRequest;
import com.insanwalat.modcalc.fanesp.module.response.FanEspCalcResponse;
import com.insanwalat.modcalc.fanesp.service.FanEspService;
import com.insanwalat.modcalc.fanesp.utils.FanEspLookupsParser;
import com.insanwalat.modcalc.fanesp.validation.FanEspValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanEspServiceImpl implements FanEspService{

    private FanEspCalcAlgorithm algorithm = new FanEspCalcAlgorithm();

    @Autowired
    private FanEspMapper mapper;

    @Autowired
    private FanEspValidation fanEspValidation;

    @Autowired
    private FanEspLookupsParser fanEspLookupsParser;

    @Override
    public FanEspCalcResponse calculate(FanEspCalcRequest request) {
        fanEspValidation.validateFanEspCalcRequest(request);
        FanEspCalcInput input = mapper.mapRequestToInput(request);
        FanEspCalcOutput output = algorithm.doCalculations(input);
        FanEspCalcResponse response = mapper.mapOutputToResponse(output);
        return response;
    }
}
