package dao.impl_BD;

import dao.LivroDao;
import model.Livro;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDaoBd extends DaoBd<Livro> implements LivroDao {

    @Override
    public void salvar(Livro livro) {
        int matricula = 0;
        try {
            String sql = "INSERT INTO livro (isbn, nome, autor, editora, ano)"
                    + "VALUES (?,?,?,?,?)";
            conectarObtendoMatricula(sql);
            comando.setInt(1, livro.getIsbn());
            comando.setString(2, livro.getNome());
            comando.setString(3, livro.getAutor());            
            comando.setString(4, livro.getEditora());
            //Trabalhando com data: lembrando dataUtil -> dataSql
            java.sql.Date dataSql = new java.sql.Date(livro.getAno().getTime());
            comando.setDate(5, dataSql);
            comando.executeUpdate();
            //Obtém o resultSet para pegar a matricula
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta a matricula para o objeto
                matricula = resultado.getInt(1);
                livro.setMatricula(matricula);
            }
            else{
                System.err.println("Erro de Sistema - Nao gerou amatricula conforme esperado!");
                throw new BDException("Nao gerou a matrícula conforme esperado!");
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar cliente no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(Livro cliente) {
        try {
            String sql = "DELETE FROM cliente WHERE matricula = ?";
            conectar(sql);
            comando.setInt(1, cliente.getMatricula());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar cliente no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void atualizar(Livro cliente) {
        try {
            String sql = "UPDATE cliente SET nome=?, telefone=? "
                    + "WHERE matricula=?";
            
            conectar(sql);
            comando.setString(1, cliente.getNome());
            comando.setString(2, cliente.getTelefone());
            comando.setInt(3, cliente.getMatricula());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar este cliente no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Livro> listar() {
        List<Livro> listaLivros = new ArrayList<>();

        String sql = "SELECT * FROM livro";

        try {
            conectar(sql);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int matricula = resultado.getInt("matricula");
                String nome = resultado.getString("nome");
                String telefone = resultado.getString("telefone");
                
                Livro cli = new Livro(matricula, nome, telefone);

                listaLivros.add(cli);
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os clientes do Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
        return (listaLivros);
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
                int matriculaX = resultado.getInt("matricula");
                String nomeX = resultado.getString("nome");
                String telefoneX = resultado.getString("telefone");

                Cliente c = new Cliente(matriculaX, nomeX, telefoneX);

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
    public Cliente procurarPorMatricula(int matricula) {
        String sql = "SELECT * FROM cliente WHERE matricula = ?";

        try {
            conectar(sql);
            comando.setInt(1, matricula);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                String nome = resultado.getString("nome");
                String telefone = resultado.getString("telefone");
                
                Cliente cli = new Cliente(matricula, nome, telefone);

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
