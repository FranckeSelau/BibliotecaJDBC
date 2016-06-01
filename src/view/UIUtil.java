
package view;

import util.Console;

public class UIUtil {

    public static final String SIM = "s";
    public static final String NAO = "n";
    
    public static void mostrarErro(Object msgErro) {
        System.err.println(msgErro);
    }
    
    public static boolean getConfirmacao(Object msg) {
        String confirmacao = "NAO";
        do {
            confirmacao = Console.scanString(msg+"(s/n): ");
            if (confirmacao.equalsIgnoreCase(SIM)) {
                return true;
            }
            else if(confirmacao.equalsIgnoreCase(NAO)){
                return false;
            }
            else{
                System.out.println("Opcao invalida!");
            }                
        }while(confirmacao.equalsIgnoreCase(SIM) || 
                confirmacao.equalsIgnoreCase(NAO));
        return false;
    }     
}
