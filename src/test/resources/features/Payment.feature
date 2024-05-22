Feature: Payment

  Background:
    Given User is in "Login Page"
    When User fills input username field with "orkhan.gahramanli"
    And User fills input password field with "test123"
    And User clicks on submit button
    Then User is in "Home Page"
    When User selects "KONTAKT HOME 47 (UKRAYNA DAIRESI)" store
    And User clicks "saleModule" module link

  @AddCashInflow
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
      And User clicks "confirmBtn" button
      And User takes sale invoice number
      And User clicks "paymentModule" module link
      And User clicks "cashInflowPageLink" page link
      And Wait 1 second for an element
      And User fills "1000517597" in "customerCode" input field
      And User clicks "customerSearchBtn" button
      And User selects "1000517597" customer
      And User selects the sale invoice and clicks "Ödə" button
      And User selects "<paymentType>" option from "paymentType"
      And User selects "<paymentCode>" option from "paymentCode"
      And User fills invoice remaining amount in payment
      And User clicks "cashInflowCompletePaymentBtn" button
      And User clicks "printEdvBtn" button
      Then Cash inflow should be completed

      Examples:
        | paymentType | paymentCode                    |
        | Nağd        |                                |
        | Kart        | PAŞA BANK (NAĞD)               |
        | Taksit      | UNİBANK UCARD(TAKSİT)/ UNİBANK |

  @InvalidCashInflow
      Scenario Outline: Add cash inflow with invalid payment details / "<caseName>"/ "<paymentType>"
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
        And User clicks "confirmBtn" button
        And User takes sale invoice number
        And User clicks "paymentModule" module link
        And User clicks "cashInflowPageLink" page link
        And Wait 1 second for an element
        And User fills "1000517597" in "customerCode" input field
        And User clicks "customerSearchBtn" button
        And User selects "1000517597" customer
        And User selects the sale invoice and clicks "Ödə" button
        And User selects "<paymentType>" option from "paymentType"
        And User selects "<paymentCode>" option from "paymentCode"
        And User fills invalid "<paymentAmount>" in cash inflow
        And User clicks "cashInflowCompletePaymentBtn" button
        And User clicks "printEdvBtn" button
        Then User should get "<error>" message

        Examples:
          | caseName                                      | paymentType | paymentCode                    | paymentAmount | error                                               |
          | Payment amount equals zero                    | Nağd        |                                |               | Ödəniş məbləği düzgün deyil.                        |
          | Payment amount equals zero                    | Kart        | PAŞA BANK (NAĞD)               |               | Ödəniş məbləği düzgün deyil.                        |
          | Payment amount equals zero                    | Taksit      | UNİBANK UCARD(TAKSİT)/ UNİBANK |               | Ödəniş məbləği düzgün deyil.                        |
          | Payment amount greater that amount to be paid | Nağd        |                                | overload      | Ödəniş Məbləği fakturanın qalan məbləğindən çoxdur. |
          | Payment amount greater that amount to be paid | Kart        | PAŞA BANK (NAĞD)               | overload      | Ödəniş Məbləği fakturanın qalan məbləğindən çoxdur. |
          | Payment amount greater that amount to be paid | Taksit      | UNİBANK UCARD(TAKSİT)/ UNİBANK | overload      | Ödəniş Məbləği fakturanın qalan məbləğindən çoxdur. |

  @AddCashOutflow
  Scenario: Add cash outflow
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
    And User clicks "confirmBtn" button
    And User takes sale invoice number
    And User clicks "paymentModule" module link
    And User clicks "cashInflowPageLink" page link
    And Wait 1 second for an element
    And User fills "1000517597" in "customerCode" input field
    And User clicks "customerSearchBtn" button
    And User selects "1000517597" customer
    And User selects the sale invoice and clicks "Ödə" button
    And User fills invoice remaining amount in payment
    And User clicks "cashInflowCompletePaymentBtn" button
    And User clicks "printEdvBtn" button
    And Wait 1 second for an element
    And User clicks "paymentModule" module link
    And User clicks "cashOutflowPageLink" page link
    And User fills "1000517597" in "customerCode" input field
    And User clicks "customerSearchBtn" button
    And User selects "1000517597" customer
    And User fills sale invoice number in "paymentSearchField" input field
    And Wait 1 second for an element
    And User clicks "selectCashInInvoiceBtn" button
    And User selects "Qaytarma" option from "paymentReasonOptionsField"
    And User fills inflow amount in pay amount field
    And User clicks "completeCashOutflowPaymentBtn" button
    And User clicks "printEdvBtn" button
    Then User should get "Kassa Məxaric Qeyd Edildi" message

  @InvalidCashOutflow
  Scenario Outline: Add cash outflow with invalid payment details / "<caseName>"
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
    And User clicks "confirmBtn" button
    And User takes sale invoice number
    And User clicks "paymentModule" module link
    And User clicks "cashInflowPageLink" page link
    And Wait 1 second for an element
    And User fills "1000517597" in "customerCode" input field
    And User clicks "customerSearchBtn" button
    And User selects "1000517597" customer
    And User selects the sale invoice and clicks "Ödə" button
    And User fills invoice remaining amount in payment
    And User clicks "cashInflowCompletePaymentBtn" button
    And User clicks "printEdvBtn" button
    And Wait 1 second for an element
    And User clicks "paymentModule" module link
    And User clicks "cashOutflowPageLink" page link
    And User fills "1000517597" in "customerCode" input field
    And User clicks "customerSearchBtn" button
    And User selects "1000517597" customer
    And User fills sale invoice number in "paymentSearchField" input field
    And Wait 1 second for an element
    And User clicks "selectCashInInvoiceBtn" button
    And User selects "<paymentReason>" option from "paymentReasonOptionsField"
    And User fills invalid "<paymentAmount>" in cash outflow
    And User clicks "completeCashOutflowPaymentBtn" button
    Then User should get "<error>" message

    Examples:
      | caseName                                      | paymentAmount | paymentReason | error                        |
      | Empty payment reason                          |               |               | Ödəniş səbəbi boş olabilməz  |
      | Payment amount equals zero                    |               | Qaytarma      | Ödəniş məbləği düzgün deyil! |
      | Payment amount greater that amount to be paid | overload      | Qaytarma      | Ödəniş məbləği düzgün deyil! |

    @RepeatCashOutflow
    Scenario: Repeat cash outflow from same cash inflow
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
      And User clicks "confirmBtn" button
      And User takes sale invoice number
      And User clicks "paymentModule" module link
      And User clicks "cashInflowPageLink" page link
      And Wait 1 second for an element
      And User fills "1000517597" in "customerCode" input field
      And User clicks "customerSearchBtn" button
      And User selects "1000517597" customer
      And User selects the sale invoice and clicks "Ödə" button
      And User fills invoice remaining amount in payment
      And User clicks "cashInflowCompletePaymentBtn" button
      And User clicks "printEdvBtn" button
      And Wait 1 second for an element
      And User clicks "paymentModule" module link
      And User clicks "cashOutflowPageLink" page link
      And User fills "1000517597" in "customerCode" input field
      And User clicks "customerSearchBtn" button
      And User selects "1000517597" customer
      And User fills sale invoice number in "paymentSearchField" input field
      And Wait 1 second for an element
      And User clicks "selectCashInInvoiceBtn" button
      And User selects "Qaytarma" option from "paymentReasonOptionsField"
      And User fills inflow amount in pay amount field
      And User clicks "completeCashOutflowPaymentBtn" button
      And User clicks "printEdvBtn" button
      And Wait 1 second for an element
      And User clicks "confirmBtn" button
      And User clicks "paymentModule" module link
      And User clicks "cashOutflowPageLink" page link
      And User fills "1000517597" in "customerCode" input field
      And User clicks "customerSearchBtn" button
      And User selects "1000517597" customer
      And User fills sale invoice number in "paymentSearchField" input field
      And Wait 1 second for an element
      And User clicks "selectCashInInvoiceBtn" button
      Then User should get "Məxaric ediləcək mədaxil məbləği tapılmadı" message