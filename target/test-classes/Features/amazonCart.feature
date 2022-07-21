Feature: Test Amazon cart functionality

  Scenario: Validate Amazone search is working
    Given browser is open
    And user is on amazon home page
    When user enters Hats for men in the search box
    And hits the enter button
    Then search results are displayed

  Scenario: Validate view product is working
    Given user has searched for a product
    When clicks on a product
    Then the product details should be displayed
    And observes the price of of one men hat

  Scenario: Validate add a product to cart is working
    Given user is on products page
    When user selects a quantity
    And clicks on the add to cart button
    Then the product is added in the cart
    And assert the subtotal in cart with two men hats is correct
    And assert the quantity in cart with two men hats is correct

  Scenario: Validate searching another product is working
    Given user is on cart page
    When user enters a Hats for Women in the search box
    And hits the enter button
    And search results are displayed
    And clicks on a product
    Then the product details should be displayed

  Scenario: Validate adding another product to the cart is working
    Given user is on products page
    And observes the price of of one women hat
    When user selects a quantity for women hat
    And clicks on the add to cart button
    Then the product is added in the cart
    And assert the subtotal in cart with two men and one women hat is correct
    And assert the quantity in cart with two men and one women hat is correct

  Scenario: Validate removing one men hat from the cart is working
    Given user is on cart page
    When user removes one men hat
    And assert the subtotal in cart with one men and one women hat is correct
    And assert the quantity in cart with one men and one women hat is correct
