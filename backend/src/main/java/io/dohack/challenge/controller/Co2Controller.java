package io.dohack.challenge.controller;

import io.dohack.challenge.service.Co2CalcService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Co2Controller {

    private Co2CalcService co2CalcService;

    @GetMapping("/test_commute")
    @ResponseStatus(HttpStatus.OK)
    public String getCommuteConfig() {
        return co2CalcService.Co2CommuteConfigtoString();
    }

    @GetMapping("/test_grocery")
    @ResponseStatus(HttpStatus.OK)
    public String getGroceryConfig() {
        return co2CalcService.Co2GroceryConfigtoString();
    }



}
