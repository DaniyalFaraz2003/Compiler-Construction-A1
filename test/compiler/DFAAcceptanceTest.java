package compiler;

import machines.DFA;
import machines.RE;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DFAAcceptanceTest {

    @Test
    @DisplayName("Test DFA for keyword: output")
    void testStringKeywordOutput() {
        TokenAutomata tk = new TokenAutomata();
        RE reRepo = new RE();
        StringBuilder regex = reRepo.getRE("keyword", "output");
        DFA dfa = tk.processRegex(regex.toString());

        ArrayList<String> testCases = new ArrayList<>();
        testCases.add("log");
        testCases.add("loglog");
        testCases.add("yeuwod");
        testCases.add("");
        testCases.add("late");
        testCases.add("lo");

        assertAll(
                () -> assertTrue(tk.testString(dfa, testCases.getFirst())),
                () -> assertFalse(tk.testString(dfa, testCases.get(1))),
                () -> assertFalse(tk.testString(dfa, testCases.get(2))),
                () -> assertFalse(tk.testString(dfa, testCases.get(3))),
                () -> assertFalse(tk.testString(dfa, testCases.get(4))),
                () -> assertFalse(tk.testString(dfa, testCases.get(5)))
        );
    }
}