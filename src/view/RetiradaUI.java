package view;

import dao.RetiradaDao;
import java.util.Date;
import java.util.InputMismatchException;
import model.Cliente;
import model.Livro;
import model.Retirada;
import repositorio.RepositorioClientes;
import repositorio.RepositorioLivros;
import repositorio.RepositorioRetirada;
import util.Console;
import view.menu.RetiradaMenu;

/**
 * Classe para Menu Retirada de Livros - Interface com o Usuário
 *
 * @author Francke
 * @since JDK 1.0
 */
public class RetiradaUI {

    private Retirada retirada;
    private RetiradaDao retiradaDao;
    
     /**
     * Construtor para inicializar Menu Retirada de Livros
     *
     * @param retirada
     * 
     */
    public RetiradaUI(Retirada retirada) {
        this.retirada = retirada;        
    }

    /**
     * Executa as opções do Menu Retirada de Livros.
     *
     */
    public void executar() {
        int opcao = 0;
        do {
            System.out.println(RetiradaMenu.getOpcoes());
            try {
                opcao = Console.scanInt("Digite sua opção: ");
            } catch (InputMismatchException e) {
                opcao = -1;
            }
            try {
                switch (opcao) {
                    case RetiradaMenu.OP_RETIRADA:
                        retirarLivro();
                        break;
                    case RetiradaMenu.OP_LISTAR:
                        mostrarRetirada();
                        break;
                    case RetiradaMenu.OP_VOLTAR:
                        System.out.println("Retornando ao menu principal..");
                        break;
                    default:
                        System.err.println("ERRO! Digite uma opção válida!!");

                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } while (opcao != RetiradaMenu.OP_VOLTAR);
    }

    /**
     * Cadastra nova retirada de Livro.
     */
    private void retirarLivro() throws Exception {
        try {
            //Busca o cliente pela matrícula
            String matricula = Console.scanString("Informe a matrícula do usuario: ");
            Cliente cliente = getCliente(matricula);
            //Busca o livro pela isbn
            String isbn = Console.scanString("ISBN do livro a ser retirado: ");
            Livro livro = getLivro(Integer.parseInt(isbn));
            //Valida se o livro a ser retirado está disponível
            
            retiradaDao.salvar(retirada);

            RetiradaLivro retirada = new RetiradaLivro();
            retirada.setId(lista.getUltimoId() + 1);
            retirada.setLivro(livro);
            retirada.setCliente(cliente);
            retirada.setRetirada(new Date(System.currentTimeMillis()));
            Boolean ok = lista.addRetirada(retirada);
            if (!ok) {
                throw new Exception("Erro! Livro não disponível para ser emprestado.");
            }
            System.out.println("Livro " + livro.getNome() + " emprestado para " + cliente.getNome() + ", devolução em: " + retirada.getEntregaFormatada());
        } catch (InputMismatchException e) {
            System.err.println("ERRO! O ISBN deve ser numérico!");
        }
    }

    /**
     * Retorna cliente através da matrícula.
     * 
     * @param matricula, insere matrícula de um cliente.
     * @return cliente selecionado.
     */
    private Cliente getCliente(String matricula) throws Exception {
        Cliente cliente = this.clientes.buscarCliente(matricula);
        if (cliente == null) {
            throw new Exception("Erro! Cliente não encontrado.");
        }
        System.out.println("Cliente selecionado: " + cliente.getNome());
        return cliente;
    }

    /**
     * Retorna livro através da ISBN.
     * 
     * @param isbn, insere isbn de um livro.
     * @return livro selecionado.
     */
    private Livro getLivro(int isbn) throws Exception {
        Livro livro = this.livros.buscarLivro(isbn);
        if (livro == null) {
            throw new Exception("Erro! Livro não encontrado.");
        }
        System.out.println("Livro selecionado: " + livro.getNome());
        return livro;
    }

    /**
     * Mostra retirada dos Clientes.
     *
     * imprime as retiradas dos clientes formatados em Strings
     */
    public void mostrarRetirada() {
        System.out.println("--------------------------------------\n");
        System.out.println(String.format("%-10s", "ID") + "\t"
                + String.format("%-20s", "|LIVRO") + "\t"
                + String.format("%-20s", "|DISP") + "\t"
                + String.format("%-20s", "|CLIENTE") + "\t"
                + String.format("%-20s", "|RETIRADA") + "\t"
                + String.format("%-20s", "|ENTREGA") + "\t"
                + String.format("%-20s", "|DEVOLVIDO")
        );
        for (RetiradaLivro retirada : lista.getListaDeRetiradas()) {
            String disponivel = retirada.getLivroDevolvido() ? "Sim" : "Não";
            System.out.println(String.format("%-10s", retirada.getId()) + "\t"
                    + String.format("%-20s", "|" + retirada.getLivro().getNome()) + "\t"
                    + String.format("%-20s", "|" + disponivel) + "\t"
                    + String.format("%-20s", "|" + retirada.getCliente().getNome()) + "\t"
                    + String.format("%-20s", "|" + retirada.getRetiradaFormatada()) + "\t"
                    + String.format("%-20s", "|" + retirada.getEntregaFormatada()) + "\t"
                    + String.format("%-20s", "|" + retirada.getDevolvidoFormatada())
            );
        }
    }
}
