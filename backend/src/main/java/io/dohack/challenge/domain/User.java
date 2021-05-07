package io.dohack.challenge.domain;

import lombok.*;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Log
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class User {
    @Id
    private String username;

    private String password;

    private String email;

    private String displayName;

    private Double points;

    private Double co2Score;

    private String seat;

    private Double defaultCommuteDistance;

    @Enumerated(EnumType.STRING)
    private CommuteType defaultCommuteType;

    private Integer level;

    private Integer levelProgression;

    private Integer levelUpThreshold;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    @OrderBy("day ASC")
    @ToString.Exclude
    private List<UserDailyStatistics> userDailyStatisticsList = new ArrayList<>();

}
