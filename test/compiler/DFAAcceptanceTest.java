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

    @Test
    @DisplayName("Test DFA for keyword: input")
    void testStringKeywordInput() {
        TokenAutomata tk = new TokenAutomata();
        RE reRepo = new RE();
        StringBuilder regex = reRepo.getRE("keyword", "input");
        DFA dfa = tk.processRegex(regex.toString());

        ArrayList<String> testCases = new ArrayList<>();
        testCases.add("scan");
        testCases.add("loglogscan");
        testCases.add("iejds");
        testCases.add("");
        testCases.add("scae");
        testCases.add("logscan");
        testCases.add("scanlog");

        assertAll(
                () -> assertTrue(tk.testString(dfa, testCases.getFirst())),
                () -> assertFalse(tk.testString(dfa, testCases.get(1))),
                () -> assertFalse(tk.testString(dfa, testCases.get(2))),
                () -> assertFalse(tk.testString(dfa, testCases.get(3))),
                () -> assertFalse(tk.testString(dfa, testCases.get(4))),
                () -> assertFalse(tk.testString(dfa, testCases.get(5))),
                () -> assertFalse(tk.testString(dfa, testCases.get(6)))
        );
    }

    @Test
    @DisplayName("Test DFA for identifier: constant")
    void testStringIdentifierConstant() {
        TokenAutomata tk = new TokenAutomata();
        RE reRepo = new RE();
        StringBuilder regex = reRepo.getRE("identifier", "constant");
        DFA dfa = tk.processRegex(regex.toString());

        ArrayList<String> testCases = new ArrayList<>();
        testCases.add("ALSDJFI");
        testCases.add("sldkjfo");
        testCases.add("sdlk343");
        testCases.add("");
        testCases.add("_sdakfj");
        testCases.add("KDIEJSD");
        testCases.add("SDFVcvx");
        testCases.add("c");
        testCases.add("C");
        testCases.add("V1");

        assertAll(
                () -> assertTrue(tk.testString(dfa, testCases.getFirst())),
                () -> assertFalse(tk.testString(dfa, testCases.get(1))),
                () -> assertFalse(tk.testString(dfa, testCases.get(2))),
                () -> assertFalse(tk.testString(dfa, testCases.get(3))),
                () -> assertFalse(tk.testString(dfa, testCases.get(4))),
                () -> assertTrue(tk.testString(dfa, testCases.get(5))),
                () -> assertFalse(tk.testString(dfa, testCases.get(6))),
                () -> assertFalse(tk.testString(dfa, testCases.get(7))),
                () -> assertTrue(tk.testString(dfa, testCases.get(8))),
                () -> assertFalse(tk.testString(dfa, testCases.get(9)))
        );
    }

    @Test
    @DisplayName("Test DFA for identifier: variable")
    void testStringIdentifierVariable() {
        TokenAutomata tk = new TokenAutomata();
        RE reRepo = new RE();
        StringBuilder regex = reRepo.getRE("identifier", "variable");
        DFA dfa = tk.processRegex(regex.toString());

        ArrayList<String> testCases = new ArrayList<>();
        testCases.add("ALSDJFI");
        testCases.add("sldkjfo");
        testCases.add("sdlk343");
        testCases.add("");
        testCases.add("_sdakfj");
        testCases.add("KDIEJSD");
        testCases.add("SDFVcvx");
        testCases.add("c");
        testCases.add("C");
        testCases.add("V1");

        assertAll(
                () -> assertFalse(tk.testString(dfa, testCases.getFirst())),
                () -> assertTrue(tk.testString(dfa, testCases.get(1))),
                () -> assertFalse(tk.testString(dfa, testCases.get(2))),
                () -> assertFalse(tk.testString(dfa, testCases.get(3))),
                () -> assertFalse(tk.testString(dfa, testCases.get(4))),
                () -> assertFalse(tk.testString(dfa, testCases.get(5))),
                () -> assertFalse(tk.testString(dfa, testCases.get(6))),
                () -> assertTrue(tk.testString(dfa, testCases.get(7))),
                () -> assertFalse(tk.testString(dfa, testCases.get(8))),
                () -> assertFalse(tk.testString(dfa, testCases.get(9)))
        );
    }

    @Test
    @DisplayName("Test DFA for datatype: primitive")
    void testStringDatatypePrimitive() {
        TokenAutomata tk = new TokenAutomata();
        RE reRepo = new RE();
        StringBuilder regex = reRepo.getRE("datatype", "primitive");
        DFA dfa = tk.processRegex(regex.toString());

        ArrayList<String> testCases = new ArrayList<>();
        testCases.add("Boolean");
        testCases.add("Integer");
        testCases.add("Decimal");
        testCases.add("Character");
        testCases.add("boolean");
        testCases.add("integer");
        testCases.add("decimal");
        testCases.add("character");
        testCases.add("");

        assertAll(
                () -> assertTrue(tk.testString(dfa, testCases.getFirst())),
                () -> assertTrue(tk.testString(dfa, testCases.get(1))),
                () -> assertTrue(tk.testString(dfa, testCases.get(2))),
                () -> assertTrue(tk.testString(dfa, testCases.get(3))),
                () -> assertFalse(tk.testString(dfa, testCases.get(4))),
                () -> assertFalse(tk.testString(dfa, testCases.get(5))),
                () -> assertFalse(tk.testString(dfa, testCases.get(6))),
                () -> assertFalse(tk.testString(dfa, testCases.get(7))),
                () -> assertFalse(tk.testString(dfa, testCases.get(8))
            )
        );
    }

    @Test
    @DisplayName("Test DFA for literal: boolean")
    void testStringLiteralBoolean() {
        TokenAutomata tk = new TokenAutomata();
        RE reRepo = new RE();
        StringBuilder regex = reRepo.getRE("literal", "boolean");
        DFA dfa = tk.processRegex(regex.toString());

        ArrayList<String> testCases = new ArrayList<>();
        testCases.add("true");
        testCases.add("false");
        testCases.add("TRUE");
        testCases.add("True");
        testCases.add("False");
        testCases.add("FALSE");
        testCases.add("anything");
        testCases.add("other");
        testCases.add("");

        assertAll(
                () -> assertTrue(tk.testString(dfa, testCases.getFirst())),
                () -> assertTrue(tk.testString(dfa, testCases.get(1))),
                () -> assertFalse(tk.testString(dfa, testCases.get(2))),
                () -> assertFalse(tk.testString(dfa, testCases.get(3))),
                () -> assertFalse(tk.testString(dfa, testCases.get(4))),
                () -> assertFalse(tk.testString(dfa, testCases.get(5))),
                () -> assertFalse(tk.testString(dfa, testCases.get(6))),
                () -> assertFalse(tk.testString(dfa, testCases.get(7))),
                () -> assertFalse(tk.testString(dfa, testCases.get(8))
                )
        );
    }

    @Test
    @DisplayName("Test DFA for literal: integer")
    void testStringLiteralInteger() {
        TokenAutomata tk = new TokenAutomata();
        RE reRepo = new RE();
        StringBuilder regex = reRepo.getRE("literal", "integer");
        DFA dfa = tk.processRegex(regex.toString());

        ArrayList<String> testCases = new ArrayList<>();
        testCases.add("-120");
        testCases.add("-0");
        testCases.add("032");
        testCases.add("1234.3");
        testCases.add("239");
        testCases.add("123m");
        testCases.add("");

        assertAll(
                () -> assertTrue(tk.testString(dfa, testCases.getFirst())),
                () -> assertFalse(tk.testString(dfa, testCases.get(1))),
                () -> assertFalse(tk.testString(dfa, testCases.get(2))),
                () -> assertFalse(tk.testString(dfa, testCases.get(3))),
                () -> assertTrue(tk.testString(dfa, testCases.get(4))),
                () -> assertFalse(tk.testString(dfa, testCases.get(5))),
                () -> assertFalse(tk.testString(dfa, testCases.get(6))
                )
        );
    }

    @Test
    @DisplayName("Test DFA for literal: decimal")
    void testStringLiteralDecimal() {
        TokenAutomata tk = new TokenAutomata();
        RE reRepo = new RE();
        StringBuilder regex = reRepo.getRE("literal", "decimal");
        DFA dfa = tk.processRegex(regex.toString());

        ArrayList<String> testCases = new ArrayList<>();
        testCases.add("-0,1232");
        testCases.add("0,323");
        testCases.add("3213,2312");
        testCases.add("-12,232");
        testCases.add("21,3433");
        testCases.add("q12,32");
        testCases.add("");
        testCases.add("-0,232");
        testCases.add("2,232322");
        testCases.add("-221,12345");
        testCases.add("-02,1234");

        assertAll(
                () -> assertTrue(tk.testString(dfa, testCases.getFirst())),
                () -> assertTrue(tk.testString(dfa, testCases.get(1))),
                () -> assertTrue(tk.testString(dfa, testCases.get(2))),
                () -> assertTrue(tk.testString(dfa, testCases.get(3))),
                () -> assertTrue(tk.testString(dfa, testCases.get(4))),
                () -> assertFalse(tk.testString(dfa, testCases.get(5))),
                () -> assertFalse(tk.testString(dfa, testCases.get(6))),
                () -> assertTrue(tk.testString(dfa, testCases.get(7))),
                () -> assertFalse(tk.testString(dfa, testCases.get(8))),
                () -> assertTrue(tk.testString(dfa, testCases.get(9))),
                () -> assertFalse(tk.testString(dfa, testCases.get(10)))



        );
    }

    @Test
    @DisplayName("Test DFA for literal: character")
    void testStringLiteralCharacter() {
        TokenAutomata tk = new TokenAutomata();
        RE reRepo = new RE();
        StringBuilder regex = reRepo.getRE("literal", "character");
        DFA dfa = tk.processRegex(regex.toString());

        ArrayList<String> testCases = new ArrayList<>();
        testCases.add("'3'");
        testCases.add("'+'");
        testCases.add("'t'");
        testCases.add("'f'");
        testCases.add("'4'");
        testCases.add("'F'");
        testCases.add("'S'");
        testCases.add("' '");
        testCases.add("'d");

        assertAll(
                () -> assertTrue(tk.testString(dfa, testCases.getFirst())),
                () -> assertFalse(tk.testString(dfa, testCases.get(1))),
                () -> assertTrue(tk.testString(dfa, testCases.get(2))),
                () -> assertTrue(tk.testString(dfa, testCases.get(3))),
                () -> assertTrue(tk.testString(dfa, testCases.get(4))),
                () -> assertTrue(tk.testString(dfa, testCases.get(5))),
                () -> assertTrue(tk.testString(dfa, testCases.get(6))),
                () -> assertFalse(tk.testString(dfa, testCases.get(7))),
                () -> assertFalse(tk.testString(dfa, testCases.get(8)))

        );
    }
}