package com.insanwalat.modcalc.service;

import com.insanwalat.modcalc.module.request.DuctSizerCalcRequest;
import com.insanwalat.modcalc.module.response.DuctSizerCalcResponse;
import com.insanwalat.modcalc.module.response.DuctSizerLookupResponse;

import java.util.List;

public interface DuctSizerService {

    DuctSizerCalcResponse calculate(DuctSizerCalcRequest request);

    List<DuctSizerLookupResponse> getDuctSizerLookup();
}
