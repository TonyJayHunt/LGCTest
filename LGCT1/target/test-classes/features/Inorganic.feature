Feature: Inorganic

  Background: To Launch the browser
    Given Launch the browser

    Scenario: Inorganic order
      Given Website has loaded
      When Inorganic is processed
      Then webpage shows "Custom solution summary"
      And Custom solution shows "1 Element(s)"
      And additional notes show "this is me adding further details"

