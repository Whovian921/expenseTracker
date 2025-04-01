import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class InputValidator {
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                return value;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();  // Consume invalid input
            }
        }
    }

    public static double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();  // Consume invalid input
            }
        }
    }

    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static Date getDateInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return dateFormat.parse(scanner.nextLine().trim());
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter in yyyy-MM-dd format.");
            }
        }
    }
}
