package com.insanwalat.modcalc.service.impl;

import com.insanwalat.modcalc.utils.DuctSizerLookupsParser;
import com.insanwalat.modcalc.business.DuctSizerCalcAlgorithm;
import com.insanwalat.modcalc.exceptions.ParsingLookupDataException;
import com.insanwalat.modcalc.mapper.DuctSizerMapper;
import com.insanwalat.modcalc.module.lookup.DuctSizerLookup;
import com.insanwalat.modcalc.module.input.DuctSizerCalcInput;
import com.insanwalat.modcalc.module.output.DuctSizerCalcOutput;
import com.insanwalat.modcalc.module.request.DuctSizerCalcRequest;
import com.insanwalat.modcalc.module.response.DuctSizerCalcResponse;
import com.insanwalat.modcalc.service.DuctSizerService;
import com.insanwalat.modcalc.validation.DuctSizerValidation;

import java.util.ArrayList;
import java.util.List;

public class DuctSizerServiceImpl implements DuctSizerService {

    private DuctSizerMapper mapper = new DuctSizerMapper();
    private DuctSizerValidation validation = new DuctSizerValidation();
    private DuctSizerCalcAlgorithm algorithm = new DuctSizerCalcAlgorithm();
    private DuctSizerLookupsParser ductSizerLookupsParser = new DuctSizerLookupsParser();

    public DuctSizerServiceImpl() {
        try {
            ductSizerLookupsParser.fillDataMap();
        } catch (Exception e) {
            throw new ParsingLookupDataException("Failed to parse Duct Sizer Lookup File");
        }
    }

    @Override
    public DuctSizerCalcResponse calculate(DuctSizerCalcRequest request) {
        validation.validateDuctSizerCalcRequest(request);
        DuctSizerCalcInput input = mapper.mapRequestToInput(request);
        DuctSizerCalcOutput output = algorithm.doCalculations(input);
        DuctSizerCalcResponse response = mapper.mapOutputToResponse(output);
        return response;
    }

    @Override
    public List<DuctSizerLookup> getDuctSizerLookup() {
        List<DuctSizerLookup> ductSizerLookupList = new ArrayList<>(ductSizerLookupsParser.getDataList());
        return ductSizerLookupList;
    }
}
