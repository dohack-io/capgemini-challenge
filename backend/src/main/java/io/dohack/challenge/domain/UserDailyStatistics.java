package io.dohack.challenge.domain;

import lombok.*;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Log
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class UserDailyStatistics {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate day;

    private Integer numberOfCoffees;

    private Double energyConsumption;

    private Double lunchScore;

    private Double dailyChallengePoints;

    private Double pointsEarned;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_daily_statistics_id")
    @ToString.Exclude
    private List<Commute> commuteList = new ArrayList<>();

}
