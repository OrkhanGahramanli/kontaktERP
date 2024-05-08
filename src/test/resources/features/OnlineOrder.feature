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
      Then "customerName" should equals "Orxan"
      Then "customerSurname" should equals "Gahramanli"
      Then "customerMobile" should equals "0551111111"
      Then "customerFinCode" should equals "55LRF4T"
      Then "customerAddress" should equals "Xudu Mammadov"
      Then "customerCity" should equals "Baku"

