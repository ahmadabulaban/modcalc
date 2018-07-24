package com.insanwalat.modcalc.controller;

import com.insanwalat.modcalc.fanesp.module.request.FanEspCalcRequest;
import com.insanwalat.modcalc.fanesp.module.request.FanEspSaveRequest;
import com.insanwalat.modcalc.fanesp.module.response.*;
import com.insanwalat.modcalc.fanesp.service.FanEspService;
import com.insanwalat.modcalc.fanesp.service.impl.FanEspServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FanEspController {

    @Autowired
    private FanEspService fanEspService;

    @CrossOrigin
    @RequestMapping(path = "/modcalc-controller/fan-esp/calculate", method = RequestMethod.POST)
    public FanEspCalcResponse calculateFanEsp(@RequestBody FanEspCalcRequest request) {
        try {
            return fanEspService.calculate(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @CrossOrigin
    @RequestMapping(path = "/modcalc-controller/fan-esp/save", method = RequestMethod.POST)
    public FanEspSaveResponse saveFanEsp(@RequestBody FanEspSaveRequest request) {
        try {
            return fanEspService.save(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @CrossOrigin
    @RequestMapping(path = "/modcalc-controller/fan-esp/load", method = RequestMethod.GET)
    public List<FanEspLoadResponse> getSavedFanEsp() {
        try {
            return fanEspService.load();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @CrossOrigin
    @RequestMapping(path = "/modcalc-controller/fan-esp/lookup", method = RequestMethod.GET)
    public List<FanEspLookupResponse> getFanEspLookup() {
        return fanEspService.getFanEspLookup();
    }

    @CrossOrigin
    @RequestMapping(path = "/modcalc-controller/fan-esp/lookup-coefficient", method = RequestMethod.GET)
    public List<FanEspCoefficientLookupResponse> getFanEspCoefficientLookup() {
        return fanEspService.getFanEspCoefficientLookup();
    }

    @CrossOrigin
    @RequestMapping(path = "/modcalc-controller/fan-esp/lookup-coefficient-data", method = RequestMethod.GET)
    public List<FanEspCoefficientDataLookupResponse> getFanEspCoefficientDataLookup() {
        return fanEspService.getFanEspCoefficientDataLookup();
    }
}
