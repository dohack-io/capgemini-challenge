package io.dohack.challenge.domain;

import lombok.*;
import lombok.extern.java.Log;

import javax.persistence.*;

@Entity
@Data
@Log
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Commute {
    @Id
    @GeneratedValue
    private Long id;

    private Double distance;

    @Enumerated(EnumType.STRING)
    private CommuteType type;
}
