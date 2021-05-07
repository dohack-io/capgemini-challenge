package io.dohack.challenge.service.challenge;

import io.dohack.challenge.domain.DailyChallenge;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReadChallengesService {
    Optional<DailyChallenge> readTodayChallenge();
    Optional<DailyChallenge> readChallengeByDate(LocalDate date);
    List<DailyChallenge> readAllDailyChallenges();
}
