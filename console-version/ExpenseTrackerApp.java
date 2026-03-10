import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * ExpenseTrackerApp - Console-based application
 * Demonstrates: Core Java, OOP, Collections, Methods, Loops, Conditionals
 */
public class ExpenseTrackerApp {
    private static ExpenseManager manager = new ExpenseManager();
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   EXPENSE TRACKER - CONSOLE VERSION   ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        // Add sample data for demonstration
        addSampleData();

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addExpenseMenu();
                    break;
                case 2:
                    manager.viewAllExpenses();
                    break;
                case 3:
                    updateExpenseMenu();
                    break;
                case 4:
                    deleteExpenseMenu();
                    break;
                case 5:
                    filterByCategoryMenu();
                    break;
                case 6:
                    searchByTitleMenu();
                    break;
                case 7:
                    manager.viewSummary();
                    break;
                case 8:
                    running = false;
                    System.out.println("\nThank you for using Expense Tracker! Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.\n");
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("═══════════════ MENU ═══════════════");
        System.out.println("1. Add New Expense");
        System.out.println("2. View All Expenses");
        System.out.println("3. Update Expense");
        System.out.println("4. Delete Expense");
        System.out.println("5. Filter by Category");
        System.out.println("6. Search by Title");
        System.out.println("7. View Summary");
        System.out.println("8. Exit");
        System.out.println("════════════════════════════════════\n");
    }

    private static void addExpenseMenu() {
        System.out.println("\n--- Add New Expense ---");
        
        String title = getStringInput("Enter title: ");
        
        System.out.println("Categories: " + String.join(", ", manager.getCategories()));
        String category = getStringInput("Enter category: ");
        
        double amount = getDoubleInput("Enter amount: ");
        
        LocalDate expenseDate = getDateInput("Enter expense date (yyyy-MM-dd): ");
        
        System.out.println("Payment Methods: " + String.join(", ", manager.getPaymentMethods()));
        String paymentMethod = getStringInput("Enter payment method: ");
        
        manager.addExpense(title, category, amount, expenseDate, paymentMethod);
        System.out.println();
    }

    private static void updateExpenseMenu() {
        System.out.println("\n--- Update Expense ---");
        int id = getIntInput("Enter expense ID to update: ");
        
        String title = getStringInput("Enter new title (or press Enter to skip): ");
        String category = getStringInput("Enter new category (or press Enter to skip): ");
        String amountStr = getStringInput("Enter new amount (or press Enter to skip): ");
        double amount = amountStr.isEmpty() ? -1 : Double.parseDouble(amountStr);
        String dateStr = getStringInput("Enter new date (yyyy-MM-dd) (or press Enter to skip): ");
        LocalDate date = dateStr.isEmpty() ? null : LocalDate.parse(dateStr, dateFormatter);
        String paymentMethod = getStringInput("Enter new payment method (or press Enter to skip): ");
        
        manager.updateExpense(id, title, category, amount, date, paymentMethod);
        System.out.println();
    }

    private static void deleteExpenseMenu() {
        System.out.println("\n--- Delete Expense ---");
        int id = getIntInput("Enter expense ID to delete: ");
        manager.deleteExpense(id);
        System.out.println();
    }

    private static void filterByCategoryMenu() {
        System.out.println("\n--- Filter by Category ---");
        System.out.println("Categories: " + String.join(", ", manager.getCategories()));
        String category = getStringInput("Enter category: ");
        manager.filterByCategory(category);
    }

    private static void searchByTitleMenu() {
        System.out.println("\n--- Search by Title ---");
        String searchTerm = getStringInput("Enter search term: ");
        manager.searchByTitle(searchTerm);
    }

    // Helper methods for input
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    private static LocalDate getDateInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String dateStr = scanner.nextLine().trim();
                return LocalDate.parse(dateStr, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format! Please use yyyy-MM-dd (e.g., 2026-03-10)");
            }
        }
    }

    // Add sample data for demonstration
    private static void addSampleData() {
        manager.addExpense("Grocery Shopping", "Food", 85.50, LocalDate.of(2026, 3, 8), "Card");
        manager.addExpense("Flight Tickets", "Travel", 650.00, LocalDate.of(2026, 3, 5), "Card");
        manager.addExpense("Restaurant Dinner", "Food", 42.30, LocalDate.of(2026, 3, 9), "UPI");
        manager.addExpense("New Laptop", "Shopping", 1200.00, LocalDate.of(2026, 3, 1), "Card");
        manager.addExpense("Electricity Bill", "Utilities", 75.00, LocalDate.of(2026, 3, 7), "Cash");
        System.out.println("✓ Sample data loaded successfully!\n");
    }
}
