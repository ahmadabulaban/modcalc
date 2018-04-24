package com.insanwalat.modcalc.controller;

import com.insanwalat.modcalc.module.lookup.DuctSizerLookup;
import com.insanwalat.modcalc.module.request.DuctSizerCalcRequest;
import com.insanwalat.modcalc.module.response.DuctSizerCalcResponse;
import com.insanwalat.modcalc.service.DuctSizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DuctSizerController {

    @Autowired
    private DuctSizerService ductSizerService;

    @RequestMapping(path = "/modcalc/ductsizer/calculate",method = RequestMethod.POST)
    public DuctSizerCalcResponse calculateDuctSizer(@RequestBody DuctSizerCalcRequest request){
        return ductSizerService.calculate(request);
    }

    @RequestMapping(path = "/modcalc/ductsizer/lookupData",method = RequestMethod.GET)
    public List<DuctSizerLookup> calculateDuctSizer(){
        return ductSizerService.getDuctSizerLookup();
    }
}
