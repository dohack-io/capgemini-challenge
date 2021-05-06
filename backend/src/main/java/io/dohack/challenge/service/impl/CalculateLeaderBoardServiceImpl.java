package io.dohack.challenge.service.impl;

import io.dohack.challenge.domain.User;
import io.dohack.challenge.dto.HighScoreDto;
import io.dohack.challenge.repositories.UserRepository;
import io.dohack.challenge.service.CalculateLeaderBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalculateLeaderBoardServiceImpl implements CalculateLeaderBoardService {
    private final UserRepository userRepository;

    @Override
    public List<HighScoreDto> calculateCurrentLeaderBoard() {
        return userRepository.findAll()
                .stream().sorted(Comparator.comparingDouble(User::getCo2Score))
                .map(it -> new HighScoreDto(it.getDisplayName(), it.getCo2Score()))
                .collect(Collectors.toList());
    }
}
