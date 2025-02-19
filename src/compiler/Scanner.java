package compiler;
import java.lang.reflect.Array;
import java.util.*;

public class Scanner {
    ArrayList<String> tokenList;

    public Scanner() {
        this.tokenList = new ArrayList<>();
    }

    private boolean isSingleToken(String token) {
        return Objects.equals(token, "(") || Objects.equals(token, ")") ||
                Objects.equals(token, "{") || Objects.equals(token, "}") || Objects.equals(token, "[") ||
                Objects.equals(token, "]") || Objects.equals(token, ";") || Objects.equals(token, ",") ||
                Objects.equals(token, "+") || Objects.equals(token, "-") || Objects.equals(token, "*") ||
                Objects.equals(token, "/") || Objects.equals(token, "%") || Objects.equals(token, "^") ||
                Objects.equals(token, "=");
    }

    private boolean isOperator(String token) {
        return Objects.equals(token, "+") || Objects.equals(token, "-") || Objects.equals(token, "*") ||
                Objects.equals(token, "/") || Objects.equals(token, "%") || Objects.equals(token, "^")
                || Objects.equals(token, "=");
    }
}
