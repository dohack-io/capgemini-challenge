package io.dohack.challenge.service;

import io.dohack.challenge.domain.*;
import io.dohack.challenge.repositories.DailyChallengeRepository;
import io.dohack.challenge.repositories.RewardsRepository;
import io.dohack.challenge.repositories.UserRepository;
import io.dohack.challenge.service.statistic.CreateDailyStatisticService;
import io.dohack.challenge.service.statistic.imlp.CreateDailyStatisticServiceImpl;
import io.dohack.challenge.util.SeatNames;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class StartUpDataService implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepository;
    private final MockElectricSensorService mockElectricSensorService;
    private final DailyChallengeRepository dailyChallengeRepository;
    private final RewardsRepository rewardsRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        userRepository.save(
                User.builder()
                        .username("anton")
                        .password("anton123")
                        .email("anton.penner@example.io")
                        .displayName("Anton Penner")
                        .points(0.0)
                        .co2Score(999.0)
                        .seat(SeatNames.ANTON_SEAT)
                        .defaultCommuteDistance(20.0)
                        .defaultCommuteType(CommuteType.BUS)
                        .level(7)
                        .levelProgression(getRandomLevelProgression())
                        .levelUpThreshold(1000)
                        .userDailyStatisticsList(getMockedUserDailyList())
                        .url("https://bonding.de/wp-content/uploads/2020/10/Anton-01-02.png")
                        .build()
        );
        userRepository.save(
                User.builder()
                        .username("christoph")
                        .password("christoph123")
                        .email("c.w@example.io")
                        .displayName("Christoph W.")
                        .points(0.0)
                        .co2Score(20.0)
                        .seat(SeatNames.CHRISTOPH_SEAT)
                        .defaultCommuteDistance(20.0)
                        .defaultCommuteType(CommuteType.HYBRID_CAR)
                        .level(7)
                        .levelProgression(getRandomLevelProgression())
                        .levelUpThreshold(1000)
                        .userDailyStatisticsList(getMockedUserDailyList())
                        .url("https://dohack.io/static/b64a216499a617a34a4ec2dfe637b756/497c6/Christoph_400x400.png")
                        .build()
        );
        userRepository.save(
                User.builder()
                        .username("johannes")
                        .password("johannes123")
                        .email("j.t@example.io")
                        .displayName("Johannes T.")
                        .points(0.0)
                        .co2Score(15.0)
                        .seat(SeatNames.JOHANNES_SEAT)
                        .defaultCommuteDistance(20.0)
                        .defaultCommuteType(CommuteType.BIKE)
                        .level(7)
                        .levelProgression(getRandomLevelProgression())
                        .levelUpThreshold(1000)
                        .userDailyStatisticsList(getMockedUserDailyList())
                        .url("https://dohack.io/static/f05dda00da2d34cf601e62c61b5efd52/497c6/Johannes_400x400.png")
                        .build()
        );
        userRepository.save(
                User.builder()
                        .username("kai")
                        .password("kai123")
                        .email("k.s@example.io")
                        .displayName("Kai S.")
                        .points(0.0)
                        .co2Score(111.0)
                        .seat(SeatNames.KAI_SEAT)
                        .defaultCommuteDistance(20.0)
                        .defaultCommuteType(CommuteType.WALKING)
                        .level(7)
                        .levelProgression(getRandomLevelProgression())
                        .levelUpThreshold(1000)
                        .userDailyStatisticsList(getMockedUserDailyList())
                        .url("https://dohack.io/static/c4b3350268a30def15da089836c6ca1e/497c6/Kai_400x400.png")
                        .build()
        );
        userRepository.save(
                User.builder()
                        .username("marius")
                        .password("marius123")
                        .email("m.w@example.io")
                        .displayName("Marius W.")
                        .points(0.0)
                        .co2Score(30.0)
                        .seat(SeatNames.MARIUS_SEAT)
                        .defaultCommuteDistance(20.0)
                        .defaultCommuteType(CommuteType.TRAM)
                        .level(7)
                        .levelProgression(getRandomLevelProgression())
                        .levelUpThreshold(1000)
                        .userDailyStatisticsList(getMockedUserDailyList())
                        .url("https://dohack.io/static/cee080b8c89b5bf73b332cc6783eb248/497c6/Igor_400x400.png")
                        .build()
        );
        userRepository.save(
                User.builder()
                        .username("Silas")
                        .password("silas")
                        .email("s.m@example.io")
                        .displayName("Silas M.")
                        .points(0.0)
                        .co2Score(0.0)
                        .seat(SeatNames.SILAS_SEAT)
                        .defaultCommuteDistance(20.0)
                        .defaultCommuteType(CommuteType.CAR)
                        .level(7)
                        .levelProgression(getRandomLevelProgression())
                        .levelUpThreshold(1000)
                        .userDailyStatisticsList(getMockedUserDailyList())
                        .url("https://dohack.io/static/98a8503d5ed05e83c69fa79f519bf47b/497c6/Silas_400x400.png")
                        .build()
        );
        mockElectricSensorService.readMockedSensorValues();
        dailyChallengeRepository.save(
                new DailyChallenge(
                        null,
                        "Heute Radln",
                        "Mit Rad zu Arbeit gefahren",
                        LocalDate.of(2021, 5, 4),
                        10.,
                        "https://images.unsplash.com/photo-1598711169429-4e0073636edf?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80"
                )
        );
        dailyChallengeRepository.save(
                new DailyChallenge(
                        null,
                        "Weniger Kaffee",
                        "Heute nur 10 Tassen Kaffee getrunken",
                        LocalDate.of(2021, 5, 5),
                        10.,
                        "https://images.unsplash.com/photo-1576169884631-382813d0090e?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8Y29mZmVlJTIwbGVzcyUyMGxlYXZlfGVufDB8fDB8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"
                )
        );
        dailyChallengeRepository.save(
                new DailyChallenge(
                        null,
                        "Geh mal lieber",
                        "Keinen Kilometer CO2-Austoß",
                        LocalDate.of(2021, 5, 6),
                        10.,
                        "https://images.unsplash.com/photo-1569992274375-e56b14e234f1?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzV8fHdhbGtpbmclMjBuYXR1cmV8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"
                )
        );
        dailyChallengeRepository.save(
                new DailyChallenge(
                        null,
                        "Einer reicht",
                        "Komme heute nur mit einem Bildschirm aus!",
                        LocalDate.of(2021, 5, 7),
                        10.,
                        "https://images.unsplash.com/photo-1576145789346-ebf75e1c5c76?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTl8fHdvcmtwbGFjZSUyMHNjcmVlbnxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"
                )
        );
        dailyChallengeRepository.save(
                new DailyChallenge(
                        null,
                        "Big Brain Time",
                        "Denke nach bevor du googlest!",
                        LocalDate.of(2021, 5, 8),
                        10.,
                        "https://images.unsplash.com/photo-1530973428-5bf2db2e4d71?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8YnJhaW58ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60"
                )
        );
        rewardsRepository.save(
                new Rewards(
                        null,
                        "Wir Pflanzen einen Baum für dich im Amazonas",
                        100.0
                )
        );
        rewardsRepository.save(
                new Rewards(
                        null,
                        "Eine Packung unserer besten Fair-Trade Schokolade",
                        200.0
                )
        );
        rewardsRepository.save(
                new Rewards(
                        null,
                        "Eine Packung unseres besten Fair-Trade Kaffees",
                        400.0
                )
        );
        rewardsRepository.save(
                new Rewards(
                        null,
                        "Ein Gutschein für ein veganes Essen",
                        1000.0
                )
        );
        rewardsRepository.save(
                new Rewards(
                        null,
                        "Ein Gutschein für einen Shop deiner Wahl über drölfzig Euros",
                        2000.0
                )
        );
    }


    Integer getRandomLevelProgression() {
        return ThreadLocalRandom.current().nextInt(1, 900 + 1);
    }

    Integer getRandomCoffee() {
        return ThreadLocalRandom.current().nextInt(0, 5 + 1);
    }

    Double getRandomLunchScore() {
        Random r = new Random();
        return 1. + (10. - 1.) * r.nextDouble();
    }

    Double getDailyChallengePoints() {
        Random r = new Random();
        return 8. + (10. - 8.) * r.nextDouble();
    }

    List<UserDailyStatistics> getMockedUserDailyList() {
        List<UserDailyStatistics> statistics = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            UserDailyStatistics mock = new UserDailyStatistics(
                    null,
                    LocalDate.of(2021, 5, i),
                    getRandomCoffee(),
                    MockElectricSensorService.randomizeValue(),
                    getRandomLunchScore(),
                    getDailyChallengePoints(),
                    0.,
                    0.,
                    i,
                    getRandomLevelProgression(),
                    1000,
                    getRandomCommuteList()
            );
            mock.setPointsEarned(CreateDailyStatisticServiceImpl.calculateScore(mock));
            mock.setCo2(CreateDailyStatisticServiceImpl.calculateCo2Score(mock));
            statistics.add(
                  mock
            );
        }
        return statistics;
    }

    List<Commute> getRandomCommuteList() {
        List<Commute> commutes = new ArrayList<>();
        Integer numberOfCommutes = ThreadLocalRandom.current().nextInt(0, 3 + 1);
        for (int i = 0; i < numberOfCommutes; i++) {
            Integer randomCommuteNumber = ThreadLocalRandom.current().nextInt(0, 11 + 1);
            Double randomDistance = 10. + (50. - 10.) * ThreadLocalRandom.current().nextDouble();
            CommuteType commuteType = CommuteType.values()[randomCommuteNumber];
            commutes.add(
                    new Commute(
                            null,
                            randomDistance,
                            commuteType
                    )
            );
        }
        return commutes;
    }
}
