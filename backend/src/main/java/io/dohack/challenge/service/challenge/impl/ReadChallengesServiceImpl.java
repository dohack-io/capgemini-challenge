package io.dohack.challenge.service.challenge.impl;

import io.dohack.challenge.domain.DailyChallenge;
import io.dohack.challenge.repositories.DailyChallengeRepository;
import io.dohack.challenge.service.challenge.ReadChallengesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReadChallengesServiceImpl implements ReadChallengesService {

    private final DailyChallengeRepository dailyChallengeRepository;

    @Override
    public Optional<DailyChallenge> readTodayChallenge() {
        return dailyChallengeRepository.findByDate(LocalDate.now());
    }

    @Override
    public Optional<DailyChallenge> readChallengeByDate(LocalDate date) {
        return dailyChallengeRepository.findByDate(date);
    }

    @Override
    public List<DailyChallenge> readAllDailyChallenges() {
        return dailyChallengeRepository.findAll();
    }
}
