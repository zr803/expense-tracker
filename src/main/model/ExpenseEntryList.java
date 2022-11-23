package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// represents a list of expenses.
public class ExpenseEntryList implements Writable {
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
        EventLog.getInstance().logEvent(new Event("A new entry has been added to your expenses."));
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
        EventLog.getInstance().logEvent(new Event("An entry has been removed from your expenses."));
    }

    // REQUIRES: there is at least one entry in the expenses.
    // EFFECTS: searches the list for an entry with the given label, returns the index if found,
    //          or -1 if no such entry exists.
    public int expenseEntryIndex(String expenseLabel) {
        int i;
        for (i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).getLabel().equals(expenseLabel)) {
                return i;
            }
        }
        return -1;
    }


    @Override
    // EFFECTS: writes expenses as json object.
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("expenses", expensesToJson());
        return json;
    }

    // EFFECTS: converts each expense to json and creates array.
    private JSONArray expensesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (ExpenseEntry e : expenses) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }


}
