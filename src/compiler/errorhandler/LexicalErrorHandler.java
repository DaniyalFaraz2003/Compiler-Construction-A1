package compiler.errorhandler;

import compiler.Lexer;

import java.util.List;
import java.util.ArrayList;

public class LexicalErrorHandler {
    private List<LexicalError> errors;

    public LexicalErrorHandler() {
        this.errors = new ArrayList<>();
    }

    public void checkForErrors(List<Lexer.Token> tokens) {
        for (Lexer.Token token : tokens) {
            // Check for invalid tokens
            if (token.getType() == Lexer.TokenType.INVALID) {
                addError(token.getLineNumber(), "Invalid token: " + token.getValue());
                continue;
            }

            // Check for invalid decimal format
            if (token.getType() == Lexer.TokenType.DECIMAL_LITERAL) {
                String value = token.getValue();
                int dotCount = value.length() - value.replace(".", "").length();
                if (dotCount > 1) {
                    addError(token.getLineNumber(), "Invalid decimal literal: " + value);
                }
            }

            // Check for invalid character literals
            if (token.getType() == Lexer.TokenType.CHARACTER_LITERAL) {
                String value = token.getValue();
                if (value.length() != 3 || value.charAt(0) != '\'' || value.charAt(2) != '\'') {
                    addError(token.getLineNumber(), "Invalid character literal: " + value);
                }
            }
        }
    }

    public void addError(int lineNumber, String message) {
        errors.add(new LexicalError(lineNumber, message));
    }

    public List<LexicalError> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public void printErrors() {
        if (!hasErrors()) {
            System.out.println("No lexical errors found.");
            return;
        }

        System.out.println("Lexical Errors:");
        for (LexicalError error : errors) {
            System.out.println(error);
        }
    }

    private static class LexicalError {
        private final int lineNumber;
        private final String message;

        public LexicalError(int lineNumber, String message) {
            this.lineNumber = lineNumber;
            this.message = message;
        }

        @Override
        public String toString() {
            return String.format("Line %d: %s", lineNumber, message);
        }
    }
}