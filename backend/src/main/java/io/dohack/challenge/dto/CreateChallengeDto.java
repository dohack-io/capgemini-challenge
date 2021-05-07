package io.dohack.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CreateChallengeDto {
    private String title;
    private String description;
    private LocalDate date;
    private Double challengePoints;
    private String url;
}
