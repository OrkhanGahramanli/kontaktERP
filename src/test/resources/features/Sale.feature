Feature: Sale

  Background:
    Given User is in "Login Page"
    When User fills input username field with "orkhan.gahramanli"
    And User fills input password field with "test123"
    And User clicks on submit button
    Then User is in "Home Page"
    When User selects "KONTAKT HOME 47 (UKRAYNA DAIRESI)" store
    And User clicks "saleModule" module link

    @AddCreditSale
    Scenario: Successful create credit sale using order number
      When User clicks "orderModule" module link
      And User clicks "newOrder" page link
      And User add "IR-000002" code
      And User clicks "productAreaExpandBtn" button
      And User fills "OLMAYAN STOK" in "productName" input field
      And User clicks "productSearchBtn" button
      And User clicks "addProductBtn" button
      And User fills "Orxan" in "customerName" input field
      And User selects "Kredit Satış" option from "saleType"
      And User fills "06/18/1993" in "customerBirthDate" input field
      And User clicks "submitOrder" button
      And User takes order number
      And User clicks "confirmBtn" button
      And User clicks "saleModule" module link
      And User clicks "newCreditSale" page link
      And Wait 1 second for an element
      And User fills order code in "orderCode" input field
      And User clicks "orderSearchBtn" button
      And User's waiting visibility of "expandCustomerSectionBtn" element for 5 seconds
      And User clicks "expandCustomerSectionBtn" button
      And User fills "641614" in "customerCode" input field
      And User clicks "customerSearchBtn" button
      And User selects "641614" customer
      And User selects "BAKI" option from "regionCode"
      And User selects "Emeqdaş" option from "customerGroupCode"
      And User fills "12" in "creditMonths" input field
      And User clicks "calculateCreditBtn" button
      And User selects "Xeyr" option from "Akb"
      And User selects "Xeyr" option from "asanFinance"
#      And User clicks "sendSMSBtn" button
#      And User clicks "confirmBtn" button
#      And User fills "123456" in "SMSCode" input field
      And User clicks "completeSaleBtn" button
      Then New sale should be created
      And User clicks "confirmBtn" button
      Then Invoice number should be displayed

  @AddCashSale
  Scenario Outline: Successful create cash sale using order number ("<paymentType>")
    When User clicks "orderModule" module link
    And User clicks "newOrder" page link
    And User add "IR-000002" code
    And User clicks "productAreaExpandBtn" button
    And User fills "OLMAYAN STOK" in "productName" input field
    And User clicks "productSearchBtn" button
    And User clicks "addProductBtn" button
    And User fills "Orxan" in "customerName" input field
    And User selects "Nağd Satış" option from "saleType"
    And User clicks "submitOrder" button
    And User takes order number
    And User clicks "confirmBtn" button
    And User clicks "saleModule" module link
    And User clicks "newCashSale" page link
    And Wait 1 second for an element
    And User fills order code in "orderCode" input field
    And User clicks "orderSearchBtn" button
    And User clicks "expandCustomerSectionBtn" button
    And User fills "641614" in "customerCode" input field
    And User clicks "customerSearchBtn" button
    And User selects "641614" customer
    And User clicks "addPaymentBtn" button
    And User selects "<paymentType>" option from "paymentTypeSelect"
    And User selects "<paymentCode>" option from "paymentCodeSelect"
    And User selects "<taksitGroup>" option from "paymentTaksitGroupSelect"
    And User selects "<taksitMonths>" option from "paymentTaksitMonthsSelect"
    And User clicks "completeCashSaleBtn" button
    And User clicks "printEdvBtn" button
    And Wait 1 second for an element
    Then New sale should be created
    And User clicks "confirmBtn" button
    Then Invoice number should be displayed

    Examples:
      | paymentType | paymentCode                    | taksitGroup | taksitMonths |
      | Nəğd        |                                |             |              |
      | Kart        | PAŞA BANK (NAĞD)               |             |              |
