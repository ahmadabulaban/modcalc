package com.insanwalat.modcalc.ductsizer.service;

import com.insanwalat.modcalc.ductsizer.module.request.DuctSizerCalcRequest;
import com.insanwalat.modcalc.ductsizer.module.response.DuctSizerCalcResponse;
import com.insanwalat.modcalc.ductsizer.module.response.DuctSizerLookupResponse;

import java.util.List;

public interface DuctSizerService {

    DuctSizerCalcResponse calculate(DuctSizerCalcRequest request);

    List<DuctSizerLookupResponse> getDuctSizerLookup();
}
