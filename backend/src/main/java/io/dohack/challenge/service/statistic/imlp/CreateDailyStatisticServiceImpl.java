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
                    .commuteList(dto.getDailyCommuteList().stream().map( createDailyCommuteDto -> new Commute(null,
                            createDailyCommuteDto.getDistance(), getTypeFromString(createDailyCommuteDto.getType(),
                            user.get()))).collect(Collectors.toList()))
                    .build();
            statistics.setPointsEarned(calculateScore(statistics));
            statistics.setCo2(calculateCo2Score(statistics));
            user.get().getUserDailyStatisticsList().add(statistics);
            user.get().setCo2Score(
                    user.get().getUserDailyStatisticsList().stream().mapToDouble(
                            UserDailyStatistics::getCo2
                    ).average().orElse(-1)
            );
            if (user.get().getLevelProgression() + user.get().getPoints().intValue() > user.get().getLevelUpThreshold()) {
                user.get().setLevel(user.get().getLevel() + 1);
                user.get().setLevelProgression(user.get().getLevelProgression() + user.get().getPoints().intValue() - user.get().getLevelUpThreshold());
            } else {
                user.get().setLevelProgression(user.get().getLevelProgression() + user.get().getPoints().intValue());
            }
            userRepository.save(user.get());
        }
        return null;
    }

    //calculation in mKg
    private Double calculateCo2Score(UserDailyStatistics statistics) {
        return GROUND_SCORE +
                (statistics.getNumberOfCoffees() * .075) +
                statistics.getEnergyConsumption() +
                (statistics.getLunchScore()) +
                statistics.getCommuteList().stream()
                        .map(commute -> (commute.getType().value * commute.getDistance() / 1000))
                        .reduce(0.0, Double::sum);
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

    private CommuteType getTypeFromString(String type, User user) {
        CommuteType result = null;
        switch (type) {
            case "WALKING":  result = CommuteType.WALKING;
                break;
            case "BIKE":  result = CommuteType.BIKE;
                break;
            case "CAR":  result = CommuteType.CAR;
                break;
            case "CAR_POOL":  result = CommuteType.CAR_POOL;
                break;
            case "BUS":  result = CommuteType.BUS;
                break;
            case "TRAM":  result = CommuteType.TRAM;
                break;
            case "PLANE":  result = CommuteType.PLANE;
                break;
            case "E_BIKE":  result = CommuteType.E_BIKE;
                break;
            case "E_SCOOTER":  result = CommuteType.E_SCOOTER;
                break;
            case "E_CAR":  result = CommuteType.E_CAR;
                break;
            case "HYBRID_CAR":  result = CommuteType.HYBRID_CAR;
                break;
            default: result = user.getDefaultCommuteType();
        }
        return result;
    }
}
