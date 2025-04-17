Feature: Login feature
  Scenario:
    Given I open login page
    When I enter email "oksana.chaklosh@testpro.io"
    And I enter password "8qUBYosp"
    And I click on submit
    Then I am logged in

  Scenario Outline:
    Given I open login page
    When I enter email "<email>"
    And I enter password "<password>"
    And I click on submit
    Then I am logged in
    Examples:
      |email  |password |
      |oksana.chaklosh@testpro.io | 8qUBYosp |
      | | 8qUBYosp |
      |oksana.chaklosh@testpro.io |  |
      |wrong@testpro.io | 8qUBYosp |
      |oksana.chaklosh@testpro.io | 8qWrongosp |
