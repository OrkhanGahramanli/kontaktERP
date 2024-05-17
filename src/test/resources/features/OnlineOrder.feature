Feature: OnlineOrder

  Background:
    Given User is in "Login Page"
    When User fills input username field with "orkhan.gahramanli"
    And User fills input password field with "test123"
    And User clicks on submit button
    Then User is in "Home Page"
    When User selects "KONTAKT HOME 47 (UKRAYNA DAIRESI)" store
    And User clicks "onlineOrder" module link

    Scenario: Successful create new cash online order
      When User clicks "onlineOrderPageLink" page link
      And User clicks "newOrderBtn" button
      And User selects "Nağd" radioButton
      And User fills "Orxan" in "customerName" input field
      And User fills "Gahramanli" in "customerSurname" input field
      And User fills "0551111111" in "customerMobile" input field
      And User fills "55LRF4T" in "customerFinCode" input field
      And User fills "Xudu Mammadov" in "customerAddress" input field
      And User fills "Baku" in "customerCity" input field
      And User clicks "productSelectBtn" button
      And User fills "OLMAYAN STOK" in "productSearchByName" input field
      And User press enter button
      And User clicks button with "Əlavə et" text
      And User clicks "completeOnlineOrderBtn" button
      And User clicks "confirmBtn" button
      Then New online order should be create
      And User takes online order number
      And User clicks "confirmBtn" button
      And User fills online order number in search field
      Then Created online order should be displayed

  Scenario: Successful create new credit online order
    When User clicks "onlineOrderPageLink" page link
    And User clicks "newOrderBtn" button
    And User selects "Kredit" radioButton
    And User fills "Orxan" in "customerName" input field
    And User fills "Gahramanli" in "customerSurname" input field
    And User fills "0551111111" in "customerMobile" input field
    And User fills "55LRF4T" in "customerFinCode" input field
    And User fills "Xudu Mammadov" in "customerAddress" input field
    And User fills "Baku" in "customerCity" input field
    And User clicks "productSelectBtn" button
    And User fills "OLMAYAN STOK" in "productSearchByName" input field
    And User press enter button
    And User clicks button with "Əlavə et" text
    And User clicks "completeOnlineOrderBtn" button
    And User clicks "confirmBtn" button
    Then New online order should be create
    And User takes online order number
    And User clicks "confirmBtn" button
    And User clicks "webOrdersLink" page link
    And User fills online order number in search field
    Then Created online order should be displayed

  @OnlineOrderFailCase
    Scenario Outline: Unsuccessful create "<orderType>" online order/ "<caseName>"
    When User clicks "onlineOrderPageLink" page link
    And User clicks "newOrderBtn" button
    And User selects "<orderType>" radioButton
    And User fills "<name>" in "customerName" input field
    And User fills "<surName>" in "customerSurname" input field
    And User fills "<mobile>" in "customerMobile" input field
    And User fills "<address>" in "customerAddress" input field
    And User add "<productName>" product in online order
    And User clicks "completeOnlineOrderBtn" button
    Then User should get "<error>" message

    Examples:
      | caseName               | orderType | name  | surName    | mobile     | address       | productName  | error                                 |
      | Empty customer name    | Nağd      |       | Gahramanli | 0551111111 | Xudu Mammadov | OLMAYAN STOK | Müştəri adı boş ola bilməz!           |
      | Empty customer surname | Nağd      | Orxan |            | 0551111111 | Xudu Mammadov | OLMAYAN STOK | Müştəri soyadı boş ola bilməz!        |
      | Empty customer mobile  | Nağd      | Orxan | Gahramanli |            | Xudu Mammadov | OLMAYAN STOK | Müştəri əlaqə nömrəsi boş ola bilməz! |
      | Empty customer address | Nağd      | Orxan | Gahramanli | 0551111111 |               | OLMAYAN STOK | Müştəri ünvanı boş ola bilməz!        |
      | Empty product          | Nağd      | Orxan | Gahramanli | 0551111111 | Xudu Mammadov |              | Sifarişdə ən azı 1 məhsul olmalıdır!  |
      | Empty customer name    | Kredit    |       | Gahramanli | 0551111111 | Xudu Mammadov | OLMAYAN STOK | Müştəri adı boş ola bilməz!           |
      | Empty customer surname | Kredit    | Orxan |            | 0551111111 | Xudu Mammadov | OLMAYAN STOK | Müştəri soyadı boş ola bilməz!        |
      | Empty customer mobile  | Kredit    | Orxan | Gahramanli |            | Xudu Mammadov | OLMAYAN STOK | Müştəri əlaqə nömrəsi boş ola bilməz! |
      | Empty customer address | Kredit    | Orxan | Gahramanli | 0551111111 |               | OLMAYAN STOK | Müştəri ünvanı boş ola bilməz!        |
      | Empty product          | Kredit    | Orxan | Gahramanli | 0551111111 | Xudu Mammadov |              | Sifarişdə ən azı 1 məhsul olmalıdır!  |

  @OnlineOrderInfo
    Scenario: Check online order details
      When User clicks "onlineOrderPageLink" page link
      And User clicks "newOrderBtn" button
      And User selects "Nağd" radioButton
      And User fills "Orxan" in "customerName" input field
      And User fills "Gahramanli" in "customerSurname" input field
      And User fills "0551111111" in "customerMobile" input field
      And User fills "55LRF4T" in "customerFinCode" input field
      And User fills "Xudu Mammadov" in "customerAddress" input field
      And User fills "Baku" in "customerCity" input field
      And User clicks "productSelectBtn" button
      And User fills "OLMAYAN STOK" in "productSearchByName" input field
      And User press enter button
      And User clicks button with "Əlavə et" text
      And User clicks "completeOnlineOrderBtn" button
      And User clicks "confirmBtn" button
      And Wait 1 second for an element
      And User takes online order number
      And User clicks "confirmBtn" button
      And User fills online order number in search field
      And User's waiting visibility of "onlineOrderInfoBtn" element for 5 seconds
      And User clicks "onlineOrderInfoBtn" button
      And User's waiting visibility of "orderNumField" element for 5 seconds
      Then Online order number should be visible in "orderNumField"
      Then "customerNameEditOrder" should equals "Orxan"
      Then "customerSurnameEditOrder" should equals "Gahramanli"
      Then "customerMobileEditOrder" should equals "994551111111"
      Then "customerFinCodeEditOrder" should equals "55LRF4T"
      Then "customerAddressEditOrder" should equals "Xudu Mammadov"
      Then "customerCityEditOrder" should equals "Baku"
      Then "productNameInOrder" should equals "OLMAYAN STOK"

  @EditOnlineOrder
    Scenario: Edit online order details and change status
      When User clicks "onlineOrderPageLink" page link
      And User clicks "newOrderBtn" button
      And User selects "Nağd" radioButton
      And User fills "Orxan" in "customerName" input field
      And User fills "Gahramanli" in "customerSurname" input field
      And User fills "0551111111" in "customerMobile" input field
      And User fills "55LRF4T" in "customerFinCode" input field
      And User fills "Xudu Mammadov" in "customerAddress" input field
      And User fills "Baku" in "customerCity" input field
      And User clicks "productSelectBtn" button
      And User fills "OLMAYAN STOK" in "productSearchByName" input field
      And User press enter button
      And User clicks button with "Əlavə et" text
      And User clicks "completeOnlineOrderBtn" button
      And User clicks "confirmBtn" button
      And Wait 1 second for an element
      And User takes online order number
      And User clicks "confirmBtn" button
      And User fills online order number in search field
      And User's waiting visibility of "onlineOrderInfoBtn" element for 5 seconds
      And User clicks "onlineOrderInfoBtn" button
      And User's waiting visibility of "customerNameEditOrder" element for 5 seconds
      And User fills "_update" in "customerNameEditOrder" input field
      And User fills "_update" in "customerSurnameEditOrder" input field
      And User fills "_update" in "customerFinCodeEditOrder" input field
      And User fills "_update" in "customerAddressEditOrder" input field
      And User clicks "selectProductInOnlineOrder" button
      And User fills "TESTTT" in "productSearchByNameInOrderEdit" input field
      And User press enter button
      And User clicks "addProductInOnlineOrder" button
      And User selects "Mağazadan aldı" option from "onlineOrderStatusOptionsField"
      And User clicks "updateOnlineOrderBtn" button
      Then User should get "Qeyd edildi" message
      Then "Mağazadan aldı" should be displayed in "onlineOrderStatusColumn"
      And User clicks "onlineOrderInfoBtn" button
      Then "customerNameEditOrder" should equals "Orxan_update"
      Then "customerSurnameEditOrder" should equals "Gahramanli_update"
      Then "customerFinCodeEditOrder" should equals "55LRF4T_update"
      Then "customerAddressEditOrder" should equals "Xudu Mammadov_update"
      Then "OLMAYAN STOK" and "TESTTT" should be displayed in "productNameInOrder"

    @OnlineOrderQuickActions
      Scenario Outline: Navigate to "<pageName>" page within online order
        When User clicks "onlineOrderPageLink" page link
        And User clicks "newOrderBtn" button
        And User selects "Nağd" radioButton
        And User fills "Orxan" in "customerName" input field
        And User fills "Gahramanli" in "customerSurname" input field
        And User fills "0551111111" in "customerMobile" input field
        And User fills "55LRF4T" in "customerFinCode" input field
        And User fills "Xudu Mammadov" in "customerAddress" input field
        And User fills "Baku" in "customerCity" input field
        And User clicks "productSelectBtn" button
        And User fills "OLMAYAN STOK" in "productSearchByName" input field
        And User press enter button
        And User clicks button with "Əlavə et" text
        And User clicks "completeOnlineOrderBtn" button
        And User clicks "confirmBtn" button
        And Wait 1 second for an element
        And User takes online order number
        And User clicks "confirmBtn" button
        And User fills online order number in search field
        And User's waiting visibility of "onlineOrderInfoBtn" element for 5 seconds
        And User clicks "onlineOrderInfoBtn" button
        And User's waiting visibility of "creditSaleBtn" element for 5 seconds
        And User clicks "<pageBtn>" button
        Then User should be navigated to "<pageName>" page in new tab

      Examples:
        | pageBtn             | pageName       |
        | creditSaleBtn       | Kredit Satış   |
        | cashSaleBtn         | Online Satış   |
        | customerAnalysisBtn | Müştəri Analiz |
        | newCustomerBtn      | Yeni Müştəri   |

  @OnlineOrderBasket
    Scenario: Add online order in basket
      When User clicks "onlineOrderPageLink" page link
      And User clicks "newOrderBtn" button
      And User selects "Nağd" radioButton
      And User fills "Orxan" in "customerName" input field
      And User fills "Gahramanli" in "customerSurname" input field
      And User fills "0551111111" in "customerMobile" input field
      And User fills "55LRF4T" in "customerFinCode" input field
      And User fills "Xudu Mammadov" in "customerAddress" input field
      And User fills "Baku" in "customerCity" input field
      And User clicks "productSelectBtn" button
      And User fills "OLMAYAN STOK" in "productSearchByName" input field
      And User press enter button
      And User clicks button with "Əlavə et" text
      And User clicks "completeOnlineOrderBtn" button
      And User clicks "confirmBtn" button
      And Wait 1 second for an element
      And User takes online order number
      And User clicks "confirmBtn" button
      And User fills online order number in search field
      And User's waiting visibility of "addToBasketBtn" element for 5 seconds
      And User clicks "addToBasketBtn" button
      Then User should get "Səbətə əlavə edildi." message
      Then 1 order should be displayed in "basketBtn"
      And User clicks "basketBtn" button
      Then Online order should be displayed in basket

  @OnlineOrderAssign
    Scenario: Reassign online order to employee
      When User clicks "onlineOrderPageLink" page link
      And User clicks "newOrderBtn" button
      And User selects "Nağd" radioButton
      And User fills "Orxan" in "customerName" input field
      And User fills "Gahramanli" in "customerSurname" input field
      And User fills "0551111111" in "customerMobile" input field
      And User fills "55LRF4T" in "customerFinCode" input field
      And User fills "Xudu Mammadov" in "customerAddress" input field
      And User fills "Baku" in "customerCity" input field
      And User clicks "productSelectBtn" button
      And User fills "OLMAYAN STOK" in "productSearchByName" input field
      And User press enter button
      And User clicks button with "Əlavə et" text
      And User clicks "completeOnlineOrderBtn" button
      And User clicks "confirmBtn" button
      And Wait 1 second for an element
      And User takes online order number
      And User clicks "confirmBtn" button
      And User fills online order number in search field
      And User's waiting visibility of "reassignOrderBtn" element for 5 seconds
      And User clicks "reassignOrderBtn" button
      And User's waiting visibility of "employeeSelectField" element for 5 seconds
      And User selects "Ramil.Mammadzada" option from "employeeSelectField"
      And User clicks "completeAssignBtn" button
      Then OrderNum "nömrəli sifariş Ramil.Mammadzada yönəldildi." message should be displayed
      And User clicks "confirmBtn" button
      Then "Ramil.Mammadzada" should be displayed in "onlineOrderViewerColumn"

  @OnlineOrderForward
    Scenario: Forward online order to creditor
      When User clicks "onlineOrderPageLink" page link
      And User clicks "newOrderBtn" button
      And User selects "Nağd" radioButton
      And User fills "Orxan" in "customerName" input field
      And User fills "Gahramanli" in "customerSurname" input field
      And User fills "0551111111" in "customerMobile" input field
      And User fills "55LRF4T" in "customerFinCode" input field
      And User fills "Xudu Mammadov" in "customerAddress" input field
      And User fills "Baku" in "customerCity" input field
      And User clicks "productSelectBtn" button
      And User fills "OLMAYAN STOK" in "productSearchByName" input field
      And User press enter button
      And User clicks button with "Əlavə et" text
      And User clicks "completeOnlineOrderBtn" button
      And User clicks "confirmBtn" button
      And Wait 1 second for an element
      And User takes online order number
      And User clicks "confirmBtn" button
      And User fills online order number in search field
      And User's waiting visibility of "forwardOrderBtn" element for 5 seconds
      And User clicks "forwardOrderBtn" button
      Then OrderNum "nömrəli sifariş kreditora yönəldildi." message should be displayed
      And User clicks "confirmBtn" button
      Then Online order datagrid should be empty
      And User clicks "webOrdersLink" page link
      And User clicks "refreshBtn" button
      Then Current date should be displayed in "forwardDateColumn"
      Then Logged in user name should be displayed in "forwardByUserNameColumn"

    @OnlineOrderCall
      Scenario Outline: Successful create "<orderType>" order
        When User clicks "<orderType>" page link
        And User clicks "addOrderBtn" button
        And User fills "Orxan" in "customerField" input field
        And User fills "0551111111" in "customerNumberField" input field
        And User fills "<productName>" in "productField" input field
        And User fills "<customerMail>" in "customerMailField" input field
        And User clicks button with "Save" text
        And User double clicks "idColumnHeader" element
        And Wait 1 second for an element
        And User clicks "editOrderBtn" button
        Then "customerField" should equals "Orxan"
        Then "customerNumberField" should equals "994551111111"
        Then "productField" should equals "<productName>"
        Then "customerMailField" should equals "<customerMail>"
        Then Current date should be displayed in "orderCreateDateField"
        Then Logged in user name should be displayed in "oderCreateUserField"


      Examples:
        | orderType       | productName  | customerMail                     |
        | videoCallOrders | Test_Product |                                  |
        | callMeOrders    |              | orkhan.gahramanli@abc-telecom.az |

    @OnlineOrderCallEdit
      Scenario Outline: Edit and change status of "<orderType>" order
        When User clicks "<orderType>" page link
        And User clicks "addOrderBtn" button
        And User fills "Orxan" in "customerField" input field
        And User fills "0551111111" in "customerNumberField" input field
        And User fills "<productName>" in "productField" input field
        And User fills "<customerMail>" in "customerMailField" input field
        And Wait 1 second for an element
        And User clicks button with "Save" text
        And User double clicks "idColumnHeader" element
        And Wait 1 second for an element
        And User clicks "editOrderBtn" button
        And User selects "Sifariş baxılmadadır" status from "orderStatusSelectField"
        And User hover to "noteField" element
        And User fills "testNote" in "noteField" input field
        And User clicks button with "Save" text
        Then Success icon should be displayed
        And User clicks "confirmBtn" button
        And User clicks "editOrderBtn" button
        And Wait 1 second for an element
        And "Sifariş baxılmadadır" status should be selected in "orderStatusSelectField"
        Then Current date should be displayed in "statusChangeDate"
        Then Logged in user name should be displayed in "<statusChangeUser>"


      Examples:
        | orderType       | productName  | customerMail                     | statusChangeUser               |
        | videoCallOrders | Test_Product |                                  | statusChangeUserVideoCallOrder |
        | callMeOrders    |              | orkhan.gahramanli@abc-telecom.az | statusChangeUserCallMeOrder    |