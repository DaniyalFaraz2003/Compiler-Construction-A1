package machines;

import java.util.*;

public class NFA {
    private HashMap<Integer, ArrayList<TransitionNode>> transitions;
    private ArrayList<Integer> finalStates;
    private Integer initialState;

    public NFA() {
        this.transitions = new HashMap<Integer, ArrayList<TransitionNode>>();
        this.finalStates = new ArrayList<>();
    }

    public void addTransition(Integer currentState, String input, Integer nextState) {
        if (this.transitions.containsKey(currentState)) {
            this.transitions.get(currentState).add(new TransitionNode(input, nextState));
        } else {
            this.transitions.put(currentState, new ArrayList<TransitionNode>(Arrays.asList(new TransitionNode(input, nextState))));
        }
    }

    public void printTransitions() {
        for (Integer currentState : this.transitions.keySet()) {
            System.out.print("State " + currentState + ": ");
            for (TransitionNode transition : this.transitions.get(currentState)) {
                System.out.print(transition.getInput() + " -> " + transition.getNextState() + ", ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Initial State: " + this.getInitialState());
        System.out.println("Final States: " + this.getFinalStates());
        System.out.println("Total States: " + this.totalStates());
    }

    public int getInitialState() {
        return this.initialState;
    }

    public void setInitialState(Integer initialState) {
        this.initialState = initialState;
    }

    public void addFinalState(Integer finalState) {
        if (this.finalStates == null) {
            this.finalStates = new ArrayList<Integer>(Arrays.asList(finalState));
        } else {
            this.finalStates.add(finalState);
        }
    }

    public int totalStates() {
        return this.transitions.size();
    }

    public ArrayList<Integer> getFinalStates() {
        return this.finalStates;
    }
}
