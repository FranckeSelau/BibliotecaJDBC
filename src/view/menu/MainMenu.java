package view.menu;

/**
 * Classe para interação Menu Principal
 *
 * @author Francke
 * @since JDK 1.0
 */
public class MainMenu {

    public static final int OP_CLIENTES = 1;
    public static final int OP_LIVROS = 2;
    public static final int OP_RETIRA = 3;
    public static final int OP_DEVOLUCAO = 4;
    public static final int OP_RELATORIOS = 5;
    public static final int OP_SAIR = 0;

    /**
     * Retorna as opções do Menu Principal.
     *
     * @return opções para o menu principal
     */
    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1 - Menu Clientes\n"
                + "2 - Menu Livros\n"
                + "3 - Menu Retirada Livros\n"
                + "4 - Menu Devolução Livros\n"
                + "5 - Menu Relatórios\n"
                + "0 - Sair da Aplicação"
                + "\n--------------------------------------");
    }
}
