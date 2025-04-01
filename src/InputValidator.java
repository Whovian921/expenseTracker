import java.util.Scanner;

/**
 * A utility class that provides methods for validating user input.
 */
public class InputValidator {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user to enter a valid double value for expense amount.
     * Ensures that the input is a positive number.
     *
     * @return A valid double representing the amount.
     */
    public static double getValidAmount() {
        while (true) {
            try {
                System.out.print("Enter amount: ");
                double amount = Double.parseDouble(scanner.nextLine());

                // Ensure the amount is positive
                if (amount > 0) {
                    return amount;
                } else {
                    System.out.println("Amount must be greater than zero. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    /**
     * Prompts the user to enter a non-empty category name.
     * Ensures that the input is not blank.
     *
     * @return A valid non-empty string representing the category.
     */
    public static String getValidCategory() {
        while (true) {
            System.out.print("Enter category: ");
            String category = scanner.nextLine().trim();

            // Ensure the category is not empty
            if (!category.isEmpty()) {
                return category;
            } else {
                System.out.println("Category cannot be empty. Try again.");
            }
        }
    }

    /**
     * Prompts the user to enter a non-empty description.
     * Ensures that the input is not blank.
     *
     * @return A valid non-empty string representing the description.
     */
    public static String getValidDescription() {
        while (true) {
            System.out.print("Enter description: ");
            String description = scanner.nextLine().trim();

            // Ensure the description is not empty
            if (!description.isEmpty()) {
                return description;
            } else {
                System.out.println("Description cannot be empty. Try again.");
            }
        }
    }

    /**
     * Prompts the user to enter a valid integer within a specified range.
     * Ensures that the input is a number and falls within the given range.
     *
     * @param min The minimum valid value.
     * @param max The maximum valid value.
     * @return A valid integer within the specified range.
     */
    public static int getValidInteger(int min, int max) {
        while (true) {
            try {
                System.out.printf("Enter a number (%d - %d): ", min, max);
                int number = Integer.parseInt(scanner.nextLine());

                // Ensure the number is within the valid range
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println("Number out of range. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
