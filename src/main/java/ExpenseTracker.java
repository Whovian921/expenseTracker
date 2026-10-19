package main.java;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map;

/**
 * The main class that runs the command-line expense tracker application.
 */
public class ExpenseTracker {
    private static ExpenseManager expenseManager = new ExpenseManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        expenseManager.loadExpenses();

        while (true) {
            System.out.println("\n========== EXPENSE TRACKER ==========");
            System.out.println("1. Add main.java.Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Edit main.java.Expense");
            System.out.println("4. Delete main.java.Expense");
            System.out.println("5. Filter by Category");
            System.out.println("6. Filter by Date Range");
            System.out.println("7. main.java.Expense Summary");
            System.out.println("8. Monthly Spending Report");
            System.out.println("9. Exit");
            System.out.println("=====================================");
            System.out.print("Enter choice: ");
            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    editExpense();
                    break;
                case 4:
                    deleteExpense();
                    break;
                case 5:
                    filterByCategory();
                    break;
                case 6:
                    filterByDateRange();
                    break;
                case 7:
                    displayExpenseSummary(expenseManager);
                    break;
                case 8:
                    displayMonthlyReport();
                    break;
                case 9:
                    expenseManager.saveExpenses();
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    /**
     * Adds a new expense by prompting the user for details.
     */
    private static void addExpense() {
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        Expense expense = new Expense(amount, category, description, new Date());
        expenseManager.addExpense(expense);
        System.out.println("main.java.Expense added successfully!");
    }

    /**
     * Displays all recorded expenses.
     */
    private static void viewExpenses() {
        List<Expense> expenses = expenseManager.getExpenses();
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        for (int i = 0; i < expenses.size(); i++) {
            System.out.println((i + 1) + ". " + expenses.get(i));
        }
    }

    /**
     * Allows the user to edit an existing expense.
     */
    private static void editExpense() {
        viewExpenses();
        System.out.print("Enter the expense number to edit: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (index < 0 || index >= expenseManager.getExpenses().size()) {
            System.out.println("Invalid expense number.");
            return;
        }

        System.out.print("Enter new amount: ");
        double newAmount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter new category: ");
        String newCategory = scanner.nextLine();

        System.out.print("Enter new description: ");
        String newDescription = scanner.nextLine();

        expenseManager.editExpense(index, newAmount, newCategory, newDescription);
        System.out.println("main.java.Expense updated successfully!");
    }

    /**
     * Allows the user to delete an expense.
     */
    private static void deleteExpense() {
        viewExpenses();
        System.out.print("Enter the expense number to delete: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (index < 0 || index >= expenseManager.getExpenses().size()) {
            System.out.println("Invalid expense number.");
            return;
        }

        expenseManager.deleteExpense(index);
        System.out.println("main.java.Expense deleted successfully!");
    }

    /**
     * Filters expenses by a given category.
     */
    private static void filterByCategory() {
        System.out.print("Enter category to filter by: ");
        String category = scanner.nextLine();

        List<Expense> filteredExpenses = expenseManager.filterByCategory(category);
        if (filteredExpenses.isEmpty()) {
            System.out.println("No expenses found in this category.");
        } else {
            for (Expense expense : filteredExpenses) {
                System.out.println(expense);
            }
        }
    }

    /**
     * Filters expenses by a date range.
     */
    private static void filterByDateRange() {
        System.out.print("Enter start date (yyyy-MM-dd): ");
        String startDateStr = scanner.nextLine();
        System.out.print("Enter end date (yyyy-MM-dd): ");
        String endDateStr = scanner.nextLine();

        try {
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);

            List<Expense> filteredExpenses = expenseManager.filterByDateRange(startDate, endDate);
            if (filteredExpenses.isEmpty()) {
                System.out.println("No expenses found in this date range.");
            } else {
                for (Expense expense : filteredExpenses) {
                    System.out.println(expense);
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    /**
     * Displays a summary of recorded expenses.
     *
     * @param manager The expense manager containing the records.
     */
    private static void displayExpenseSummary(ExpenseManager manager) {
        System.out.println("\n========== EXPENSE SUMMARY ==========");

        System.out.println("Total Expenses: " + manager.getExpenses().size());

        System.out.printf(
                "Total Spending: $%.2f%n",
                manager.getTotalSpending()
        );

        System.out.printf(
                "Average main.java.Expense: $%.2f%n",
                manager.getAverageExpense()
        );

        System.out.println("\nSpending by Category:");
        System.out.println("-------------------------------------");

        Map<String, Double> categoryTotals = manager.getSpendingByCategory();

        if (categoryTotals.isEmpty()) {
            System.out.println("No expenses recorded.");
        } else {
            for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
                System.out.printf(
                        "%-20s $%.2f%n",
                        entry.getKey(),
                        entry.getValue()
                );
            }
        }

        System.out.println("=====================================");
    }

    /**
     * Displays spending statistics for a selected month and year.
     */
    private static void displayMonthlyReport() {
        int month;
        int year;

        try {
            System.out.print("Enter month (1-12): ");
            month = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter year (e.g., 2026): ");
            year = Integer.parseInt(scanner.nextLine().trim());

            if (month < 1 || month > 12 || year < 1) {
                System.out.println("Invalid month or year.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid numbers.");
            return;
        }

        List<Expense> monthlyExpenses =
                expenseManager.filterByMonth(month, year);

        if (monthlyExpenses.isEmpty()) {
            System.out.println("No expenses found for this month.");
            return;
        }

        double total = 0.0;
        Map<String, Double> categoryTotals =
                new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        for (Expense expense : monthlyExpenses) {
            total += expense.getAmount();

            categoryTotals.merge(
                    expense.getCategory(),
                    expense.getAmount(),
                    Double::sum
            );
        }

        double average = total / monthlyExpenses.size();

        String monthName = new java.text.DateFormatSymbols()
                .getMonths()[month - 1];

        System.out.println("\n========== MONTHLY EXPENSE REPORT ==========");
        System.out.println("Month: " + monthName + " " + year);
        System.out.println("Total Expenses: " + monthlyExpenses.size());
        System.out.printf("Total Spending: $%.2f%n", total);
        System.out.printf("Average main.java.Expense: $%.2f%n", average);

        System.out.println("\nSpending by Category:");
        System.out.println("--------------------------------------------");

        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            System.out.printf(
                    "%-20s $%.2f%n",
                    entry.getKey(),
                    entry.getValue()
            );
        }

        System.out.println("============================================");
    }
}
