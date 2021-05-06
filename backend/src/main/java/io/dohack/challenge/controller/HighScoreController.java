package io.dohack.challenge.controller;

import io.dohack.challenge.dto.HighScoreDto;
import io.dohack.challenge.service.highscore.CalculateLeaderBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HighScoreController {
    private final CalculateLeaderBoardService calculateLeaderBoardService;

    @GetMapping("statistics/leaderboard")
    List<HighScoreDto> getLeaderBoard() {
        return calculateLeaderBoardService.calculateCurrentLeaderBoard();
    }
}
