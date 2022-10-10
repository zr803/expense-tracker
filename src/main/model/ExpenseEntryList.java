package model;

import java.util.ArrayList;

public class ExpenseEntryList {
    private final ArrayList<ExpenseEntry> expenses;

    public ExpenseEntryList(ArrayList<ExpenseEntry> expenses) {
        this.expenses = expenses;
    }

    public ArrayList<ExpenseEntry> getExpenseHistory() {
        return expenses;
    }

    public int getExpenseHistoryLength() {
        return expenses.size();
    }

    public void addExpenseEntry(ExpenseEntry expenseEntry) {
        expenses.add(expenseEntry);
    }

    public int totalExpenses() {
        int sum = 0;
        int i;

        for (i = 0; i < expenses.size(); i++) {
            sum += expenses.get(i).getAmount();
        }
        return sum;

    }

}
