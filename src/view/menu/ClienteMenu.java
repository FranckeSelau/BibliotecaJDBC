package view.menu;

public class ClienteMenu {
    public static final int OP_CADASTRAR = 1;
    public static final int OP_DELETAR = 2;
    public static final int OP_ATUALIZAR = 3;
    public static final int OP_LISTAR = 4;
    public static final int OP_CONSULTAR = 5;
    public static final int OP_SAIR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Cadastrar Clientes\n"
                + "2- Deletar Clientes\n"
                + "3- Atualizar dados do Clientes\n"
                + "4- Listar Clientes\n"
                + "5- Consultar Clientes por Nome\n"
                + "0- Sair"
                + "\n--------------------------------------");
    }    
}
