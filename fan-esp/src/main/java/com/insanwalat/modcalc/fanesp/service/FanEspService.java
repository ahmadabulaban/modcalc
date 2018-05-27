package com.insanwalat.modcalc.fanesp.service;

import com.insanwalat.modcalc.fanesp.module.request.FanEspCalcRequest;
import com.insanwalat.modcalc.fanesp.module.response.FanEspCalcResponse;

public interface FanEspService {

    FanEspCalcResponse calculate(FanEspCalcRequest request);
}
