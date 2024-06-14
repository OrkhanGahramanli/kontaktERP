#Feature: Product
#
#  Background:
#    Given User is in "Login Page"
#    When User fills input username field with "orkhan.gahramanli"
#    And User fills input password field with "test123"
#    And User clicks on submit button
#    Then User is in "Home Page"
#    When User selects "KONTAKT HOME 47 (UKRAYNA DAIRESI)" store
#    And User clicks "productModuleLink" module link
#
#    Scenario: Add product specifications
#      When User clicks "groupParameterPageLink" page link
#      And User selects "Vinil" option from "productCategoryList"
#      And Delete parameter associated to the product category
#      And User fills "Ölçü" in "parameterNameAze" input field
#      And User fills "Размер" in "parameterNameRu" input field
#      And User fills "1" in "webOrder" input field
#      And User clicks on "viewOnWeb" checkbox
#      And User clicks on "makeActive" checkbox
#      And User clicks "addParameterBtn" button
#      And User takes parameter group code
#      And User clicks "productModuleLink" module link
#      And User clicks "parameterOptionsPageLink" page link
#      And User clicks "filterByGroupCodeBtn" button
#      And Delete all parameter options
#      And User clicks "addParameterOptionBtn" button
#      And User selects "Vinil" option from "productGroupList"
#      And User selects "Vinil" option from "productGroupList"
#      And User selects "Ölçü / Размер" option from "parameterNameList"
#      And User clicks "parameterOptionPlusBtn" button
#      And User fills "Böyük" in "parameterOptionAze" input field
#      And User fills "Большой" in "parameterOptionRu" input field
#      And User clicks "saveParameterOptionsBtn" button
#      And User clicks "confirmBtn" button
#      And User clicks "productParameterPageLink" page link
#      And User clicks "productAreaExpandBtn" button
#      And User selects "Vinil" option from "productCategories"
#      And User clicks "productSearchBtn" button
#      And User selects "OLMAYAN STOK" product
#      And User selects "Böyük" option from "optionAze"
#      And User selects "Большой" option from "optionRu"
#      And User clicks "saveProductParameterOptionsBtn" button
#      Then User should get "Məlumatlar sistemə qeyd edildi" message
#      And User selects "OLMAYAN STOK" product
#      And User's waiting visibility of "optionAze" element for 5 seconds
#      Then "Böyük" option should be selected in "optionAze" and "Большой" in "optionRu"