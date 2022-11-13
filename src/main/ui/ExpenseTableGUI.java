package ui;

import model.ExpenseEntry;
import model.ExpenseEntryList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ExpenseTableGUI extends ScrollPane {
    private JTable table;
    private DefaultTableModel model;

    public ExpenseTableGUI() {
        super();
//        String[] columnLabels = {
//                "Date",
//                "Label",
//                "Amount"
//        };

//        Object[][] data = {
//                {"10/08/2022", "groceries", 103.23}
//        };

//        table = new JTable(data, columnLabels);
        model = new DefaultTableModel();
        table = new JTable(model);
        model.addColumn("Date");
        model.addColumn("Label");
        model.addColumn("Amount");
        JScrollPane sp = new JScrollPane(table);
        super.add(sp);
    }

    public void addRowToTable(ExpenseEntryList entries) {
        ArrayList<ExpenseEntry> history = entries.getExpenseHistory();
        Object[] rowData = new Object[3];
        for (ExpenseEntry e: history) {
            rowData[0] = e.getDate();
            rowData[1] = e.getLabel();
            rowData[2] = e.getAmount();
            model.addRow(rowData);
        }
        model.fireTableDataChanged();

    }

}
