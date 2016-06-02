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

    public LivroUI() {
        livroNegocio = new LivroNegocio();
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
                        deletarCliente();
                        break;
                    case LivroMenu.OP_ATUALIZAR:
                        atualizarCliente();
                        break;
                    case LivroMenu.OP_LISTAR:
                        mostrarLivros();
                        break;
                    case LivroMenu.OP_CONSULTAR:
                        consultarClientePorNome();
                        break;
                    case LivroMenu.OP_SAIR:
                        System.out.println("Finalizando a aplicacao..");
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
            System.out.println("Cliente " + nome + " cadastrado com sucesso!");
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

    private void deletarCliente() {
        mostrarLivros();
        int matricula = Console.scanInt("\nMatrícula do cliente a ser deletado: ");
        try {
            Cliente cli = livroNegocio.procurarMatricula1(matricula);
            this.mostrarCliente(cli);
            if (UIUtil.getConfirmacao("Realmente deseja excluir esse cliente?")) {
                livroNegocio.deletar(cli);
                System.out.println("Clente deletado com sucesso!");
            } else {
                System.out.println("Operacao cancelada!");
            }
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }

    private void atualizarCliente() {
        mostrarLivros();
        int matricula = Console.scanInt("\nMatrícula do Cliente a ser alterado: ");
        try {
            Cliente cli = livroNegocio.procurarMatricula1(matricula);
            this.mostrarCliente(cli);

            System.out.println("Digite os dados do cliente que quer alterar [Vazio caso nao queira]");
            String nomeNovo = Console.scanString("Nome: ");
            String telefoneNovo = Console.scanString("Telefone: ");

            if (matricula != 0) {
                if (!nomeNovo.isEmpty()) {
                    cli.setNome(nomeNovo);
                }

                if (!telefoneNovo.isEmpty()) {
                    cli.setTelefone(telefoneNovo);
                }
            }

            livroNegocio.atualizar(cli);
            System.out.println("Cliente " + cli.getNome() + " atualizado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }

    private void consultarClientePorNome() {
        String nome = Console.scanString("Nome: ");
        try {
            List<Cliente> listaCli = livroNegocio.procurarNome(nome);
            this.mostrarClientes(listaCli);
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }

    }

    private void mostrarCliente(Cliente c) {
        System.out.println("-----------------------------");
        System.out.println("Cliente");
        System.out.println("Nome: " + c.getNome());
        System.out.println("Telefone: " + c.getTelefone());
        System.out.println("-----------------------------");
    }

    private void mostrarLivros(List<Cliente> listaClientes) {
        if (listaClientes.isEmpty()) {
            System.out.println("Clientes nao encontrados!");
        } else {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-20s", "|MATRÍCULA") +"\t"+ String.format("%-20s", "|NOME") + "\t"
                    + String.format("%-20s", "|TELEFONE"));
            for (Cliente cliente : listaClientes) {
                System.out.println(String.format("%-20s", "|" + cliente.getMatricula()) + "\t"+ String.format("%-20s", "|" + cliente.getNome()) + "\t"+ String.format("%-20s", "|" + cliente.getTelefone()));
            }
        }
    }    
}
