package com.insanwalat.modcalc.fanesp.repository;

import com.insanwalat.modcalc.fanesp.exceptions.InvalidFanEspSaveInputException;
import com.insanwalat.modcalc.fanesp.module.input.FanEspSaveInput;
import com.insanwalat.modcalc.fanesp.module.output.FanEspLoadOutput;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class FanEspMockRepository implements FanEspRepository {

    Map<String, FanEspLoadOutput> outputMap = new HashMap<>();

    @Override
    public void save(FanEspSaveInput fanEspSaveInput) {
        if (outputMap.containsKey(fanEspSaveInput.getName()))
            throw new InvalidFanEspSaveInputException("Name Already Exist");
        FanEspLoadOutput fanEspLoadOutput = new FanEspLoadOutput();
        fanEspLoadOutput.setName(fanEspSaveInput.getName());
        fanEspLoadOutput.setFanEspCalcInput(fanEspSaveInput.getFanEspCalcInput());
        fanEspLoadOutput.setDate(new Date().toString());
        outputMap.put(fanEspSaveInput.getName(), fanEspLoadOutput);
    }

    @Override
    public List<FanEspLoadOutput> load() {
        return new ArrayList<>(outputMap.values());
    }
}
