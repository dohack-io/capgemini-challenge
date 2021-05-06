package io.dohack.challenge.service.rewards;

import io.dohack.challenge.domain.Rewards;

import java.util.List;

public interface ReadRewardsService {
    List<Rewards> readAllRewards();
}
