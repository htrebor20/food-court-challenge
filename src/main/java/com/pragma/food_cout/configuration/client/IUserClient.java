package com.pragma.food_cout.configuration.client;

import com.pragma.food_cout.domain.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-food-court-user", url = "http://localhost:8080/user", configuration = FeignClientProperties.FeignClientConfiguration.class)

public interface IUserClient {
    @GetMapping("/{id}")
    ResponseEntity<User> findUserById(@PathVariable Long id);}
