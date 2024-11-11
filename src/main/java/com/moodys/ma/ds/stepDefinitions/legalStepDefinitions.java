package com.moodys.ma.ds.stepDefinitions;

import com.moodys.ma.ds.LegalService;
import com.moodys.ma.ds.rest.RestResponse;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@ActiveProfiles("uat")
public class legalStepDefinitions {
    @Autowired
    private LegalService legalService;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ObjectMapper objectMapper;

    private  String apiResponse;

    private RestResponse restResponse;
    private int statusCode;

    @Given("I invoke the legal subcollection API with parameteres {string} {string}")
    public void invokeLegalsAPI(String idType, String id) {
        restResponse = legalService.getLegalService(idType, id);

    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        Assertions.assertEquals(expectedStatusCode, statusCode, "Status code does not match");
        System.out.println("print test here");
    }

    @Then("the response should contain valid context structure")
    public void theResponseShouldContainValidContextStructure() {
        Assertions.assertTrue(apiResponse.contains("context"), "Response does not contain 'context'");
        // Add further assertions based on the expected structure
    }

    @Then("the response should contain valid resource information")
    public void theResponseShouldContainValidResourceInformation() {
        Assertions.assertTrue(apiResponse.contains("resources"), "Response does not contain 'resources'");
        // Add more detailed checks here
    }
    @Then("the response should contain valid data")
    public void theResponseShouldContainValidData() {
        Assertions.assertTrue(apiResponse.contains("data"), "Response does not contain 'data'");
        // Check for specific fields within data
    }


}