#      | Taksit      | UNİBANK UCARD(TAKSİT)/ UNİBANK | TQK-007     | 3 AY-0%      |

  @AddCreditSale2
  Scenario: Successful create credit sale without order number
    When User clicks "newCreditSale" page link
    And User clicks "productAreaExpandBtn" button
    And User fills "OLMAYAN STOK" in "productName" input field
    And User clicks "productSearchBtn" button
    And User clicks "addProductBtn" button
    And User clicks "expandCustomerSectionBtn" button
    And User fills "641614" in "customerCode" input field
    And User clicks "customerSearchBtn" button
    And User selects "641614" customer
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
    Then Invoice number should be displayed

  @AddCashSale2
  Scenario Outline: Successful create cash sale without order number ("<paymentType>")
    When User clicks "newCashSale" page link
    And User clicks "productAreaExpandBtn" button
    And User fills "14MTL73RU" in "productName" input field
    And User clicks "productSearchBtn" button
    And User clicks "addProductBtn" button
    And User clicks "expandCustomerSectionBtn" button
    And User fills "641614" in "customerCode" input field
    And User clicks "customerSearchBtn" button
    And User selects "641614" customer
    And User clicks "productSellerBtn" button
    And User add seller to the product
    And User clicks "addPaymentBtn" button
    And User selects "<paymentType>" option from "paymentTypeSelect"
    And User selects "<paymentCode>" option from "paymentCodeSelect"
    And User selects "<taksitGroup>" option from "paymentTaksitGroupSelect"
    And User selects "<taksitMonths>" option from "paymentTaksitMonthsSelect"
    And User clicks "completeCashSaleBtn" button
    And User clicks "printEdvBtn" button
    And Wait 1 second for an element
    Then New sale should be created
    And User clicks "confirmBtn" button
    Then Invoice number should be displayed

    Examples:
      | paymentType | paymentCode                    | taksitGroup | taksitMonths |
      | Nəğd        |                                |             |              |
      | Kart        | PAŞA BANK (NAĞD)               |             |              |
#      | Taksit      | UNİBANK UCARD(TAKSİT)/ UNİBANK | TQK-007     | 3 AY-0%      |

@InvalidCalculateCreditSale
  Scenario Outline: Unsuccessful credit calculate/Scenario Name: "<caseName>"
    When User clicks "newCreditSale" page link
    And User clicks "productAreaExpandBtn" button
    And User fills "<product>" in "productName" input field
    And User search and add "<product>" product
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
    | Empty product               |              | 641614       | BAKI       | Emeqdaş           | 12           | Məhsul seçilməyib.                                            |
    | Empty customer code         | OLMAYAN STOK |              | BAKI       | Emeqdaş           | 12           | Müştəri seçilməyib.                                           |
    | Empty region code           | OLMAYAN STOK | 641614       |            | Emeqdaş           | 12           | Bölgə kodu seçilməyib.                                        |
    | Empty customer group code   | OLMAYAN STOK | 641614       | BAKI       |                   | 12           | Qrup kodu seçilməyib.                                         |
    | Empty credit months         | OLMAYAN STOK | 641614       | BAKI       | Emeqdaş           |              | Zəhmət olmasa, kredit sayını daxil edin.                      |
    | Invalid credit months       | OLMAYAN STOK | 641614       | BAKI       | Emeqdaş           | 1            | Uyğun kredit şərti tapılmadı...\nKredit ayları uyğun deyil... |
    | Invalid customer group code | OLMAYAN STOK | 641614       | BAKI       | TOPDAN            | 12           | Müşterinin bölgesine ve qrupuna uyğun limit tapılmadı...      |

  @InvalidCreateCreditSale
  Scenario Outline: Unsuccessful credit sale create/Scenario Name: "<caseName>"
    When User clicks "newCreditSale" page link
    And User clicks "productAreaExpandBtn" button
    And User fills "OLMAYAN STOK" in "productName" input field
    And User clicks "productSearchBtn" button
    And User clicks "addProductBtn" button
    And User clicks "expandCustomerSectionBtn" button
    And User fills "641614" in "customerCode" input field
    And User clicks "customerSearchBtn" button
    And User selects "641614" customer
    And User selects "BAKI" option from "regionCode"
    And User selects "Emeqdaş" option from "customerGroupCode"
    And User fills "12" in "creditMonths" input field
    And User clicks "productSellerBtn" button
    And User add seller to the product
    And User clicks "calculateCreditBtn" button
    And User selects "<Akb>" option from "Akb"
    And User selects "<asanFinance>" option from "asanFinance"
