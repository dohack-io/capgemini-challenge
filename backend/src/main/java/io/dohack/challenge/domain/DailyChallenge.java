package io.dohack.challenge.domain;

import lombok.*;
import lombok.extern.java.Log;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@Log
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class DailyChallenge {

    @Id
    private Long id;

    private String description;

    private LocalDate date;

    private Double challengePoints;
}
