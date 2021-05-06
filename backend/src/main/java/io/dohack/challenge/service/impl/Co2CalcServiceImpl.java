package io.dohack.challenge.service.impl;

import io.dohack.challenge.config.Co2CommuteConfig;
import io.dohack.challenge.config.Co2GroceryConfig;
import io.dohack.challenge.service.Co2CalcService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log
public class Co2CalcServiceImpl implements Co2CalcService {


    private Co2CommuteConfig co2CommuteConfig;
    private Co2GroceryConfig co2GroceryConfig;

    public String Co2CommuteConfigtoString(){
        log.info("Config loaded: " + co2CommuteConfig.toString() );
        return co2CommuteConfig.toString();
    }

    public String Co2GroceryConfigtoString(){
        log.info("Config loaded: " + co2GroceryConfig.toString() );
        return co2GroceryConfig.toString();
    }


}
