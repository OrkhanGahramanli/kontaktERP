Feature: Payment

  Background:
    When User fills input username field with "orkhan.gahramanli"
    And User fills input password field with "test123"
    And User clicks on submit button
    Then User is in "Home Page"
    When User selects "KONTAKT HOME 47 (UKRAYNA DAIRESI)" store
    And User clicks "paymentModule" module link

    Scenario Outline: Add cash inflow / "<paymentType>"
      When User clicks "cashInflowPageLink" page link
      And User fills "1000517597" in "customerCode" input field
      And User clicks "customerSearchBtn" button
      And User selects "1000517597" customer
      And User selects any sale invoice and clicks "Ödə" button
      And User selects "<paymentType>" option from "paymentTypeSelect"
      And User selects "<paymentCode>" option from "paymentCodeSelect"
      And User selects "<taksitGroup>" option from "paymentTaksitGroupSelect"
      And User selects "<taksitMonths>" option from "paymentTaksitMonthsSelect"
      And User fills invoice remaining amount in payment
      And User clicks "completePaymentBtn" button
      And User clicks "printEdvBtn" button
      Then User should get "Ödəniş tamamlandı" message

      Examples:
        | paymentType | paymentCode                    | taksitGroup | taksitMonths |
        | Nəğd        |                                |             |              |
        | Kart        | PAŞA BANK (NAĞD)               |             |              |
        | Taksit      | UNİBANK UCARD(TAKSİT)/ UNİBANK | TQK-007     | 3 AY-0%      |