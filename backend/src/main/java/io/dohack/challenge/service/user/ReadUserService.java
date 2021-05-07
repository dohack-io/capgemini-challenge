package io.dohack.challenge.service.user;

import io.dohack.challenge.domain.User;

import java.util.Optional;

public interface ReadUserService {
    Optional<User> readUserForUsername(String username);
}
