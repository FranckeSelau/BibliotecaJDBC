package view;

import model.Cliente;

import view.menu.ClienteMenu;
import java.util.InputMismatchException;
import java.util.List;
import negocio.ClienteNegocio;
import negocio.NegocioException;
import util.Console;

/**
 *
 * @author lhries
 */
public class ClienteUI {

    private ClienteNegocio clienteNegocio;
    private Cliente cliente;

    public ClienteUI() {
        clienteNegocio = new ClienteNegocio();
    }
    
     /**
     * Construtor para inicializar menu cliente
     *
     * @param cliente de clientes.
     */
    public ClienteUI(Cliente cliente) {
        this(); // chama o construtor acima "ClienteUI()" que está sem parametro
        this.cliente = cliente;
    }

    public void menu() {
        int opcao = -1;
        do {
            try {
                System.out.println(ClienteMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção: ");
                switch (opcao) {
                    case ClienteMenu.OP_CADASTRAR:
                        cadastrarCliente();
                        break;
                    case ClienteMenu.OP_DELETAR:
                        deletarCliente();
                        break;
                    case ClienteMenu.OP_ATUALIZAR:
                        atualizarCliente();
                        break;
                    case ClienteMenu.OP_LISTAR:
                        mostrarClientes();
                        break;
                    case ClienteMenu.OP_CONSULTAR:
                        consultarClientePorNome();
                        break;
                    case ClienteMenu.OP_SAIR:
                        System.out.println("Finalizando a aplicacao..");
                        break;
                    default:
                        System.out.println("Opção inválida..");
                }
            } catch (InputMismatchException ex) {
                UIUtil.mostrarErro("Somente numeros sao permitidos!");
            }

        } while (opcao != ClienteMenu.OP_SAIR);
    }

    private void cadastrarCliente() {
        String nome = Console.scanString("Nome: ");
        String telefone = Console.scanString("telefone: ");
        try {
            clienteNegocio.salvar(new Cliente(nome, telefone));
            System.out.println("Cliente " + nome + " cadastrado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }

    public void mostrarClientes() {
        List<Cliente> listaClientes = clienteNegocio.listar();
        this.mostrarClientes(listaClientes);
    }

    private void deletarCliente() {
        mostrarClientes();
        int matricula = Console.scanInt("\nMatrícula do cliente a ser deletado: ");
        try {
            Cliente cli = clienteNegocio.procurarMatricula(matricula);
            this.mostrarCliente(cli);
            if (UIUtil.getConfirmacao("Realmente deseja excluir esse cliente?")) {
                clienteNegocio.deletar(cli);
                System.out.println("Clente deletado com sucesso!");
            } else {
                System.out.println("Operacao cancelada!");
            }
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }
    
   

    private void atualizarCliente() {
        mostrarClientes();
        int matricula = Console.scanInt("\nMatrícula do Cliente a ser alterado: ");
        try {
            Cliente cli = clienteNegocio.procurarMatricula(matricula);
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

            clienteNegocio.atualizar(cli);
            System.out.println("Cliente " + cli.getNome() + " atualizado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }

    private void consultarClientePorNome() {
        String nome = Console.scanString("Nome: ");
        try {
            List<Cliente> listaCli = clienteNegocio.procurarNome(nome);
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

    private void mostrarClientes(List<Cliente> listaClientes) {
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
