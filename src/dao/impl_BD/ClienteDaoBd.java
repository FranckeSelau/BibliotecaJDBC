package dao.impl_BD;

import dao.ClienteDao;
import dominio.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoBd extends DaoBd<Cliente> implements ClienteDao {

    //Metodo salvar: trabalhar com data e recebe o id auto-increment 
    //e já relaciona no objeto paciente (recebido por parâmetro)
    //Caso queira retornar, só retornar id.
    @Override
    public void salvar(Cliente cliente) {
        try {
            String sql = "INSERT INTO cliente (nome, telefone)"
                    + "VALUES (?,?)";
            conectar(sql);
            comando.setString(1, cliente.getNome());
            comando.setString(2, cliente.getTelefone());
            comando.executeUpdate();
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
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
    public void atualizar(Cliente cliente) {
        try {
            String sql = "UPDATE cliente SET nome=?, telefone=? "
                    + "WHERE nome=?";

            conectar(sql);
            comando.setString(1, cliente.getNome());
            comando.setString(2, cliente.getTelefone());
            comando.setString(3, cliente.getNome());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar paciente no Banco de Dados!");
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
            System.err.println("Erro de Sistema - Problema ao buscar os pacientes do Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

        return (listaClientes);
    }

    /*
    @Override
    public Cliente procurarPorId(int id) {
        String sql = "SELECT * FROM paciente WHERE id = ?";

        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                String rg = resultado.getString("rg");
                String nome = resultado.getString("nome");
                //Trabalhando com data: lembrando dataSql -> dataUtil
                java.sql.Date dataSql = resultado.getDate("datanascimento");
                java.util.Date dataUtil = new java.util.Date(dataSql.getTime());

                Paciente pac = new Paciente(id, rg, nome, dataUtil);

                return pac;

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o paciente pelo id do Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public Paciente procurarPorRg(String rg) {
        String sql = "SELECT * FROM paciente WHERE rg = ?";

        try {
            conectar(sql);
            comando.setString(1, rg);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                //Trabalhando com data: lembrando dataSql -> dataUtil
                java.sql.Date dataSql = resultado.getDate("datanascimento");
                java.util.Date dataUtil = new java.util.Date(dataSql.getTime());

                Paciente pac = new Paciente(id, rg, nome, dataUtil);

                return pac;

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o paciente pelo rg do Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }
     */
    @Override
    public List<Cliente> procurarPorNomeLista(String nome) {
        List<Cliente> listaCliente = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE nome LIKE ?";

        try {
            conectar(sql);
            comando.setString(1, "%" + nome + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                //int matricula = resultado.getInt("matricula");
                String nomeX = resultado.getString("nome");
                String telefoneX = resultado.getString("telefone");

                Cliente c = new Cliente(nomeX, telefoneX);

                listaCliente.add(c);

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os pacientes pelo nome do Banco de Dados!");
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
                String nome1 = resultado.getString("nome");
                String telefone1 = resultado.getString("telefone");

                Cliente cli = new Cliente(nome1, telefone1);

                return cli;
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o paciente pelo id do Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }
}