#    And User clicks "sendSMSBtn" button
#    And User clicks "confirmBtn" button
#    And User fills "123456" in "SMSCode" input field
    And User clicks "completeSaleBtn" button
    Then User should get "<errorMessage>" message

    Examples:
      | caseName          | Akb  | asanFinance | errorMessage            |
      | Empty Akb         |      | Xeyr        | Akb seçilməyib          |
      | Empty asanFinance | Xeyr |             | Asan Finance seçilməyib |

  @InvalidCreateCashSale
  Scenario Outline: Unsuccessful create cash sale without order number ("<paymentType>")/Scenario name: "<caseName>"
    When User clicks "newCashSale" page link
    And User clicks "productAreaExpandBtn" button
    And User fills "<product>" in "productName" input field
    And User search and add "<product>" product
    And User clicks "expandCustomerSectionBtn" button
    And User fills "<customerCode>" in "customerCode" input field
    And User clicks "customerSearchBtn" button
    And User selects "<customerCode>" customer
    And User clicks "<seller>" button and add any seller for the product
    And User clicks "<paymentBtn>" button
    And User selects "<paymentType>" option from "paymentTypeSelect"
    And User selects "<paymentCode>" option from "paymentCodeSelect"
    And User selects "<taksitGroup>" option from "paymentTaksitGroupSelect"
    And User selects "<taksitMonths>" option from "paymentTaksitMonthsSelect"
    And User clicks "completeCashSaleBtn" button
    And User clicks "printEdvBtn" button
    Then User should get "<errorMessage>" message

    Examples:
      | caseName           | product   | customerCode | seller           | paymentType | paymentCode                    | taksitGroup | taksitMonths | paymentBtn    | errorMessage                 |
      | Empty product      |           | 641614       |                  |             |                                |             |              |               | Məhsul seçilməyib.           |
      | Empty customer     | 14MTL73RU |              | productSellerBtn | Nəğd        |                                |             |              | addPaymentBtn | Müştəri seçilməyib !         |
      | Empty customer     | 14MTL73RU |              | productSellerBtn | Kart        | PAŞA BANK (NAĞD)               |             |              | addPaymentBtn | Müştəri seçilməyib !         |
      | Empty customer     | 14MTL73RU |              | productSellerBtn | Taksit      | UNİBANK UCARD(TAKSİT)/ UNİBANK | TQK-007     | 3 AY-0%      | addPaymentBtn | Müştəri seçilməyib !         |
      | Empty payment      | 14MTL73RU | 641614       | productSellerBtn |             |                                |             |              |               | Ödənən məbləğ düzgün deyil ! |
      | Empty paymentCode  | 14MTL73RU | 641614       | productSellerBtn | Kart        |                                |             |              | addPaymentBtn | Ödəniş kodu seçilməyib!      |
      | Empty taksitMonths | 14MTL73RU | 641614       | productSellerBtn | Taksit      | UNİBANK UCARD(TAKSİT)/ UNİBANK |             |              | addPaymentBtn | Taksit ayi seçilməyib.       |
      | Empty seller       | 14MTL73RU | 641614       |                  | Nəğd        |                                |             |              | addPaymentBtn | satıcı kodu seçilməyib.      |

  @InvalidSmsCode
  Scenario Outline: Unsuccessful create sale credit with wrong sms code/Scenario Name: "<caseName>"
    When User clicks "newCreditSale" page link
    And User clicks "productAreaExpandBtn" button
    And User fills "OLMAYAN STOK" in "productName" input field
    And User clicks "productSearchBtn" button
    And User clicks "addProductBtn" button
    And User clicks "expandCustomerSectionBtn" button
    And User fills "641614" in "customerCode" input field
    And User clicks "customerSearchBtn" button
    And User selects "641614" customer
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
#    And User fills "<smsCode>" in "SMSCode" input field
#    Then "completeSaleBtn" button should be disabled

    Examples:
      | caseName        | smsCode |
      | Empty smsCode   |         |
      | Invalid smsCode | 12345   |
