package ui;

import model.ExpenseEntry;
import model.ExpenseEntryList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

// JTable for the expenses to be displayed.
public class ExpenseTableGUI extends ScrollPane {
    private JTable table;
    private DefaultTableModel model;

    // MODIFIES: this
    // EFFECTS: constructs a table and initializes the columns.
    public ExpenseTableGUI() {
        super();
        model = new DefaultTableModel();
        table = new JTable(model);
        model.addColumn("Date");
        model.addColumn("Label");
        model.addColumn("Amount");
        JScrollPane sp = new JScrollPane(table);
        super.add(sp);
    }

    // REQUIRES: entry list is not empty.
    // MODIFIES: this
    // EFFECTS: creates a row from the expense entry, and adds it to the table.
    public void addRowToTable(ExpenseEntry expense) {
        Object[] rowData = new Object[3];
        rowData[0] = expense.getDate();
        rowData[1] = expense.getLabel();
        rowData[2] = expense.getAmount();
        model.addRow(rowData);
        model.fireTableDataChanged();

    }

    // REQUIRES: entry list is not empty.
    // MODIFIES: this
    // EFFECTS: checks if a row with the label exists, and removes it if found.
    public void removeEntryFromTable(ExpenseEntryList entries, String label) {
        if (entries.expenseEntryIndex(label) == -1) {
            model.fireTableDataChanged();
        } else {
            model.removeRow(entries.expenseEntryIndex(label));
            model.fireTableDataChanged();
        }
    }

    // MODIFIES: this
    // EFFECTS: takes entries and creates a row in the table for each entry.
    public void openTable(ExpenseEntryList entries) {
        ArrayList<ExpenseEntry> history = entries.getExpenseHistory();
        Object[] rowData = new Object[3];
        for (ExpenseEntry e : history) {
            rowData[0] = e.getDate();
            rowData[1] = e.getLabel();
            rowData[2] = e.getAmount();
            model.addRow(rowData);
        }
        model.fireTableDataChanged();
    }

}
