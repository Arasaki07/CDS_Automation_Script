package com.moodys.ma.ds.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;


@Service
@RequiredArgsConstructor
public class RestExecutorService {


    private final RestTemplate restTemplate;


    public RestResponse execute(String url, String body, Map<String, String> headers, HttpMethod httpMethod) {

        ResponseEntity<String> response;
        var restResponseBuilder = RestResponse.builder().status(HttpStatus.OK).success(true);
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set(CONTENT_TYPE, "application/json");
            httpHeaders.setAll(headers);
            HttpEntity<String> requestUpdate = new HttpEntity<>(body, httpHeaders);
            response = restTemplate.exchange(url, httpMethod, requestUpdate, String.class);
            restResponseBuilder.response(response.getBody());
        } catch (RestClientResponseException e) {
            buildRestClientErrorMessage(e, restResponseBuilder);
        } catch (Exception e) {
            buildErrorMessage(e, restResponseBuilder);
        }
        return restResponseBuilder.build();
    }

    private void buildRestClientErrorMessage(
            RestClientResponseException e, RestResponse.RestResponseBuilder restResponseBuilder) {
        restResponseBuilder
                .errorMessage(e.getMessage())
                .status(e.getStatusCode())
                .success(false);
    }

    private void buildErrorMessage(
            Exception e, RestResponse.RestResponseBuilder restResponseBuilder) {
        restResponseBuilder
                .errorMessage(e.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .success(false);
    }
}
