package com.megha.xpert.config;

import com.megha.xpert.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests((requests) -> requests
                        .requestMatchers("/register", "/register", "/login", "/index").permitAll()
                        .anyRequest().authenticated())
                .formLogin((form) -> form
                        .loginPage("/login")
                        .successHandler(new SavedRequestAwareAuthenticationSuccessHandler())
                        .permitAll())
                .logout(LogoutConfigurer::permitAll)
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
