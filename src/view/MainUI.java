package view;

import java.util.Date;
import java.util.InputMismatchException;
import model.Cliente;
import model.Livro;

import util.Console;
import view.menu.MainMenu;

/**
 * Classe para Menu Principal - Interface com o Usuário
 *
 * @author Francke
 * @since JDK 1.0
 */
public class MainUI {

    private Cliente cliente;
    private Livro livro;

    /**
     * Construtor para Menu Principal
     */
    public MainUI() {

    }

    /**
     * Executa as opções do Menu Principal.
     *
     */
    public void executar() {
        int opcao = 0;
        do {
            System.out.println(MainMenu.getOpcoes());
            try {
                opcao = Console.scanInt("Digite sua opção: ");
            } catch (InputMismatchException e) {

                opcao = -1;
            }
            switch (opcao) {
                case MainMenu.OP_CLIENTES:
                    new ClienteUI(cliente).menu();
                    break;
                case MainMenu.OP_LIVROS:
                    new LivroUI(livro).menu();
                    break;
                case MainMenu.OP_RETIRA:
                    //  
                    break;
                case MainMenu.OP_DEVOLUCAO:
                    //                  
                    break;
                case MainMenu.OP_RELATORIOS:
                    //    
                    break;
                case MainMenu.OP_SAIR:
                    System.out.println("Aplicação finalizada!");
                    break;
                default:
                    System.err.println("ERRO! Opção inválida!");

            }
        } while (opcao != MainMenu.OP_SAIR);
    }
}
