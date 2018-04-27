package com.insanwalat.modcalc.controller;

import com.insanwalat.modcalc.module.request.DuctSizerCalcRequest;
import com.insanwalat.modcalc.module.response.DuctSizerCalcResponse;
import com.insanwalat.modcalc.module.response.DuctSizerLookupResponse;
import com.insanwalat.modcalc.service.DuctSizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DuctSizerController {

    @Autowired
    private DuctSizerService ductSizerService;

    @CrossOrigin
    @RequestMapping(path = "/modcalc/ductsizer/calculate", method = RequestMethod.POST)
    public DuctSizerCalcResponse calculateDuctSizer(@RequestBody DuctSizerCalcRequest request) {
        return ductSizerService.calculate(request);
    }

    @CrossOrigin
    @RequestMapping(path = "/modcalc/ductsizer/lookup-data", method = RequestMethod.GET)
    public List<DuctSizerLookupResponse> getDuctSizerLookupData() {
        return ductSizerService.getDuctSizerLookup();
    }
}
