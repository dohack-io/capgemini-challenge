package io.dohack.challenge.service.statistic.imlp;

import io.dohack.challenge.domain.User;
import io.dohack.challenge.domain.UserDailyStatistics;
import io.dohack.challenge.repositories.UserRepository;
import io.dohack.challenge.service.statistic.ReadDailyStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReadDailyStatisticsServiceImpl implements ReadDailyStatisticsService {
    private final UserRepository userRepository;

    @Override
    public Optional<UserDailyStatistics> readTodayStatisticForUser(String username) {
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()) {
            List<UserDailyStatistics> statistics = user.get().getUserDailyStatisticsList().stream()
                    .filter( it -> it.getDay().equals(LocalDate.now()))
                    .collect(Collectors.toList());
            if (statistics.isEmpty()) {
                return Optional.empty();
            } else {
                return Optional.of(statistics.get(0));
            }
        }
        return Optional.empty();
    }

    @Override
    public List<UserDailyStatistics> readAllDailyStatisticsForUser(String username) {
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()) {
            return user.get().getUserDailyStatisticsList();
        } else {
            return Collections.emptyList();
        }
    }
}
