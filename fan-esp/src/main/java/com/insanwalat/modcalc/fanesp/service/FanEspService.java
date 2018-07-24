package com.insanwalat.modcalc.fanesp.service;

import com.insanwalat.modcalc.fanesp.module.request.FanEspCalcRequest;
import com.insanwalat.modcalc.fanesp.module.request.FanEspSaveRequest;
import com.insanwalat.modcalc.fanesp.module.response.*;

import java.util.List;

public interface FanEspService {

    FanEspCalcResponse calculate(FanEspCalcRequest request);

    FanEspSaveResponse save(FanEspSaveRequest request);

    List<FanEspLoadResponse> load();

    List<FanEspLookupResponse> getFanEspLookup();

    List<FanEspCoefficientLookupResponse> getFanEspCoefficientLookup();

    List<FanEspCoefficientDataLookupResponse> getFanEspCoefficientDataLookup();
}
