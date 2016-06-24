package view;

import java.text.ParseException;
import model.Livro;
import view.menu.LivroMenu;
import java.util.InputMismatchException;
import java.util.List;
import negocio.LivroNegocio;
import negocio.NegocioException;
import util.Console;
import util.DateUtil;

/**
 *
 * @author Francke
 */
public class LivroUI {

    private LivroNegocio livroNegocio;
    private Livro livro;

    public LivroUI() {
        livroNegocio = new LivroNegocio();
    }    
     /**
     * Construtor para inicializar menu cliente
     *
     * @param livro de clientes.
     */
    public LivroUI(Livro livro) {
        this();
        this.livro = livro;
    }   
    
    public void menu() {
        int opcao = -1;
        do {
            try {
                System.out.println(LivroMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção: ");
                switch (opcao) {
                    case LivroMenu.OP_CADASTRAR:
                        cadastrarLivro();
                        break;
                    case LivroMenu.OP_DELETAR:
                        deletarLivro();
                        break;
                    case LivroMenu.OP_ATUALIZAR:
                        atualizarLivro();
                        break;
                    case LivroMenu.OP_LISTAR:
                        mostrarLivros();
                        break;
                    case LivroMenu.OP_CONSULTAR:
                        consultarLivroPorNome();
                        break;
                    case LivroMenu.OP_SAIR:
                        System.out.println("Retornando ao menu principal..");
                        break;
                    default:
                        System.out.println("Opção inválida..");
                }
            } catch (InputMismatchException ex) {
                UIUtil.mostrarErro("Somente numeros sao permitidos!");
            }

        } while (opcao != LivroMenu.OP_SAIR);
    }   

    private void cadastrarLivro() {
        String isbn = Console.scanString("ISBN: ");
        String nome = Console.scanString("Nome: ");
        String autor = Console.scanString("Autor: ");
        String editora = Console.scanString("Editora: ");
        String dataString = Console.scanString("Ano: ");
        
        try {
            livroNegocio.salvar(new Livro(isbn, nome, autor, editora, DateUtil.stringToYear(dataString)));
            System.out.println("Livro " + nome + " cadastrado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }catch (ParseException ex) {
            UIUtil.mostrarErro("Formato de Data inválido!");
        }
    }

    public void mostrarLivros() {
        List<Livro> listaLivros = livroNegocio.listar();
        this.mostrarLivros(listaLivros);
    }

    private void deletarLivro() {
        mostrarLivros();
        String isbn = Console.scanString("\nISBN do livro a ser deletado: ");
        try {
            Livro liv = livroNegocio.procurarPorIsbn(isbn);
            this.mostrarLivro(liv);
            if (UIUtil.getConfirmacao("Realmente deseja excluir esse livro?")) {
                livroNegocio.deletar(liv);
                System.out.println("Livro deletado com sucesso!");
            } else {
                System.out.println("Operacao cancelada!");
            }
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }

    private void atualizarLivro() {
        mostrarLivros();
        String isbn = Console.scanString("\nISBN do livro a ser alterado: ");
        try {
            Livro liv = livroNegocio.procurarPorIsbn(isbn);
            this.mostrarLivro(liv);

            System.out.println("Digite os dados do livro que quer alterar [Vazio caso nao queira]");
            String nomeNovo = Console.scanString("Nome: ");
            String autorNovo = Console.scanString("Autor: ");
            String editoraNovo = Console.scanString("Editora: ");
            String dataStringNovo = Console.scanString("Ano: ");

            if (isbn != null) {
                if (!nomeNovo.isEmpty()) {
                    liv.setNome(nomeNovo);
                }
                if (!autorNovo.isEmpty()) {
                    liv.setAutor(autorNovo);
                }
                if (!editoraNovo.isEmpty()) {
                    liv.setEditora(editoraNovo);
                }
                if (!dataStringNovo.isEmpty()) {
                    liv.setAno(DateUtil.stringToYear(dataStringNovo));
                }
            }

            livroNegocio.atualizar(liv);
            System.out.println("Livro " + liv.getNome() + " atualizado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        } catch (ParseException ex) {
            UIUtil.mostrarErro("Formato de Data inválido!");
        }
    }

    private void consultarLivroPorNome() {
        String nome = Console.scanString("Nome: ");
        try {
            List<Livro> listaLiv = livroNegocio.procurarNome(nome);
            this.mostrarLivros(listaLiv);
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }

    private void mostrarLivro(Livro l) {
        System.out.println("-----------------------------");
        System.out.println("Livro");
        System.out.println("Nome: " + l.getNome());
        System.out.println("Autor: " + l.getAutor());
        System.out.println("Editora: " + l.getEditora());
        System.out.println("Ano: " + DateUtil.yearToString(l.getAno()));
        System.out.println("-----------------------------");
    }

    private void mostrarLivros(List<Livro> listaLivros) {
        if (listaLivros.isEmpty()) {
            System.out.println("Livros nao encontrados!");
        } else {
            System.out.println("--------------------------------------\n");
            System.out.println(String.format("%-10s", "ISBN") + "\t"
                    + String.format("%-40s", "|NOME") + "\t"
                    + String.format("%-20s", "|AUTOR") + "\t"
                    + String.format("%-20s", "|EDITORA") + "\t"
                    + String.format("%-5s", "|ANO"));
            for (Livro livro : listaLivros) {
                System.out.println(String.format("%-10s", livro.getIsbn()) + "\t"
                        + String.format("%-40s", "|" + livro.getNome()) + "\t"
                        + String.format("%-20s", "|" + livro.getAutor()) + "\t"
                        + String.format("%-20s", "|" + livro.getEditora()) + "\t"
                        + String.format("%-5s", "|" + DateUtil.yearToString(livro.getAno()))); // converte ano data em String
            }
        }
    }
}
