package io.dohack.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDailyStatisticDto {
    private String username;
    private Integer numberOfCoffees;
    private Double lunchScore;
    private Boolean dailyChallengeCompleted;
    private List<CreateDailyCommuteDto> dailyCommuteList;
}
