package io.dohack.challenge.service;

import io.dohack.challenge.domain.MockedSensorValue;
import io.dohack.challenge.repositories.MockedSensorValueRepository;
import io.dohack.challenge.util.SeatNames;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MockElectricSensorService {

    private static final Double RANGE_MINIMUM = 5.;
    private static final Double RANGE_MAXIMUM = 10.;

    private final MockedSensorValueRepository mockedSensorValueRepository;

    @Scheduled(fixedRate = 600000)
    public void readMockedSensorValues() {
        mockedSensorValueRepository.save(
                new MockedSensorValue(SeatNames.ANTON_SEAT, randomizeValue(), LocalDateTime.now())
        );
        mockedSensorValueRepository.save(
                new MockedSensorValue(SeatNames.CHRISTOPH_SEAT, randomizeValue(), LocalDateTime.now())
        );
        mockedSensorValueRepository.save(
                new MockedSensorValue(SeatNames.JOHANNES_SEAT, randomizeValue(), LocalDateTime.now())
        );
        mockedSensorValueRepository.save(
                new MockedSensorValue(SeatNames.KAI_SEAT, randomizeValue(), LocalDateTime.now())
        );
        mockedSensorValueRepository.save(
                new MockedSensorValue(SeatNames.MARIUS_SEAT, randomizeValue(), LocalDateTime.now())
        );
        mockedSensorValueRepository.save(
                new MockedSensorValue(SeatNames.SILAS_SEAT, randomizeValue(), LocalDateTime.now())
        );
    }

    static Double randomizeValue() {
        Random r = new Random();
        return RANGE_MINIMUM + (RANGE_MAXIMUM - RANGE_MINIMUM) * r.nextDouble();
    }
}
