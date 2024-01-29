@Order
  Feature: Order

    Background:
      When User fills input username field with "orkhan.gahramanli"
      And User fills input password field with "test123"
      And User clicks on submit button
      Then User should navigate to Home Page


    Scenario Outline: User want to create new order ("<Sale Type>")
      Given User is in "Home Page"
      When User selects "KONTAKT HOME 47 (UKRAYNA DAIRESI)" store
      And User clicks order menu
      And User clicks new order link
      And User add "<Seller>" code
      And User add "<Product>" product
      And User fills "<Customer>" field
      And User selects "<Sale Type>"
      And User fills customer "<Birthdate>" field "Kredit Satış"
      And User clicks submit order button
      Then New order should be create
      Then New created order should be in Web Orders list
      Then Type of new created order should be as "<Sale Type>"

      Examples:
        | Sale Type    | Product | Customer | Seller    | Birthdate  |
        | Nağd Satış   | samsung | Orxan    | IR-000002 |            |
        | Kredit Satış | samsung | Orxan    | IR-000002 | 06/18/1993 |


  Scenario Outline: User want to create new order with incorrect inputs ("<Sale Type>") "<CaseName>"
    Given User is in "Home Page"
    When User selects "KONTAKT HOME 47 (UKRAYNA DAIRESI)" store
    And User clicks order menu
    And User clicks new order link
    And User add "<Seller>" code
    And User add "<Product>" product
    And User fills "<Customer>" field
    And User selects "<Sale Type>"
    And User fills customer "<Birthdate>" field "Kredit Satış"
    And User clicks submit order button
    Then User should get "<error>" message in new Order Page

    Examples:
      | CaseName                       | Sale Type    | Product | Customer | Seller    | Birthdate  | error                                  |
      | Empty required fields          | Nağd Satış   |         |          |           |            | Satıcı kodu seçilməyib !               |
      | Empty Seller field             | Nağd Satış   | samsung | Orxan    |           | 06/18/1993 | Satıcı kodu seçilməyib !               |
      | Empty Product field            | Nağd Satış   |         | Orxan    | IR-000002 | 06/18/1993 | Məhsul seçilməyib.                     |
      | Empty Customer field           | Nağd Satış   | samsung |          | IR-000002 | 06/18/1993 | Müştərinin adı min 5 simvol olmalıdır! |
      | Incorrect Seller input         | Nağd Satış   | samsung | Orxan    | IR-000001 | 06/18/1993 | Satıcı kodu seçilməyib !               |
      | Incorrect Customer input       | Nağd Satış   | samsung | Orxa     | IR-000002 | 06/18/1993 | Müştərinin adı min 5 simvol olmalıdır! |
      | Empty Customer birthdate field | Kredit Satış | samsung | Orxan    | IR-000002 |            | Tarix düzgün qeyd olunmayıb            |
      | Empty required fields          | Kredit Satış |         |          |           |            | Satıcı kodu seçilməyib !               |
      | Empty Seller field             | Kredit Satış | samsung | Orxan    |           | 06/18/1993 | Satıcı kodu seçilməyib !               |
      | Empty Product field            | Kredit Satış |         | Orxan    | IR-000002 | 06/18/1993 | Məhsul seçilməyib.                     |
      | Empty Customer field           | Kredit Satış | samsung |          | IR-000002 | 06/18/1993 | Müştərinin adı min 5 simvol olmalıdır! |
      | Incorrect Seller input         | Kredit Satış | samsung | Orxan    | IR-000001 | 06/18/1993 | Satıcı kodu seçilməyib !               |
      | Incorrect Customer input       | Kredit Satış | samsung | Orxa     | IR-000002 | 06/18/1993 | Müştərinin adı min 5 simvol olmalıdır! |

    Scenario Outline: Check products, services in new order ("<Sale Type>")
      Given User is in "Home Page"
      When User selects "KONTAKT HOME 47 (UKRAYNA DAIRESI)" store
      And User clicks order menu
      And User clicks new order link
      And User add "<Seller>" code
      And User add "<Product>" product
      And User fills "<Customer>" field
      And User selects "<Sale Type>"
      And User fills customer "<Birthdate>" field "Kredit Satış"
      And User add "<Service>" service in order
      And User add Bundle in order
      And User clicks submit order button
      Then New order should be create
      Then Products and services should be visible in new order

      Examples:
        | Sale Type    | Product | Customer | Seller    | Birthdate  | Service |
        | Nağd Satış   | samsung | Orxan    | IR-000002 |            | PXK-009 |
        | Kredit Satış | samsung | Orxan    | IR-000002 | 06/18/1993 | PXK-009 |


    Scenario: Check total amount after adding products, services in new order
      Given User is in "Home Page"
      When User selects "KONTAKT HOME 47 (UKRAYNA DAIRESI)" store
      And User clicks order menu
      And User clicks new order link
      And User add "samsung" product
      And User add "PXK-009" service in order
      And User add Bundle in order
      Then Total amount should be sum of all prices

    Scenario: Check products in bundle
      Given User is in "Home Page"
      When User selects "KONTAKT HOME 47 (UKRAYNA DAIRESI)" store
      And User clicks order menu
      And User clicks new order link
      And User clicks bundle select button
      And User clicks on button to see products in bundle
      Then Products should be displayed in bundle

    Scenario Outline: Create order with product from different store ("<Sale Type>")
      Given User is in "Home Page"
      When User selects "KONTAKT HOME 47 (UKRAYNA DAIRESI)" store
      And User clicks order menu
      And User clicks new order link
      And User add "<Seller>" code
      And User add "<Product>" product from different store
      And User selects "Basqa Magaza veya Anbardan Satis Magazasina Teslim" delivery type of the product
      And User fills "<Customer>" field
      And User selects "<Sale Type>"
      And User fills customer "<Birthdate>" field "Kredit Satış"
      And User clicks submit order button
      Then New order should be create
      Then New created order should be in Web Orders list
      Then Type of new created order should be as "<Sale Type>"

      Examples:
        | Sale Type    | Product | Customer | Seller    | Birthdate  |
        | Nağd Satış   | samsung | Orxan    | IR-000002 |            |
        | Kredit Satış | samsung | Orxan    | IR-000002 | 06/18/1993 |

    @Case
      Scenario: Set work status of creditor
        Given User is in "Home Page"
        When User selects "KONTAKT HOME 47 (UKRAYNA DAIRESI)" store
        And User clicks order menu
        And User directs to Creditors page
        And User change work status of creditor
        Then Work status should be changed