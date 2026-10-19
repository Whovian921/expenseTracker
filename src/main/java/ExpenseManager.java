package main.java;

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
     * Replaces the current expense list with the saved data.
     */
    public void loadExpenses() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        List<Expense> loadedExpenses = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

                if (line.isBlank()) {
                    continue;
                }

                loadedExpenses.add(Expense.fromCSV(line));
            }

            expenses.clear();
            expenses.addAll(loadedExpenses);

        } catch (IOException | IllegalArgumentException e) {
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
        return Collections.unmodifiableList(expenses);
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

    /**
     * Calculates the total amount spent across all expenses.
     *
     * @return The total amount of all recorded expenses.
     */
    public double getTotalSpending() {
        double total = 0.0;

        for (Expense expense : expenses) {
            total += expense.getAmount();
        }

        return total;
    }

    /**
     * Calculates the average amount of recorded expenses.
     *
     * @return The average expense amount, or 0.0 if empty.
     */
    public double getAverageExpense() {
        if (expenses.isEmpty()) {
            return 0.0;
        }

        return getTotalSpending() / expenses.size();
    }

    /**
     * Calculates total spending for each expense category.
     *
     * @return A map containing categories and their spending totals.
     */
    public Map<String, Double> getSpendingByCategory() {
        Map<String, Double> categoryTotals = new TreeMap<>(
                String.CASE_INSENSITIVE_ORDER
        );

        for (Expense expense : expenses) {
            String category = expense.getCategory();
            double amount = expense.getAmount();

            categoryTotals.merge(category, amount, Double::sum);
        }

        return categoryTotals;
    }

    /**
     * Retrieves all expenses recorded during a specific month and year.
     *
     * @param month The month (1-12).
     * @param year  The year to filter by.
     * @return A list of expenses from the selected month.
     */
    public List<Expense> filterByMonth(int month, int year) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException(
                    "Month must be between 1 and 12."
            );
        }

        List<Expense> filtered = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        for (Expense expense : expenses) {
            calendar.setTime(expense.getDate());

            int expenseMonth = calendar.get(Calendar.MONTH) + 1;
            int expenseYear = calendar.get(Calendar.YEAR);

            if (expenseMonth == month && expenseYear == year) {
                filtered.add(expense);
            }
        }

        return filtered;
    }
}
