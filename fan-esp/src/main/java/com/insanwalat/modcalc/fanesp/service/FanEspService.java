package com.insanwalat.modcalc.fanesp.service;

import com.insanwalat.modcalc.fanesp.module.request.FanEspCalcRequest;
import com.insanwalat.modcalc.fanesp.module.response.FanEspCalcResponse;
import com.insanwalat.modcalc.fanesp.module.response.FanEspCoefficientDataLookupResponse;
import com.insanwalat.modcalc.fanesp.module.response.FanEspCoefficientLookupResponse;
import com.insanwalat.modcalc.fanesp.module.response.FanEspLookupResponse;

import java.util.List;

public interface FanEspService {

    FanEspCalcResponse calculate(FanEspCalcRequest request);

    List<FanEspLookupResponse> getFanEspLookup();

    List<FanEspCoefficientLookupResponse> getFanEspCoefficientLookup();

    List<FanEspCoefficientDataLookupResponse> getFanEspCoefficientDataLookup();
}
