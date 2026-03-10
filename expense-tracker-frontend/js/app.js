// ================================================
// Expense Tracker - JavaScript Application
// ================================================

// API Base URL
const API_URL = 'http://localhost:8080/api/expenses';

// Bootstrap Modal instances
let editModal;
let summaryModal;

// Initialize application
document.addEventListener('DOMContentLoaded', () => {
    // Set today's date as default
    document.getElementById('expenseDate').valueAsDate = new Date();
    
    // Initialize Bootstrap modals
    editModal = new bootstrap.Modal(document.getElementById('editModal'));
    summaryModal = new bootstrap.Modal(document.getElementById('summaryModal'));
    
    // Load all expenses on page load
    loadAllExpenses();
    
    // Setup form submission handler
    document.getElementById('expenseForm').addEventListener('submit', handleAddExpense);
    
    // Setup Enter key for search
    document.getElementById('searchTitle').addEventListener('keypress', (e) => {
        if (e.key === 'Enter') {
            searchExpenses();
        }
    });
});

// ==================== CRUD Operations ====================

/**
 * Load all expenses from API
 */
async function loadAllExpenses() {
    showLoading(true);
    try {
        const response = await fetch(API_URL);
        if (!response.ok) {
            throw new Error('Failed to fetch expenses');
        }
        const expenses = await response.json();
        displayExpenses(expenses);
    } catch (error) {
        console.error('Error loading expenses:', error);
        showNotification('Error loading expenses. Please check if the backend is running.', 'danger');
    } finally {
        showLoading(false);
    }
}

/**
 * Add new expense
 */
async function handleAddExpense(event) {
    event.preventDefault();
    
    const expenseData = {
        title: document.getElementById('title').value.trim(),
        category: document.getElementById('category').value,
        amount: parseFloat(document.getElementById('amount').value),
        expenseDate: document.getElementById('expenseDate').value,
        paymentMethod: document.getElementById('paymentMethod').value
    };
    
    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(expenseData)
        });
        
        if (!response.ok) {
            const error = await response.json();
            throw new Error(error.message || 'Failed to add expense');
        }
        
        const newExpense = await response.json();
        showNotification('Expense added successfully!', 'success');
        
        // Reset form and reload expenses
        document.getElementById('expenseForm').reset();
        document.getElementById('expenseDate').valueAsDate = new Date();
        loadAllExpenses();
        
    } catch (error) {
        console.error('Error adding expense:', error);
        showNotification(error.message || 'Failed to add expense', 'danger');
    }
}

/**
 * Delete expense
 */
async function deleteExpense(id) {
    if (!confirm('Are you sure you want to delete this expense?')) {
        return;
    }
    
    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'DELETE'
        });
        
        if (!response.ok) {
            throw new Error('Failed to delete expense');
        }
        
        showNotification('Expense deleted successfully!', 'success');
        loadAllExpenses();
        
    } catch (error) {
        console.error('Error deleting expense:', error);
        showNotification('Failed to delete expense', 'danger');
    }
}

/**
 * Show edit modal with expense data
 */
function editExpense(id) {
    fetch(`${API_URL}/${id}`)
        .then(response => response.json())
        .then(expense => {
            document.getElementById('editExpenseId').value = expense.expenseId;
            document.getElementById('editTitle').value = expense.title;
            document.getElementById('editCategory').value = expense.category;
            document.getElementById('editAmount').value = expense.amount;
            document.getElementById('editExpenseDate').value = expense.expenseDate;
            document.getElementById('editPaymentMethod').value = expense.paymentMethod;
            
            editModal.show();
        })
        .catch(error => {
            console.error('Error loading expense:', error);
            showNotification('Failed to load expense details', 'danger');
        });
}

/**
 * Save edited expense
 */
async function saveEdit() {
    const id = document.getElementById('editExpenseId').value;
    
    const expenseData = {
        title: document.getElementById('editTitle').value.trim(),
        category: document.getElementById('editCategory').value,
        amount: parseFloat(document.getElementById('editAmount').value),
        expenseDate: document.getElementById('editExpenseDate').value,
        paymentMethod: document.getElementById('editPaymentMethod').value
    };
    
    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(expenseData)
        });
        
        if (!response.ok) {
            throw new Error('Failed to update expense');
        }
        
        showNotification('Expense updated successfully!', 'success');
        editModal.hide();
        loadAllExpenses();
        
    } catch (error) {
        console.error('Error updating expense:', error);
        showNotification('Failed to update expense', 'danger');
    }
}

