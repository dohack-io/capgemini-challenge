package io.dohack.challenge.repositories;

import io.dohack.challenge.domain.MockedSensorValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MockedSensorValueRepository extends JpaRepository<MockedSensorValue, String> {
}