@TotalAmountSale
  Scenario Outline: Check total amount after adding products, services in "<saleType>"
    When User clicks "<saleType>" page link
    And User clicks "productAreaExpandBtn" button
    And User fills "OLMAYAN STOK" in "productName" input field
    And User clicks "productSearchBtn" button
    And User clicks "addProductBtn" button
    And User clicks "serviceBtn" button
    And User add "PXK-009" service
    And User clicks "windowCloseBtn" button
    And User clicks "bundleBtn" button
    And User's waiting visibility of "bundleAddBtn" element for 10 seconds
    And User clicks "bundleAddBtn" button
    Then Total amount should be sum of all prices

  Examples:
    | saleType      |
    | newCreditSale |
    | newCashSale   |

  @CheckProductsInBundleSale
  Scenario Outline: Check products in bundle in "<saleType>"
    When User clicks "<saleType>" page link
    And User clicks "bundleBtn" button
    And User's waiting visibility of "bundleDetailsBtn" element for 10 seconds
    And User clicks "bundleDetailsBtn" button
    Then Products should be displayed in bundle

    Examples:
      | saleType      |
      | newCreditSale |
      | newCashSale   |

  @NewCreditSaleDifferentStore
  Scenario: Create credit sale with product from different store
    When User clicks "newCreditSale" page link
    And User clicks "productAreaExpandBtn" button
    And User fills "14MTL73RU" in "productName" input field
    And User clicks "productSearchBtn" button
    And User clicks "otherStoresBtn" button
    And User's waiting visibility of "addProductOtherStoreBtn" element for 10 seconds
    And User clicks "addProductOtherStoreBtn" button
    And User clicks "windowCloseBtn" button
    And User clicks "expandCustomerSectionBtn" button
    And User fills "641614" in "customerCode" input field
    And User clicks "customerSearchBtn" button
    And User selects "641614" customer
    And User selects "BAKI" option from "regionCode"
    And User selects "Emeqdaş" option from "customerGroupCode"
    And User fills "12" in "creditMonths" input field
    And User clicks "productSellerBtn" button
    And User add seller to the product
    And User selects "Basqa Magaza veya Anbardan Satis Magazasina Teslim" option from "productDeliveryType"
    And User clicks "calculateCreditBtn" button
    And User selects "Xeyr" option from "Akb"
    And User selects "Xeyr" option from "asanFinance"
#    And User clicks "sendSMSBtn" button
#    And User clicks "confirmBtn" button
#    And User fills "123456" in "SMSCode" input field
    And User clicks "completeSaleBtn" button
    Then New sale should be created
    And User clicks "confirmBtn" button
    Then Invoice number should be displayed

  @NewCashSaleDifferentStore
  Scenario Outline: Successful create cash sale without order number ("<paymentType>")
    When User clicks "newCashSale" page link
    And User clicks "productAreaExpandBtn" button
    And User fills "14MTL73RU" in "productName" input field
    And User clicks "productSearchBtn" button
    And User clicks "otherStoresBtn" button
    And User's waiting visibility of "addProductOtherStoreBtn" element for 10 seconds
    And User clicks "addProductOtherStoreBtn" button
    And User clicks "windowCloseBtn" button
    And User clicks "expandCustomerSectionBtn" button
    And User fills "641614" in "customerCode" input field
    And User clicks "customerSearchBtn" button
    And User selects "641614" customer
    And User clicks "productSellerBtn" button
    And User add seller to the product
    And User selects "Basqa Magaza veya Anbardan Satis Magazasina Teslim" option from "productDeliveryType"
    And User clicks "addPaymentBtn" button
    And User selects "<paymentType>" option from "paymentTypeSelect"
    And User selects "<paymentCode>" option from "paymentCodeSelect"
    And User selects "<taksitGroup>" option from "paymentTaksitGroupSelect"
    And User selects "<taksitMonths>" option from "paymentTaksitMonthsSelect"
    And User clicks "completeCashSaleBtn" button
    And User clicks "printEdvBtn" button
    And Wait 1 second for an element
    Then New sale should be created
    And User clicks "confirmBtn" button
    Then Invoice number should be displayed

    Examples:
      | paymentType | paymentCode                    | taksitGroup | taksitMonths |
      | Nəğd        |                                |             |              |
      | Kart        | PAŞA BANK (NAĞD)               |             |              |
      | Taksit      | UNİBANK UCARD(TAKSİT)/ UNİBANK | TQK-007     | 3 AY-0%      |

    @CreditSaleCancel
    Scenario: Cancel credit sale
      When User clicks "newCreditSale" page link
      And User clicks "productAreaExpandBtn" button
      And User fills "OLMAYAN STOK" in "productName" input field
      And User clicks "productSearchBtn" button
      And User clicks "addProductBtn" button
      And User clicks "expandCustomerSectionBtn" button
      And User fills "641614" in "customerCode" input field
      And User clicks "customerSearchBtn" button
      And User selects "641614" customer
      And User selects "BAKI" option from "regionCode"
      And User selects "Emeqdaş" option from "customerGroupCode"
      And User fills "12" in "creditMonths" input field
      And User clicks "productSellerBtn" button
      And User add seller to the product
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
        And User clicks "expandCustomerSectionBtn" button
        And User fills "641614" in "customerCode" input field
        And User clicks "customerSearchBtn" button
        And User selects "641614" customer
        And User selects "BAKI" option from "regionCode"
        And User selects "Emeqdaş" option from "customerGroupCode"
        And User fills "12" in "creditMonths" input field
        And User clicks "productSellerBtn" button
        And User add seller to the product
        And User clicks "calculateCreditBtn" button
        And User selects "Xeyr" option from "Akb"
        And User selects "Xeyr" option from "asanFinance"
