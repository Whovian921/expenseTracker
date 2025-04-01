import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The main class that runs the command-line expense tracker application.
 */
public class ExpenseTracker {
    private static ExpenseManager expenseManager = new ExpenseManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        expenseManager.loadExpenses();

        while (true) {
            System.out.println("1. Add Expense\n2. View Expenses\n3. Edit Expense\n4. Delete Expense\n5. Filter by Category\n6. Filter by Date Range\n7. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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
        System.out.println("Expense added successfully!");
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
        System.out.println("Expense updated successfully!");
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
        System.out.println("Expense deleted successfully!");
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
}
