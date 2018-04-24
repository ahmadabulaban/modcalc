package com.insanwalat.modcalc.service;

import com.insanwalat.modcalc.module.lookup.DuctSizerLookup;
import com.insanwalat.modcalc.module.request.DuctSizerCalcRequest;
import com.insanwalat.modcalc.module.response.DuctSizerCalcResponse;

import java.util.List;

public interface DuctSizerService {

    DuctSizerCalcResponse calculate(DuctSizerCalcRequest request);

    List<DuctSizerLookup> getDuctSizerLookup();
}
