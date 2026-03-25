Feature: User Login

  Scenario: Successful login with valid credentials
    Given the user is on the home page
    When the user navigates to the login page
    And enters valid email and password and clicks on login button
    Then the user should be redirected to the dashboard

