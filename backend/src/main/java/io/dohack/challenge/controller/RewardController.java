package io.dohack.challenge.controller;

import io.dohack.challenge.domain.Rewards;
import io.dohack.challenge.dto.CreateRewardDto;
import io.dohack.challenge.service.rewards.CreateRewardsService;
import io.dohack.challenge.service.rewards.ReadRewardsService;
import io.dohack.challenge.service.rewards.RedeemRewardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RewardController {

    private final CreateRewardsService createRewardsService;
    private final ReadRewardsService readRewardsService;
    private final RedeemRewardsService redeemRewardsService;

    @PostMapping("rewards/create")
    public Rewards createReward(@RequestBody CreateRewardDto dto){
        return createRewardsService.createReward(dto);
    }

    @PostMapping("rewards/{username}/{id}")
    public Boolean redeemRewards(@PathVariable("username") String username, @PathVariable("id") Long id) {
        return redeemRewardsService.redeemReward(username, id);
    }

    @GetMapping("rewards")
    public List<Rewards> readRewards() {
        return readRewardsService.readAllRewards();
    }
}
