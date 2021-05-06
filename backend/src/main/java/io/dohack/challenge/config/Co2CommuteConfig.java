package io.dohack.challenge.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "emission.commute")
@PropertySource("classpath:application.yml")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Co2CommuteConfig {
    private Double walking;
    private Double bike;
    private Double ebike;
    private Double carNormal;
    private Double carHybrid;
    private Double busShortterm;
    private Double busLongterm;
    private Double trainShortterm;
    private Double trainLongterm;
    private Double tramShortterm;
    private Double tramLongterm;
    private Double plainCountry;
    private Double plainGlobal;
    private Double escooter;
}
