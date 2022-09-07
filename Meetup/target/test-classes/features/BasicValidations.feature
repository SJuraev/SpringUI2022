@meetup
  Feature: Basic Validation

    Background:
      Given the user navigates to Meetup homepage

    Scenario: Title verification
      Then verifies the title contains "Meetup"

    Scenario: URL verification
      Then verify the url contains "https://www.meetup.com/"
