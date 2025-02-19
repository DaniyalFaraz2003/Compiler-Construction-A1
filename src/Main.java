import machines.RE;
import machines.NFARepository;
import compiler.TokenAutomata;
public class Main {
    public static void main(String[] args) {
        RE re = new RE();
        TokenAutomata tokenAutomata = new TokenAutomata();
        StringBuilder regex = re.getRE("literal", "boolean");
        System.out.println("The regular expression is: " + regex.toString());
        tokenAutomata.processRegex(regex.toString());
    }
}