package compiler;

import java.util.*;
import machines.NFA;
import machines.TransitionNode;
import machines.DFA;



public class TokenAutomata {
    private ArrayList<Character> alphabet;
    private int states;
    private ArrayList<Integer> initStates;
    private ArrayList<Integer> finStates;

    public TokenAutomata() {
        this.alphabet = new ArrayList<>();
        this.initStates = new ArrayList<>();
        this.finStates = new ArrayList<>();
        this.states = 1;
    }

    private void buildAlphabet(String regex) {
        Set<Character> chars = new HashSet<>();
        for (char c : regex.toCharArray()) {
            if (isOperand(c)) {
                chars.add(c);
            }
        }
        alphabet = new ArrayList<>(chars);
        Collections.sort(alphabet);
    }

    private boolean isOperand(char c) {
        return Character.isLetterOrDigit(c) ||
                (c != '(' && c != ')' && c != '|' && c != '*' && c != '.' &&
                        c != '+' && c != '?' && c != '[' && c != ']' && c != '{' && c != '}');
    }

    private String preprocessRegex(String regex) {
        StringBuilder result = new StringBuilder("(");

        for (int i = 0; i < regex.length(); i++) {
            result.append(regex.charAt(i));
            if (i + 1 < regex.length()) {
                char curr = regex.charAt(i);
                char next = regex.charAt(i + 1);

                if ((isOperand(curr) && isOperand(next)) ||
                        (curr == ')' && next == '(') ||
                        (isOperand(curr) && next == '(') ||
                        (curr == ')' && isOperand(next)) ||
                        (curr == '*' && (next == '(' || isOperand(next)))) {
                    result.append('.');
                }
            }
        }
        result.append(')');
        return result.toString();
    }

    private String convertToPostfix(String regex) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        Map<Character, Integer> precedence = new HashMap<>();
        precedence.put('|', 1);
        precedence.put('.', 2);
        precedence.put('*', 3);

