@kiwisaver @regression
Feature: KiwiSaver Calculator

  As a customer I wish to be able to use the KiwiSaver calculator to plan retirement savings


  Background: Navigate to the KiwiSaver calculator page
    Given I click the "KiwiSaver calculator" link under the "KiwiSaver" heading in the menu bar
    When I click the link with text "Click here to get started."


  Scenario: Clicking the information icon beside Current age displays the correct message
    When I click the information icon beside the "Current age" field
    Then I see the "Current age" information message stating "This calculator has an age limit of 64 years old as you need to be under the age of 65 to join KiwiSaver."


  Scenario Outline: Users are able to calculate their projected balance would be at retirement
    Given I have entered the KiwiSaver calculator inputs <Current age> <Employment status> <Salary> <Contribution percent> <PIR> <Profile> <Balance> <Contribution amount> <Contribution frequency> <Savings goal>
    When I click the calculate button on the KiwiSaver calculator page
    Then I see KiwiSaver calculator results are displayed

    Examples:
      | Current age | Employment status | Salary | Contribution percent | PIR   | Profile | Balance | Contribution amount | Contribution frequency | Savings goal |
      | 30          | Employed          | 82000  | 4%                   | 17.5% | High    |         |                     |                        |              |
      | 45          | Self-employed     |        |                      | 10.5% | Medium  | 100000  | 90                  | Fortnightly            | 290000       |
      | 55          | Not               |        |                      | 10.5% | Medium  | 140000  | 10                  | Annually               | 200000       |