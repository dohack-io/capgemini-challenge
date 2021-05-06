package io.dohack.challenge.service.rewards.impl;

import io.dohack.challenge.domain.Rewards;
import io.dohack.challenge.domain.User;
import io.dohack.challenge.repositories.RewardsRepository;
import io.dohack.challenge.repositories.UserRepository;
import io.dohack.challenge.service.rewards.RedeemRewardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedeemRewardsServiceImpl implements RedeemRewardsService {

    private final UserRepository userRepository;
    private final RewardsRepository rewardsRepository;

    @Override
    public Boolean redeemReward(String username, Long rewardsId) {
        Optional<Rewards> rewards = rewardsRepository.findById(rewardsId);
        Optional<User> user = userRepository.findById(username);
        if (rewards.isPresent() && user.isPresent() && user.get().getPoints() > rewards.get().getPointPrice()) {
            user.get().setPoints(user.get().getPoints() - rewards.get().getPointPrice());
            userRepository.save(user.get());
            return true;
        }
        return false;
    }
}
