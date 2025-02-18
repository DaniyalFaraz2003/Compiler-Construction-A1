package machines;
import machines.NFA;
import java.util.*;

public class NFARepository {
    private HashMap<String, HashMap<String, NFA>> nfaRepository;

    public NFARepository() {
        this.nfaRepository = new HashMap<String, HashMap<String, NFA>>();

        this.nfaRepository.put("operator", new HashMap<>(Map.of(
            "arithmetic", new NFA(),
            "assignment", new NFA()
        )));

        this.nfaRepository.put("keyword", new HashMap<>(Map.of(
            "output", new NFA(),
            "input", new NFA()
        )));

        this.nfaRepository.put("identifier", new HashMap<>(Map.of(
            "constant", new NFA(),
            "variable", new NFA(),
            "function", new NFA()
        )));

        this.nfaRepository.put("datatype", new HashMap<>(Map.of(
            "primitive", new NFA()
        )));

        this.nfaRepository.put("literal", new HashMap<>(Map.of(
            "boolean", new NFA(),
            "integer", new NFA(),
            "decimal", new NFA(),
            "character", new NFA()
        )));

        this.nfaRepository.put("comment", new HashMap<>(Map.of(
            "singleline", new NFA(),
            "multiline", new NFA()
        )));
    }

    public void buildNFAs() {
        this.nfaRepository.get("operator").get("arithmetic").addTransition(0, "ε", 1);
        this.nfaRepository.get("operator").get("arithmetic").addTransition(0, "ε", 3);
        this.nfaRepository.get("operator").get("arithmetic").addTransition(0, "ε", 5);
        this.nfaRepository.get("operator").get("arithmetic").addTransition(0, "ε", 7);
        this.nfaRepository.get("operator").get("arithmetic").addTransition(0, "ε", 9);
        this.nfaRepository.get("operator").get("arithmetic").addTransition(0, "ε", 11);
        this.nfaRepository.get("operator").get("arithmetic").addTransition(1, "+", 2);
        this.nfaRepository.get("operator").get("arithmetic").addTransition(3, "-", 4);
        this.nfaRepository.get("operator").get("arithmetic").addTransition(5, "*", 6);
        this.nfaRepository.get("operator").get("arithmetic").addTransition(7, "%", 8);
        this.nfaRepository.get("operator").get("arithmetic").addTransition(9, "/", 10);
        this.nfaRepository.get("operator").get("arithmetic").addTransition(11, "^", 12);
        this.nfaRepository.get("operator").get("arithmetic").addTransition(2, "ε", 13);
        this.nfaRepository.get("operator").get("arithmetic").addTransition(4, "ε", 13);
        this.nfaRepository.get("operator").get("arithmetic").addTransition(6, "ε", 13);
        this.nfaRepository.get("operator").get("arithmetic").addTransition(8, "ε", 13);
        this.nfaRepository.get("operator").get("arithmetic").addTransition(10, "ε", 13);
        this.nfaRepository.get("operator").get("arithmetic").addTransition(12, "ε", 13);
        this.nfaRepository.get("operator").get("arithmetic").setInitialState(0);
        this.nfaRepository.get("operator").get("arithmetic").addFinalState(13);

        this.nfaRepository.get("operator").get("assignment").addTransition(0, "ε", 1);
        this.nfaRepository.get("operator").get("assignment").addTransition(1, "=", 2);
        this.nfaRepository.get("operator").get("assignment").addTransition(2, "ε", 3);
        this.nfaRepository.get("operator").get("assignment").setInitialState(0);
        this.nfaRepository.get("operator").get("assignment").addFinalState(3);

        this.nfaRepository.get("keyword").get("output").addTransition(0, "ε", 1);
        this.nfaRepository.get("keyword").get("output").addTransition(1, "log", 2);
        this.nfaRepository.get("keyword").get("output").addTransition(2, "ε", 3);
        this.nfaRepository.get("keyword").get("output").setInitialState(0);
        this.nfaRepository.get("keyword").get("output").addFinalState(3);

        this.nfaRepository.get("keyword").get("input").addTransition(0, "ε", 1);
        this.nfaRepository.get("keyword").get("input").addTransition(1, "scan", 2);
        this.nfaRepository.get("keyword").get("input").addTransition(2, "ε", 3);
        this.nfaRepository.get("keyword").get("input").setInitialState(0);
        this.nfaRepository.get("keyword").get("input").addFinalState(3);


    }

    public void printNfaStats (String category, String subcategory) {
        this.nfaRepository.get(category).get(subcategory).printTransitions();
    }
}
