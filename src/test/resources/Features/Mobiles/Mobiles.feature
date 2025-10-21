Feature: Run the Appium-Server, Launch Real or Virtual device then Launch the Application

  Scenario: Launch the SwagLabs mobile Application
    Given Launch the APP
    When Successfully launched
    Then Verify launched and get the home page