package io.dohack.challenge.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service("MockElectricSensorService")
public class MockElectricSensorService {

    private static final Double RANGE_MINIMUM = 50.0;
    private static final Double RANGE_MAXIMUM = 100.0;

    @Scheduled
    private void readMockedSensorValues() {

    }

    private Double randomizeValue() {
        Random r = new Random();
        return RANGE_MINIMUM + (RANGE_MAXIMUM - RANGE_MINIMUM) * r.nextDouble();
    }
}
