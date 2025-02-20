import compiler.*;
import compiler.errorhandler.LexicalErrorHandler;
import machines.DFA;
import machines.RE;
import machines.NFARepository;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Sample code to test - you can also load from file using lexer.loadCodeFromFile()


        // Initialize components
        Lexer lexer = new Lexer();
        String sourceCode = lexer.loadCodeFromFile("src/code/main.txt");
        SymbolTableGenerator symbolTableGenerator = new SymbolTableGenerator();
        LexicalErrorHandler errorHandler = new LexicalErrorHandler();

        try {
            // Step 1: Perform lexical analysis
            System.out.println("Step 1: Performing Lexical Analysis...");
            List<Lexer.Token> tokens = lexer.tokenize(sourceCode);

            // Print all tokens
            System.out.println("\nTokens generated:");
            for (Lexer.Token token : tokens) {
                System.out.println(token);
            }

            // Step 2: Check for lexical errors
            System.out.println("\nStep 2: Checking for Lexical Errors...");
            errorHandler.checkForErrors(tokens);

            if (errorHandler.hasErrors()) {
                System.out.println("\nLexical Errors Found:");
                errorHandler.printErrors();
                return; // Stop compilation if there are lexical errors
            }

            // Step 3: Generate symbol table
            System.out.println("\nStep 3: Generating Symbol Table...");
            SymbolTable symbolTable = symbolTableGenerator.generateSymbolTable(tokens);

            // Print symbol table
            System.out.println("\nSymbol Table Contents:");
            symbolTable.printSymbolTable();

        } catch (Exception e) {
            System.err.println("Compilation Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Utility method to demonstrate file loading
    public static void compileFile(String filePath) {
        Lexer lexer = new Lexer();
        String sourceCode = lexer.loadCodeFromFile(filePath);

        if (sourceCode == null) {
            System.err.println("Failed to load source file: " + filePath);
            return;
        }

        SymbolTableGenerator symbolTableGenerator = new SymbolTableGenerator();
        LexicalErrorHandler errorHandler = new LexicalErrorHandler();

        // Perform compilation steps
        List<Lexer.Token> tokens = lexer.tokenize(sourceCode);
        errorHandler.checkForErrors(tokens);

        if (errorHandler.hasErrors()) {
            errorHandler.printErrors();
            return;
        }

        SymbolTable symbolTable = symbolTableGenerator.generateSymbolTable(tokens);
        symbolTable.printSymbolTable();
    }
}