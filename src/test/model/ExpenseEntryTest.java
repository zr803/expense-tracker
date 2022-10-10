package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ExpenseEntryTest {
    private ExpenseEntryList testExpenses1;
    private ExpenseEntry testE1;
    private ExpenseEntry testE2;


    @BeforeEach
    void runBefore(){
        testE1 = new ExpenseEntry( 10082022, "Groceries", 128);
        testE2 = new ExpenseEntry(21082022, "Hollister", 54);
        testExpenses1 = new ExpenseEntryList(new ArrayList<>());
    }

    @Test
    void testConstructorExpenseEntry() {
        assertEquals(10082022, testE1.getDate());
        assertEquals("Groceries", testE1.getLabel());
        assertEquals(128, testE1.getAmount());

    }

    @Test
    void testConstructorMonthly() {
        assertTrue(testExpenses1.getExpenseHistory().isEmpty());
    }

    @Test
    void testAddExpense() {
        testExpenses1.addExpenseEntry(testE1);
        assertFalse(testExpenses1.getExpenseHistory().isEmpty());
        assertEquals(1, testExpenses1.getExpenseHistoryLength());
        assertTrue(testExpenses1.getExpenseHistory().contains(testE1));
    }

    @Test
    void testAddMultipleExpenseEntries() {
        testExpenses1.addExpenseEntry(testE1);
        testExpenses1.addExpenseEntry(testE2);
        assertFalse(testExpenses1.getExpenseHistory().isEmpty());
        assertEquals(2, testExpenses1.getExpenseHistoryLength());
        assertTrue(testExpenses1.getExpenseHistory().contains(testE1));
        assertTrue(testExpenses1.getExpenseHistory().contains(testE2));
    }

    @Test
    void testExpenseTotal() {
        testExpenses1.addExpenseEntry(testE1);
        testExpenses1.addExpenseEntry(testE2);
        assertEquals(182, testExpenses1.totalExpenses());
    }


}