Feature: Prestashop purchase checkout workflow

  Scenario Outline: Checkout a single product on demo Prestashop website
    Given I am on the Prestashop website "<url>"
    When I search for "<product_name>"
    Then I see a list of "<product_name>" products
    And I open the product page for "<product_name>"
    And I select the "<product_size>" product size
    And I enter valid "<valid_quantity>" value in the quantity field
    And I select valid "<color>" variant
    When I click the Add to cart a button
    Then The Added to cart page is displayed
    When I click the Proceed to checkout button
    Then The Shopping Cart page is displayed and contains the added product
    When I click the Cart Proceed to checkout button
    Then The Personal information page is displayed
    Examples:
      | url                                    | product_name | product_size | valid_quantity | color |
      | https://demo.prestashop.com/#/en/front | t-shirt      | M            | 2              | Black |