# cucumber_second_project_5

This framework combines the **Java** programming language, the **Apache Maven** build tool, and the **Cucumber** testing library to create automated tests for web applications.
This Java-Maven-Cucumber framework works by defining the test cases using the Gherkin language in a feature file. The feature file contains a list of scenarios that describe the functionality of the application. Each scenario consists of a series of steps defined using **Gherkin** keywords
In this project I use **JUnit** for its annotations, assertions, and simple reports. I use Selenium for UI scripts to automate browsers. Cucumber enables me to create feature files in Gherkin language, which hides the implementation and creates **HTML reports** that are easy to  follow in the agile environment. For UI testing, there are a few design patterns that I follow. I implement **Page Object Model** and **Singleton design patterns** for UI scripts and centralize all UI web elements and
page-related methods in object repositories  using **PageFactory class**. Singleton allows me to use the same driver instance in our framework.
The project also contains a utill package that contains very useful methods such as: 
 - randomNumberGenerator
 - ConfigReader
 - Driver
 - Waiter
 
The project is configured to execute **@Smoke** and **@Regression** Suites with the help of maven commands (mvn test -DCucumber.options="--tags @Regression" or mvn test -DCucumber.options="--tags @Smoke"). 

