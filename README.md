### Execution config
Following command will execute all scenarios from directory: _src/test/resources/features_ that are tagged by the tag: _@test_  
Scenarios will be executed in a parallel mode in 6 threads (each scenario in a separate java thread). Environment configuration selected is _resources/environments/Test.yaml_.

`gradle clean test -Dcucumber.filter.tags="@test" -Pthreads=6 -Denvironment=Test`

- **@test** - represent the feature or scenario tag (see: https://cucumber.io/docs/cucumber/api/?lang=java#tags)
- **threads** - represent the number of parallel threads used to execute the selected scenarios, if not provided a default value stored in _gradle.properties_ is used. 
- **environment** - represents the name of the environment configuration file (without extension) stored in _resources/environments_, if not provided the default value used is: _Test_ 
