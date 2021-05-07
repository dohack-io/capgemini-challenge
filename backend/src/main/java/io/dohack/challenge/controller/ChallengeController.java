package io.dohack.challenge.controller;

import io.dohack.challenge.domain.DailyChallenge;
import io.dohack.challenge.dto.CreateChallengeDto;
import io.dohack.challenge.service.challenge.CreateChallengeService;
import io.dohack.challenge.service.challenge.ReadChallengesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ChallengeController {
    private final CreateChallengeService createChallengeService;
    private final ReadChallengesService readChallengesService;

    @GetMapping("challenge/daily")
    Optional<DailyChallenge> getDailyChallenge() {
        Optional<DailyChallenge> dailyChallenge = readChallengesService.readTodayChallenge();
        if (dailyChallenge.isPresent()) {
            return dailyChallenge;
        }
        throw new ResourceNotFoundException();
    }

    @GetMapping("challenge/all")
    List<DailyChallenge> getAllDailyChallenge() {
        return readChallengesService.readAllDailyChallenges();
    }

    @GetMapping("challenge/date/{date}")
    Optional<DailyChallenge> getDailyChallengeByDate(@PathVariable("date") String date) {
        LocalDate localDate = LocalDate.parse(date);
        Optional<DailyChallenge> dailyChallenge = readChallengesService.readChallengeByDate(localDate);
        if (dailyChallenge.isPresent()) {
            return dailyChallenge;
        }
        throw new ResourceNotFoundException();
    }

    @PostMapping("challenge/create")
    DailyChallenge createDailyChallenge(@RequestBody CreateChallengeDto dto) {
        return createChallengeService.createDailyChallenge(dto);
    }
}
