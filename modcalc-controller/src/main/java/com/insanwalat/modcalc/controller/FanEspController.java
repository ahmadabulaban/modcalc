package com.insanwalat.modcalc.controller;

import com.insanwalat.modcalc.fanesp.module.request.FanEspCalcRequest;
import com.insanwalat.modcalc.fanesp.module.response.FanEspCalcResponse;
import com.insanwalat.modcalc.fanesp.module.response.FanEspCoefficientDataLookupResponse;
import com.insanwalat.modcalc.fanesp.module.response.FanEspCoefficientLookupResponse;
import com.insanwalat.modcalc.fanesp.module.response.FanEspLookupResponse;
import com.insanwalat.modcalc.fanesp.service.impl.FanEspServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FanEspController {

    @Autowired
    private FanEspServiceImpl fanEspService;

    @CrossOrigin
    @RequestMapping(path = "/fan-esp/calculate", method = RequestMethod.POST)
    public FanEspCalcResponse calculateFanEsp(@RequestBody FanEspCalcRequest request) {
        return fanEspService.calculate(request);
    }

    @CrossOrigin
    @RequestMapping(path = "/fan-esp/lookup", method = RequestMethod.GET)
    public List<FanEspLookupResponse> getFanEspLookup() {
        return fanEspService.getFanEspLookup();
    }

    @CrossOrigin
    @RequestMapping(path = "/fan-esp/lookup-coefficient", method = RequestMethod.GET)
    public List<FanEspCoefficientLookupResponse> getFanEspCoefficientLookup() {
        return fanEspService.getFanEspCoefficientLookup();
    }

    @CrossOrigin
    @RequestMapping(path = "/fan-esp/lookup-coefficient-data", method = RequestMethod.GET)
    public List<FanEspCoefficientDataLookupResponse> getFanEspCoefficientDataLookup() {
        return fanEspService.getFanEspCoefficientDataLookup();
    }
}
