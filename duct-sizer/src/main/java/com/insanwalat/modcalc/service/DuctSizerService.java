package com.insanwalat.modcalc.service;

import com.insanwalat.modcalc.module.request.DuctSizerCalcRequest;
import com.insanwalat.modcalc.module.response.DuctSizerCalcResponse;

public interface DuctSizerService {

    DuctSizerCalcResponse calculate(DuctSizerCalcRequest request);
}
