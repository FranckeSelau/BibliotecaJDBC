package view;

import dao.RetiradaDao;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
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
    public RetiradaUI() {
        retiradaNegocio = new RetiradaNegocio();
        clienteNegocio = new ClienteNegocio();
        livroNegocio = new LivroNegocio();
    }

    public RetiradaUI(Retirada retirada) {
        this(); // chama o construtor acima "ClienteUI()" que está sem parametro
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
                e.printStackTrace();
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
            int isbn = Console.scanInt("ISBN do livro a ser retirado: ");
            try {
                retiradaNegocio.salvar(new Retirada(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + (7 * DAY_IN_MS)), new Date(System.currentTimeMillis() + (7 * DAY_IN_MS)), clienteNegocio.procurarMatricula(matricula), livroNegocio.procurarPorIsbn(Integer.toString(isbn)), Boolean.FALSE));
                System.out.println("Retirada cadastrado com sucesso!");
            } catch (NegocioException ex) {
                UIUtil.mostrarErro(ex.getMessage());
            }catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

            System.out.println("Livro " + livroNegocio.procurarPorIsbn(Integer.toString(isbn)).getNome() + " emprestado para " + clienteNegocio.procurarMatricula(matricula).getNome() + ", devolução em: " + retirada.getEntregaFormatada());
        } catch (InputMismatchException e) {
            System.err.println("ERRO! O ISBN deve ser numérico!");
        }catch (NegocioException ex) {
                UIUtil.mostrarErro(ex.getMessage());
            }
    }
    
    public void mostrarRetirada() {
        List<Retirada> listaRetiradas = retiradaNegocio.listar();
        this.mostrarRetirada(listaRetiradas);
    }

    /**
     * Mostra retirada dos Clientes.
     *
     * imprime as retiradas dos clientes formatados em Strings
     */
    public void mostrarRetirada(List<Retirada> listaRetiradas) {
        System.out.println("--------------------------------------\n");
        System.out.println(String.format("%-10s", "ID") + "\t"
                + String.format("%-40s", "|LIVRO") + "\t"
                + String.format("%-20s", "|DISP") + "\t"
                + String.format("%-20s", "|CLIENTE") + "\t"
                + String.format("%-20s", "|RETIRADA") + "\t"
                + String.format("%-20s", "|ENTREGA") + "\t"
                + String.format("%-20s", "|DEVOLVIDO")
        );
        for (Retirada retirada : listaRetiradas) {
            String disponivel = retirada.getLivroDevolvido() ? "Sim" : "Não";
            System.out.println(String.format("%-10s", retirada.getId()) + "\t"
                    + String.format("%-40s", "|" + retirada.getLivro().getNome()) + "\t"
                    + String.format("%-20s", "|" + disponivel) + "\t"
                    + String.format("%-20s", "|" + retirada.getCliente().getNome()) + "\t"
                    + String.format("%-20s", "|" + retirada.getRetiradaFormatada()) + "\t"
                    + String.format("%-20s", "|" + retirada.getEntregaFormatada()) + "\t"
                    + String.format("%-20s", "|" + retirada.getDevolvidoFormatada())
            );
        }
    }
}
