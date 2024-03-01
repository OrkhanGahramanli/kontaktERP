Feature: Order

    Background:
      When User fills input username field with "orkhan.gahramanli"
      And User fills input password field with "test123"
      And User clicks on submit button
      Then User is in "Home Page"
      When User selects "KONTAKT HOME 47 (UKRAYNA DAIRESI)" store
      And User clicks "orderModule" module link

@NewOrder
    Scenario Outline: User want to create new order ("<Sale Type>")
      When User clicks "newOrder" page link
      And User add "<Seller>" code
      And User clicks "productAreaExpandBtn" button
      And User selects "<Product>" option from "productBrand"
      And User clicks "productSearchBtn" button
      And User add any product
      And User fills "<Customer>" in "customerName" input field
      And User selects "<Sale Type>" option from "saleType"
      And User fills "<Birthdate>" in "customerBirthDate" input field
      And User clicks "submitOrder" button
      Then New order should be create
      And User clicks "confirmBtn" button
      And User clicks "onlineOrder" module link
      And User clicks "webOrdersLink" page link
      Then New created order should be in Web Orders list
      Then Type of new created order should be as "<Sale Type>"

      Examples:
        | Sale Type    | Product | Customer | Seller    | Birthdate  |
        | Nağd Satış   | SAMSUNG | Orxan    | IR-000002 |            |
        | Kredit Satış | SAMSUNG | Orxan    | IR-000002 | 06/18/1993 |

  @InvalidCreateOrder
  Scenario Outline: User want to create new order with incorrect inputs ("<Sale Type>") "<CaseName>"
    When User clicks "newOrder" page link
    And User add "<Seller>" code
    And User clicks "productAreaExpandBtn" button
    And User selects "<Product>" option from "productBrand"
    And User clicks "productSearchBtn" button
    And User add any product
    And User fills "<Customer>" in "customerName" input field
    And User selects "<Sale Type>" option from "saleType"
    And User fills "<Birthdate>" in "customerBirthDate" input field
    And User clicks "submitOrder" button
    Then User should get "<error>" message

    Examples:
      | CaseName                       | Sale Type    | Product | Customer | Seller    | Birthdate  | error                                  |
      | Empty required fields          | Nağd Satış   |         |          |           |            | Satıcı kodu seçilməyib !               |
      | Empty Seller field             | Nağd Satış   | SAMSUNG | Orxan    |           | 06/18/1993 | Satıcı kodu seçilməyib !               |
      | Empty Product field            | Nağd Satış   |         | Orxan    | IR-000002 | 06/18/1993 | Məhsul seçilməyib.                     |
      | Empty Customer field           | Nağd Satış   | SAMSUNG |          | IR-000002 | 06/18/1993 | Müştərinin adı min 5 simvol olmalıdır! |
      | Incorrect Seller input         | Nağd Satış   | SAMSUNG | Orxan    | IR-000001 | 06/18/1993 | Satıcı kodu seçilməyib !               |
      | Incorrect Customer input       | Nağd Satış   | SAMSUNG | Orxa     | IR-000002 | 06/18/1993 | Müştərinin adı min 5 simvol olmalıdır! |
      | Empty Customer birthdate field | Kredit Satış | SAMSUNG | Orxan    | IR-000002 |            | Tarix düzgün qeyd olunmayıb            |
      | Empty required fields          | Kredit Satış |         |          |           |            | Satıcı kodu seçilməyib !               |
      | Empty Seller field             | Kredit Satış | SAMSUNG | Orxan    |           | 06/18/1993 | Satıcı kodu seçilməyib !               |
      | Empty Product field            | Kredit Satış |         | Orxan    | IR-000002 | 06/18/1993 | Məhsul seçilməyib.                     |
      | Empty Customer field           | Kredit Satış | SAMSUNG |          | IR-000002 | 06/18/1993 | Müştərinin adı min 5 simvol olmalıdır! |
      | Incorrect Seller input         | Kredit Satış | SAMSUNG | Orxan    | IR-000001 | 06/18/1993 | Satıcı kodu seçilməyib !               |
      | Incorrect Customer input       | Kredit Satış | SAMSUNG | Orxa     | IR-000002 | 06/18/1993 | Müştərinin adı min 5 simvol olmalıdır! |
  @CheckProductsAfterCreate
    Scenario Outline: Check products, services in new order ("<Sale Type>")
      When User clicks "newOrder" page link
      And User add "<Seller>" code
      And User clicks "productAreaExpandBtn" button
      And User selects "<Product>" option from "productBrand"
      And User clicks "productSearchBtn" button
      And User add any product
      And User fills "<Customer>" in "customerName" input field
      And User selects "<Sale Type>" option from "saleType"
      And User fills "<Birthdate>" in "customerBirthDate" input field
      And User clicks "serviceBtn" button
      And User add "<Service>" service
      And User clicks "windowCloseBtn" button
      And User clicks "bundleBtn" button
      And User clicks "bundleAddBtn" button
      And User clicks "windowCloseBtn" button
      And User clicks "submitOrder" button
      Then New order should be create
      And User clicks "confirmBtn" button
      And User clicks "onlineOrder" module link
      And User clicks "webOrdersLink" page link
      Then Products and services should be visible in new order

      Examples:
        | Sale Type    | Product | Customer | Seller    | Birthdate  | Service |
        | Nağd Satış   | SAMSUNG | Orxan    | IR-000002 |            | PXK-009 |
        | Kredit Satış | SAMSUNG | Orxan    | IR-000002 | 06/18/1993 | PXK-009 |

  @CheckTotalAmountOrder
    Scenario: Check total amount after adding products, services in new order
      When User clicks "newOrder" page link
      And User clicks "productAreaExpandBtn" button
      And User selects "SAMSUNG" option from "productBrand"
      And User clicks "productSearchBtn" button
      And User add any product
      And User clicks "serviceBtn" button
      And User add "PXK-009" service
      And User clicks "windowCloseBtn" button
      And User clicks "bundleBtn" button
      And User clicks "bundleAddBtn" button
      Then Total amount should be sum of all prices
