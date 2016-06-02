package view;

import java.util.Date;
import java.util.InputMismatchException;
import model.Cliente;
import model.Livro;
import repositorio.RepositorioLivros;
import repositorio.RepositorioClientes;
import repositorio.RepositorioDevolucao;
import repositorio.RepositorioRetirada;
import util.Console;
import view.menu.MainMenu;

/**
 * Classe para Menu Principal - Interface com o Usuário
 *
 * @author Francke
 * @since JDK 1.0
 */
public class MainUI {

    private RepositorioClientes listaClientes;
    private RepositorioLivros listaLivros;
    private RepositorioRetirada listaRetiradas;
    private RepositorioDevolucao listaDevolucoes;
    
    /**
     * Construtor para Menu Principal
     */
    public MainUI() {
        Cliente c = new Cliente("1", "Saulo", "12323432");
        Cliente c2 = new Cliente("2", "Francke", "12323432");
        listaClientes = new RepositorioClientes();
        listaClientes.addClientes(c);
        listaClientes.addClientes(c2);
        
        Livro l = new Livro(1, "Harry Potter I", "JK", "asd", new Date());
        Livro l2 = new Livro(2, "Harry Potter II", "JK", "asd", new Date());
        Livro l3 = new Livro(3, "Harry Potter III", "JK", "asd", new Date());
        Livro l4 = new Livro(4, "Harry Potter IV", "JK", "asd", new Date());
        listaLivros = new RepositorioLivros();
        listaLivros.addLivros(l);
        listaLivros.addLivros(l2);
        listaLivros.addLivros(l3);
        listaLivros.addLivros(l4);
        
        
        listaRetiradas = new RepositorioRetirada();
        listaDevolucoes = new RepositorioDevolucao(listaRetiradas);
        //adicionar as listas que faltam (listaRetirada, listaEntrega, ListaRelatótios)
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
                    new ClienteUI(listaClientes).executar();
                    break;
                case MainMenu.OP_LIVROS:
                    new LivroUI(listaLivros).executar();
                    break;
                case MainMenu.OP_RETIRA:
                    new RetiradaUI(listaRetiradas, listaLivros, listaClientes).executar();
                    break;
                case MainMenu.OP_DEVOLUCAO:
                    new DevolucaoUI(listaRetiradas, listaDevolucoes).executar();
                    break;
                case MainMenu.OP_RELATORIOS:
                    new RelatoriosUI(listaLivros, listaRetiradas, listaClientes).executar();
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
