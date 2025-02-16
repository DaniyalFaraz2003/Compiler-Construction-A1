package machines;
import java.util.*;

public class RE {
    private HashMap<String, HashMap<String, StringBuilder>> REs;

    public RE() {
        this.REs = new HashMap<String, HashMap<String, StringBuilder>>();
        this.REs.put("operator", new HashMap<>(Map.of(
            "arithmetic", new StringBuilder("[-+*/%^]"),
            "assignment", new StringBuilder("[=]")
        )));
        this.REs.put("keyword", new HashMap<>(Map.of(
            "output", new StringBuilder("log"),
            "input", new StringBuilder("scan")
        )));
        this.REs.put("identifier", new HashMap<>(Map.of(
            "constant", new StringBuilder("[ABCDEFGHIJKLMNOPQRSTUVWXYZ]+"),
            "variable", new StringBuilder("[abcdefghijklmnopqrstuvwxyz]+"),
            "function", new StringBuilder("[abcdefghijklmnopqrstuvwxyz]+")
        )));
        this.REs.put("datatype", new HashMap<>(Map.of(
            "primitive", new StringBuilder("Boolean | Integer | Decimal | Character")
        )));
        this.REs.put("literal", new HashMap<>(Map.of(
            "boolean", new StringBuilder("true | false"),
            "integer", new StringBuilder("[-]?[1-9][0-9]*"),
            "decimal", new StringBuilder("[-]?(([1-9][0-9]*) | [0])\\.[0-9]{1,5}"),
            "character", new StringBuilder("'[a-zA-Z0-9&$%#@!^*()_+-=<>?/|{}\\[\\]]'")
        )));
        this.REs.put("comment", new HashMap<>(Map.of(
            "singleline", new StringBuilder("//.*\\n"),
            "multiline", new StringBuilder("** [a-zA-Z0-9&$%#@!^()_+-=<>?/|{}\\[\\]\\n]* **")
        )));
    }

    public void printRE (String category, String subcategory) {
        System.out.println(this.REs.get(category).get(subcategory).toString());
    }
}
