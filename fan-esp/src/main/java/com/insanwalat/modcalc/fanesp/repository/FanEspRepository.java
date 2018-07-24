package com.insanwalat.modcalc.fanesp.repository;

import com.insanwalat.modcalc.fanesp.module.input.FanEspSaveInput;
import com.insanwalat.modcalc.fanesp.module.output.FanEspLoadOutput;

import java.util.List;

public interface FanEspRepository {
    void save(FanEspSaveInput fanEspSaveInput);
    List<FanEspLoadOutput> load();
}
