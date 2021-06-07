Feature: Organic

  Background: To Launch the browser
    Given Launch the browser

    Scenario: Organic order
      Given Website has loaded
      And I have Order file "CustomQuoteUpload_15_CAS_Only.xls"
      When Organic Order is processed
      Then webpage shows "Custom solution summary"
      And Custom solution shows "17 Component(s)"
      And additional notes show "this is me adding further details"

