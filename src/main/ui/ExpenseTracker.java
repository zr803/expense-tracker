package ui;


import model.ExpenseEntry;
import model.ExpenseEntryList;

import java.util.ArrayList;
import java.util.Scanner;

// Expense tracker application
// modeled command processing after AccountNotRobust application.
public class ExpenseTracker {
    private ExpenseEntryList entries;
    private Scanner input;

    // MODIFIES: this
    // EFFECTS: processes the user's input
    public ExpenseTracker() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            printMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nSee you next time!");
    }

    // MODIFIES: this
    // EFFECTS: initializes the expense entries.
    private void init() {
        entries = new ExpenseEntryList(new ArrayList<>());
        input = new Scanner(System.in);
    }

    // EFFECTS: displays the menu of options the user can select.
    private void printMenu() {
        System.out.println("You can request the following information");
        System.out.println("1 -> Create new entry");
        System.out.println("2 -> Entry list");
        System.out.println("3 -> Remove an existing entry");
        System.out.println("4 -> Get expense total");
        System.out.println("or press 'q' to quit");
    }

    // MODIFIES: this
    // EFFECTS: processes the user's commands.
    private void processCommand(String command) {
        if (command.equals("1")) {
            doNewEntry();
        } else if (command.equals("2")) {
            doViewEntries();
        } else if (command.equals("3")) {
            doRemoveEntry();
        } else if (command.equals("4")) {
            doExpenseTotal();
        } else {
            System.out.println("This selection is not valid");
        }
    }

    // MODIFIES: this
    // EFFECTS: allows the user to create a new expense entry.
    private void doNewEntry() {
        System.out.println("Enter date of spending:");
        String date = input.next();

        System.out.println("Enter a description of the expense:");
        String label = input.next();

        System.out.println("Enter the amount spent:");
        double amount = input.nextDouble();
        input.nextLine();

        ExpenseEntry entry = new ExpenseEntry();
        entry.setDate(date);
        entry.setLabel(label);
        entry.setAmount(amount);
        entries.addExpenseEntry(entry);
        System.out.println("\nSuccess! The entry has been added!\n");
    }

    // MODIFIES: this
    // EFFECTS: converts each entry to a string to display it to the user.
    private void doViewEntries() {
        int i;
        System.out.println("Expense Entries:");
        for (i = 0; i < entries.getExpenseHistoryLength(); i++) {
            System.out.println(" " + entries.getExpenseHistory().get(i).getDate() + ", "
                    + entries.getExpenseHistory().get(i).getLabel() + ", "
                    + entries.getExpenseHistory().get(i).getAmount() + "\n");
        }

    }

    // MODIFIES: this
    // EFFECTS: searches for an entry and removes it if found.
    private void doRemoveEntry() {
        System.out.println("Enter the name of the expense you would like to remove:");
        String labelName = input.next();
        int lengthInitial = entries.getExpenseHistoryLength();
        entries.removeExpenseEntry(labelName);
        if (entries.getExpenseHistoryLength() == lengthInitial) {
            System.out.println("Entry not found");
        } else {
            System.out.println("Success! The entry has been removed.\n");
        }
    }

    // EFFECTS: adds up a total of the expenses in the list of entries.
    private void doExpenseTotal() {
        System.out.println("Your total expenses are " + entries.totalExpenses() + "\n");
    }


}
