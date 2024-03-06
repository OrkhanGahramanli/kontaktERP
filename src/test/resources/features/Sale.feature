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
      And User fills "0017136" in "orderCode" input field
      And User clicks "orderSearchBtn" button
      And User clicks "expandCustomerSectionBtn" button
      And User fills "1000517597" in "customerCode" input field
      And User clicks "customerSearchBtn" button
      And User selects "1000517597" customer
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
      Then New sale should be created
      And User clicks "confirmBtn" button
      Then Invoice number should be displayed

  @AddCreditSale2
  Scenario: Successful create credit sale without order number
    When User clicks "newCreditSale" page link
    And User clicks "productAreaExpandBtn" button
    And User fills "OLMAYAN STOK" in "productName" input field
    And User clicks "productSearchBtn" button
    And User clicks "addProductBtn" button
    And User add seller to the product
    And User clicks "expandCustomerSectionBtn" button
    And User fills "1000517597" in "customerCode" input field
    And User clicks "customerSearchBtn" button
    And User selects "1000517597" customer
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
    Then New sale should be created
    And User clicks "confirmBtn" button
    Then Invoice number should be displayed

@InvalidCalculateCreditSale
  Scenario Outline: Unsuccessful credit calculate/Scenario Name: "<caseName>"
    When User clicks "newCreditSale" page link
    And User clicks "productAreaExpandBtn" button
    And User fills "<product>" in "productName" input field
    And User clicks "productSearchBtn" button
    And User clicks "addProductBtn" button
    And User clicks "expandCustomerSectionBtn" button
    And User fills "<customerCode>" in "customerCode" input field
    And User clicks "customerSearchBtn" button
    And User selects "<customerCode>" customer
    And User selects "<regionCode>" option from "regionCode"
    And User selects "<customerGroupCode>" option from "customerGroupCode"
    And User fills "<creditMonths>" in "creditMonths" input field
    And User clicks "calculateCreditBtn" button
    Then User should get "<errorMessage>" message

  Examples:
    | caseName                    | product      | customerCode | regionCode | customerGroupCode | creditMonths | errorMessage                                                  |
    | Empty product               |              | 1000517597   | BAKI       | Emeqdaş           | 12           | Məhsul seçilməyib.                                            |
    | Empty customer code         | OLMAYAN STOK |              | BAKI       | Emeqdaş           | 12           | Müştəri seçilməyib.                                           |
    | Empty region code           | OLMAYAN STOK | 1000517597   |            | Emeqdaş           | 12           | Bölgə kodu seçilməyib.                                        |
    | Empty customer group code   | OLMAYAN STOK | 1000517597   | BAKI       |                   | 12           | Qrup kodu seçilməyib.                                         |
    | Empty credit months         | OLMAYAN STOK | 1000517597   | BAKI       | Emeqdaş           |              | Zəhmət olmasa, kredit sayını daxil edin.                      |
    | Invalid credit months       | OLMAYAN STOK | 1000517597   | BAKI       | Emeqdaş           | 1            | Uyğun kredit şərti tapılmadı...\nKredit ayları uyğun deyil... |
    | Invalid customer group code | OLMAYAN STOK | 1000517597   | BAKI       | TOPDAN            | 12           | Müşterinin bölgesine ve qrupuna uyğun limit tapılmadı...      |

  @InvalidCreateCreditSale
  Scenario Outline: Unsuccessful credit sale create/Scenario Name: "<caseName>"
    When User clicks "newCreditSale" page link
    And User clicks "productAreaExpandBtn" button
    And User fills "OLMAYAN STOK" in "productName" input field
    And User clicks "productSearchBtn" button
    And User clicks "addProductBtn" button
    And User add seller to the product
    And User clicks "expandCustomerSectionBtn" button
    And User fills "1000517597" in "customerCode" input field
    And User clicks "customerSearchBtn" button
    And User selects "1000517597" customer
    And User selects "BAKI" option from "regionCode"
    And User selects "Emeqdaş" option from "customerGroupCode"
    And User fills "12" in "creditMonths" input field
    And User clicks "calculateCreditBtn" button
    And User selects "<Akb>" option from "Akb"
    And User selects "<asanFinance>" option from "asanFinance"
    And User clicks "sendSMSBtn" button
    And User clicks "confirmBtn" button
    And User fills "123456" in "SMSCode" input field
    And User clicks "completeSaleBtn" button
    Then User should get "<errorMessage>" message

    Examples:
      | caseName          | Akb  | asanFinance | errorMessage            |
      | Empty Akb         |      | Xeyr        | Akb seçilməyib          |
      | Empty asanFinance | Xeyr |             | Asan Finance seçilməyib |

  @InvalidSmsCode
  Scenario Outline: Unsuccessful create sale credit with wrong sms code/Scenario Name: "<caseName>"
    When User clicks "newCreditSale" page link
    And User clicks "productAreaExpandBtn" button
    And User fills "OLMAYAN STOK" in "productName" input field
    And User clicks "productSearchBtn" button
    And User clicks "addProductBtn" button
    And User add seller to the product
    And User clicks "expandCustomerSectionBtn" button
    And User fills "1000517597" in "customerCode" input field
    And User clicks "customerSearchBtn" button
    And User selects "1000517597" customer
    And User selects "BAKI" option from "regionCode"
    And User selects "Emeqdaş" option from "customerGroupCode"
    And User fills "12" in "creditMonths" input field
    And User clicks "calculateCreditBtn" button
    And User selects "Xeyr" option from "Akb"
    And User selects "Xeyr" option from "asanFinance"
    And User clicks "sendSMSBtn" button
    And User clicks "confirmBtn" button
    And User fills "<smsCode>" in "SMSCode" input field
    Then "completeSaleBtn" button should be disabled

    Examples:
      | caseName        | smsCode |
      | Empty smsCode   |         |
      | Invalid smsCode | 12345   |
