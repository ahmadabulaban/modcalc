package com.insanwalat.modcalc.controller;

import com.insanwalat.modcalc.module.request.DuctSizerCalcRequest;
import com.insanwalat.modcalc.module.response.DuctSizerCalcResponse;
import com.insanwalat.modcalc.module.response.DuctSizerLookupResponse;
import com.insanwalat.modcalc.service.DuctSizerService;
import com.insanwalat.modcalc.service.impl.DuctSizerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.servlet.http.HttpServletResponseWrapper;
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
