package com.insanwalat.modcalc.configuration;

import com.insanwalat.modcalc.service.DuctSizerService;
import com.insanwalat.modcalc.service.impl.DuctSizerServiceImpl;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class DuctSizerConfiguration {

    @Bean
    public DuctSizerService getDuctSizerService(){
        return new DuctSizerServiceImpl();
    }
}
