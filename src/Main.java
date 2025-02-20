import compiler.Lexer;
import compiler.SymbolTable;
import compiler.SymbolTableEntry;
import machines.DFA;
import machines.RE;
import machines.NFARepository;
import compiler.TokenAutomata;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Path to the source code file
        String filePath = "src/code/main.txt"; // Replace with the actual file path

        // Create an instance of the Lexer
        Lexer lexer = new Lexer();

        // Load the source code from the file
        String sourceCode = lexer.loadCodeFromFile(filePath);
        if (sourceCode == null) {
            System.out.println("Failed to load source code from file.");
            return;
        }

        System.out.println("Source code loaded successfully:");
        System.out.println(sourceCode);

        // Tokenize the source code
        List<Lexer.Token> tokens = lexer.tokenize(sourceCode);

        // Print the tokens
        System.out.println("\nTokens:");
        for (Lexer.Token token : tokens) {
            System.out.println(token);
        }
    }
}