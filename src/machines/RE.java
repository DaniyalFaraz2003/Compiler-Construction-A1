package machines;
import java.util.*;

public class RE {
    private HashMap<String, HashMap<String, StringBuilder>> REs;

    public RE() {
        this.REs = new HashMap<String, HashMap<String, StringBuilder>>();
        this.REs.put("operator", new HashMap<>(Map.of(
            "arithmetic", new StringBuilder("-|+|*|/|%|^"),
            "assignment", new StringBuilder("=")
        )));
        this.REs.put("keyword", new HashMap<>(Map.of(
            "output", new StringBuilder("log"),
            "input", new StringBuilder("scan")
        )));
        this.REs.put("identifier", new HashMap<>(Map.of(
            "constant", new StringBuilder("((A|B|C|D|E|F|G|H|I|J|K|L|M|N|O|P|Q|R|S|T|U|V|W|X|Y|Z)*)"),
            "variable", new StringBuilder("((a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z)*)"),
            "function", new StringBuilder("((a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z)*)")
        )));
        this.REs.put("datatype", new HashMap<>(Map.of(
            "primitive", new StringBuilder("Boolean|Integer|Decimal|Character")
        )));
        this.REs.put("literal", new HashMap<>(Map.of(
            "boolean", new StringBuilder("true|false"),
            "integer", new StringBuilder("(-(1|2|3|4|5|6|7|8|9)(0|1|2|3|4|5|6|7|8|9)*)|((1|2|3|4|5|6|7|8|9)(0|1|2|3|4|5|6|7|8|9)*) "),
            "decimal", new StringBuilder("((-(1|2|3|4|5|6|7|8|9)(0|1|2|3|4|5|6|7|8|9)*)|((1|2|3|4|5|6|7|8|9)(0|1|2|3|4|5|6|7|8|9)*)|(0)),(((0|1|2|3|4|5|6|7|8|9))|((0|1|2|3|4|5|6|7|8|9)(0|1|2|3|4|5|6|7|8|9))|((0|1|2|3|4|5|6|7|8|9)(0|1|2|3|4|5|6|7|8|9)(0|1|2|3|4|5|6|7|8|9))|((0|1|2|3|4|5|6|7|8|9)(0|1|2|3|4|5|6|7|8|9)(0|1|2|3|4|5|6|7|8|9)(0|1|2|3|4|5|6|7|8|9))|((0|1|2|3|4|5|6|7|8|9)(0|1|2|3|4|5|6|7|8|9)(0|1|2|3|4|5|6|7|8|9)(0|1|2|3|4|5|6|7|8|9)(0|1|2|3|4|5|6|7|8|9)))"),
            "character", new StringBuilder("'((a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z|A|B|C|D|E|F|G|H|I|J|K|L|M|N|O|P|Q|R|S|T|U|V|W|X|Y|Z|0|1|2|3|4|5|6|7|8|9)*)'")
        )));
        this.REs.put("comment", new HashMap<>(Map.of(
            "singleline", new StringBuilder("//((a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z|A|B|C|D|E|F|G|H|I|J|K|L|M|N|O|P|Q|R|S|T|U|V|W|X|Y|Z|0|1|2|3|4|5|6|7|8|9)*)"),
            "multiline", new StringBuilder("/=((a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z|A|B|C|D|E|F|G|H|I|J|K|L|M|N|O|P|Q|R|S|T|U|V|W|X|Y|Z|0|1|2|3|4|5|6|7|8|9)*)=/")
        )));
    }

    public StringBuilder getRE(String category, String subcategory) {
        return this.REs.get(category).get(subcategory);
    }

    public void printRE (String category, String subcategory) {
        System.out.println(this.REs.get(category).get(subcategory).toString());
    }
}

