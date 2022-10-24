package persistence;

import model.ExpenseEntry;
import model.ExpenseEntryList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ExpenseEntryList ee = new ExpenseEntryList(new ArrayList<>());
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyExpenseEntryList() {
        try {
            ExpenseEntryList ee = new ExpenseEntryList(new ArrayList<>());
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyExpenseEntryList.json");
            writer.open();
            writer.write(ee);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyExpenseEntryList.json");
            ee = reader.read();
            assertEquals(0, ee.getExpenseHistoryLength());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralExpenseEntryList() {
        try {
            ExpenseEntryList ee = new ExpenseEntryList(new ArrayList<>());
            ee.addExpenseEntry(new ExpenseEntry("10/08/2022", "groceries", 53.25));
            ee.addExpenseEntry(new ExpenseEntry("16/08/2022", "skincare", 74.23));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralExpenseEntryList.json");
            writer.open();
            writer.write(ee);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralExpenseEntryList.json");
            ee = reader.read();
            ArrayList<ExpenseEntry> expenses = ee.getExpenseHistory();
            assertEquals(2, ee.getExpenseHistoryLength());
            assertEquals("10/08/2022", expenses.get(0).getDate());
            assertEquals("groceries", expenses.get(0).getLabel());
            assertEquals(53.25, expenses.get(0).getAmount());
            assertEquals("16/08/2022", expenses.get(1).getDate());
            assertEquals("skincare", expenses.get(1).getLabel());
            assertEquals(74.23, expenses.get(1).getAmount());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
