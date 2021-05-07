package io.dohack.challenge.service.statistic;

import io.dohack.challenge.domain.UserDailyStatistics;

import java.util.List;
import java.util.Optional;

public interface ReadDailyStatisticsService {
    Optional<UserDailyStatistics> readTodayStatisticForUser(String username);
    List<UserDailyStatistics> readAllDailyStatisticsForUser(String username);
}
