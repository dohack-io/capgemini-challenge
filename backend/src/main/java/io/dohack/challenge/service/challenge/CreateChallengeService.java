package io.dohack.challenge.service.challenge;

import io.dohack.challenge.domain.DailyChallenge;
import io.dohack.challenge.dto.CreateChallengeDto;

public interface CreateChallengeService {
    DailyChallenge createDailyChallenge(CreateChallengeDto dto);
}
