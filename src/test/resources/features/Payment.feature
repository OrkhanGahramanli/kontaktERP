Feature: Payment

  Background:
    When User fills input username field with "orkhan.gahramanli"
    And User fills input password field with "test123"
    And User clicks on submit button
    Then User is in "Home Page"
    When User selects "KONTAKT HOME 47 (UKRAYNA DAIRESI)" store
    And User clicks "saleModule" module link

    Scenario Outline: Add cash inflow / "<paymentType>"
      When User clicks "newCreditSale" page link
      And User clicks "productAreaExpandBtn" button
      And User fills "OLMAYAN STOK" in "productName" input field
      And User clicks "productSearchBtn" button
      And User clicks "addProductBtn" button
      And User clicks "expandCustomerSectionBtn" button
      And User fills "1000517597" in "customerCode" input field
      And User clicks "customerSearchBtn" button
      And User selects "1000517597" customer
      And User selects "BAKI" option from "regionCode"
      And User selects "Emeqdaş" option from "customerGroupCode"
      And User fills "12" in "creditMonths" input field
      And User clicks "productSellerBtn" button
      And User add seller to the product
      And User clicks "calculateCreditBtn" button
      And User selects "Xeyr" option from "Akb"
      And User selects "Xeyr" option from "asanFinance"
#    And User clicks "sendSMSBtn" button
#    And User clicks "confirmBtn" button
#    And User fills "123456" in "SMSCode" input field
      And User clicks "completeSaleBtn" button
      And Wait 1 second for an element
      Then New sale should be created
      And User clicks "confirmBtn" button
      And User takes sale invoice number
#      And User clicks "paymentModule" module link
#      And User clicks "cashInflowPageLink" page link
#      And Wait 1 second for an element
#      And User fills "1000517597" in "customerCode" input field
#      And User clicks "customerSearchBtn" button
#      And User selects "1000517597" customer
#      And User selects the sale invoice and clicks "Ödə" button
#      And User selects "<paymentType>" option from "paymentType"
#      And User selects "<paymentCode>" option from "paymentCode"
#      And User fills invoice remaining amount in payment
#      And User clicks "completePaymentBtn" button
#      And User clicks "printEdvBtn" button
#      Then Cash inflow should be completed

      Examples:
        | paymentType | paymentCode                    |
        | Nağd        |                                |
        | Kart        | PAŞA BANK (NAĞD)               |
#        | Taksit      | UNİBANK UCARD(TAKSİT)/ UNİBANK |