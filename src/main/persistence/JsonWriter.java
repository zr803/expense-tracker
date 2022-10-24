package persistence;

import model.ExpenseEntryList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes JSON representation of workroom to file
// Modelled after JsonSerialization Demo project.
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file.
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot be
    //          opened for writing.
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file.
    public void write(ExpenseEntryList ee) {
        JSONObject json = ee.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of expense entries to file.
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file.
    private void saveToFile(String json) {
        writer.print(json);
    }
}
