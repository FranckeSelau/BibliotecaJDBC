package view.menu;

/**
 * Classe para interação Menu Retirada Livros
 *
 * @author Francke
 * @since JDK 1.0
 */
public class DevolucaoMenu {
    public static final int OP_RETIRADA = 1;
    public static final int OP_LISTAR = 2;
    public static final int OP_VOLTAR = 0;

    /**
     * Retorna as opções do Menu Retirada Livros.
     *
     * @return opções para o menu Retirada
     */
    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1 - Registrar Devolução\n"
                + "2 - Listar Devoluções\n"
                + "0 - Voltar"
                + "\n--------------------------------------");
    }
}
