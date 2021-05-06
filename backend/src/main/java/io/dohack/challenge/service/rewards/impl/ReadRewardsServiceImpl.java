package io.dohack.challenge.service.rewards.impl;

import io.dohack.challenge.domain.Rewards;
import io.dohack.challenge.repositories.RewardsRepository;
import io.dohack.challenge.service.rewards.ReadRewardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadRewardsServiceImpl implements ReadRewardsService {

    private final RewardsRepository rewardsRepository;

    @Override
    public List<Rewards> readAllRewards() {
        return rewardsRepository.findAll();
    }
}
