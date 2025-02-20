package compiler;

import compiler.errorhandler.LexicalErrorHandler;

import java.util.*;

public class SymbolTableGenerator {
    private final SymbolTable symbolTable;
    private String currentScope;

    public SymbolTableGenerator() {
        this.symbolTable = new SymbolTable();
        this.currentScope = "global";
    }

    public SymbolTable generateSymbolTable(List<Lexer.Token> tokens) {
        for (int i = 0; i < tokens.size(); i++) {
            Lexer.Token token = tokens.get(i);

            // Handle scope changes
            if (token.getValue().equals("{")) {
                currentScope = "local";
                continue;
            }
            if (token.getValue().equals("}")) {
                currentScope = "global";
                continue;
            }

            // Handle variable declarations
            if (token.getType() == Lexer.TokenType.DATATYPE) {
                if (i + 1 < tokens.size()) {
                    Lexer.Token nextToken = tokens.get(i + 1);
                    if (nextToken.getType() == Lexer.TokenType.IDENTIFIER) {
                        // Check if symbol already exists in current scope
                        String symbolName = nextToken.getValue();
                        if (!symbolTable.containsSymbol(symbolName)) {
                            symbolTable.addSymbol(
                                    symbolName,
                                    token.getValue(),
                                    currentScope,
                                    nextToken.getLineNumber()
                            );
                        } else {
                            System.err.printf("Line %d: Symbol '%s' already declared in this scope%n",
                                    nextToken.getLineNumber(), symbolName);
                        }
                    }
                }
            }

            // Handle function declarations
            if (token.getValue().equals("void") || token.getValue().equals("main")) {
                if (i + 1 < tokens.size()) {
                    Lexer.Token nextToken = tokens.get(i + 1);
                    if (nextToken.getType() == Lexer.TokenType.IDENTIFIER) {
                        symbolTable.addSymbol(
                                nextToken.getValue(),
                                "function",
                                "global",
                                nextToken.getLineNumber()
                        );
                    }
                }
            }
        }

        return symbolTable;
    }

    public void processTokens(List<Lexer.Token> tokens) {
        // Create error handler
        LexicalErrorHandler errorHandler = new LexicalErrorHandler();
        errorHandler.checkForErrors(tokens);

        // Generate symbol table
        SymbolTable symbolTable = generateSymbolTable(tokens);

        // Print results
        if (errorHandler.hasErrors()) {
            errorHandler.printErrors();
        } else {
            System.out.println("Lexical analysis completed successfully.");
            System.out.println("\nSymbol Table:");
            symbolTable.printSymbolTable();
        }
    }
}