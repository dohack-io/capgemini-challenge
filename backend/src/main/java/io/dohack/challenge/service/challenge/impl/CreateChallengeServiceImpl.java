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
                null, dto.getTitle(), dto.getDescription(), dto.getDate(), dto.getChallengePoints(),
                dto.getUrl().isBlank() ? "https://images.unsplash.com/photo-1588392382834-a891154bca4d?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8bmF0dXJlfGVufDB8fDB8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60" :
                        dto.getUrl()
        );
        return dailyChallengeRepository.save(dailyChallenge);
    }
}
