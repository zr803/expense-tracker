package model;


import org.json.JSONObject;

// represents an entry describing the details of spending, including the date, a description, and amount.
public class ExpenseEntry {
    private String date;            // represents the date the entry is for, in the form DDMMYYY.
    private String label;           // description of how the money was spent/earned.
    private double amount;          // amount spent


    // REQUIRES: amountSpent must be > 0; expenseLabel has a non-zero length.
    // EFFECTS: constructs an entry describing the expense.
    public ExpenseEntry(String dateSpent, String expenseLabel, double amountSpent) {
        this.date = dateSpent;
        this.amount = amountSpent;
        this.label = expenseLabel;
    }

    // EFFECTS: constructs and entry without any parameters (to be set by the user).
    public ExpenseEntry(){
    }

    public double getAmount() {
        return amount;
    }

    public String getLabel() {
        return label;
    }

    public String getDate() {
        return date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("date", date);
        json.put("label", label);
        json.put("amount", amount);
        return json;
    }


}

