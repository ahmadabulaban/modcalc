package com.insanwalat.modcalc.controller;

import com.insanwalat.modcalc.ductsizer.module.request.DuctSizerCalcRequest;
import com.insanwalat.modcalc.ductsizer.module.response.DuctSizerCalcResponse;
import com.insanwalat.modcalc.ductsizer.module.response.DuctSizerLookupResponse;
import com.insanwalat.modcalc.ductsizer.service.impl.DuctSizerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DuctSizerController {

    @Autowired
    private DuctSizerServiceImpl ductSizerService;

    @CrossOrigin
    @RequestMapping(path = "/duct-sizer/calculate", method = RequestMethod.POST)
    public DuctSizerCalcResponse calculateDuctSizer(@RequestBody DuctSizerCalcRequest request) {
        return ductSizerService.calculate(request);
    }

    @CrossOrigin
    @RequestMapping(path = "/duct-sizer/lookup-data", method = RequestMethod.GET)
    public List<DuctSizerLookupResponse> getDuctSizerLookupData() {
        return ductSizerService.getDuctSizerLookup();
    }
}
