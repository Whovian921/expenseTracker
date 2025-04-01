import java.util.*;

public class ExpenseTracker {
    private static Scanner scanner = new Scanner(System.in);
    private static ExpenseManager expenseManager = new ExpenseManager();

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add Expense\n2. View Expenses\n3. Filter by Category\n4. Filter by Date Range\n5. Edit Expense\n6. Delete Expense\n7. Exit");
            int choice = InputValidator.getIntInput("Enter choice: ");

            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewExpenses(expenseManager.getExpenses());
                    break;
                case 3:
                    filterByCategory();
                    break;
                case 4:
                    filterByDateRange();
                    break;
                case 5:
                    editExpense();
                    break;
                case 6:
                    deleteExpense();
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

    private static void addExpense() {
        double amount = InputValidator.getDoubleInput("Enter amount (must be positive): ");
        String category = InputValidator.getStringInput("Enter category: ");
        String description = InputValidator.getStringInput("Enter description: ");
        Expense expense = new Expense(amount, category, description, new Date());
        expenseManager.addExpense(expense);
        System.out.println("Expense added successfully!");
    }

    private static void viewExpenses(List<Expense> expenseList) {
        if (expenseList.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        for (Expense expense : expenseList) {
            System.out.println(expense);
        }
    }

    private static void filterByCategory() {
        String category = InputValidator.getStringInput("Enter category to filter by: ");
        List<Expense> filtered = expenseManager.filterByCategory(category);
        viewExpenses(filtered);
    }

    private static void filterByDateRange() {
        Date startDate = InputValidator.getDateInput("Enter start date (yyyy-MM-dd): ");
        Date endDate = InputValidator.getDateInput("Enter end date (yyyy-MM-dd): ");
        List<Expense> filtered = expenseManager.filterByDateRange(startDate, endDate);
        viewExpenses(filtered);
    }

    private static void editExpense() {
        int index = InputValidator.getIntInput("Enter the index of the expense to edit (starting from 1): ") - 1;
        if (index < 0 || index >= expenseManager.getExpenses().size()) {
            System.out.println("Invalid index.");
            return;
        }
        Expense expense = expenseManager.getExpenses().get(index);
        System.out.println("Editing expense: " + expense);
        double amount = InputValidator.getDoubleInput("Enter new amount (leave blank to keep current): ");
        String category = InputValidator.getStringInput("Enter new category (leave blank to keep current): ");
        String description = InputValidator.getStringInput("Enter new description (leave blank to keep current): ");
        Expense updatedExpense = new Expense(amount > 0 ? amount : expense.getAmount(),
                !category.isEmpty() ? category : expense.getCategory(),
                !description.isEmpty() ? description : expense.getDescription(),
                expense.getDate());
        expenseManager.editExpense(index, updatedExpense);
        System.out.println("Expense edited successfully!");
    }

    private static void deleteExpense() {
        int index = InputValidator.getIntInput("Enter the index of the expense to delete (starting from 1): ") - 1;
        if (index < 0 || index >= expenseManager.getExpenses().size()) {
            System.out.println("Invalid index.");
            return;
        }
        expenseManager.deleteExpense(index);
        System.out.println("Expense deleted successfully!");
    }
}
