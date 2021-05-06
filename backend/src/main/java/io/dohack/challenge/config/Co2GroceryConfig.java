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
@ConfigurationProperties(prefix = "emission.grocery")
@PropertySource("classpath:application.yml")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Co2GroceryConfig {
    private Double cattle;
    private Double pig;
    private Double poultry;
    private Double lamb;
    private Double fish;
    private Double rice;
    private Double noodles;
    private Double fries;
    private Double butter;
    private Double cream;
    private Double milk;
    private Double potatoes;
    private Double lenses;
    private Double oatmeal;

}
