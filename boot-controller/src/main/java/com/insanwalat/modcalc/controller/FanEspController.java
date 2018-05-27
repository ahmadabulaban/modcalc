package com.insanwalat.modcalc.controller;

import com.insanwalat.modcalc.fanesp.module.request.FanEspCalcRequest;
import com.insanwalat.modcalc.fanesp.module.response.FanEspCalcResponse;
import com.insanwalat.modcalc.fanesp.service.impl.FanEspServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FanEspController {

    @Autowired
    private FanEspServiceImpl fanEspService;

    @CrossOrigin
    @RequestMapping(path = "/modcalc-deployment/fan-esp/calculate", method = RequestMethod.POST)
    public FanEspCalcResponse calculateFanEsp(@RequestBody FanEspCalcRequest request) {
        try {
            return fanEspService.calculate(request);
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
