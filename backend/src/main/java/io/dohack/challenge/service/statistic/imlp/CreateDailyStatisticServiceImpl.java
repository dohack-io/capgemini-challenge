package io.dohack.challenge.service.statistic.imlp;

import io.dohack.challenge.domain.*;
import io.dohack.challenge.dto.CreateDailyStatisticDto;
import io.dohack.challenge.repositories.DailyChallengeRepository;
import io.dohack.challenge.repositories.MockedSensorValueRepository;
import io.dohack.challenge.repositories.UserRepository;
import io.dohack.challenge.service.statistic.CreateDailyStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreateDailyStatisticServiceImpl implements CreateDailyStatisticService {
    private final DailyChallengeRepository dailyChallengeRepository;
    private final UserRepository userRepository;
    private final MockedSensorValueRepository mockedSensorValueRepository;

    private static final int GROUND_SCORE = 50;

    @Override
    public UserDailyStatistics createUserDailyStatistic(CreateDailyStatisticDto dto) {
        Optional<User> user = userRepository.findById(dto.getUsername());
        if (user.isPresent()) {
            Optional<MockedSensorValue> mockedSensorValue = mockedSensorValueRepository.findById(user.get().getSeat());
            Optional<DailyChallenge> dailyChallenge = dailyChallengeRepository.findByDate(LocalDate.now());

            UserDailyStatistics statistics = UserDailyStatistics.builder()
                    .day(LocalDate.now())
                    .numberOfCoffees(dto.getNumberOfCoffees())
                    .energyConsumption(mockedSensorValue.isPresent() ? mockedSensorValue.get().getValue() : 100)
                    .lunchScore(dto.getLunchScore())
                    .dailyChallengePoints(dailyChallenge.isPresent() ? dailyChallenge.get().getChallengePoints() : 0.0)
                    .commuteList(dto.getDailyCommuteList().stream().map( createDailyCommuteDto -> new Commute(null, createDailyCommuteDto.getDistance(), createDailyCommuteDto.getType())).collect(Collectors.toList()))
                    .build();
            statistics.setPointsEarned(calculateScore(statistics));
            user.get().getUserDailyStatisticsList().add(statistics);
            userRepository.save(user.get());
        }
        return null;
    }

    private Double calculateScore(UserDailyStatistics statistics) {
        return GROUND_SCORE +
                (statistics.getNumberOfCoffees() * -2) +
                (statistics.getEnergyConsumption() * -2) +
                (statistics.getLunchScore() * -3) +
                statistics.getCommuteList().stream()
                        .map(commute -> (commute.getType().value * commute.getDistance() / 100))
                        .reduce(0.0, Double::sum) +
                statistics.getDailyChallengePoints();
    }
}
