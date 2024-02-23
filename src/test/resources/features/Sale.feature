Feature: Sale

  Background:
    When User fills input username field with "orkhan.gahramanli"
    And User fills input password field with "test123"
    And User clicks on submit button
    Then User is in "Home Page"
    When User selects "KONTAKT HOME 47 (UKRAYNA DAIRESI)" store
    And User clicks "saleModule" module link

    @AddCreditSale
    Scenario: Successful create credit sale using order number
      When User clicks "newCreditSale" page link
      And User fills "001713" in "orderCode" input field
      And User clicks "orderSearchBtn" button
      And User expands customer section
      And User fills "1000517597" in "customerCode" input field
      And User clicks "customerSearchBtn" button
      And User selects customer
      And User selects "BAKI" option from "regionCode"
      And User selects "Emeqdaş" option from "customerGroupCode"
      And User fills "12" in "creditMonths" input field
      And User clicks "calculateCreditBtn" button
      And User selects "Xeyr" option from "Akb"
      And User selects "Xeyr" option from "asanFinance"
      And User clicks "sendSMSBtn" button
      And User clicks "confirmBtn" button
      And User fills "123456" in "SMSCode" input field
      And User clicks "completeSaleBtn" button
      And User clicks "confirmBtn" button
      Then Invoice number should be displayed