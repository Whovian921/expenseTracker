import java.io.*;
import java.util.*;

/**
 * Manages a list of expenses, including adding, editing, deleting, saving, loading, and filtering expenses.
 */
public class ExpenseManager {
    private static final String FILE_NAME = "expenses.csv";
    private List<Expense> expenses = new ArrayList<>();

    /**
     * Loads expenses from the CSV file into the application.
     */
    public void loadExpenses() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                expenses.add(Expense.fromCSV(fileScanner.nextLine()));
            }
        } catch (IOException e) {
            System.out.println("Error loading expenses: " + e.getMessage());
        }
    }

    /**
     * Saves all expenses to the CSV file.
     */
    public void saveExpenses() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Expense expense : expenses) {
                writer.println(expense.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }

    /**
     * Adds a new expense to the list.
     *
     * @param expense The expense to be added.
     */
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    /**
     * Returns all recorded expenses.
     *
     * @return A list of expenses.
     */
    public List<Expense> getExpenses() {
        return expenses;
    }

    /**
     * Filters expenses by category.
     *
     * @param category The category to filter by.
     * @return A list of expenses matching the given category.
     */
    public List<Expense> filterByCategory(String category) {
        List<Expense> filtered = new ArrayList<>();
        for (Expense expense : expenses) {
            if (expense.getCategory().equalsIgnoreCase(category)) {
                filtered.add(expense);
            }
        }
        return filtered;
    }

    /**
     * Filters expenses by a given date range.
     *
     * @param startDate The start date of the range.
     * @param endDate   The end date of the range.
     * @return A list of expenses that fall within the given date range.
     */
    public List<Expense> filterByDateRange(Date startDate, Date endDate) {
        List<Expense> filtered = new ArrayList<>();
        for (Expense expense : expenses) {
            if (!expense.getDate().before(startDate) && !expense.getDate().after(endDate)) {
                filtered.add(expense);
            }
        }
        return filtered;
    }

    /**
     * Edits an existing expense.
     *
     * @param index       The index of the expense to edit.
     * @param newAmount   The new amount.
     * @param newCategory The new category.
     * @param newDesc     The new description.
     */
    public void editExpense(int index, double newAmount, String newCategory, String newDesc) {
        if (index >= 0 && index < expenses.size()) {
            Expense expense = expenses.get(index);
            expenses.set(index, new Expense(newAmount, newCategory, newDesc, expense.getDate()));
        } else {
            System.out.println("Invalid expense index.");
        }
    }

    /**
     * Deletes an expense at a given index.
     *
     * @param index The index of the expense to delete.
     */
    public void deleteExpense(int index) {
        if (index >= 0 && index < expenses.size()) {
            expenses.remove(index);
        } else {
            System.out.println("Invalid expense index.");
        }
    }
}