// ==================== Filter and Search ====================

/**
 * Filter expenses by category
 */
async function filterExpenses() {
    const category = document.getElementById('filterCategory').value;
    
    if (!category) {
        loadAllExpenses();
        return;
    }
    
    showLoading(true);
    try {
        const response = await fetch(`${API_URL}/filter/category/${category}`);
        if (!response.ok) {
            throw new Error('Failed to filter expenses');
        }
        const expenses = await response.json();
        displayExpenses(expenses);
    } catch (error) {
        console.error('Error filtering expenses:', error);
        showNotification('Failed to filter expenses', 'danger');
    } finally {
        showLoading(false);
    }
}

/**
 * Search expenses by title
 */
async function searchExpenses() {
    const searchTerm = document.getElementById('searchTitle').value.trim();
    
    if (!searchTerm) {
        showNotification('Please enter a search term', 'warning');
        return;
    }
    
    showLoading(true);
    try {
        const response = await fetch(`${API_URL}/search?q=${encodeURIComponent(searchTerm)}`);
        if (!response.ok) {
            throw new Error('Failed to search expenses');
        }
        const expenses = await response.json();
        displayExpenses(expenses);
    } catch (error) {
        console.error('Error searching expenses:', error);
        showNotification('Failed to search expenses', 'danger');
    } finally {
        showLoading(false);
    }
}

/**
 * Clear search and filters
 */
function clearSearch() {
    document.getElementById('searchTitle').value = '';
    document.getElementById('filterCategory').value = '';
    loadAllExpenses();
}

// ==================== Summary ====================

/**
 * Show expense summary
 */
async function showSummary() {
    try {
        const response = await fetch(`${API_URL}/summary`);
        if (!response.ok) {
            throw new Error('Failed to fetch summary');
        }
        const summary = await response.json();
        displaySummary(summary);
        summaryModal.show();
    } catch (error) {
        console.error('Error loading summary:', error);
        showNotification('Failed to load summary', 'danger');
    }
}

/**
 * Display summary in modal
 */
function displaySummary(summary) {
    const summaryContent = document.getElementById('summaryContent');
    
    let html = `
        <div class="row mb-4">
            <div class="col-md-6">
                <div class="summary-stat">
                    <h3>$${summary.totalExpenses.toFixed(2)}</h3>
                    <p>Total Expenses</p>
                </div>
            </div>
            <div class="col-md-6">
                <div class="summary-stat">
                    <h3>${summary.numberOfExpenses}</h3>
                    <p>Total Transactions</p>
                </div>
            </div>
        </div>
        
        <h5 class="mb-3">Category-wise Breakdown</h5>
    `;
    
    const categoryBreakdown = summary.categoryBreakdown || {};
    const categories = ['Food', 'Travel', 'Shopping', 'Utilities', 'Others'];
    
    categories.forEach(category => {
        const amount = categoryBreakdown[category] || 0;
        if (amount > 0) {
            const percentage = ((amount / summary.totalExpenses) * 100).toFixed(1);
            html += `
                <div class="category-summary">
                    <div class="d-flex justify-content-between align-items-center">
                        <h5 class="mb-0">
                            <span class="category-badge category-${category}">${category}</span>
                        </h5>
                        <p class="mb-0">$${amount.toFixed(2)} <small class="text-muted">(${percentage}%)</small></p>
                    </div>
                </div>
            `;
        }
    });
    
    summaryContent.innerHTML = html;
}

// ==================== Display Functions ====================

/**
 * Display expenses in the UI
 */
function displayExpenses(expenses) {
    const expensesList = document.getElementById('expensesList');
    const noExpenses = document.getElementById('noExpenses');
    const expenseCount = document.getElementById('expenseCount');
    
    expenseCount.textContent = `${expenses.length} expense${expenses.length !== 1 ? 's' : ''}`;
    
    if (expenses.length === 0) {
        expensesList.innerHTML = '';
        noExpenses.classList.remove('d-none');
        return;
    }
    
    noExpenses.classList.add('d-none');
    
    expensesList.innerHTML = expenses.map(expense => createExpenseCard(expense)).join('');
}

