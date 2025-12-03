Markdown

# Foreca_POM_Selenium

[![Selenium WebDriver](https://img.shields.io/badge/Selenium-WebDriver-43B02A?logo=selenium&style=for-the-badge)](https://www.selenium.dev/)
[![Design Pattern](https://img.shields.io/badge/Pattern-Page%20Object%20Model-yellow?style=for-the-badge)]()
[![Test Target](https://img.shields.io/badge/Target-Foreca.com-informational?style=for-the-badge)](https://foreca.com/)

## Project Overview

This repository contains an end-to-end test automation suite for the **[Foreca.com](https://foreca.com/)** website, specifically designed to execute smoke and critical path tests.

Unlike other projects, this suite is built using **Selenium WebDriver**, the industry-standard tool for cross-browser web automation. The project strictly implements the **Page Object Model (POM)** to ensure highly readable, reusable, and maintainable test scripts.

---

## Technology Stack

* **Selenium WebDriver** - The primary tool for browser automation and interaction.
* **Programming Language** - (e.g., JavaScript/TypeScript/Java/Python) – The language used for writing test logic.
* **Page Object Model (POM)** - Used to isolate selectors and page interactions from test cases.
* **Build/Package Manager** - (e.g., npm/Yarn, Maven/Gradle) – Used to manage dependencies.

---

## Prerequisites

To successfully set up and run the tests, you must have the following installed:

1.  **Node.js & npm** (If the project is JavaScript/TypeScript based)
2.  **Browser Drivers** (e.g., ChromeDriver, GeckoDriver) - Necessary for Selenium to control the respective browsers. *These are often managed automatically by project dependencies.*
3.  **Git**

---

## Installation and Execution

The steps below outline the standard process for setting up and running a Node.js-based Selenium project.

### 1. Clone the Repository

```bash
git clone [https://github.com/ivan-milutinovic/Foreca_POM_Selenium.git](https://github.com/ivan-milutinovic/Foreca_POM_Selenium.git)
cd Foreca_POM_Selenium

2. Install Dependencies
Install all necessary project dependencies:
Bash
npm install

3. Running the Tests
Execute the full test suite using the defined test script command:
Bash
npm test
Note: If the command above fails, check the package.json file for the exact script command (e.g., npm run test:e2e).

4. Customizing Execution
To execute tests on different browsers, ensure the necessary browser drivers are available and configured in the project settings.

Project Structure
The project follows the best practices of the Page Object Model (POM) to ensure separation of concerns:

.
├── src/
│   ├── pages/          # Contains all Page Object classes (the heart of POM)
│   └── tests/          # Contains the test scenarios (using page objects)
├── config/             # Configuration files (e.g., environment settings)
├── package.json        # Project dependencies and scripts
└── ...
