package io.dohack.challenge.service.user.impl;

import io.dohack.challenge.domain.User;
import io.dohack.challenge.repositories.UserRepository;
import io.dohack.challenge.service.user.ReadUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReadUserServiceImpl implements ReadUserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> readUserForUsername(String username) {
        return userRepository.findById(username);
    }
}
