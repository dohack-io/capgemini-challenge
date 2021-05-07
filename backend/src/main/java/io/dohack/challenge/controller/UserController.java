package io.dohack.challenge.controller;

import io.dohack.challenge.domain.User;
import io.dohack.challenge.service.user.ReadUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final ReadUserService readUserService;

    @GetMapping("/user/{username}")
    Optional<User> getUserForUsername(@PathVariable("username") String username) {
        return readUserService.readUserForUsername(username);
    }
}
