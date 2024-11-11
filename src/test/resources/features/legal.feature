@legal
Feature: API response validation

  @normal @regression @sanity @positive_case
  Scenario: Verify API status code and response structure
    Given I invoke the legal subcollection API with parameteres "orbisid " "81170352 "
    Then the response status code should be 200
    And the response should contain valid context structure
    And the response should contain valid resource information
    And the response should contain valid data

