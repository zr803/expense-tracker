package ui;


import javax.swing.*;
import java.awt.*;

public class ExpenseTrackerGUI extends JFrame {
    public static final int WIDTH = 810;
    public static final int HEIGHT = 550;
    public static final int GAP = 5;

    private JPanel controls;

    // EFFECTS: initializes the JFrame window for expense tracker will operate.
    public ExpenseTrackerGUI() {
        super("Expense Tracker");
        setLayout(new BorderLayout(10, 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        JPanel viewPanel = new JPanel();
        viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.X_AXIS));
        ExpenseTableGUI table = new ExpenseTableGUI();
        viewPanel.add(table);
        viewPanel.add(controlPanel());

        super.getContentPane().add(BorderLayout.PAGE_START, addMenu());
        super.getContentPane().add(BorderLayout.CENTER, viewPanel);
        pack();

        setSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
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


    private JPanel controlPanel() {
        controls = new JPanel();
        controls.setLayout(new GridLayout(0, 1, 2, 2));

        AddEntryGUI entryGUI = new AddEntryGUI("Create a new entry");
        controls.add(entryGUI);
        RemoveEntryGUI removeGUI = new RemoveEntryGUI();
        controls.add(removeGUI);
        controls.setSize(400, 200);
        controls.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        return controls;

    }


}
