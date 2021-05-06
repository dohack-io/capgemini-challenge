package io.dohack.challenge.service.rewards.impl;

import io.dohack.challenge.domain.Rewards;
import io.dohack.challenge.dto.CreateRewardDto;
import io.dohack.challenge.repositories.RewardsRepository;
import io.dohack.challenge.service.rewards.CreateRewardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateRewardsServiceImpl implements CreateRewardsService {

    private final RewardsRepository rewardsRepository;

    @Override
    public Rewards createReward(CreateRewardDto dto) {
        Rewards reward = new Rewards(null, dto.getDescription(), dto.getPoints());
        return rewardsRepository.save(reward);
    }
}
