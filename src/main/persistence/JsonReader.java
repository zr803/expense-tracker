package persistence;

import model.ExpenseEntry;
import model.ExpenseEntryList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file.
    public JsonReader(String source) {
        this.source = source;
    }


    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file.
    public ExpenseEntryList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseExpenseEntryList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private ExpenseEntryList parseExpenseEntryList(JSONObject jsonObject) {
        ExpenseEntryList ee = new ExpenseEntryList(new ArrayList<>());
        addEntries(ee, jsonObject);
        return ee;
    }

    // MODIFIES: wr
    // EFFECTS: parses entries from JSON object and adds them to expense entry list.
    private void addEntries(ExpenseEntryList ee, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("expenses");
        for (Object json : jsonArray) {
            JSONObject nextEntry = (JSONObject) json;
            addEntry(ee, nextEntry);
        }
    }


    // MODIFIES: wr
    // EFFECTS: parses entry from JSON object and adds it to expense entry list.
    private void addEntry(ExpenseEntryList ee, JSONObject jsonObject) {
        String date = jsonObject.getString("date");
        String label = jsonObject.getString("label");
        Double amount = jsonObject.getDouble("amount");
        ExpenseEntry entry = new ExpenseEntry(date, label, amount);
        ee.addExpenseEntry(entry);

    }


}