        for (char c : regex.toCharArray()) {
            if (isOperand(c)) {
                result.append(c);
            }
            else if (c == '(') {
                stack.push(c);
            }
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                if (!stack.isEmpty()) stack.pop(); // Remove '('
            }
            else {
                while (!stack.isEmpty() && stack.peek() != '(' &&
                        precedence.getOrDefault(stack.peek(), 0) >= precedence.getOrDefault(c, 0)) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    private NFA constructNFA(String postfix) {
        NFA nfa = new NFA();

        for (char x : postfix.toCharArray()) {
            if (isOperand(x)) {
                int start = states++;
                int end = states++;

                nfa.addTransition(start, String.valueOf(x), end);

                initStates.add(start);
                finStates.add(end);
            }
            else switch (x) {
                case '.': {
                    int m = finStates.get(finStates.size() - 2);
                    int n = initStates.get(initStates.size() - 1);
                    nfa.addTransition(m, "ε", n);

                    finStates.remove(finStates.size() - 2);
                    initStates.remove(initStates.size() - 1);
                    break;
                }
                case '|': {
                    int newInit = states++;
                    int newFin = states++;

                    int init1 = initStates.get(initStates.size() - 2);
                    int init2 = initStates.get(initStates.size() - 1);
                    int fin1 = finStates.get(finStates.size() - 2);
                    int fin2 = finStates.get(finStates.size() - 1);

                    nfa.addTransition(newInit, "ε", init1);
                    nfa.addTransition(newInit, "ε", init2);
                    nfa.addTransition(fin1, "ε", newFin);
                    nfa.addTransition(fin2, "ε", newFin);

                    initStates.remove(initStates.size() - 1);
                    initStates.set(initStates.size() - 1, newInit);
                    finStates.remove(finStates.size() - 1);
                    finStates.set(finStates.size() - 1, newFin);
                    break;
                }
                case '*': {
                    int newInit = states++;
                    int newFin = states++;

                    int oldInit = initStates.get(initStates.size() - 1);
                    int oldFin = finStates.get(finStates.size() - 1);

                    nfa.addTransition(newInit, "ε", oldInit);
                    nfa.addTransition(newInit, "ε", newFin);
                    nfa.addTransition(oldFin, "ε", oldInit);
                    nfa.addTransition(oldFin, "ε", newFin);

                    initStates.set(initStates.size() - 1, newInit);
                    finStates.set(finStates.size() - 1, newFin);
                    break;
                }
            }
        }

        nfa.setInitialState(initStates.get(0));
        for (Integer finalState : finStates) {
            nfa.addFinalState(finalState);
        }

        return nfa;
    }

    private void printNFATable(NFA nfa) {
        System.out.println("\nNFA TRANSITION TABLE\n");

        // Print header
        System.out.printf("%-10s", "State");
        for (char c : alphabet) {
            System.out.printf("%-10s", c);
        }
        System.out.printf("%-10s%-10s\n", "ε1", "ε2");

        for (int i = 0; i < 60; i++) System.out.print("-");
        System.out.println();

        // Print transitions
        for (int i = 1; i < states; i++) {
            System.out.printf("%-10d", i);

            for (char c : alphabet) {
                String transitions = "";
                if (nfa.getTransitions().containsKey(i)) {
                    for (TransitionNode tn : nfa.getTransitions().get(i)) {
                        if (tn.getInput().equals(String.valueOf(c))) {
                            transitions += tn.getNextState() + " ";
                        }
                    }
                }
                System.out.printf("%-10s", transitions.isEmpty() ? "--" : transitions.trim());
            }

            // Print epsilon transitions
            String eps1 = "", eps2 = "";
            if (nfa.getTransitions().containsKey(i)) {
                int epsCount = 0;
                for (TransitionNode tn : nfa.getTransitions().get(i)) {
                    if (tn.getInput().equals("ε")) {
                        if (epsCount == 0) eps1 = String.valueOf(tn.getNextState());
                        else if (epsCount == 1) eps2 = String.valueOf(tn.getNextState());
                        epsCount++;
                    }
                }
            }
            System.out.printf("%-10s%-10s\n", eps1.isEmpty() ? "--" : eps1, eps2.isEmpty() ? "--" : eps2);
        }

        System.out.println("\nInitial state: " + nfa.getInitialState());
        System.out.print("Final states: ");
        for (Integer fs : nfa.getFinalStates()) {
            System.out.print(fs + " ");
        }
        System.out.println();
    }



    // Add these methods to the RegexToAutomata class:

    private Set<Integer> epsilonClosure(NFA nfa, Integer state) {
        Set<Integer> closure = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        stack.push(state);
        closure.add(state);

        while (!stack.isEmpty()) {
            Integer current = stack.pop();

            if (nfa.getTransitions().containsKey(current)) {
                for (TransitionNode transition : nfa.getTransitions().get(current)) {
                    if (transition.getInput().equals("ε") && !closure.contains(transition.getNextState())) {
                        closure.add(transition.getNextState());
                        stack.push(transition.getNextState());
                    }
                }
            }
        }

        return closure;
    }

    private Set<Integer> epsilonClosure(NFA nfa, Set<Integer> states) {
        Set<Integer> closure = new HashSet<>();
        for (Integer state : states) {
            closure.addAll(epsilonClosure(nfa, state));
        }
        return closure;
    }

    private Set<Integer> move(NFA nfa, Set<Integer> states, char symbol) {
        Set<Integer> result = new HashSet<>();

        for (Integer state : states) {
            if (nfa.getTransitions().containsKey(state)) {
                for (TransitionNode transition : nfa.getTransitions().get(state)) {
                    if (transition.getInput().equals(String.valueOf(symbol))) {
                        result.add(transition.getNextState());
                    }
                }
            }
        }

        return result;
    }

    public DFA convertToDFA(NFA nfa) {
        DFA dfa = new DFA();
        Map<Set<Integer>, Integer> dfaStates = new HashMap<>();
        Queue<Set<Integer>> unprocessedStates = new LinkedList<>();
        int stateCounter = 1;

        // Get initial state's epsilon closure
        Set<Integer> initialClosure = epsilonClosure(nfa, nfa.getInitialState());
        dfaStates.put(initialClosure, stateCounter);
        dfa.setInitialState(stateCounter);
        unprocessedStates.add(initialClosure);

        // Check if initial state is final
        for (Integer state : initialClosure) {
            if (nfa.getFinalStates().contains(state)) {
                dfa.addFinalState(stateCounter);
                break;
            }
        }

        // Process all states
        while (!unprocessedStates.isEmpty()) {
            Set<Integer> currentStates = unprocessedStates.poll();
            int currentDFAState = dfaStates.get(currentStates);

            // For each input symbol
            for (char symbol : alphabet) {
                Set<Integer> nextStates = move(nfa, currentStates, symbol);
                nextStates = epsilonClosure(nfa, nextStates);

                if (!nextStates.isEmpty()) {
                    // Create new DFA state if needed
                    if (!dfaStates.containsKey(nextStates)) {
                        stateCounter++;
                        dfaStates.put(nextStates, stateCounter);
                        unprocessedStates.add(nextStates);

                        // Check if new state is final
                        for (Integer state : nextStates) {
                            if (nfa.getFinalStates().contains(state)) {
                                dfa.addFinalState(stateCounter);
                                break;
                            }
                        }
                    }

                    // Add transition
                    dfa.addTransition(currentDFAState, String.valueOf(symbol),
                            dfaStates.get(nextStates));
                }
            }
        }

        // Print DFA transition table
        printDFATable(dfa, dfaStates);

        return dfa;
    }

    private void printDFATable(DFA dfa, Map<Set<Integer>, Integer> dfaStates) {
        System.out.println("\nDFA TRANSITION TABLE\n");

        // Print header
        System.out.printf("%-10s", "State");
        for (char c : alphabet) {
            System.out.printf("%-10s", c);
        }
        System.out.printf("%-15s\n", "State Type");

        for (int i = 0; i < 75; i++) System.out.print("-");
        System.out.println();

        // Print transitions
        for (Map.Entry<Set<Integer>, Integer> state : dfaStates.entrySet()) {
            int currentState = state.getValue();

            // Print state number
            System.out.printf("%-10d", currentState);

            // Print transitions for each symbol
            for (char c : alphabet) {
                String transition = "--";
                if (dfa.getTransitions().containsKey(currentState)) {
                    for (TransitionNode tn : dfa.getTransitions().get(currentState)) {
                        if (tn.getInput().equals(String.valueOf(c))) {
                            transition = String.valueOf(tn.getNextState());
                            break;
                        }
                    }
                }
                System.out.printf("%-10s", transition);
            }

            // Print state type
            String stateType = "";
            boolean isStart = (currentState == dfa.getInitialState());
            boolean isAccept = dfa.getFinalStates().contains(currentState);

            if (isStart && isAccept) stateType = "Start/Accept";
            else if (isStart) stateType = "Start";
            else if (isAccept) stateType = "Accept";
            else stateType = "-";

            System.out.printf("%-15s\n", stateType);
        }

        // Print summary
        System.out.println("\nDFA States Summary:");
        System.out.println("Start State: " + dfa.getInitialState());
        System.out.print("Accept States: ");
        for (Integer state : dfa.getFinalStates()) {
            System.out.print(state + " ");
        }
        System.out.println("\n");
    }

    private boolean runDFA(DFA dfa, String input) {
        int currentState = dfa.getInitialState();

        for (char c : input.toCharArray()) {
            boolean transitionFound = false;

            // Check if there's a transition for the current character
            if (dfa.getTransitions().containsKey(currentState)) {
                for (TransitionNode tn : dfa.getTransitions().get(currentState)) {
                    if (tn.getInput().equals(String.valueOf(c))) {
                        currentState = tn.getNextState();
                        transitionFound = true;
                        break;
                    }
                }
            }

            // If no transition is found for the current character, the string is rejected
            if (!transitionFound) {
                return false;
            }
        }

        // After processing the entire string, check if the current state is a final state
        return dfa.getFinalStates().contains(currentState);
    }

    // Update the processRegex method to include DFA conversion:
    public DFA processRegex(String regex) {
        buildAlphabet(regex);

        System.out.println("\nAlphabet: ");
        for (char c : alphabet) {
            System.out.print(c + " ");
        }
        System.out.println();

        String preprocessed = preprocessRegex(regex);
        System.out.println("Preprocessed: " + preprocessed);

        String postfix = convertToPostfix(preprocessed);
        System.out.println("Postfix: " + postfix);

        NFA nfa = constructNFA(postfix);
        printNFATable(nfa);

        System.out.println("\nConverting NFA to DFA...");
        DFA dfa = convertToDFA(nfa);
        return dfa;

    }

    public boolean testString(DFA dfa, String input) {
        boolean accepted = runDFA(dfa, input);
        return accepted;
    }

}