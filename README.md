# Selenium Automation Template

A scalable and modular Selenium automation framework template built with Java.

## Driver Management

This framework provides a centralized and thread-safe WebDriver management solution.
The goal is to make driver handling reusable, maintaniable and CI/CD friendly.

### Thread-Safe Execution

WebDriver intances are managed using ThreadLocal to support parallel test execution. Each test thread gets its own isolated WebDriver instance,
avoiding race conditions and shared state issues.

### Browser Selection with Enums

Browser selection is handled using a type-safe enum instead of raw strings.
This approach prevents invalid browser values and makes the framework easier to extend.

### WebDriver Binary Management

WebDriver binaries are managed automatically using Bonigarcia WebDriverManager.
This removes the need for manual driver setup and ensures compatibility across
different operating systems and browser versions.
