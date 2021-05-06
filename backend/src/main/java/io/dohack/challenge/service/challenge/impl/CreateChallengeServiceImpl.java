package io.dohack.challenge.service.challenge.impl;

import io.dohack.challenge.domain.DailyChallenge;
import io.dohack.challenge.dto.CreateChallengeDto;
import io.dohack.challenge.repositories.DailyChallengeRepository;
import io.dohack.challenge.service.challenge.CreateChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateChallengeServiceImpl implements CreateChallengeService {

    private final DailyChallengeRepository dailyChallengeRepository;

    @Override
    public DailyChallenge createDailyChallenge(CreateChallengeDto dto) {
        DailyChallenge dailyChallenge = new DailyChallenge(
                null, dto.getDescription(), dto.getDate(), dto.getChallengePoints()
        );
        return dailyChallengeRepository.save(dailyChallenge);
    }
}
