package model;

import java.util.ArrayList;

// represents a list of expenses.
public class ExpenseEntryList {
    private final ArrayList<ExpenseEntry> expenses;

    // EFFECTS: creates a list of expenses.
    public ExpenseEntryList(ArrayList<ExpenseEntry> expenses) {
        this.expenses = expenses;
    }

    public ArrayList<ExpenseEntry> getExpenseHistory() {
        return expenses;
    }

    public int getExpenseHistoryLength() {
        return expenses.size();
    }

    // MODIFIES: this
    // EFFECTS: adds an expense to the list of entries.
    public void addExpenseEntry(ExpenseEntry expenseEntry) {
        expenses.add(expenseEntry);
    }

    // REQUIRES: list of expenses is not empty.
    // EFFECTS: adds up the amounts of all expenses in the list.
    public double totalExpenses() {
        double sum = 0;
        int i;

        for (i = 0; i < expenses.size(); i++) {
            sum += expenses.get(i).getAmount();
        }
        return sum;

    }

    // REQUIRES: there is at least one entry in the expenses.
    // MODIFIES: this
    // EFFECTS: searches the list of expenses for an entry with the given name, and removes it if found.
    public void removeExpenseEntry(String expenseLabel) {
        int i;
        for (i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).getLabel().equals(expenseLabel)) {
                expenses.remove(i);
            }
        }
    }

}
