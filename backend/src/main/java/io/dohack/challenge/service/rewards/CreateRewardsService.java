package io.dohack.challenge.service.rewards;

import io.dohack.challenge.domain.Rewards;
import io.dohack.challenge.dto.CreateRewardDto;

public interface CreateRewardsService {
    Rewards createReward(CreateRewardDto dto);
}
