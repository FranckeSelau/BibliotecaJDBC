package dao.impl_BD;

import dao.ClienteDao;
import model.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoBd extends DaoBd<Cliente> implements ClienteDao {

    @Override
    public void salvar(Cliente cliente) {
        try {
            String sql = "INSERT INTO cliente (nome, telefone)"
                    + "VALUES (?,?)";
            conectar(sql);
            comando.setString(1, cliente.getNome());
            comando.setString(2, cliente.getTelefone());
            comando.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar cliente no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(Cliente cliente) {
        try {
            String sql = "DELETE FROM cliente WHERE nome = ?";
            conectar(sql);
            comando.setString(1, cliente.getNome());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar cliente no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void atualizar(Cliente cliente, String nome) {
        try {
            String sql = "UPDATE cliente SET nome=?, telefone=? "
                    + "WHERE nome=?";
            conectar(sql);
            comando.setString(1, cliente.getNome());
            comando.setString(2, cliente.getTelefone());
            comando.setString(3, nome);
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar este cliente no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Cliente> listar() {
        List<Cliente> listaClientes = new ArrayList<>();

        String sql = "SELECT * FROM cliente";

        try {
            conectar(sql);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                String nome = resultado.getString("nome");
                String telefone = resultado.getString("telefone");

                Cliente cli = new Cliente(nome, telefone);

                listaClientes.add(cli);
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os clientes do Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
        return (listaClientes);
    }
    
    @Override
    public List<Cliente> procurarPorNomeLista(String nome) {
        List<Cliente> listaCliente = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE nome LIKE ?";

        try {
            conectar(sql);
            comando.setString(1, "%" + nome + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                String nomeX = resultado.getString("nome");
                String telefoneX = resultado.getString("telefone");

                Cliente c = new Cliente(nomeX, telefoneX);

                listaCliente.add(c);
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os clientes pelo nome do Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
        return (listaCliente);
    }

    @Override
    public Cliente procurarPorNome(String nome) {
        String sql = "SELECT * FROM cliente WHERE nome = ?";

        try {
            conectar(sql);
            comando.setString(1, nome);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                String telefone = resultado.getString("telefone");

                Cliente cli = new Cliente(nome, telefone);                
                return cli;
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o cliente pelo nome do Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }    
}