/**
 * Create HTML for expense card
 */
function createExpenseCard(expense) {
    const isHighValue = expense.amount >= 500;
    const formattedDate = new Date(expense.expenseDate).toLocaleDateString('en-US', {
        year: 'numeric',
        month: 'short',
        day: 'numeric'
    });
    
    const createdDate = new Date(expense.createdDateTime).toLocaleDateString('en-US', {
        year: 'numeric',
        month: 'short',
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit'
    });
    
    return `
        <div class="col-md-6 col-lg-4">
            <div class="expense-card ${isHighValue ? 'high-value' : ''}">
                ${isHighValue ? `
                    <div class="mb-2">
                        <span class="high-value-badge">
                            <i class="fas fa-exclamation-triangle"></i> HIGH VALUE
                        </span>
                    </div>
                ` : ''}
                
                <h3 class="expense-title">${escapeHtml(expense.title)}</h3>
                <div class="expense-amount">$${expense.amount.toFixed(2)}</div>
                
                <div class="expense-details">
                    <div class="expense-detail-item">
                        <i class="fas fa-tag"></i>
                        <span class="category-badge category-${expense.category}">${expense.category}</span>
                    </div>
                    <div class="expense-detail-item">
                        <i class="fas fa-calendar-alt"></i>
                        <span>${formattedDate}</span>
                    </div>
                    <div class="expense-detail-item">
                        <i class="fas fa-credit-card"></i>
                        <span class="payment-badge payment-${expense.paymentMethod}">${expense.paymentMethod}</span>
                    </div>
                </div>
                
                <div class="text-muted small">
                    <i class="fas fa-clock"></i> Created: ${createdDate}
                </div>
                
                <div class="expense-actions">
                    <button class="btn btn-sm btn-warning" onclick="editExpense(${expense.expenseId})">
                        <i class="fas fa-edit"></i> Edit
                    </button>
                    <button class="btn btn-sm btn-danger" onclick="deleteExpense(${expense.expenseId})">
                        <i class="fas fa-trash"></i> Delete
                    </button>
                </div>
            </div>
        </div>
    `;
}

// ==================== Utility Functions ====================

/**
 * Show/hide loading spinner
 */
function showLoading(show) {
    const spinner = document.getElementById('loadingSpinner');
    if (show) {
        spinner.classList.remove('d-none');
    } else {
        spinner.classList.add('d-none');
    }
}

/**
 * Show notification
 */
function showNotification(message, type = 'info') {
    // Create toast container if it doesn't exist
    let toastContainer = document.querySelector('.toast-container');
    if (!toastContainer) {
        toastContainer = document.createElement('div');
        toastContainer.className = 'toast-container';
        document.body.appendChild(toastContainer);
    }
    
    // Create toast element
    const toastId = 'toast-' + Date.now();
    const bgClass = type === 'success' ? 'bg-success' : 
                    type === 'danger' ? 'bg-danger' : 
                    type === 'warning' ? 'bg-warning' : 'bg-info';
    
    const toastHtml = `
        <div id="${toastId}" class="toast align-items-center text-white ${bgClass} border-0" role="alert">
            <div class="d-flex">
                <div class="toast-body">
                    ${message}
                </div>
                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast"></button>
            </div>
        </div>
    `;
    
    toastContainer.insertAdjacentHTML('beforeend', toastHtml);
    
    const toastElement = document.getElementById(toastId);
    const toast = new bootstrap.Toast(toastElement, { delay: 3000 });
    toast.show();
    
    // Remove toast element after it's hidden
    toastElement.addEventListener('hidden.bs.toast', () => {
        toastElement.remove();
    });
}

/**
 * Escape HTML to prevent XSS
 */
function escapeHtml(text) {
    const map = {
        '&': '&amp;',
        '<': '&lt;',
        '>': '&gt;',
        '"': '&quot;',
        "'": '&#039;'
    };
    return text.replace(/[&<>"']/g, m => map[m]);
}
