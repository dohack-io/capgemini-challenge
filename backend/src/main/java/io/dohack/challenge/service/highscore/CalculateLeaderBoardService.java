package io.dohack.challenge.service.highscore;

import io.dohack.challenge.dto.HighScoreDto;

import java.util.List;

public interface CalculateLeaderBoardService {
    List<HighScoreDto> calculateCurrentLeaderBoard();
}
