package com.moodys.ma.ds.stepDefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.skyscreamer.jsonassert.JSONAssert;
import com.moodys.ma.ds.util.FileUtil;
import com.moodys.ma.ds.util.JsonUtil;


public class ValidationHelper {



    public static <T> void validateJSONRecord(String fileName, T record) throws JsonProcessingException, JSONException {
        var expectedResponse = JsonUtil.prettyPrint(FileUtil.getFileContentsAsString(fileName));
        var actualResponse = JsonUtil.prettyPrintObject(record);
        JSONAssert.assertEquals(expectedResponse, actualResponse, JsonUtil.getCustomization());
    }

    public static void validateRESTResponse(ObjectMapper objectMapper, String expectedResponseFileName, String actualResponse){

        var formattedExpectedResponse = getJSONDataAsString(objectMapper, FileUtil.getFileContentsAsString(expectedResponseFileName));
        var formattedActualResponse = getJSONDataAsString(objectMapper, actualResponse);

        Assertions.assertNotNull(actualResponse, "Response is null");

        try {
            JSONAssert.assertEquals(formattedExpectedResponse, formattedActualResponse, JsonUtil.getCustomization());
        } catch (JSONException e) {
            Assertions.fail("Unable to compare JSON response. Reason : " + e.getMessage());
        }
    }

    private static String getJSONDataAsString(ObjectMapper objectMapper, String jsonData) {
        String jsonDataAsString = null;
        try {
            Object object = objectMapper.readValue(jsonData, Object.class);
            jsonDataAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception e) {
            Assertions.fail("Unable to format expected json response. Reason: " + e.getMessage());
        }
        return jsonDataAsString;
    }
}
