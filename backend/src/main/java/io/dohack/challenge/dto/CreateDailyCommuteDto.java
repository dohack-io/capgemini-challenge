package io.dohack.challenge.dto;

import io.dohack.challenge.domain.CommuteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDailyCommuteDto {
    private Double distance;
    private String type;
}