@TotalAmountCreditSale
  Scenario: Check total amount after adding products, services in new credit sale
    When User clicks "newCreditSale" page link
    And User clicks "productAreaExpandBtn" button
    And User fills "OLMAYAN STOK" in "productName" input field
    And User clicks "productSearchBtn" button
    And User clicks "addProductBtn" button
    And User clicks "serviceBtn" button
    And User add "PXK-009" service
    And User clicks "windowCloseBtn" button
    And User clicks "bundleBtn" button
    And User clicks "bundleAddBtn" button
    Then Total amount should be sum of all prices

  @CheckProductsInBundleSale
  Scenario: Check products in bundle
    When User clicks "newCreditSale" page link
    And User clicks "bundleBtn" button
    And User clicks "bundleDetailsBtn" button
    Then Products should be displayed in bundle

  @NewSaleDifferentStore
  Scenario: Create credit sale with product from different store
    When User clicks "newCreditSale" page link
    And User clicks "productAreaExpandBtn" button
    And User fills "<Product>" in "productName" input field
    And User clicks "productSearchBtn" button
    And User clicks "otherStoresBtn" button
    And User clicks "windowCloseBtn" button
    And User add seller to the product
    And User selects "Basqa Magaza veya Anbardan Satis Magazasina Teslim" option from "productDeliveryType"
    And User clicks "expandCustomerSectionBtn" button
    And User fills "1000517597" in "customerCode" input field
    And User clicks "customerSearchBtn" button
    And User selects "1000517597" customer
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
    Then New sale should be created
    And User clicks "confirmBtn" button
    Then Invoice number should be displayed

    @CreditSaleCancel
    Scenario: Cancel credit sale
      When User clicks "newCreditSale" page link
      And User clicks "productAreaExpandBtn" button
      And User fills "OLMAYAN STOK" in "productName" input field
      And User clicks "productSearchBtn" button
      And User clicks "addProductBtn" button
      And User add seller to the product
      And User clicks "expandCustomerSectionBtn" button
      And User fills "1000517597" in "customerCode" input field
      And User clicks "customerSearchBtn" button
      And User selects "1000517597" customer
      And User selects "BAKI" option from "regionCode"
      And User selects "Emeqdaş" option from "customerGroupCode"
      And User fills "12" in "creditMonths" input field
      And User clicks "calculateCreditBtn" button
      And User selects "Kredit şərtləri ilə uyğunsuzluq" option from "cancelSubject"
      And User selects "Şəxsiyyət vəsiqəsi etibarsızdır" option from "cancelReason"
      And User clicks "creditCancelBtn" button
      Then Credit sale should be canceled successfully

      @ProductStockDecrease
      Scenario: Check product count in store after credit sale
        When User clicks "newCreditSale" page link
        And User clicks "productAreaExpandBtn" button
        And User fills "OLMAYAN STOK" in "productName" input field
        And User clicks "productSearchBtn" button
        And User clicks "addProductBtn" button
        And Collect product stock count
        And User add seller to the product
        And User clicks "expandCustomerSectionBtn" button
        And User fills "1000517597" in "customerCode" input field
        And User clicks "customerSearchBtn" button
        And User selects "1000517597" customer
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
        And User clicks "saleModule" module link
        And User clicks "newCreditSale" page link
        And User clicks "productAreaExpandBtn" button
        And User fills "OLMAYAN STOK" in "productName" input field
        And User clicks "productSearchBtn" button
        Then Product stock should be less 1 less