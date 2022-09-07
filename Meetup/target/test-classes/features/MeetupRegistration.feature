@meetup
  Feature: Meetup registration

    Background:
      Given the user navigates to Meetup homepage
      And user should click on "Join Meetup" button

    Scenario:  Verify the user is provided the option to sign up with Facebook
      Then user should be able to sign up with "facebook-register"

    Scenario:  Verify the user is provided the option to sign up with Google
      Then user should be able to sign up with "google-register"

    Scenario:  Verify the user is provided the option to sign up with Apple
      Then user should be able to sign up with "apple-register"

    Scenario:  Verify the user is provided the option to sign up with email
      Then user should be able to sign up with "email-register"




