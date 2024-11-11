package com.moodys.ma.ds.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class HealthCheckService {



    private final RestTemplate restTemplate;

    public boolean isHealthy(String url) {
        try {
            return restTemplate.getForObject(url + "/actuator/health", String.class).contains("\"status\":\"UP\"");
        } catch (Exception e) {
            log.error("Error while checking health of service", e);
            return false;
        }
    }
}
