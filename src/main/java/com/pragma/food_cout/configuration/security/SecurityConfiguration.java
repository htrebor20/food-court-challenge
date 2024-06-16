package com.pragma.food_cout.configuration.security;

import com.pragma.food_cout.configuration.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/restaurant/{id}").hasAnyRole("OWNER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/restaurant/").hasAnyRole("CUSTOMER", "ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/order/{id}").hasAnyRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/order/").hasAnyRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/order/").hasAnyRole("CUSTOMER")
                        .requestMatchers(HttpMethod.PATCH, "/order/**").permitAll()
                        .requestMatchers("/restaurant/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/dishes/").hasAnyRole("CUSTOMER")
                        .anyRequest().permitAll())
                .sessionManagement(sessionManager -> sessionManager
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).build();
    }
}
