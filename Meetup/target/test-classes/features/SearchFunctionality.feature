@search
Feature: Search Functionality

  Background:
    Given the user navigates to Meetup homepage

  Scenario: Verify search results
    When the user searches for "Grayslake Bicycle Slow Roll" in "Lake Bluff, Illinois, US"
    Then verifies the result set contains search criteria in the title