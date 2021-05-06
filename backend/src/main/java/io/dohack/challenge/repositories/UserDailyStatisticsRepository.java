package io.dohack.challenge.repositories;

import io.dohack.challenge.domain.UserDailyStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDailyStatisticsRepository extends JpaRepository<UserDailyStatistics, Long> {
}
