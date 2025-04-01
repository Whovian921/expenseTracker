import java.io.*;
import java.util.*;

public class ExpenseManager {
    private static final String FILE_NAME = "expenses.csv";
    private List<Expense> expenses;

    public ExpenseManager() {
        this.expenses = new ArrayList<>();
        loadExpenses();
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void editExpense(int index, Expense updatedExpense) {
        if (index >= 0 && index < expenses.size()) {
            expenses.set(index, updatedExpense);
        }
    }

    public void deleteExpense(int index) {
        if (index >= 0 && index < expenses.size()) {
            expenses.remove(index);
        }
    }

    public void saveExpenses() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Expense expense : expenses) {
                writer.println(expense.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }

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

    public List<Expense> filterByCategory(String category) {
        List<Expense> filtered = new ArrayList<>();
        for (Expense expense : expenses) {
            if (expense.getCategory().equalsIgnoreCase(category)) {
                filtered.add(expense);
            }
        }
        return filtered;
    }

    public List<Expense> filterByDateRange(Date startDate, Date endDate) {
        List<Expense> filtered = new ArrayList<>();
        for (Expense expense : expenses) {
            if (!expense.getDate().before(startDate) && !expense.getDate().after(endDate)) {
                filtered.add(expense);
            }
        }
        return filtered;
    }
}
