@regression @bdd
Feature: SauceDemo login
  As a shopper I want to sign in so that I can browse products

  Background:
    Given the login page is open

  @smoke
  Scenario: Successful login with valid credentials
    When I log in as a standard user
    Then I should land on the products page

  Scenario Outline: Login is rejected for invalid credentials
    When I log in with username "<username>" and password "<password>"
    Then I should see the error "<message>"

    Examples:
      | username      | password       | message                                                                   |
      | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.                       |
      | standard_user | wrong_password | Epic sadface: Username and password do not match any user in this service. |
