package io.dohack.challenge.service.statistic;

import io.dohack.challenge.domain.UserDailyStatistics;

import java.util.List;

public interface ReadDailyStatisticsService {
    UserDailyStatistics readTodayStatisticForUser(String username);
    List<UserDailyStatistics> readAllDailyStatisticsForUser(String username);
}
