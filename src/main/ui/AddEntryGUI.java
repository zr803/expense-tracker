package ui;

import model.ExpenseEntry;
import model.ExpenseEntryList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddEntryGUI extends JPanel implements ActionListener {
    private JTextField dateField;
    private JTextField labelField;
    private JTextField amountField;
    private ExpenseEntryList entries;
    private ExpenseTableGUI table;


    public AddEntryGUI(String description) {
        super();
        JPanel entryPanel = new JPanel();
        entryPanel.setOpaque(true);
        entryPanel.setBackground(Color.LIGHT_GRAY);
        entryPanel.setLayout(new BoxLayout(entryPanel, BoxLayout.Y_AXIS));
        entryPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK,1), description, TitledBorder.LEFT,
                TitledBorder.DEFAULT_POSITION));

        entryPanel.add(dateEntry());
        entryPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        entryPanel.add(labelEntry());
        entryPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        entryPanel.add(amountEntry());
        entryPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        JButton addButton = new JButton("Add Entry");
        addButton.setBackground(Color.WHITE);
        addButton.addActionListener(this);
        addButton.setActionCommand("add entry");
        entryPanel.add(addButton);
        entryPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        entries = new ExpenseEntryList(new ArrayList<>());
        table = new ExpenseTableGUI();
        super.add(entryPanel);
    }

    public JPanel dateEntry() {
        JPanel addDate = new JPanel();
        addDate.setLayout(new BoxLayout(addDate, BoxLayout.Y_AXIS));
        JLabel date = new JLabel("Enter date of spending:");
        addDate.add(date);
        dateField = new JTextField();
        dateField.addActionListener(this);
        addDate.add(dateField);

        return addDate;
    }

    private JPanel labelEntry() {
        JPanel addLabel = new JPanel();
        addLabel.setLayout(new BoxLayout(addLabel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Enter description of spending:");
        addLabel.add(label);
        labelField = new JTextField();
        addLabel.add(labelField);

        return addLabel;
    }

    private JPanel amountEntry() {
        JPanel addAmount = new JPanel();
        addAmount.setLayout(new BoxLayout(addAmount, BoxLayout.Y_AXIS));
        JLabel amount = new JLabel("Enter amount spent:");
        addAmount.add(amount);
        amountField = new JTextField();
        addAmount.add(amountField);

        return addAmount;
    }


    public void actionPerformed(ActionEvent e) {
        String date = dateField.getText();
        String label = labelField.getText();
        Double amount = Double.parseDouble(amountField.getText());

        if (e.getActionCommand().equals("add entry")) {
            ExpenseEntry expense = new ExpenseEntry(date, label, amount);
            entries.addExpenseEntry(expense);
            table.addRowToTable(entries);

        }
    }

}
