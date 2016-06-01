package view.menu;

public class LivroMenu {
    public static final int OP_CADASTRAR = 1;
    public static final int OP_DELETAR = 2;
    public static final int OP_ATUALIZAR = 3;
    public static final int OP_LISTAR = 4;
    public static final int OP_CONSULTAR = 5;
    public static final int OP_SAIR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Cadastrar Livros\n"
                + "2- Deletar Livros\n"
                + "3- Atualizar dados do Livros\n"
                + "4- Listar Livros\n"
                + "5- Consultar Livros por Nome\n"
                + "0- Sair"
                + "\n--------------------------------------");
    }    
}
