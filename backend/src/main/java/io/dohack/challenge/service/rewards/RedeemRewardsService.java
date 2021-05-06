package io.dohack.challenge.service.rewards;

import io.dohack.challenge.domain.Rewards;

public interface RedeemRewardsService {
    Boolean redeemReward(String username, Long rewardsId);
}
