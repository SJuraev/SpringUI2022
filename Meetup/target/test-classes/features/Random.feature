@random
Feature: Random tests
  
  Scenario: Google Title verification
    When the user navigates to google
    And the user inputs "apple" in search window
    Then verifies "apple" is in the title of the page
