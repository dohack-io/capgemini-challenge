package io.dohack.challenge.service;

import io.dohack.challenge.domain.CommuteType;
import io.dohack.challenge.domain.User;
import io.dohack.challenge.repositories.UserRepository;
import io.dohack.challenge.util.SeatNames;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StartUpDataService implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepository;
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        userRepository.save(
                User.builder()
                        .username("anton")
                        .password("anton123")
                        .email("anton.penner@example.io")
                        .displayName("Anton Penner")
                        .points(0.0)
                        .co2Score(0.0)
                        .seat(SeatNames.ANTON_SEAT)
                        .defaultCommuteDistance(20.0)
                        .defaultCommuteType(CommuteType.BUS)
                        .build()
        );
        userRepository.save(
                User.builder()
                        .username("christoph")
                        .password("christoph123")
                        .email("c.w@example.io")
                        .displayName("Christoph W.")
                        .points(0.0)
                        .co2Score(0.0)
                        .seat(SeatNames.CHRISTOPH_SEAT)
                        .defaultCommuteDistance(20.0)
                        .defaultCommuteType(CommuteType.HYBRID_CAR)
                        .build()
        );
        userRepository.save(
                User.builder()
                        .username("johannes")
                        .password("johannes123")
                        .email("j.t@example.io")
                        .displayName("Johannes T.")
                        .points(0.0)
                        .co2Score(0.0)
                        .seat(SeatNames.JOHANNES_SEAT)
                        .defaultCommuteDistance(20.0)
                        .defaultCommuteType(CommuteType.BIKE)
                        .build()
        );
        userRepository.save(
                User.builder()
                        .username("kai")
                        .password("kai123")
                        .email("k.s@example.io")
                        .displayName("Kai S.")
                        .points(0.0)
                        .co2Score(0.0)
                        .seat(SeatNames.KAI_SEAT)
                        .defaultCommuteDistance(20.0)
                        .defaultCommuteType(CommuteType.WALKING)
                        .build()
        );
        userRepository.save(
                User.builder()
                        .username("marius")
                        .password("marius123")
                        .email("m.w@example.io")
                        .displayName("Marius W.")
                        .points(0.0)
                        .co2Score(0.0)
                        .seat(SeatNames.MARIUS_SEAT)
                        .defaultCommuteDistance(20.0)
                        .defaultCommuteType(CommuteType.TRAM)
                        .build()
        );
        userRepository.save(
                User.builder()
                        .username("silas")
                        .password("silas123")
                        .email("s.m@example.io")
                        .displayName("Silas M.")
                        .points(0.0)
                        .co2Score(0.0)
                        .seat(SeatNames.SILAS_SEAT)
                        .defaultCommuteDistance(20.0)
                        .defaultCommuteType(CommuteType.CAR)
                        .build()
        );
    }
}
