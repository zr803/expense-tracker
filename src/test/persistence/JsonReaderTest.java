package persistence;

import model.ExpenseEntry;
import model.ExpenseEntryList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ExpenseEntryList ee = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyExpenseEntryList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyExpenseEntryList.json");
        try {
            ExpenseEntryList ee = reader.read();
            assertEquals(0, ee.getExpenseHistoryLength());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralExpenseEntryList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralExpenseEntryList.json");
        try {
            ExpenseEntryList ee = reader.read();
            ArrayList<ExpenseEntry> expenses = ee.getExpenseHistory();
            assertEquals("10/08/2022", expenses.get(0).getDate());
            assertEquals("groceries", expenses.get(0).getLabel());
            assertEquals(53.25, expenses.get(0).getAmount());
            assertEquals("16/08/2022", expenses.get(1).getDate());
            assertEquals("skincare", expenses.get(1).getLabel());
            assertEquals(74.23, expenses.get(1).getAmount());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
