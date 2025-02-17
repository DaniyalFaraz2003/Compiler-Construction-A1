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

}
