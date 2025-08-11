package com.jobseek.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(withDefaults -> {
                })
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/signin", "/users/signup", "/swagger-ui/**", "/v3/api-docs/**", "/error",
                                "/skill/get",
                                "/recruiter/add/**",
                                "/candidate/**",
                                "/candidate/education/**",
                                "/candidate/experience/**",
                                "/candidate/skills/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/admin/**").permitAll()
                        .requestMatchers("/recruiter/**").hasRole("RECRUITER")
//                        .requestMatchers("/recruiter/**").permitAll()
                        .requestMatchers("/candidate/**").hasRole("CANDIDATE")
//                        .requestMatchers("/candidate/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
