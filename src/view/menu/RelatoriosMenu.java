/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.menu;

/**
 *
 * @author Francke
 */
public class RelatoriosMenu {
    
    public static final int OP_RELATORIO_LIVROS_DISP = 1;
    public static final int OP_RELATORIO_LIVROS_RET = 2;
    public static final int OP_RELATORIO_CLIENTES_RET = 3;
    public static final int OP_RELATORIO_CLIENTES_ATR = 4;
    public static final int OP_RELATORIO_LIVROS_DATA = 5;
    public static final int OP_VOLTAR = 0;    
    
    /**
     * Retorna as opções do Menu Relatorio.
     *
     * @return opções para o menu exibir os relatorios
     */
    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1 - Livros disponíveis\n"
                + "2 - Livros mais retirados\n"
                + "3 - Clientes que mais retiraram livros\n"
                + "4 - Clientes que mais Atrasaram livros\n"
                + "0 - Voltar"
                + "\n--------------------------------------");
    }    
}
