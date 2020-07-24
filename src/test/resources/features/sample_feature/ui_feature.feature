Feature: Google Search?
  What can I search in google?

  @ui_demo
  Scenario: Finding some cheese
    Given I am on the Google search page
    When I search for "Cheese!"
    Then the page title should start with "cheese"