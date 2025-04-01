**Expense Tracker**
A simple command-line application to track expenses. The program allows you to add, view, edit, delete, filter by category, and filter by date range. It supports saving expenses to a CSV file and loading them on startup.

**Features**
Add Expense: Allows users to add a new expense by entering the amount, category, and description.
View Expenses: Displays all recorded expenses.
Filter by Category: View expenses filtered by a specific category.
Filter by Date Range: View expenses within a specific date range.
Edit Expense: Modify an existing expense by updating its amount, category, or description.
Delete Expense: Remove an expense from the list.
Persistent Storage: Expenses are saved to a CSV file and loaded on program startup.

**Requirements**
Java 8 or later
A text editor or IDE to run the program (e.g., IntelliJ IDEA, Eclipse, or VS Code)

**Usage**
1. Add Expense
Enter the amount of the expense (e.g., 100.50).
Enter the category of the expense (e.g., Food).
Enter a description (e.g., Lunch at restaurant).

2. View Expenses
Lists all recorded expenses in the format: $amount - category (description) on date.

3. Filter by Category
Enter a category to view only the expenses belonging to that category (e.g., Food).

4. Filter by Date Range
Enter a start and end date to filter the expenses that fall within the specified date range (format: yyyy-MM-dd).

5. Edit Expense
Enter the index of the expense to edit.
Modify any of the expense's fields (amount, category, description).

6. Delete Expense
Enter the index of the expense to delete from the list.

7. Exit
Save all expenses to the CSV file and exit the program.

**Code Structure**
Expense.java: The data model representing an individual expense. It includes fields for the amount, category, description, and date, and methods to convert expenses to/from CSV format.
ExpenseManager.java: Manages all expenses. This class handles loading, saving, adding, editing, deleting, and filtering expenses.
ExpenseTracker.java: The main application class that provides the command-line interface for interacting with the user. It communicates with ExpenseManager to perform the necessary operations.
InputValidator.java: A utility class for validating user input (e.g., ensuring that amounts are positive, categories are non-empty, and dates are valid).

**Example**
1. Add Expense
2. View Expenses
3. Filter by Category
4. Filter by Date Range
5. Edit Expense
6. Delete Expense
7. Exit

Enter choice: 1
Enter amount (must be positive): 100.50
Enter category: Food
Enter description: Lunch at restaurant
Expense added successfully!

Enter choice: 2
$100.50 - Food (Lunch at restaurant) on Mon Apr 01 12:00:00 GMT 2025

Enter choice: 7
Goodbye!

**How to Run**
Clone the repository or download the project files.
Open the project in your preferred IDE or text editor.
Compile and run ExpenseTracker.java.
Use the menu in the command-line interface to interact with the program.

**Saving and Loading**
Expenses are saved to a CSV file named expenses.csv. On program startup, any previously saved expenses are loaded automatically.

**Author**
Logan Barr
