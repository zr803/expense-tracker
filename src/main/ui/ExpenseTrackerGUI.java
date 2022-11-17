package ui;


import model.ExpenseEntry;
import model.ExpenseEntryList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// Expense Tracker application with graphical interface
public class ExpenseTrackerGUI extends JFrame implements ActionListener {
    public static final int WIDTH = 855;
    public static final int HEIGHT = 550;
    public static final int GAP = 5;
    private static final String JSON_STORE = "./data/expenses.json";
    private JTextField dateField;
    private JTextField labelField;
    private JTextField amountField;
    private JTextField labelRemove;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private ExpenseEntryList entries;
    private ExpenseTableGUI table;

    private JPanel controls;

    // MODIFIES: this
    // EFFECTS: initializes the JFrame window for expense tracker will operate, as well as
    //          table, expense entry list and json writer/reader.
    public ExpenseTrackerGUI() {
        super("Expense Tracker");
        setLayout(new BorderLayout(10, 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        JPanel viewPanel = new JPanel();
        viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.X_AXIS));
        entries = new ExpenseEntryList(new ArrayList<>());
        table = new ExpenseTableGUI();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        viewPanel.add(table);
        viewPanel.add(controlPanel());

        super.getContentPane().add(BorderLayout.PAGE_START, addMenu());
        super.getContentPane().add(BorderLayout.CENTER, viewPanel);
        pack();
        setSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: creates a menu for the user to select save or open file options.
    private JMenuBar addMenu() {
        JMenuBar menu = new JMenuBar();
        JMenu m1 = new JMenu("File");
        menu.add(m1);
        JMenuItem menuSave = new JMenuItem("Save");
        menuSave.addActionListener(this);
        menuSave.setActionCommand("save");
        JMenuItem menuOpen = new JMenuItem("Open");
        menuOpen.addActionListener(this);
        menuOpen.setActionCommand("open");
        menu.add(menuOpen);
        menu.add(menuSave);

        return menu;
    }


    // MODIFIES: this
    // EFFECTS: creates a JPanel containing the add and remove entry interfaces.
    private JPanel controlPanel() {
        controls = new JPanel();
        controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
        controls.add(addEntryGUI());
        controls.add(removeEntryGUI());
        controls.setSize(400, 200);
        controls.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        JPanel image = new JPanel();
        JLabel img = new JLabel(new ImageIcon("C:\\Users\\ziyaa\\OneDrive\\Pictures\\logo5.jpg"));
        img.setSize(new Dimension(15, 20));
        image.add(img);
        controls.add(image);
        return controls;

    }

    // MODIFIES: this
    // EFFECTS: constructs a JPanel with the interface to add an expense entry.
    private JPanel addEntryGUI() {
        JPanel entry = new JPanel();
        JPanel entryPanel = new JPanel();
        entryPanel.setOpaque(true);
        entryPanel.setBackground(Color.LIGHT_GRAY);
        entryPanel.setLayout(new BoxLayout(entryPanel, BoxLayout.Y_AXIS));
        entryPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1), "Create a new entry", TitledBorder.LEFT,
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
        entry.add(entryPanel);

        return entry;
    }

    // MODIFIES: this
    // EFFECTS: creates a JPanel where the user can enter the date of their expense.
    private JPanel dateEntry() {
        JPanel addDate = new JPanel();
        addDate.setLayout(new BoxLayout(addDate, BoxLayout.Y_AXIS));
        JLabel date = new JLabel("Enter date of spending:");
        addDate.add(date);
        dateField = new JTextField(10);
        addDate.add(dateField);

        return addDate;
    }

    // MODIFIES: this
    // EFFECTS: creates a JPanel where the user can enter a label for their expense.
    private JPanel labelEntry() {
        JPanel addLabel = new JPanel();
        addLabel.setLayout(new BoxLayout(addLabel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Enter description of spending:");
        addLabel.add(label);
        labelField = new JTextField();
        addLabel.add(labelField);

        return addLabel;
    }

    // MODIFIES: this
    // EFFECTS: creates a JPanel where the user can enter the amount they spent.
    private JPanel amountEntry() {
        JPanel addAmount = new JPanel();
        addAmount.setLayout(new BoxLayout(addAmount, BoxLayout.Y_AXIS));
        JLabel amount = new JLabel("Enter amount spent:");
        addAmount.add(amount);
        amountField = new JTextField();
        addAmount.add(amountField);

        return addAmount;
    }

    // MODIFIES: this
    // EFFECTS: constructs a JPanel with the interface to remove an expense entry.
    private JPanel removeEntryGUI() {
        JPanel removePanel = new JPanel();
        JPanel removeEntry = new JPanel();
        removeEntry.setOpaque(true);
        removeEntry.setBackground(Color.LIGHT_GRAY);
        removeEntry.setLayout(new BoxLayout(removeEntry, BoxLayout.Y_AXIS));
        removeEntry.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        removeEntry.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),
                "Remove an entry",
                TitledBorder.LEFT,
                TitledBorder.DEFAULT_POSITION));
        removeEntry.add(removeBlock());
        removeEntry.add(Box.createRigidArea(new Dimension(0, 15)));
        JButton removeButton = new JButton("Remove Entry");
        removeButton.addActionListener(this);
        removeButton.setActionCommand("remove entry");
        removeButton.setBackground(Color.WHITE);
        removeEntry.add(removeButton);
        removeEntry.add(Box.createRigidArea(new Dimension(0, 8)));
        removePanel.add(removeEntry);

        return removePanel;
    }


    // MODIFIES: this
    // EFFECTS: constructs a JPanel where the user can enter a name for the expense to be removed.
    private JPanel removeBlock() {
        JPanel removeLabel = new JPanel();
        removeLabel.setLayout(new BoxLayout(removeLabel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Enter entry label:");
        removeLabel.add(label);
        labelRemove = new JTextField();
        removeLabel.add(labelRemove);

        return removeLabel;
    }

    // EFFECTS: gets the source of an action from the interface and calls the appropriate
    //          method to perform the user story.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("add entry")) {
            addEntryAction();
        }
        if (e.getActionCommand().equals("remove entry")) {
            removeEntryAction();
        }
        if (e.getActionCommand().equals("save")) {
            saveAction();
        }
        if (e.getActionCommand().equals("open")) {
            openAction();
        }
    }

    // EFFECTS: gets text from all fields related to creating an expense entry, and adds
    //          the entry to the list and table.
    private void addEntryAction() {
        String date = dateField.getText();
        String label = labelField.getText();
        Double amount = Double.parseDouble(amountField.getText());
        ExpenseEntry expense = new ExpenseEntry(date, label, amount);
        entries.addExpenseEntry(expense);
        table.addRowToTable(expense);
        dateField.setText("");
        labelField.setText("");
        amountField.setText("");
    }

    // EFFECTS: gets the label of the expense to be removed, removes it from list
    //          and table.
    private void removeEntryAction() {
        String removal = labelRemove.getText();
        table.removeEntryFromTable(entries, removal);
        entries.removeExpenseEntry(removal);
        labelRemove.setText("");
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file, and displays it if found.
    private void openAction() {
        try {
            entries = jsonReader.read();
            table.openTable(entries);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the workroom to file.
    private void saveAction() {
        try {
            jsonWriter.open();
            jsonWriter.write(entries);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

}
