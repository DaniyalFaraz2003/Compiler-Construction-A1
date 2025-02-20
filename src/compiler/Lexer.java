package compiler;
import machines.RE;
import compiler.TokenAutomata;
import machines.DFA;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Lexer {
    ArrayList<String> tokenList;
    private final RE reRepo;

    public Lexer() {
        this.tokenList = new ArrayList<>();
        this.reRepo = new RE();
    }

    public String loadCodeFromFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            System.out.println("Reading file from: " + path.toAbsolutePath());
            return Files.readString(path);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
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

    public List<Token> tokenize(String sourceCode) {
        List<Token> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();
        boolean inSingleLineComment = false;
        boolean inMultiLineComment = false;
        boolean inCharacterLiteral = false;
        int currentLine = 1; // Track current line number

        for (int i = 0; i < sourceCode.length(); i++) {
            char c = sourceCode.charAt(i);
            char nextChar = (i + 1 < sourceCode.length()) ? sourceCode.charAt(i + 1) : '\0';

            // Track line numbers
            if (c == '\n') {
                currentLine++;
            }

            // Handle comments
            if (c == '/' && nextChar == '/' && !inMultiLineComment && !inCharacterLiteral) {
                inSingleLineComment = true;
                i++;
                continue;
            }
            if (c == '/' && nextChar == '=' && !inSingleLineComment && !inCharacterLiteral) {
                inMultiLineComment = true;
                i++;
                continue;
            }
            if (c == '=' && nextChar == '/' && inMultiLineComment) {
                inMultiLineComment = false;
                i++;
                continue;
            }
            if (inSingleLineComment && c == '\n') {
                inSingleLineComment = false;
                continue;
            }
            if (inMultiLineComment || inSingleLineComment) {
                continue;
            }

            // Handle character literals
            if (c == '\'' && !inCharacterLiteral) {
                inCharacterLiteral = true;
                currentToken.append(c);
                continue;
            }
            if (c == '\'' && inCharacterLiteral) {
                inCharacterLiteral = false;
                currentToken.append(c);
                addToken(tokens, currentToken, currentLine);
                continue;
            }
            if (inCharacterLiteral) {
                currentToken.append(c);
                continue;
            }

            // Handle whitespace
            if (Character.isWhitespace(c)) {
                addToken(tokens, currentToken, currentLine);
                continue;
            }

            // Handle single-character tokens
            if (isSingleToken(String.valueOf(c))) {
                addToken(tokens, currentToken, currentLine);
                tokens.add(new Token(String.valueOf(c), getTokenType(String.valueOf(c)), currentLine));
                continue;
            }

            // Build current token
            currentToken.append(c);
        }

        // Add any remaining token
        addToken(tokens, currentToken, currentLine);
        return tokens;
    }

    private void addToken(List<Token> tokens, StringBuilder currentToken, int lineNumber) {
        if (currentToken.length() > 0) {
            String tokenStr = currentToken.toString();
            tokens.add(new Token(tokenStr, getTokenType(tokenStr), lineNumber));
            currentToken.setLength(0);
        }
    }

    private TokenType getTokenType(String token) {
        // Check keywords
        TokenAutomata tokenAutomata = new TokenAutomata();
        DFA keywordOutputDfa = tokenAutomata.processRegex(reRepo.getRE("keyword", "output").toString());

        tokenAutomata = new TokenAutomata();
        DFA keywordInputDfa = tokenAutomata.processRegex(reRepo.getRE("keyword", "input").toString());
        if (tokenAutomata.testString(keywordOutputDfa, token) || tokenAutomata.testString(keywordInputDfa, token)) {
            return TokenType.KEYWORD;
        }

        // Check datatypes
        tokenAutomata = new TokenAutomata();
        DFA datatypeDfa = tokenAutomata.processRegex(reRepo.getRE("datatype", "primitive").toString());
        if (tokenAutomata.testString(datatypeDfa, token)) {
            return TokenType.DATATYPE;
        }

        // Check literals
        tokenAutomata = new TokenAutomata();
        DFA booleanDfa = tokenAutomata.processRegex(reRepo.getRE("literal", "boolean").toString());

        tokenAutomata = new TokenAutomata();
        DFA integerDfa = tokenAutomata.processRegex(reRepo.getRE("literal", "integer").toString());

        tokenAutomata = new TokenAutomata();
        DFA decimalDfa = tokenAutomata.processRegex(reRepo.getRE("literal", "decimal").toString());

        tokenAutomata = new TokenAutomata();
        DFA characterDfa = tokenAutomata.processRegex(reRepo.getRE("literal", "character").toString());

        if (tokenAutomata.testString(booleanDfa, token)) return TokenType.BOOLEAN_LITERAL;
        if (tokenAutomata.testString(integerDfa, token)) return TokenType.INTEGER_LITERAL;

        if (token.contains(".")) {
            String normalizedToken = token.replace('.', ','); // Replace period with comma

            if (tokenAutomata.testString(decimalDfa, normalizedToken)) {
                return TokenType.DECIMAL_LITERAL;
            }
        } else {
            if (tokenAutomata.testString(decimalDfa, token)) {
                return TokenType.DECIMAL_LITERAL;
            }
        }

        if (tokenAutomata.testString(characterDfa, token)) return TokenType.CHARACTER_LITERAL;

        // Check identifiers
        tokenAutomata = new TokenAutomata();
        DFA variableDfa = tokenAutomata.processRegex(reRepo.getRE("identifier", "variable").toString());
        if (tokenAutomata.testString(variableDfa, token)) {
            return TokenType.IDENTIFIER;
        }

        // Check operators
        if (isOperator(token)) {
            return TokenType.OPERATOR;
        }

        // Check symbols
        if (isSingleToken(token)) {
            return TokenType.SYMBOL;
        }

        return TokenType.INVALID;
    }

    public static class Token {
        private final String value;
        private final TokenType type;
        private final int lineNumber;

        public Token(String value, TokenType type, int lineNumber) {
            this.value = value;
            this.type = type;
            this.lineNumber = lineNumber;
        }

        public String getValue() {
            return value;
        }

        public TokenType getType() {
            return type;
        }

        public int getLineNumber() {
            return lineNumber;
        }

        @Override
        public String toString() {
            return String.format("Token{value='%s', type=%s, line=%d}", value, type, lineNumber);
        }
    }

    public enum TokenType {
        KEYWORD,
        IDENTIFIER,
        DATATYPE,
        INTEGER_LITERAL,
        DECIMAL_LITERAL,
        BOOLEAN_LITERAL,
        CHARACTER_LITERAL,
        OPERATOR,
        SYMBOL,
        INVALID
    }
}