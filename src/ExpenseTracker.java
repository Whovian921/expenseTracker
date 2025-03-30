import java.util.*;

class Expense {
    private double amount;
    private String category;
    private String description;
    private Date date;

    public Expense(double amount, String category, String description, Date date) {
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("$%.2f - %s (%s) on %s", amount, category, description, date.toString());
    }
}

public class ExpenseTracker {
    private static List<Expense> expenses = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add Expense\n2. View Expenses\n3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addExpense() {
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        Expense expense = new Expense(amount, category, description, new Date());
        expenses.add(expense);
        System.out.println("Expense added successfully!");
    }

    private static void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }
}
