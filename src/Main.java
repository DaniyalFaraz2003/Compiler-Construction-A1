import machines.RE;
import machines.NFARepository;
import compiler.TokenAutomata;
public class Main {
    public static void main(String[] args) {
        TokenAutomata tokenAutomata = new TokenAutomata();
        tokenAutomata.processRegex("(a|b)*abb");
    }
}// aabbabb