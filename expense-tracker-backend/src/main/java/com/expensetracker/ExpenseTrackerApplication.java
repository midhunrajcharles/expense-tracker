package com.expensetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot Application
 */
@SpringBootApplication
public class ExpenseTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpenseTrackerApplication.class, args);
        System.out.println("\n╔═══════════════════════════════════════════╗");
        System.out.println("║  Expense Tracker API is running!         ║");
        System.out.println("║  URL: http://localhost:8080               ║");
        System.out.println("╚═══════════════════════════════════════════╝\n");
    }
}
