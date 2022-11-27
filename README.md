### Execution config
Following command will execute all scenarios from directory: _src/test/resources/features_ that are tagged by the tag: _@test_  
Scenarios will be executed in a parallel mode (each scenario in a separate java thread).

`gradle clean test -Dcucumber.filter.tags="@test" -Pthreads=6`

- @test - represent the feature or scenario tag (see: https://cucumber.io/docs/cucumber/api/?lang=java#tags)
- threads - represent the number of parallel threads used to execute the selected scenarios 
