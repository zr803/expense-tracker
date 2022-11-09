package ui;

import model.ExpenseEntryList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class ExpenseTrackerGUI extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int GAP = 5;

    private ExpenseEntryList entries;
    private JPanel controls;

    // EFFECTS: initializes the JFrame window for expense tracker will operate.
    public ExpenseTrackerGUI() {
        super("Expense Tracker");
        entries = new ExpenseEntryList(new ArrayList<>());
        setLayout(new BorderLayout(10, 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        JPanel viewPanel = new JPanel();
        viewPanel.setLayout(new GridLayout(0, 4, 5, 5));
        viewPanel.add(expensePanel());
        viewPanel.add(newEntry());
        viewPanel.add(addMenu());

        super.getContentPane().add(BorderLayout.PAGE_START, addMenu());
        super.getContentPane().add(BorderLayout.LINE_START, expensePanel());
        super.getContentPane().add(BorderLayout.CENTER, controlPanel());

        setSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);


    }

    private JMenuBar addMenu() {
        JMenuBar menu = new JMenuBar();
        JMenu m1 = new JMenu("File");
        menu.add(m1);
        JMenuItem menuSave = new JMenuItem("Open");
        JMenuItem menuOpen = new JMenuItem("Save");
        menu.add(menuOpen);
        menu.add(menuSave);

        return menu;
    }

    private JScrollPane expensePanel() {
        JPanel entriesPanel = new JPanel();
        entriesPanel.setLayout(new GridBagLayout());
        entriesPanel.setOpaque(true);
        entriesPanel.setBackground(Color.LIGHT_GRAY);
        entriesPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));

        String[] columnLabels = {
                "Date",
                "Label",
                "Amount"
        };

        Object[][] data = {
                {"10/08/2022", "groceries", 103.23}
        };

        JTable table = new JTable(data, columnLabels);
        JScrollPane sp = new JScrollPane(table);

        return sp;
    }

    private JPanel controlPanel() {
        controls = new JPanel();
        controls.setLayout(new GridLayout(0, 1, 2, 2));

        controls.add(newEntry());
        controls.add(removeEntryPanel());
        controls.setSize(400, 200);
        controls.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        return controls;

    }

    private JPanel newEntry() {
        JPanel entryFields = new JPanel();
        entryFields.setOpaque(true);
        entryFields.setBackground(Color.LIGHT_GRAY);
        entryFields.setLayout(new BoxLayout(entryFields, BoxLayout.Y_AXIS));
        entryFields.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK,1),
                "Create a new entry",
                TitledBorder.LEFT,
                TitledBorder.DEFAULT_POSITION));

        JLabel date = new JLabel("Enter date of spending:");
        entryFields.add(date);
        JTextField dateEntry = new JTextField();
        entryFields.add(dateEntry);

        JLabel label = new JLabel("Enter description of spending:");
        entryFields.add(label);
        JTextField labelEntry = new JTextField();
        entryFields.add(labelEntry);

        JLabel amount = new JLabel("Enter amount spent:");
        entryFields.add(amount);
        JTextField amountEntry = new JTextField();
        entryFields.add(amountEntry);

        JButton addButton = new JButton("Add Entry");
        addButton.setBackground(Color.LIGHT_GRAY);
        entryFields.add(addButton);

        return entryFields;

    }

    private JPanel removeEntryPanel() {
        JPanel removeEntry = new JPanel();
        removeEntry.setOpaque(true);
        removeEntry.setBackground(Color.LIGHT_GRAY);
        removeEntry.setLayout(new BoxLayout(removeEntry, BoxLayout.Y_AXIS));
        removeEntry.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));

        removeEntry.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK,1),
                "Remove an entry",
                TitledBorder.LEFT,
                TitledBorder.DEFAULT_POSITION));

        JLabel remove = new JLabel("Enter entry label:");
        removeEntry.add(remove);
        JTextField removeLabel = new JTextField();
        removeEntry.add(removeLabel);

        return removeEntry;

    }



}
