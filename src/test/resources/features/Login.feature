Feature: Login Feature
    @chrome
    Scenario: Successful Login on Chrome  
    Given user is on login page
    When user enters username "standard_user" and password "secret_sauce"
    Then user should be redirected to home page
    
    @firefox
    Scenario: Successful Login on Firefox  
    Given user is on login page
    When user enters username "standard_user" and password "secret_sauce"
    Then user should be redirected to home page