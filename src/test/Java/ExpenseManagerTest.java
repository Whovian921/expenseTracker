package test.java;

import main.java.Expense;
import main.java.ExpenseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseManagerTest {

    private ExpenseManager manager;

    /**
     * Creates a fresh ExpenseManager before each test.
     */
    @BeforeEach
    void setUp() {
        manager = new ExpenseManager();
    }

    /**
     * Verifies that expenses can be added successfully.
     */
    @Test
    void testAddExpense() {
        Expense expense = new Expense(
                25.50,
                "Food",
                "Lunch",
                new Date()
        );

        manager.addExpense(expense);

        assertEquals(1, manager.getExpenses().size());
        assertSame(expense, manager.getExpenses().get(0));
    }

    /**
     * Verifies that total spending is calculated correctly.
     */
    @Test
    void testTotalSpending() {
        manager.addExpense(
                new Expense(25.50, "Food", "Lunch", new Date())
        );

        manager.addExpense(
                new Expense(40.00, "Gas", "Fuel", new Date())
        );

        assertEquals(65.50, manager.getTotalSpending(), 0.001);
    }

    /**
     * Verifies that average spending is calculated correctly.
     */
    @Test
    void testAverageExpense() {
        manager.addExpense(
                new Expense(20.00, "Food", "Breakfast", new Date())
        );

        manager.addExpense(
                new Expense(40.00, "Food", "Dinner", new Date())
        );

        assertEquals(30.00, manager.getAverageExpense(), 0.001);
    }

    /**
     * Verifies that an empty expense list returns zero totals.
     */
    @Test
    void testEmptyExpenseList() {
        assertTrue(manager.getExpenses().isEmpty());
        assertEquals(0.0, manager.getTotalSpending(), 0.001);
        assertEquals(0.0, manager.getAverageExpense(), 0.001);
    }

    /**
     * Verifies that monthly filtering only returns expenses
     * from the selected month and year.
     */
    @Test
    void testFilterByMonth() {
        Calendar calendar = Calendar.getInstance();

        calendar.clear();
        calendar.set(2026, Calendar.OCTOBER, 10);
        Date octoberDate = calendar.getTime();

        calendar.clear();
        calendar.set(2026, Calendar.SEPTEMBER, 15);
        Date septemberDate = calendar.getTime();

        manager.addExpense(
                new Expense(50.00, "Food", "Groceries", octoberDate)
        );

        manager.addExpense(
                new Expense(75.00, "Utilities", "Electricity", septemberDate)
        );

        assertEquals(1, manager.filterByMonth(10, 2026).size());
        assertEquals(
                "Food",
                manager.filterByMonth(10, 2026).get(0).getCategory()
        );
    }

    @Test
    void testEditExpense() {
        manager.addExpense(
                new Expense(25.00, "Food", "Lunch", new Date())
        );

        manager.editExpense(0, 40.00, "Transportation", "Gas");

        Expense updated = manager.getExpenses().get(0);

        assertEquals(40.00, updated.getAmount(), 0.001);
        assertEquals("Transportation", updated.getCategory());
        assertEquals("Gas", updated.getDescription());
    }

    @Test
    void testDeleteExpense() {
        manager.addExpense(
                new Expense(25.00, "Food", "Lunch", new Date())
        );

        manager.addExpense(
                new Expense(50.00, "Utilities", "Internet", new Date())
        );

        manager.deleteExpense(0);

        assertEquals(1, manager.getExpenses().size());
        assertEquals(
                "Utilities",
                manager.getExpenses().get(0).getCategory()
        );
    }

    @Test
    void testFilterByCategory() {
        manager.addExpense(
                new Expense(20.00, "Food", "Breakfast", new Date())
        );

        manager.addExpense(
                new Expense(50.00, "Transportation", "Gas", new Date())
        );

        manager.addExpense(
                new Expense(30.00, "food", "Lunch", new Date())
        );

        assertEquals(2, manager.filterByCategory("FOOD").size());
    }

    @Test
    void testSpendingByCategory() {
        manager.addExpense(
                new Expense(20.00, "Food", "Breakfast", new Date())
        );

        manager.addExpense(
                new Expense(30.00, "food", "Lunch", new Date())
        );

        manager.addExpense(
                new Expense(40.00, "Utilities", "Electricity", new Date())
        );

        Map<String, Double> totals = manager.getSpendingByCategory();

        assertEquals(2, totals.size());
        assertEquals(50.00, totals.get("Food"), 0.001);
        assertEquals(40.00, totals.get("Utilities"), 0.001);
    }

    @Test
    void testInvalidMonth() {
        assertThrows(
                IllegalArgumentException.class,
                () -> manager.filterByMonth(13, 2026)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> manager.filterByMonth(0, 2026)
        );
    }
}
