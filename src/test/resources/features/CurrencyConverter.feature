@fx @regression
Feature: Currency Converter

  As a customer I wish to be able to convert FX rates

  Background: Navigate to the currency converter page
    Given I click the "Currency converter" link under the "FX, travel & migrant" heading in the menu bar

  Scenario: Error displayed if no amount entered when converting FX rates
    Given I select different source and destination currencies
    When I click the convert button
    Then I see a currency conversion error stating "Please enter the amount you want to convert."

  Scenario Outline: User can convert from Currency A to Currency B and vice versa
    Given I select <source>  and <destination> as the currencies
    When I enter "1" as the amount
    And I click the convert button
    Then I see the conversion has been successful

    Examples:
      | source               | destination          |
      | New Zealand Dollar   | United States Dollar |
      | United States Dollar | New Zealand Dollar   |
      | Pound Sterling       | New Zealand Dollar   |
      | Swiss Franc          | Euro                 |

