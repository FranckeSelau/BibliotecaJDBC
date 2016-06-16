package view;

import dao.RetiradaDao;
import dao.impl_BD.RetiradaDaoBd;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import model.Devolucao;
import model.Retirada;
import negocio.ClienteNegocio;
import negocio.DevolucaoNegocio;
import negocio.LivroNegocio;
import negocio.NegocioException;
import util.Console;
import view.menu.DevolucaoMenu;

/**
 * Classe para Menu Devolução de Livros - Interface com o Usuário
 *
 * @author Francke
 * @since JDK 1.0
 */
public class DevolucaoUI {

    private Retirada retirada;
    private RetiradaDao retiradaDao;
    private DevolucaoNegocio devolucaoNegocio;

    /**
     * Construtor para inicializar Menu Retirada de Livros
     *
     * @param retirada
     *
     */
    public DevolucaoUI() {
        devolucaoNegocio = new DevolucaoNegocio();
        
    }

    public DevolucaoUI(Retirada retirada) {
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
            System.out.println(DevolucaoMenu.getOpcoes());
            try {
                opcao = Console.scanInt("Digite sua opção: ");
            } catch (InputMismatchException e) {
                opcao = -1;
            }
            try {
                switch (opcao) {
                    case DevolucaoMenu.OP_RETIRADA:
                        devolverLivro();
                        break;
                    case DevolucaoMenu.OP_LISTAR:
                        mostrarRetirada();
                        break;
                    case DevolucaoMenu.OP_VOLTAR:
                        System.out.println("Retornando ao menu principal..");
                        break;
                    default:
                        System.err.println("ERRO! Digite uma opção válida!!");

                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getMessage());
            }
        } while (opcao != DevolucaoMenu.OP_VOLTAR);
    }

    /**
     * Cadastra nova retirada de Livro.
     */
    private void devolverLivro() throws Exception {
        try {
            long DAY_IN_MS = 1000 * 60 * 60 * 24; // formatar data entrega
            int codigo = Console.scanInt("Informe o código da retirada do livro: ");
            retiradaDao = new RetiradaDaoBd();
            Retirada retirada = retiradaDao.procurarPorId(codigo);
            try {
                devolucaoNegocio.salvar(retirada);
                System.out.println("Devolucao cadastrado com sucesso!");
            } catch (Exception ex) {
                UIUtil.mostrarErro(ex.getMessage());
            }

            System.out.println("Livro " + retirada.getLivro().getNome() + " devolvido por " + retirada.getCliente().getNome());
        } catch (InputMismatchException e) {
            System.err.println("ERRO! O ISBN deve ser numérico!");
        }
    }

   
    public void mostrarRetirada() {
        List<Devolucao> lista = devolucaoNegocio.listar();
        this.mostraDevolucao(lista);
    }

    /**
     * Mostra retirada dos Clientes.
     *
     * imprime as retiradas dos clientes formatados em Strings
     */
    public void mostraDevolucao(List<Devolucao> lista) {
        System.out.println("--------------------------------------\n");
        System.out.println(String.format("%-10s", "ID") + "\t"
                + String.format("%-20s", "|LIVRO") + "\t"
                + String.format("%-20s", "|DISP") + "\t"
                + String.format("%-20s", "|CLIENTE") + "\t"
                + String.format("%-20s", "|RETIRADA") + "\t"
                + String.format("%-20s", "|ENTREGA") + "\t"
                + String.format("%-20s", "|DEVOLVIDO")
        );
        for (Devolucao dev : lista) {
            String disponivel = dev.getRetirada().getLivroDevolvido() ? "Sim" : "Não";
            System.out.println(String.format("%-10s", dev.getId()) + "\t"
                    + String.format("%-20s", "|" + dev.getRetirada().getLivro().getNome()) + "\t"
                    + String.format("%-20s", "|" + disponivel) + "\t"
                    + String.format("%-20s", "|" + dev.getRetirada().getCliente().getNome()) + "\t"
                    + String.format("%-20s", "|" + dev.getRetirada().getRetiradaFormatada()) + "\t"
                    + String.format("%-20s", "|" + dev.getRetirada().getEntregaFormatada()) + "\t"
                    + String.format("%-20s", "|" + dev.getRetirada().getDevolvidoFormatada())
            );
        }
    }
}
