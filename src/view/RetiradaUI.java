package view;

import dao.RetiradaDao;
import java.util.Date;
import java.util.InputMismatchException;
import model.Cliente;
import model.Livro;
import model.Retirada;
import negocio.ClienteNegocio;
import negocio.LivroNegocio;
import negocio.NegocioException;
import negocio.RetiradaNegocio;
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
    private ClienteNegocio clienteNegocio;
    private LivroNegocio livroNegocio;
    private RetiradaNegocio retiradaNegocio;
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
            long DAY_IN_MS = 1000 * 60 * 60 * 24; // formatar data entrega
            int matricula = Console.scanInt("Informe a matrícula do usuario: ");
            String isbn = Console.scanString("ISBN do livro a ser retirado: ");
        try {
            retiradaNegocio.salvar(new Retirada(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + (7 * DAY_IN_MS)), new Date(System.currentTimeMillis() + (7 * DAY_IN_MS)), clienteNegocio.procurarMatricula(matricula), livroNegocio.procurarPorIsbn(isbn), Boolean.FALSE));
            System.out.println("Retirada cadastrado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
            
            System.out.println("Livro " + livroNegocio.procurarPorIsbn(isbn).getNome() + " emprestado para " + clienteNegocio.procurarMatricula(matricula).getNome() + ", devolução em: " + retirada.getEntregaFormatada());
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
    private Cliente getCliente(int matricula) throws Exception {
        Cliente cliente = this.clienteNegocio.procurarMatricula(matricula);
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
    private Livro getLivro(String isbn) throws Exception {
        Livro livro = this.livroNegocio.procurarPorIsbn(isbn);
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
