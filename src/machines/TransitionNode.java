package machines;

public class TransitionNode {
    private String input;
    private Integer nextState;

    public TransitionNode(String input, Integer nextState) {
        this.input = input;
        this.nextState = nextState;
    }

    public String getInput() {
        return input;
    }

    public Integer getNextState() {
        return nextState;
    }

}
