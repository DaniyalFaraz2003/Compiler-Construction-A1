import machines.RE;
import machines.NFARepository;
public class Main {
    public static void main(String[] args) {
        NFARepository nfas = new NFARepository();
        nfas.buildNFAs();
        nfas.printNfaStats("operator", "assignment");
    }
}