import java.util.Date;

public class Expense {
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

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public String toCSV() {
        return String.format("%.2f,%s,%s,%d", amount, category, description, date.getTime());
    }

    public static Expense fromCSV(String line) {
        String[] parts = line.split(",");
        double amount = Double.parseDouble(parts[0]);
        String category = parts[1];
        String description = parts[2];
        Date date = new Date(Long.parseLong(parts[3]));
        return new Expense(amount, category, description, date);
    }

    @Override
    public String toString() {
        return String.format("$%.2f - %s (%s) on %s", amount, category, description, date.toString());
    }
}
