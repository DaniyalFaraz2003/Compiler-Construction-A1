package compiler;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, SymbolTableEntry> table;  // Map to store symbol entries

    // Constructor
    public SymbolTable() {
        this.table = new HashMap<>();
    }

    // Method to add a new symbol to the table
    public void addSymbol(String name, String type, String scope, int lineNumber) {
        SymbolTableEntry entry = new SymbolTableEntry(name, type, scope, lineNumber);
        table.put(name, entry);
    }

    // Method to lookup a symbol by name
    public SymbolTableEntry lookupSymbol(String name) {
        return table.get(name);
    }

    public boolean containsSymbol(String name) {
        return table.containsKey(name);
    }

    public void removeSymbol(String name) {
        table.remove(name);
    }

    public void printSymbolTable() {
        for (Map.Entry<String, SymbolTableEntry> entry : table.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    public int size() {
        return table.size();
    }
}