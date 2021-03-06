package io.dohack.challenge.domain;

import lombok.*;
import lombok.extern.java.Log;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Log
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Rewards {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    private Double pointPrice;

}
