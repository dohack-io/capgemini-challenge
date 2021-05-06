package io.dohack.challenge.repositories;

import io.dohack.challenge.domain.UserDailyStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDailyStatisticsRepository extends JpaRepository<UserDailyStatistics, Long> {
}