@CheckProductsInBundleOrder
    Scenario: Check products in bundle
      When User clicks "newOrder" page link
      And User clicks "bundleBtn" button
      And User clicks "bundleDetailsBtn" button
      Then Products should be displayed in bundle
@NewOrderDifferentStore
    Scenario Outline: Create order with product from different store ("<Sale Type>")
      When User clicks "newOrder" page link
      And User add "<Seller>" code
      And User clicks "productAreaExpandBtn" button
      And User selects "<Product>" option from "productBrand"
      And User clicks "productSearchBtn" button
      And User add any product from different store
      And User clicks "windowCloseBtn" button
      And User selects "Basqa Magaza veya Anbardan Satis Magazasina Teslim" option from "productDeliveryType"
      And User fills "<Customer>" in "customerName" input field
      And User selects "<Sale Type>" option from "saleType"
      And User fills "<Birthdate>" in "customerBirthDate" input field
      And User clicks "submitOrder" button
      Then New order should be create
      And User clicks "confirmBtn" button
      And User clicks "onlineOrder" module link
      And User clicks "webOrdersLink" page link
      Then New created order should be in Web Orders list
      Then Type of new created order should be as "<Sale Type>"

      Examples:
        | Sale Type    | Product | Customer | Seller    | Birthdate  |
        | Nağd Satış   | SAMSUNG | Orxan    | IR-000002 |            |
        | Kredit Satış | SAMSUNG | Orxan    | IR-000002 | 06/18/1993 |

@Creditors
      Scenario: Set work status of creditor
        When User clicks "creditors" page link
        And User change work status of creditor
        Then Work status should be changed

    @ProductsInfo
    Scenario Outline: Check "<infoButton>" buttons in "Məhsullar üzrə məlumat" page
      When User clicks "productsInfo" page link
      And User selects "SAMSUNG" option from "productBrand"
      And User clicks "productSearchBtn" button
      And User clicks "<infoButton>" of a product
      Then Relative "<info>" should be displayed in new window

      Examples:
        | infoButton     | info                            |
        | Parametrlər    | Məlumatlar                      |
        | Qiymət Cədvəli | Məhsul Qiymət Cədvəli           |
        | Ətraflı        | Məhsul haqqında ətraflı məlumat |
        | Barkodlar      | Məhsul barkodları               |
        | Digər Anbarlar | Məhsullar                       |
        | Bonus          | Məlumatlar                      |