package persistence;


import org.json.JSONObject;

// EFFECTS: allows classes to write JSONobjects.
public interface Writable {
    JSONObject toJson();
}
