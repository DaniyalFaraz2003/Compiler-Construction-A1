package machines;
import machines.NFA;

public class DFA extends NFA {
    public DFA() {
        super();
    }

    public Boolean isAccepted(String input) {
        Integer currentState = this.getInitialState();
        for (int i = 0; i < input.length(); i++) {
            currentState = this.getNextState(currentState, String.valueOf(input.charAt(i)));
            if (currentState == null) {
                return false;
            }
        }
        return this.getFinalStates().contains(currentState);
    }

}
