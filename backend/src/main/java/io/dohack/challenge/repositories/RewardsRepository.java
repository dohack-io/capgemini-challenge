package io.dohack.challenge.repositories;

import io.dohack.challenge.domain.Rewards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardsRepository extends JpaRepository<Rewards, Long> {
}
