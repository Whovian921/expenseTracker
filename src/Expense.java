import java.util.Date;

/**
 * Represents an individual expense with an amount, category, description, and date.
 */
public class Expense {
    private double amount;
    private String category;
    private String description;
    private Date date;

    /**
     * Constructs a new Expense.
     *
     * @param amount     The monetary value of the expense.
     * @param category   The category of the expense (e.g., Food, Transportation).
     * @param description A brief description of the expense.
     * @param date       The date when the expense occurred.
     */
    public Expense(double amount, String category, String description, Date date) {
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    /**
     * Gets the amount of the expense.
     *
     * @return The expense amount.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Gets the category of the expense.
     *
     * @return The expense category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the description of the expense.
     *
     * @return The expense description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the date of the expense.
     *
     * @return The expense date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Converts the expense details to a CSV-formatted string.
     *
     * @return A string in CSV format: "amount,category,description,date".
     */
    public String toCSV() {
        return String.format("%.2f,%s,%s,%d", amount, category, description, date.getTime());
    }

    /**
     * Creates an Expense object from a CSV-formatted string.
     *
     * @param line A CSV-formatted string containing expense details.
     * @return A new Expense object.
     */
    public static Expense fromCSV(String line) {
        String[] parts = line.split(",");
        double amount = Double.parseDouble(parts[0]);
        String category = parts[1];
        String description = parts[2];
        Date date = new Date(Long.parseLong(parts[3]));
        return new Expense(amount, category, description, date);
    }

    /**
     * Returns a user-friendly string representation of the expense.
     *
     * @return A formatted string representing the expense.
     */
    @Override
    public String toString() {
        return String.format("$%.2f - %s (%s) on %s", amount, category, description, date.toString());
    }
}
