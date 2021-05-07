package io.dohack.challenge.service.highscore.impl;

import io.dohack.challenge.domain.User;
import io.dohack.challenge.domain.UserDailyStatistics;
import io.dohack.challenge.dto.HighScoreDto;
import io.dohack.challenge.repositories.UserRepository;
import io.dohack.challenge.service.highscore.CalculateLeaderBoardService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalculateLeaderBoardServiceImpl implements CalculateLeaderBoardService {
    private final UserRepository userRepository;

    @Override
    public List<HighScoreDto> calculateCurrentLeaderBoard() {
        return userRepository.findAll()
                .stream()
                .filter(it -> it.getCo2Score() >= 0)
                .sorted(Comparator.comparingDouble(User::getCo2Score))
                .map(it -> new HighScoreDto(it.getDisplayName(),
                        it.getCo2Score(),
                        calculateAverageLunchScorePerMonth(it),
                        calculateAverageDailyChallengesPerMonth(it),
                        calculateAverageCo2ScorePerMonth(it)))
                .collect(Collectors.toList());
    }

    Double calculateAverageLunchScorePerMonth(User user) {
        Month month = LocalDate.now().getMonth();
        return user.getUserDailyStatisticsList().stream()
                .filter(it -> it.getDay().getMonth().equals(month))
                .mapToDouble(UserDailyStatistics::getLunchScore).average().orElse(-1);
    }

    Double calculateAverageDailyChallengesPerMonth(User user) {
        boolean isLeapYear = LocalDate.now().isLeapYear();
        Month month = LocalDate.now().getMonth();
        return (double) (user.getUserDailyStatisticsList().stream()
                .filter(it -> it.getDay().getMonth().equals(month))
                .filter(it -> it.getDailyChallengePoints() > 0)
                .count() / month.length(isLeapYear));
    }

    Double calculateAverageCo2ScorePerMonth(User user) {
        Month month = LocalDate.now().getMonth();
        return user.getUserDailyStatisticsList().stream()
                .filter(it -> it.getDay().getMonth().equals(month))
                .mapToDouble(UserDailyStatistics::getCo2).average().orElse(-1);
    }
}
