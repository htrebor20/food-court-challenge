package com.pragma.food_cout.configuration.client;

import com.pragma.food_cout.domain.model.SmsBody;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-twilio", url = "http://localhost:8888/api/v1/sms", configuration = FeignClientProperties.FeignClientConfiguration.class)

public interface IMessagingClient {
    @PostMapping
    void sendSms(@Valid @RequestBody SmsBody smsRequest);
}
