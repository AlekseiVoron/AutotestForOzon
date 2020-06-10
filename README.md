# AutotestForOzon
Autotests for Ozon.ru. Soft Testing, 6 term. 

Screenshots of allure report are located in `report_screenshots`.

Results of the last test are located in `target/allure-results`.

Main test's class - `src/test/java/autotests/TestAllCases`.

Allure's report created correctly if used JDK 8.

To run the tests use `mvn clean test`.

To show the results in browser use `mvn allure:serve`.

During the tests, you need to enter the code from SMS twice. The phone number is indicated explicitly in the `Config` class. The timeout for entering the code is 40 seconds.
