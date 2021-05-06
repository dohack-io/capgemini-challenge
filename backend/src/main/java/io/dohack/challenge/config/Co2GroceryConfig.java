package io.dohack.challenge.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ConfigurationProperties(prefix = "emission.grocery")
@PropertySource("classpath:applicaton.yml")
@Getter
public class Co2GroceryConfig {
    private Double commuteWalking;
}
