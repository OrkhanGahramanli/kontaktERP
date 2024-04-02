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
#      And User clicks "sendSMSBtn" button
#      And User clicks "confirmBtn" button
#      And User fills "123456" in "SMSCode" input field
      And User clicks "completeSaleBtn" button
      Then New sale should be created
      And User clicks "confirmBtn" button
      Then Invoice number should be displayed

  @AddCashSale
  Scenario Outline: Successful create cash sale using order number ("<paymentType>")
    When User clicks "newCashSale" page link
    And User fills "0017175" in "orderCode" input field
    And User clicks "orderSearchBtn" button
    And User clicks "expandCustomerSectionBtn" button
    And User fills "1000517597" in "customerCode" input field
    And User clicks "customerSearchBtn" button
    And User selects "1000517597" customer
    And User clicks "addPaymentBtn" button
    And User selects "<paymentType>" option from "paymentTypeSelect"
    And User selects "<paymentCode>" option from "paymentCodeSelect"
    And User selects "<taksitGroup>" option from "paymentTaksitGroupSelect"
    And User selects "<taksitMonths>" option from "paymentTaksitMonthsSelect"
    And User clicks "completeCashSaleBtn" button
    And User clicks "printEdvBtn" button
    Then New sale should be created
    And User clicks "confirmBtn" button
    Then Invoice number should be displayed

    Examples:
      | paymentType | paymentCode                    | taksitGroup | taksitMonths |
      | Nəğd        |                                |             |              |
      | Kart        | PAŞA BANK (NAĞD)               |             |              |
      | Taksit      | UNİBANK UCARD(TAKSİT)/ UNİBANK | TQK-007     | 3 AY-0%      |

  @AddCreditSale2
  Scenario: Successful create credit sale without order number
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
    And User fills "1000517597" in "customerCode" input field
    And User clicks "customerSearchBtn" button
    And User selects "1000517597" customer
    And User clicks "productSellerBtn" button
    And User add seller to the product
    And User clicks "addPaymentBtn" button
    And User selects "<paymentType>" option from "paymentTypeSelect"
    And User selects "<paymentCode>" option from "paymentCodeSelect"
    And User selects "<taksitGroup>" option from "paymentTaksitGroupSelect"
    And User selects "<taksitMonths>" option from "paymentTaksitMonthsSelect"
    And User clicks "completeCashSaleBtn" button
    And User clicks "printEdvBtn" button
    Then New sale should be created
    And User clicks "confirmBtn" button
    Then Invoice number should be displayed

    Examples:
      | paymentType | paymentCode                    | taksitGroup | taksitMonths |
      | Nəğd        |                                |             |              |
      | Kart        | PAŞA BANK (NAĞD)               |             |              |
      | Taksit      | UNİBANK UCARD(TAKSİT)/ UNİBANK | TQK-007     | 3 AY-0%      |

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
      | Empty product      |           | 1000517597   |                  |             |                                |             |              |               | Məhsul seçilməyib.           |
      | Empty customer     | 14MTL73RU |              | productSellerBtn | Nəğd        |                                |             |              | addPaymentBtn | Müştəri seçilməyib !         |
      | Empty customer     | 14MTL73RU |              | productSellerBtn | Kart        | PAŞA BANK (NAĞD)               |             |              | addPaymentBtn | Müştəri seçilməyib !         |
      | Empty customer     | 14MTL73RU |              | productSellerBtn | Taksit      | UNİBANK UCARD(TAKSİT)/ UNİBANK | TQK-007     | 3 AY-0%      | addPaymentBtn | Müştəri seçilməyib !         |
      | Empty payment      | 14MTL73RU | 1000517597   | productSellerBtn |             |                                |             |              |               | Ödənən məbləğ düzgün deyil ! |
      | Empty paymentCode  | 14MTL73RU | 1000517597   | productSellerBtn | Kart        |                                |             |              | addPaymentBtn | Ödəniş kodu seçilməyib!      |
      | Empty taksitMonths | 14MTL73RU | 1000517597   | productSellerBtn | Taksit      | UNİBANK UCARD(TAKSİT)/ UNİBANK |             |              | addPaymentBtn | Taksit ayi seçilməyib.       |
      | Empty seller       | 14MTL73RU | 1000517597   |                  | Nəğd        |                                |             |              | addPaymentBtn | satıcı kodu seçilməyib.      |

  @InvalidSmsCode
  Scenario Outline: Unsuccessful create sale credit with wrong sms code/Scenario Name: "<caseName>"
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
    And User fills "1000517597" in "customerCode" input field
    And User clicks "customerSearchBtn" button
    And User selects "1000517597" customer
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
    And User fills "1000517597" in "customerCode" input field
    And User clicks "customerSearchBtn" button
    And User selects "1000517597" customer
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
      And User fills "1000517597" in "customerCode" input field
      And User clicks "customerSearchBtn" button
      And User selects "1000517597" customer
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
    And User fills "1000517597" in "customerCode" input field
    And User clicks "customerSearchBtn" button
    And User selects "1000517597" customer
    And User clicks "productSellerBtn" button
    And User add seller to the product
    And User clicks "addPaymentBtn" button
    And User selects "Nəğd" option from "paymentTypeSelect"
    And User clicks "completeCashSaleBtn" button
    And User clicks "printEdvBtn" button
    And Waiting for an element using thread sleep
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

#      @CheckReturnAmount
#      Scenario: Check return amount of products
#        When User clicks "newCashSale" page link
#        And User clicks "productAreaExpandBtn" button
#        And User fills "14MTL73RU" in "productName" input field
#        And User clicks "productSearchBtn" button
#        And User clicks "addProductBtn" button 2 times
#        And User clicks "expandCustomerSectionBtn" button
#        And User fills "1000517597" in "customerCode" input field
#        And User clicks "customerSearchBtn" button
#        And User selects "1000517597" customer
#        And User add sellers to the products
#        And User clicks "addPaymentBtn" button
#        And User selects "Nəğd" option from "paymentTypeSelect"
#        And User clicks "completeCashSaleBtn" button
#        And User clicks "printEdvBtn" button
#        And User's waiting visibility of "confirmBtn" element for 20 seconds
#        And User clicks "confirmBtn" button
#        And User takes sale invoice number
#        And User clicks "saleModule" page link
#        And User clicks "saleReturn" page link
#        And User fills sale invoice number in "saleInvoice" input field
#        And User clicks "invoiceSearchBtn" button
#        And User selects all products
#        Then Total return amount should equals sum of products' amount
#        And User selects "Baha başa gəldi" option from "returnGroup"
#        And User selects "Kampaniya şərtlərilə dəyiş." option from "returnReason"
#        And User clicks "returnCompleteBtn" button