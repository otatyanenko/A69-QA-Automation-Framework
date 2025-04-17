Feature: Login feature
  Scenario:
    Given I open login page
    When I enter email "oksana.chaklosh@testpro.io"
    And I enter password "8qUBYosp"
    And I click on submit
    Then I am logged in