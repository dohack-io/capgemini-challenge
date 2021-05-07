package io.dohack.challenge.config;

import io.dohack.challenge.domain.User;
import io.dohack.challenge.repositories.UserRepository;
import kotlin.Pair;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${cors.origin}")
    String corsOrigin;
    private final UserRepository userRepository;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable().exceptionHandling()
                .and()
                .authorizeRequests().anyRequest().permitAll()
                .and()
                .formLogin().permitAll()
                .successHandler(new CustomLoginSuccessHandler())
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .and()
                .logout().permitAll()
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK));
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        List<Pair<String, String>> usernameAndPassword = Arrays.asList(
                new Pair("anton", "anton123"),
                new Pair("christoph", "christoph123"),
                new Pair("johannes", "johannes123"),
                new Pair("kai", "kai123"),
                new Pair("marius", "marius123"),
                new Pair("silas", "silas123"));
        return new InMemoryUserDetailsManager(
                usernameAndPassword.stream().map(it -> org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
                        .username(it.getFirst())
                        .password(it.getSecond())
                        .roles("USER").build()).collect(Collectors.toList())
        );
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource()  {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList(corsOrigin));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "xsrf-token", "observe",
                "Cache-Control", "remember-me", "WWW-Authenticate", "Access-Control-Allow-Origin"));
        configuration.setExposedHeaders(Collections.singletonList("xsrf-token"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
