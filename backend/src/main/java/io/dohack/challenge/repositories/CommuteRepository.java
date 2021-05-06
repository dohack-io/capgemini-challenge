package io.dohack.challenge.repositories;

import io.dohack.challenge.domain.Commute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommuteRepository extends JpaRepository<Commute, Long> {
}
