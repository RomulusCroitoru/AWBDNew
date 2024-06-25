package org.proiect.awbd.config;

import org.proiect.awbd.service.security.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("dev")
public class SecurityJpaConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    private final JpaUserDetailsService userDetailsService;
//
//    public SecurityJpaConfig(JpaUserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }

//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeRequests(auth -> auth
//                        .requestMatchers("/product/form").hasRole("ADMIN")
//                        .requestMatchers("/", "/webjars/**", "/login", "/resources/**").permitAll()
//                        .requestMatchers("/product/*").hasAnyRole("ADMIN", "GUEST")
//                        .requestMatchers("/categories/*").hasAnyRole("ADMIN", "GUEST")
//                        .anyRequest().authenticated()
//                )
//                .userDetailsService(userDetailsService)
//                .httpBasic(Customizer.withDefaults())
//                .build();
//    }








}