package io.dohack.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TotalUserScoreDto {

    private String displayName;

    private Double points;

    private int totalNumberOfCoffees;

    private Double totalEnergyConsumption;

    private Double totalLunchScore;

    private Double totalDailyChallengePoints;

    private Double totalPointsEarned;

}
