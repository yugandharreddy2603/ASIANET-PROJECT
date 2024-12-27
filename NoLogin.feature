Feature: Login
  Scenario: Login Functionality
    Given user navigates to the website www.asianet-tours.it/
    And there user logs in through Login Window by using wrong Username as "USER" and wrong Password as "PASSWORD"
    Then login must fail with an error message