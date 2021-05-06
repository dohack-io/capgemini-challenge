package io.dohack.challenge.service.statistic;

import io.dohack.challenge.domain.UserDailyStatistics;
import io.dohack.challenge.dto.CreateDailyStatisticDto;

public interface CreateDailyStatisticService {
    UserDailyStatistics createUserDailyStatistic(CreateDailyStatisticDto dto);
}
