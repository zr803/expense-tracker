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
    private ExpenseEntry testE3;


    @BeforeEach
    void runBefore(){
        testE1 = new ExpenseEntry( "10/08/2022", "Groceries", 128);
        testE2 = new ExpenseEntry("21/08/2022", "Hollister", 54);
        testE3 = new ExpenseEntry();
        testExpenses1 = new ExpenseEntryList(new ArrayList<>());
    }

    @Test
    void testConstructorExpenseEntryWithParameters() {
        assertEquals("10/08/2022", testE1.getDate());
        assertEquals("Groceries", testE1.getLabel());
        assertEquals(128, testE1.getAmount());

    }

    @Test
    void testConstructorExpenseEntryNoParameters() {
        assertEquals(null, testE3.getDate());
        assertEquals(null, testE3.getLabel());
        assertEquals(0, testE3.getAmount());
    }

    @Test
    void testSetDate() {
        testE3.setDate("20/08/2022");
        assertEquals("20/08/2022", testE3.getDate());
    }

    @Test
    void testSetLabel() {
        testE3.setLabel("Skincare");
        assertEquals("Skincare", testE3.getLabel());
    }

    @Test
    void testSetAmount() {
        testE3.setAmount(20.25);
        assertEquals(20.25, testE3.getAmount());
    }

    @Test
    void testConstructorExpenseEntryList() {
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

    @Test
    void testRemoveExpense() {
        testExpenses1.addExpenseEntry(testE1);
        testExpenses1.addExpenseEntry(testE2);
        testExpenses1.removeExpenseEntry("Groceries");
        assertFalse(testExpenses1.getExpenseHistory().contains(testE1));
        assertEquals(1, testExpenses1.getExpenseHistoryLength());
    }

    @Test
    void testRemoveMultipleExpenses() {
        testExpenses1.addExpenseEntry(testE1);
        testExpenses1.addExpenseEntry(testE2);
        testExpenses1.removeExpenseEntry("Groceries");
        testExpenses1.removeExpenseEntry("Hollister");
        assertFalse(testExpenses1.getExpenseHistory().contains(testE1));
        assertFalse(testExpenses1.getExpenseHistory().contains(testE2));
        assertEquals(0, testExpenses1.getExpenseHistoryLength());
    }

    @Test
    void testRemoveExpenseNotFound() {
        testExpenses1.addExpenseEntry(testE1);
        assertEquals(1, testExpenses1.getExpenseHistoryLength());
        testExpenses1.removeExpenseEntry("Skincare");
        assertEquals(1, testExpenses1.getExpenseHistoryLength());
        assertTrue(testExpenses1.getExpenseHistory().contains(testE1));
    }

    @Test
    void testExpenseEntryIndexFound() {
        testExpenses1.addExpenseEntry(testE1);
        testExpenses1.addExpenseEntry(testE2);
        assertEquals(0, testExpenses1.expenseEntryIndex("Groceries"));
        assertEquals(1, testExpenses1.expenseEntryIndex("Hollister"));
    }

    @Test
    void testExpenseEntryIndexNotFound() {
        testExpenses1.addExpenseEntry(testE1);
        testExpenses1.addExpenseEntry(testE2);
        assertEquals(-1, testExpenses1.expenseEntryIndex("Skincare"));
    }

}