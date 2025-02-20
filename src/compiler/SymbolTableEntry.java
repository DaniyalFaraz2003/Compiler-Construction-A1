package compiler;

public class SymbolTableEntry {
    private String name;        // Name of the symbol (e.g., variable name)
    private String type;        // Type of the symbol (e.g., "int", "float", "function")
    private String scope;       // Scope of the symbol (e.g., "global", "local")
    private int lineNumber;     // Line number where the symbol is declared
    private Object value;       // Value of the symbol (if applicable)

    // Constructor
    public SymbolTableEntry(String name, String type, String scope, int lineNumber) {
        this.name = name;
        this.type = type;
        this.scope = scope;
        this.lineNumber = lineNumber;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SymbolTableEntry{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", scope='" + scope + '\'' +
                ", lineNumber=" + lineNumber +
                ", value=" + value +
                '}';
    }
}