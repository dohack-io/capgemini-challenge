package io.dohack.challenge.repositories;

import io.dohack.challenge.domain.DailyChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyChallengeRepository extends JpaRepository<DailyChallenge, Long> {
}
