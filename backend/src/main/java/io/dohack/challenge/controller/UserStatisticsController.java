package io.dohack.challenge.controller;

import io.dohack.challenge.domain.UserDailyStatistics;
import io.dohack.challenge.dto.CreateDailyStatisticDto;
import io.dohack.challenge.service.statistic.CreateDailyStatisticService;
import io.dohack.challenge.service.statistic.ReadDailyStatisticsService;
import io.dohack.challenge.service.statistic.imlp.ReadDailyStatisticsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserStatisticsController {
    private final CreateDailyStatisticService createDailyStatisticService;
    private final ReadDailyStatisticsService readDailyStatisticsService;

    @PostMapping("statistics/daily/create")
    UserDailyStatistics createDailyStatistic(@RequestBody CreateDailyStatisticDto dto) {
        return createDailyStatisticService.createUserDailyStatistic(dto);
    }

    @GetMapping("statistics/daily/{username}/current")
    Optional<UserDailyStatistics> getTodayDailyStatistic(@PathVariable("username") String username) {
        Optional<UserDailyStatistics> userDailyStatistics = readDailyStatisticsService.readTodayStatisticForUser(username);
        if (userDailyStatistics.isPresent()) {
            return userDailyStatistics;
        }
        throw new ResourceNotFoundException();

    }

    @GetMapping("statistics/daily/{username}/all")
    List<UserDailyStatistics> getAllDailyStatistic(@PathVariable("username") String username) {
        return readDailyStatisticsService.readAllDailyStatisticsForUser(username);
    }
}
