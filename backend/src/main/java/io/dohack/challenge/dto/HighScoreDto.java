package io.dohack.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class HighScoreDto {
    private String displayName;
    private Double co2Score;
    private Double averageLunchScorePerMonth;
    private Double averageCompletedChallengesPerMonth;
    private Double averCo2ScorePerMonth;
}
