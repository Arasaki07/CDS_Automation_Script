package com.moodys.ma.ds.util;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonUtil {



    public static CustomComparator getCustomization() {

        return new CustomComparator(
                JSONCompareMode.STRICT,
                new Customization("context.trackingId", (o1, o2) -> true)
        );
    }

    public static String prettyPrint(String json) throws JsonProcessingException {
        ObjectMapper objectMapper =
                new ObjectMapper()
                        .enable(SerializationFeature.INDENT_OUTPUT)
                        .setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(objectMapper.readValue(json, Object.class));
    }

    public static String prettyPrintObject(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper =
                new ObjectMapper()
                        .enable(SerializationFeature.INDENT_OUTPUT)
                        .setSerializationInclusion(JsonInclude.Include.NON_NULL);
        JavaTimeModule module = new JavaTimeModule();
        objectMapper.registerModule(module);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    }
}
