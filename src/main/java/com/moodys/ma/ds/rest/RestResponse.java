package com.moodys.ma.ds.rest;

import lombok.Builder;
import org.springframework.http.HttpStatusCode;

@Builder(toBuilder = true)
public record RestResponse(

        String response,
        RestResponse restResponse,
        HttpStatusCode status,
        String errorMessage,
        boolean success

) {}
