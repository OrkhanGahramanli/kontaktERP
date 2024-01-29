@Login
Feature: Login
  Scenario Outline: User should login with correct credentials
    Given User is in "Login Page"
    When User fills input username field with "<username>"
    And User fills input password field with "<password>"
    And User selects "<language>" language
    And User clicks on submit button
    Then User should navigate to Home Page
    Then Home Page language should be in "<language>"

    Examples:
      | username          | password | language |
      | orkhan.gahramanli | test123  | az       |
      | orkhan.gahramanli | test123  | en       |
      | orkhan.gahramanli | test123  | ru       |
      | orkhan.gahramanli | test123  | ka       |

  Scenario Outline: User should login with "<name>"
    Given User is in "Login Page"
    When User fills input username field with "<username>"
    And User fills input password field with "<password>"
    And User clicks on submit button
    Then User should get "<error>" message in Login Page

    Examples:
      | name                                      | username          | password | error                                               |
      | Correct username and incorrect password   | orkhan.gahramanli | Noyabr   | İstifadəçi adı və ya şifrə düzgün deyil.            |
      | InCorrect username and correct password   | orxan             | test123  | İstifadəçi mövcud deyil.                            |
      | Incorrect username and incorrect password | orxan             | Noyabr   | İstifadəçi mövcud deyil.                            |
      | Correct username and empty password       | orkhan.gahramanli |          | Şifrə boş olabilməz.                                |
      | Empty username and correct password       |                   | test123  | İstifadəçi adı boş olabilməz.                       |
      | Empty username and empty password         |                   |          | İstifadəçi adı boş olabilməz.\nŞifrə boş olabilməz. |

