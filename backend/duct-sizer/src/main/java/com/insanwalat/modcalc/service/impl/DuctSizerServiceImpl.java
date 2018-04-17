package com.insanwalat.modcalc.service.impl;

import com.insanwalat.modcalc.business.DuctSizerCalcAlgorithm;
import com.insanwalat.modcalc.mapper.DuctSizerMapper;
import com.insanwalat.modcalc.module.input.DuctSizerCalcInput;
import com.insanwalat.modcalc.module.output.DuctSizerCalcOutput;
import com.insanwalat.modcalc.module.request.DuctSizerCalcRequest;
import com.insanwalat.modcalc.module.response.DuctSizerCalcResponse;
import com.insanwalat.modcalc.service.DuctSizerService;
import com.insanwalat.modcalc.validation.DuctSizerValidation;

public class DuctSizerServiceImpl implements DuctSizerService {

    private DuctSizerMapper mapper = new DuctSizerMapper();
    private DuctSizerValidation validation = new DuctSizerValidation();
    private DuctSizerCalcAlgorithm algorithm = new DuctSizerCalcAlgorithm();

    @Override
    public DuctSizerCalcResponse calculate(DuctSizerCalcRequest request) {
        //validation.validateDuctSizerCalcRequest(request);
        DuctSizerCalcInput input = mapper.mapRequestToInput(request);
        DuctSizerCalcOutput output = algorithm.doCalculations(input);
        DuctSizerCalcResponse response = mapper.mapOutputToResponse(output);
        return response;
    }
}
