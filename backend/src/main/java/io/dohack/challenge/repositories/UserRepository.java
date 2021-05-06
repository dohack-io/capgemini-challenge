package io.dohack.challenge.repositories;

import io.dohack.challenge.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
