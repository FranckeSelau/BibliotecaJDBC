package Sistema;


import util.Console;
import view.ClienteUI;
import view.LivroUI;

/**
 *
 * @author Francke
 */
public class Sistema {

    /**
     * @param args executa o sistema
     */
    public static void main(String[] args) {
        char verifica = Console.scanChar("Livro ou cliente? (l/c): ");
        if (verifica == 'l') {
             new LivroUI().menu();
        } else {
            new ClienteUI().menu();
        }
        
    }
}
