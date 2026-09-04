# Prestashop purchase flow Test Automation Framework PoC

This is a toy test automation framework for Prestashop purchase flow

## Getting started:

### Prerequisites

- Java 25
- JAVA_HOME added to your PATH
- Git
- Apache Maven 3.9.6

### Technologies
- Java 25
- Maven
- Lombok
- Playwright for browser automation
- Cucumber for BDD scenarios
- Page Object Model pattern
- PicoContainer - light DI for building up test steps dependencies
- AssertJ for validations in test steps
- AbstractTestNGCucumberTests for executing tests in parallel

This project is using one `Playwright`, `BrowserContext` and `Page` objects for each **Cucumber** scenario execution.

### Configuration

The project configuration uses several layers:
```shell
System property
     ↓
config.yaml
     ↓
BrowserConfig.defaults()
```
The project uses the following configuration settings:

*Browser settings:*
- `type`: WEB browser type; possible values: `CHROMIUM`, `FIREFOX`, `WEBKIT` (default value: `CHROMIUM`)
- `headless`: (default value:`true`)
- `maximized`: (default value: `false`)
- `timeout`:  **Playwright** default timeout in `milliseconds` (this setting **MUST** be set up in the **YAML** config file only)
- `navigationTimeout`: **Playwright** default navigation timeout in `milliseconds` (this setting **MUST** be set up in the **YAML** config file only)

*Test parallel execution settings:*
- `threads.number`: The number of working threads (**TestNG** threads) which run in parallel. 
A working thread execute a single **Cucumber** scenario. 
This setting can be configured in the `pom.xml` file or as a **System** property. (default value: `1`)

## How to run:

*1.Using Maven from CLI*

```shell
mvn clean test
```

Optionally, one can specify the number of scenarios to run in parallel. By default, they run in sequence.
Also, the following **browser** settings can be configured as **System** properties: `type`, `headless`, `maximized`.
For example, in order to have at most `4` scenarios running in parallel on `maximized` `CHROMIUM` browsers:

```shell
mvn clean test -Dthreads.number=4 -Dbrowser.headless=false -Dbrowser.maximized=true
```