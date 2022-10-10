package model;

public class ExpenseEntry {
    private String month;
    private final int date;         // represents the date the entry is for
    private String label;           // description of how the money was spent/earned.
    private final int amount;       // amount spent



    public ExpenseEntry(int dateSpent, String expenseLabel, int amountSpent) {
        this.date = dateSpent;
        this.amount = amountSpent;
        this.label = expenseLabel;
    }

    public int getAmount() {
        return amount;
    }

    public String getLabel() {
        return label;
    }

    public int getDate() {
        return date;
    }


}

