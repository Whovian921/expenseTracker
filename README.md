
# Java Expense Tracker

[![Java CI with Maven](https://github.com/Whovian921/expenseTracker/actions/workflows/maven.yml/badge.svg?branch=master)](https://github.com/Whovian921/expenseTracker/actions/workflows/maven.yml)

## Overview

Java Expense Tracker is a command-line application designed to help users record, organize, and analyze their personal expenses.

The application allows users to add, edit, and delete expenses, filter transactions by category or date, and generate spending summaries. It also provides monthly reports to help users better understand their spending habits.

Expense data is stored locally using CSV files, allowing transactions to persist between application sessions.

The project is developed using Java and Maven, with automated unit testing through JUnit 5 and continuous integration using GitHub Actions.


## Features

### Expense Management
- **Add Expenses:** Record expenses with an amount, category, description, and date.
- **View Expenses:** Display all recorded transactions.
- **Edit Expenses:** Update an existing expense's amount, category, or description.
- **Delete Expenses:** Remove an existing expense.

### Filtering and Reporting
- **Category Filtering:** View expenses belonging to a specific category.
- **Date Range Filtering:** View expenses within a specified date range.
- **Spending Summary:** Calculate total spending, average expense amount, and spending totals by category.
- **Monthly Reports:** Generate spending reports for a selected month and year.

### Data Storage
- **CSV Persistence:** Save and load expenses using a local CSV file.
- **Input Validation:** Validate expense amounts, dates, and other user inputs.

### Testing and Automation
- **JUnit 5:** Automated tests covering core application functionality.
- **Apache Maven:** Dependency management and automated builds.
- **GitHub Actions:** Continuous integration that automatically builds and tests the project.

## Technologies Used

| Technology | Purpose |
|------------|---------|
| Java | Application development |
| Maven | Build automation and dependency management |
| JUnit 5 | Unit testing |
| GitHub Actions | Continuous integration |
| CSV | Local data storage |
| Git | Version control |

## Requirements

Before running the application, ensure you have:

- Java Development Kit (JDK) 17 or later
- Apache Maven 3.9 or later
- Git (optional, for cloning the repository)

Verify your installations:

```bash
java -version
mvn -version
```

## Installation

### 1. Clone the Repository

```bash
git clone https://github.com/Whovian921/expenseTracker.git
cd expenseTracker
```

### 2. Compile the Application

```bash
mvn clean compile
```

### 3. Run the Application

```bash
java -cp target/classes main.java.ExpenseTracker
```

The application will launch in your terminal.

## Usage

The application provides an interactive menu:

```text
===== Expense Tracker =====

1. Add Expense
2. View Expenses
3. Filter by Category
4. Filter by Date Range
5. Edit Expense
6. Delete Expense
7. Expense Summary
8. Monthly Report
9. Exit

Enter your choice:
```

### Adding an Expense

Select option `1` and enter the requested information.

Example:

```text
Enter amount: 45.50
Enter category: Food
Enter description: Groceries

Expense added successfully!
```

### Viewing Expenses

Select option `2` to display your recorded expenses.

Example:

```text
$45.50 - Food (Groceries)
$30.00 - Transportation (Gas)
$75.00 - Utilities (Internet)
```

### Filtering Expenses

Use option `3` to filter expenses by category.

Use option `4` to filter expenses within a date range.

Dates are entered in the format:

```text
yyyy-MM-dd
```

### Editing and Deleting Expenses

Use option `5` to modify an existing expense.

Use option `6` to remove an expense.

Follow the on-screen instructions to select the expense you want to modify or delete.

### Expense Summary

Select option `7` to view:

- Total spending
- Average expense amount
- Spending totals by category

### Monthly Reports

Select option `8` and enter a month and year.

The application generates a report containing:

- Total spending for the selected month
- Average expense amount
- Spending totals grouped by category

## Data Persistence

Expense records are stored locally in a CSV file named `expenses.csv`.

The application loads previously saved expenses when it starts and saves expense records when exiting.

## Project Structure

```text
expenseTracker/
├── .github/
│   └── workflows/
│       └── maven.yml
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── Expense.java
│   │       ├── ExpenseManager.java
│   │       ├── ExpenseTracker.java
│   │       └── InputValidator.java
│   └── test/
│       └── java/
│           └── ExpenseManagerTest.java
├── expenses.csv
├── pom.xml
└── README.md
```

### Main Classes

**Expense.java**
- Represents an individual expense.
- Stores the amount, category, description, and date.
- Provides methods for converting expenses to and from CSV format.

**ExpenseManager.java**
- Manages the collection of expenses.
- Handles adding, editing, deleting, and filtering.
- Calculates spending totals and averages.
- Generates category-based and monthly spending data.
- Handles CSV loading and saving.

**ExpenseTracker.java**
- Contains the application's entry point.
- Provides the interactive command-line menu.
- Connects user input to expense management functionality.

**InputValidator.java**
- Handles user input validation.
- Helps prevent invalid amounts, dates, and other incorrect entries.

## Testing

The project uses JUnit 5 to verify application functionality.

The automated test suite includes tests for:

- Adding expenses
- Editing expenses
- Deleting expenses
- Total spending calculations
- Average spending calculations
- Empty expense lists
- Category filtering
- Category spending totals
- Monthly filtering
- Invalid month handling

Run the tests using:

```bash
mvn test
```

Run a clean build and execute all verification steps:

```bash
mvn clean verify
```

## Continuous Integration

This project uses GitHub Actions to automatically compile and test the application.

The workflow runs when changes are pushed to the `master` branch or when a pull request targets that branch.

The workflow performs the following steps:

1. Checks out the repository.
2. Configures Java 17.
3. Builds the project using Maven.
4. Executes the JUnit test suite.
5. Reports the build status.

You can view the workflow results in the [GitHub Actions tab](https://github.com/Whovian921/expenseTracker/actions).

## Future Improvements

Potential future enhancements include:

- Monthly budget limits and spending alerts
- Additional reporting and spending analytics
- Exporting financial summaries
- Improved CSV error handling
- More comprehensive automated testing
- A graphical user interface
- Improved monetary precision using BigDecimal

## Author

**Logan Barr**

[GitHub Profile](https://github.com/Whovian921)