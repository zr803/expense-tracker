package ui;

import model.ExpenseEntryList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ExpenseTableGUI extends ScrollPane {
    private JTable table;

    public ExpenseTableGUI() {
        super();
        String[] columnLabels = {
                "Date",
                "Label",
                "Amount"
        };

        Object[][] data = {
                {"10/08/2022", "groceries", 103.23}
        };

        table = new JTable(data, columnLabels);
        JScrollPane sp = new JScrollPane(table);
        super.add(sp);
    }

    public void addRowToTable(ExpenseEntryList entries) {
        DefaultTableModel model = new DefaultTableModel();

    }

}