#        And User clicks "sendSMSBtn" button
#        And User clicks "confirmBtn" button
#        And User fills "123456" in "SMSCode" input field
        And User clicks "completeSaleBtn" button
        And User clicks "confirmBtn" button
        And User clicks "saleModule" module link
        And User clicks "newCreditSale" page link
        And User clicks "productAreaExpandBtn" button
        And User fills "OLMAYAN STOK" in "productName" input field
        And User clicks "productSearchBtn" button
        Then Product stock should be less 1 less

  @ReturnCreditSale
  Scenario: Successful return credit sale
    When User clicks "newCreditSale" page link
    And User clicks "productAreaExpandBtn" button
    And User fills "OLMAYAN STOK" in "productName" input field
    And User clicks "productSearchBtn" button
    And User clicks "addProductBtn" button
    And User clicks "expandCustomerSectionBtn" button
    And User fills "641614" in "customerCode" input field
    And User clicks "customerSearchBtn" button
    And User selects "641614" customer
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
    And User clicks "confirmBtn" button
    And User takes sale invoice number
    And User clicks "saleModule" page link
    And User clicks "saleReturn" page link
    And User fills sale invoice number in "saleInvoice" input field
    And User clicks "invoiceSearchBtn" button
    And User selects a product
    And User selects "Baha başa gəldi" option from "returnGroup"
    And User selects "Kampaniya şərtlərilə dəyiş." option from "returnReason"
    And User clicks "returnCompleteBtn" button
    Then "Kampaniya şərtlərilə dəyiş." should be displayed in "createdReturnReason" text field

  @ReturnCashSale
  Scenario: Successful return cash sale
    When User clicks "newCashSale" page link
    And User clicks "productAreaExpandBtn" button
    And User fills "14MTL73RU" in "productName" input field
    And User clicks "productSearchBtn" button
    And User clicks "addProductBtn" button
    And User clicks "expandCustomerSectionBtn" button
    And User fills "641614" in "customerCode" input field
    And User clicks "customerSearchBtn" button
    And User selects "641614" customer
    And User clicks "productSellerBtn" button
    And User add seller to the product
    And User clicks "addPaymentBtn" button
    And User selects "Nəğd" option from "paymentTypeSelect"
    And User clicks "completeCashSaleBtn" button
    And User clicks "printEdvBtn" button
    And Wait 1 second for an element
    And User clicks "confirmBtn" button
    And User takes sale invoice number
    And User clicks "saleModule" page link
    And User clicks "saleReturn" page link
    And User fills sale invoice number in "saleInvoice" input field
    And User clicks "invoiceSearchBtn" button
    And User selects a product
    And User selects "Baha başa gəldi" option from "returnGroup"
    And User selects "Kampaniya şərtlərilə dəyiş." option from "returnReason"
    And User clicks "returnCompleteBtn" button
    Then "Kampaniya şərtlərilə dəyiş." should be displayed in "createdReturnReason" text field

    @InvalidSaleReturn
    Scenario Outline: Return sale with incorrect details ("<CaseName>")
      When User clicks "saleReturn" page link
      And User fills "00172" in "saleInvoice" input field
      And User clicks "invoiceSearchBtn" button
      And User selects "<returnGroupOption>" option from "returnGroup"
      And User clicks "returnCompleteBtn" button
      Then User should get "<errorMessage>" message

      Examples:
        | CaseName           | returnGroupOption | errorMessage                   |
        | Unselected product | Baha başa gəldi   | Qaytarılacaq məhsul seçilməyib |
        | Empty returnReason |                   | Qaytarma statusu seçilməyib.   |

      @CheckReturnAmount
      Scenario: Check return amount of products
        When User clicks "newCashSale" page link
        And User clicks "productAreaExpandBtn" button
        And User fills "14MTL73RU" in "productName" input field
        And User clicks "productSearchBtn" button
        And User clicks "addProductBtn" button 2 times
        And User clicks "expandCustomerSectionBtn" button
        And User fills "641614" in "customerCode" input field
        And User clicks "customerSearchBtn" button
        And User selects "641614" customer
        And User add sellers to the products
        And User clicks "addPaymentBtn" button
        And User selects "Nəğd" option from "paymentTypeSelect"
        And User clicks "completeCashSaleBtn" button
        And User clicks "printEdvBtn" button
        And Wait 1 second for an element
        And User clicks "confirmBtn" button
        And User takes sale invoice number
        And User clicks "saleModule" page link
        And User clicks "saleReturn" page link
        And User fills sale invoice number in "saleInvoice" input field
        And User clicks "invoiceSearchBtn" button
        And User selects all products
        And Sum prices of the products
        And Wait 1 second for an element
        And User selects "Digər" option from "returnGroup"
        And User selects "Müştəriyə zəng çatmadı" option from "returnReason"
        And User clicks "returnCompleteBtn" button
        Then Total return amount should equals sum of products' amount

        @ServiceCreditSale
        Scenario Outline: Successful create new service credit sale/ "<serviceType>"
          When User clicks "serviceCreditSaleLink" page link
          And User clicks "expandCustomerSectionBtn" button
          And User fills "641614" in "customerCode" input field
          And User clicks "customerSearchBtn" button
          And User selects "641614" customer
          And User clicks "stockActionsExpandBtn" button
          And User fills "apple" in "stockActionProductSearch" input field
          And Wait 5 second for an element
          And User chooses first product and clicks "stockActionProductSelectBtn" button
          And User selects "<serviceType>" option from "serviceTypeList"
          And User fills "12" in "creditMonths" input field
          And User clicks "selectServiceBtn" button
          And User add "<service>" service
          And User clicks "windowCloseBtn" button
          And User clicks "saveServiceCreditSaleBtn" button
          And User clicks "confirmBtn" button
          And Wait 1 second for an element
          And User clicks "acceptSuccessMessageBtn" button
          Then "<service>" service name should be displayed

          Examples:
            | serviceType   | service         |
            | Qızıl Zəmanət | Zəmanət + 1 il  |
            | Xidmət        | MEHSULUN TEMIRI |

  @ModifyServiceCreditSalePrice
  Scenario Outline: Modify service credit sale price/ "<serviceType>"
    When User clicks "serviceCreditSaleLink" page link
    And User clicks "expandCustomerSectionBtn" button
    And User fills "641614" in "customerCode" input field
    And User clicks "customerSearchBtn" button
    And User selects "641614" customer
    And User clicks "stockActionsExpandBtn" button
    And User fills "apple" in "stockActionProductSearch" input field
    And Wait 5 second for an element
    And User chooses first product and clicks "stockActionProductSelectBtn" button
    And User selects "<serviceType>" option from "serviceTypeList"
    And User fills "12" in "creditMonths" input field
    And User clicks "selectServiceBtn" button
    And User add "<service>" service
    And User clicks "windowCloseBtn" button
    And User clear "serviceCreditSalePrice" input field
    And User fills "<newPrice>" in "serviceCreditSalePrice" input field
    And User clicks "saveServiceCreditSaleBtn" button
    And User clicks "confirmBtn" button
    And Wait 1 second for an element
    And User clicks "acceptSuccessMessageBtn" button
    Then "<newPrice>" service price should be displayed

    Examples:
      | serviceType   | service         | newPrice |
      | Qızıl Zəmanət | Zəmanət + 1 il  | 200      |
      | Xidmət        | MEHSULUN TEMIRI | 150      |

  @DeleteServiceCredit
  Scenario: Delete service credit sale
    When User clicks "serviceCreditSaleLink" page link
    And User clicks "expandCustomerSectionBtn" button
    And User fills "641614" in "customerCode" input field
    And User clicks "customerSearchBtn" button
    And User selects "641614" customer
    And User clicks "stockActionsExpandBtn" button
    And User fills "apple" in "stockActionProductSearch" input field
    And Wait 5 second for an element
    And User chooses first product and clicks "stockActionProductSelectBtn" button
    And User selects "Xidmət" option from "serviceTypeList"
    And User fills "12" in "creditMonths" input field
    And User clicks "selectServiceBtn" button
    And User add "MEHSULUN TEMIRI" service
    And User clicks "windowCloseBtn" button
    And User clicks "deleteServiceBtn" button
    Then Services should be deleted from table

    @InvoiceCorrection
      Scenario: Edit invoice details using invoice correction page
      When User clicks "newCashSale" page link
      And User clicks "productAreaExpandBtn" button
      And User fills "14MTL73RU" in "productName" input field
      And User clicks "productSearchBtn" button
      And User clicks "addProductBtn" button
      And User clicks "expandCustomerSectionBtn" button
      And User fills "641614" in "customerCode" input field
      And User clicks "customerSearchBtn" button
      And User selects "641614" customer
      And User clicks "productSellerBtn" button
      And User add seller to the product
      And User clicks "addPaymentBtn" button
      And User selects "Nəğd" option from "paymentTypeSelect"
      And User clicks "completeCashSaleBtn" button
      And User clicks "printEdvBtn" button
      And Wait 1 second for an element
      Then New sale should be created
      And User clicks "confirmBtn" button
      And User takes sale invoice number
      And User clicks "saleModule" module link
      And User clicks "invoiceCorrectionPageLink" page link
      And User fills sale invoice number in "saleInvoiceSearch" input field
      And User clicks "invoiceSearchBtn" button
      And User clicks "productSellerBtn" button
      And User fills "IR-000045" in "sellerSearchField" input field
      And User clicks "sellerSelectBtn" button
      And User selects "Basqa Magaza veya Anbardan Satis Magazasina Teslim" option from "invoiceProductDeliveryType"
      And User clicks "customerAddressBtn" button
      And User's waiting visibility of "customerAddressSelectBtn" element for 10 seconds
      And User clicks "customerAddressSelectBtn" button
      And User clicks on "installation" checkbox
      And User selects "Yolda" option from "productDeliveryStatusOptions"
      And User clicks "completeInvoiceCorrectionBtn" button
      And User clicks "confirmBtn" button
      Then User should get "Qeyd tamamlandı" message
      And User clicks "confirmBtn" button
      And User fills sale invoice number in "saleInvoiceSearch" input field
      And User clicks "invoiceSearchBtn" button
      Then "sellerCode" should equals "IR-000045"
      Then "Basqa Magaza veya Anbardan Satis Magazasina Teslim" should be selected in "invoiceProductDeliveryType"
      Then "customerAddress" should equals "Xudu Məmmədov, 174"
      Then "installation" should be checked
      Then "Yolda" should be selected in "productDeliveryStatusOptions"

   @OnlineSaleSuccessCreate
    Scenario Outline: User want to create online sale invoice with correct inputs / "<saleType>"/"<paymentType>"
      When User clicks "onlineOrder" module link
      When User clicks "onlineSale" page link
      And User clicks "productAreaExpandBtn" button
      And User fills "14MTL73RU" in "productName" input field
      And User clicks "productSearchBtn" button
      And User clicks "addProductBtn" button
      And User clicks "expandCustomerSectionBtn" button
      And User fills "641614" in "customerCode" input field
      And User clicks "customerSearchBtn" button
      And User selects "641614" customer
      And User clicks "productSellerBtn" button
      And User add seller to the product
      And User selects "<saleType>" option from "saleTypeFieldOnlineSale"
      And User selects "<paymentType>" option from "paymentTypeFieldOnlineSale"
      And User fills "<taksitMonths>" in "taksitMonthsField" input field
      And User clicks "completeOnlineSaleBtn" button
      And User clicks "printEdvBtn" button
      And Wait 1 second for an element
      Then New sale should be created
      And User clicks "confirmBtn" button
      Then Invoice number should be displayed

      Examples:
        | saleType     | paymentType   | taksitMonths |
        | Nəğd Satış   | Nəğd          |              |
        | Nəğd Satış   | Kart          |              |
        | Nəğd Satış   | Taksit        | 12           |
        | Nəğd Satış   | Hədiyyə Kartı |              |
        | Nəğd Satış   | Bonus Kart    |              |
        | Taksit Satış | Taksit        | 12           |