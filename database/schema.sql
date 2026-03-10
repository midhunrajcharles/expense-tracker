-- ================================================
-- Expense Tracker Database Schema
-- ================================================

-- Create database
CREATE DATABASE IF NOT EXISTS expense_tracker_db;
USE expense_tracker_db;

-- Create expenses table
CREATE TABLE IF NOT EXISTS expenses (
    expense_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL CHECK (category IN ('Food', 'Travel', 'Shopping', 'Utilities', 'Others')),
    amount DECIMAL(10, 2) NOT NULL CHECK (amount > 0),
    expense_date DATE NOT NULL,
    payment_method VARCHAR(20) NOT NULL CHECK (payment_method IN ('Cash', 'Card', 'UPI')),
    created_date_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_category (category),
    INDEX idx_expense_date (expense_date),
    INDEX idx_payment_method (payment_method)
);

-- Insert sample data
INSERT INTO expenses (title, category, amount, expense_date, payment_method, created_date_time) VALUES
('Grocery Shopping', 'Food', 85.50, '2026-03-08', 'Card', '2026-03-08 10:30:00'),
('Flight Tickets', 'Travel', 650.00, '2026-03-05', 'Card', '2026-03-05 14:20:00'),
('Restaurant Dinner', 'Food', 42.30, '2026-03-09', 'UPI', '2026-03-09 20:15:00'),
('New Laptop', 'Shopping', 1200.00, '2026-03-01', 'Card', '2026-03-01 11:45:00'),
('Electricity Bill', 'Utilities', 75.00, '2026-03-07', 'Cash', '2026-03-07 09:00:00'),
('Cab Fare', 'Travel', 25.50, '2026-03-10', 'UPI', '2026-03-10 08:30:00'),
('Coffee', 'Food', 5.75, '2026-03-10', 'Cash', '2026-03-10 15:00:00');

-- Display all expenses
SELECT * FROM expenses ORDER BY expense_date DESC;

-- Show summary
SELECT 
    COUNT(*) AS total_expenses,
    SUM(amount) AS total_amount,
    AVG(amount) AS average_amount
FROM expenses;

-- Category-wise breakdown
SELECT 
    category,
    COUNT(*) AS count,
    SUM(amount) AS total,
    AVG(amount) AS average
FROM expenses
GROUP BY category
ORDER BY total DESC;
