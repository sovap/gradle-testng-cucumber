Feature: Example feature

  @test
  Scenario: Example scenario 1
    When User will open google search
    And User will search for "selenium"
    Then User will sleep for 1 seconds

  @test
  Scenario: Example scenario 2
    When User will open google search
    And User will search for "testng"
    Then User will sleep for 2 seconds

  @test
  Scenario: Example scenario 3
    When User will open google search
    And User will search for "gradle"
    Then User will sleep for 3 seconds

  @test
  Scenario: Example scenario 4
    When User will open google search
    And User will search for "cucumber"
    Then User will sleep for 4 seconds

  @test
  Scenario: Example scenario 5
    Then User will sleep for 5 seconds
#    And Scenario will fail
