import machines.DFA;
import machines.RE;
import machines.NFARepository;
import compiler.TokenAutomata;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        RE re = new RE();
        TokenAutomata tokenAutomata = new TokenAutomata();
        StringBuilder regex = re.getRE("literal", "boolean");
        System.out.println("The regular expression is: " + regex.toString());
        DFA dfa = tokenAutomata.processRegex(regex.toString());
        ArrayList<String> testCases_literalBoolean = new ArrayList<>();
        testCases_literalBoolean.add("true");
        testCases_literalBoolean.add("false");
        testCases_literalBoolean.add("truefalse");
        testCases_literalBoolean.add("falsefalse");
        testCases_literalBoolean.add("truefalsefalse");
        testCases_literalBoolean.add("aasdfasdf");
    }
}